package cn.itcast.oa.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sun.misc.BASE64Encoder;


import cn.itcast.oa.domain.Application;
import cn.itcast.oa.domain.ApproveInfo;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.TaskView;
import cn.itcast.oa.domain.Template;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.IApplicationService;
import cn.itcast.oa.service.IApproveInfoService;
import cn.itcast.oa.service.IFlowService;
import cn.itcast.oa.service.ITemplateService;
import cn.itcast.oa.utils.HqlHelper;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class FlowAction extends ActionSupport {
	
	@Resource
	private ITemplateService templateService;
	@Resource
	private IFlowService flowService;
	@Resource
	private IApplicationService applicationService;
	@Resource
	private IApproveInfoService approveInfoService;
	///////////////////////申请人的操作///////////////////////////////
	//起草申请
	public String templateList() {
		List<Template> templateList = templateService.findAll();
		ActionContext.getContext().put("templateList", templateList);
		return "templateList";
	}
	private Integer templateId;//模版id
	//跳转到提交申请页面
	public String submitUI(){
		return "submitUI";
	}
	private File uploadFile;
	//提交申请页面
	public String submit(){
		//上传文件
		String filePath = uploadFile(uploadFile);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//保存申请实体
		Application application=new Application();
		application.setApplicate(getCurrentUser());
		application.setApplyTime(new Date());
		application.setFilePath(filePath);
		application.setStatus(Application.STATUS_RUNNIG);
		application.setTemplate(templateService.getById(templateId));
		String  title=application.getTemplate().getName()+"_"+application.getApplicate().getName()+"_"+sdf.format(new Date());
		application.setTitle(title);//申请标题结构   模版名称_申请人姓名_日期
		
		flowService.submit(application);
		return  "toMyApplicationList";
	}
	private int currentPage=1;
	private String status;
	//我的申请查询
	public String myApplicationList(){
		//准备模版列表s
		 List<Template> templateList = templateService.findAll();
		 ActionContext.getContext().put("templateList", templateList);

		 HqlHelper hqlHelper=new HqlHelper(Application.class);		
	     hqlHelper.addWhereCondition("o.applicate = ?",getCurrentUser());
	     if(templateId!=null){
	    	 //根据模板查询
	    	 hqlHelper.addWhereCondition("o.template.id=?",templateId);
	     }
	     if(status!=null&&status.length()>0){
	    	 //根据申请状态
	    	 hqlHelper.addWhereCondition("o.status=?",status);
	     }
	     hqlHelper.addOrderBy("o.applyTime", false);
	     
	     PageBean pageBean=applicationService.getPageBean(currentPage,hqlHelper);
	     ActionContext.getContext().getValueStack().push(pageBean);
			return "myApplicationList";
	}
	
	///////////////////////////申批人操作//////////////////////////////////
	//待我申批 页面
	public String myTaskList(){
		List<TaskView> list=flowService.findMyTaskList(getCurrentUser());
		ActionContext.getContext().put("taskviewList", list);
		return "myTaskList";
	}
	private String taskId;
	
	//跳转到申批页面
	public String approveUI(){
		return "approveUI";
	}
	private String comment;
	private Boolean approval;
	//审批处理
	public String approve(){
		Application application = applicationService.getById(applicationId);
		//保存审批信息
		ApproveInfo ai=new ApproveInfo();
		ai.setApplication(application);//申请
		ai.setApproval(approval);  //通过否
		ai.setApprover(getCurrentUser());//审批人
		ai.setApproveTime(new Date());//时间
		ai.setComment(comment);//意见
		//审批处理
		//修改申请状态 
		flowService.approve(ai,taskId);
		return "toMyTaskList";
	}
	/////////////////公共操作/////////////////////////
	//查询流转记录
	public String historyApproveList(){
		List<ApproveInfo> approveInfoList=approveInfoService.findApproveListByApplicationId(applicationId);
		ActionContext.getContext().put("approveInfoList", approveInfoList);
		return "historyApprovedList";
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	private Long applicationId;
	private InputStream inputStream;
	private String filename;//提供下载文件名字
	public String download(){
		Application application=applicationService.getById(applicationId);
		String tname = application.getTemplate().getName();
		String name = application.getApplicate().getName();
		filename=tname+"_"+name+".doc";
		//解决下载乱码
		String agent = ServletActionContext.getRequest().getHeader("user-agent");
		try {
			filename = this.encodeDownloadFilename(filename, agent);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			inputStream=new FileInputStream(new File(application.getFilePath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "download";
	}
	
	
	//上传文件 返回文件的实际保存路径
	protected String uploadFile(File uploadFile) {
		//获取真实目录路径
		String realPath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/uploadFiles");
		
		SimpleDateFormat sdf=new SimpleDateFormat("/yyyy/MM/dd/");
		String dateStr = sdf.format(new Date());
		
		//如果 日期的目录不存在则创建
		File dateFile=new File(realPath + dateStr);
		if(!dateFile.exists()){
			dateFile.mkdirs();
		}
		
		//文件名可以使用UUID
		String pathname=realPath + dateStr + UUID.randomUUID().toString()+".doc";
		File dest=new File(pathname);
		uploadFile.renameTo(dest);//剪切功能未实现
		return pathname;
	}
	
	public User getCurrentUser() {
		return (User) ServletActionContext.getRequest().getSession().getAttribute(
				"loginUser");
	}
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}


	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * 下载文件时，针对不同浏览器，进行附件名的编码
	 * @param filename 下载文件名
	 * @param agent 客户端浏览器(通过request.getHeader("user-agent")获得)
	 * @return 编码后的下载附件名
	 * @throws IOException
	 */
	public String encodeDownloadFilename(String filename, String agent) throws IOException{
		if(agent.contains("Firefox")){ // 火狐浏览器
			filename = "=?UTF-8?B?"+new BASE64Encoder().encode(filename.getBytes("utf-8"))+"?=";
		}else{ // IE及其他浏览器
			filename = URLEncoder.encode(filename,"utf-8");
		}
		return filename;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getApproval() {
		return approval;
	}

	public void setApproval(Boolean approval) {
		this.approval = approval;
	}
}
