package cn.itcast.oa.base;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.IBookService;
import cn.itcast.oa.service.IDeparementService;
import cn.itcast.oa.service.IForumManagerService;
import cn.itcast.oa.service.IForumService;
import cn.itcast.oa.service.IPrivilegeService;
import cn.itcast.oa.service.IReplyService;
import cn.itcast.oa.service.IRoleService;
import cn.itcast.oa.service.ITemplateService;
import cn.itcast.oa.service.ITopicService;
import cn.itcast.oa.service.IUserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//通用  action
@SuppressWarnings( { "unchecked", "serial" })
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	protected T model;
	@Resource
	protected IBookService bookService;
	@Resource
	protected IRoleService roleService;
	@Resource
	protected IDeparementService deparementService;
	@Resource
	protected IUserService userService;
	@Resource
	protected IPrivilegeService privilegeService;
	@Resource
	protected IForumManagerService forumManagerService;
	@Resource
	protected IForumService forumService;
	@Resource
	protected ITopicService topicService;
	@Resource
	protected IReplyService replyService;
	@Resource
	protected ITemplateService templateService;
	public BaseAction() {
		ParameterizedType genericSuperclass = (ParameterizedType) this
				.getClass().getGenericSuperclass();
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		Class<T> clazz = (Class<T>) actualTypeArguments[0];
		try {
			model = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public User getCurrentUser() {
		return (User) ServletActionContext.getRequest().getSession().getAttribute(
				"loginUser");
	}
	public String getIpAddress(){
		return ServletActionContext.getRequest().getRemoteAddr();
	}
	//分页查询
	protected int currentPage=1;//默认值为1
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
}
