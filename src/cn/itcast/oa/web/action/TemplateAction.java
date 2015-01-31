package cn.itcast.oa.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sun.misc.BASE64Encoder;
import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Template;
import cn.itcast.oa.service.IProcessDefinitionService;

import com.opensymphony.xwork2.ActionContext;

//模版管理
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TemplateAction extends BaseAction<Template> {
	private File uploadFile;//接收上传文件 
	private String fileName;
	@Resource
	private IProcessDefinitionService processDefinitionService;
	public String list() {
		List<Template> list = templateService.findAll();
		ActionContext.getContext().put("list", list);
		return "list";
	}
	public String delete(){
		templateService.delete(model.getId());
		return "toList";
	}
	public String addUI(){
		//准备流程定义的key数据
		List<ProcessDefinition> findLastList = processDefinitionService.findLastList();
		ActionContext.getContext().put("pdList", findLastList);
		return "addUI";
	}
	public String add(){
		//上传文件 移动到指定文件下/web-inf/uploadFiles
		String pathname = uploadFile(uploadFile);
		
		model.setFilePath(pathname);
		templateService.save(model);
		
		return "toList";
	}
	public String editUI(){
		Template template = templateService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(template);
		//准备流程定义的key数据
		List<ProcessDefinition> findLastList = processDefinitionService.findLastList();
		ActionContext.getContext().put("pdList", findLastList);
		return "editUI";
	}
	public String edit(){
		Template template = templateService.getById(model.getId());
		template.setName(model.getName());
		template.setProcessKey(model.getProcessKey());
		if(uploadFile!=null){
			//用户上传了文件 
			// 1删除旧文件 
			String filePath = template.getFilePath();
			File file=new File(filePath);
			if(file.exists()){
				file.delete();
			}
			// 2 保存新文件  
			String uploadFile2 = uploadFile(uploadFile);
			
			// 3设置文件路径
			template.setFilePath(uploadFile2);
		}
		templateService.update(template);
		return "toList";
	}
	private InputStream is;
	public String download(){
		Template template=templateService.getById(model.getId());
		String filePath = template.getFilePath();
		
		try {
			is=new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		fileName=template.getProcessKey()+".doc";
		String agent = ServletActionContext.getRequest().getHeader("user-agent");
		try {
			fileName=this.encodeDownloadFilename(fileName, agent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "download";
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
	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	public InputStream getIs() {
		return is;
	}
	public void setIs(InputStream is) {
		this.is = is;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
