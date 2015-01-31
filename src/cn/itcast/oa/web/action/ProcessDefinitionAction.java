package cn.itcast.oa.web.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sun.misc.BASE64Encoder;


import cn.itcast.oa.service.IProcessDefinitionService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//流程定义管理
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ProcessDefinitionAction extends ActionSupport {
	@Resource
	private IProcessDefinitionService processDefinitionService;
	private String key;
	
	// 查询列表
	public String list() {
		List<ProcessDefinition> list=processDefinitionService.findLastList();
		ActionContext.getContext().put("list", list);
		return "list";
	}

	// 根据流程定义的key删除所有的流程
	public String delete() {
		try {
			key=new String(key.getBytes("GBK"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		//解决get方式乱码  带有附件的
//		try {
//			key=new String(key.getBytes("ISO8859-1"),"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		processDefinitionService.deleteByKey(key);
		return "toList";
	}
	
	//跳转到流程部署
	public String addUI(){
		return "addUI";
	}
	private File uploadFile;//用于文件上传
	public String add(){
//		System.out.println(uploadFile);
		processDefinitionService.deploy(uploadFile);
		return "toList";
	}
	private String id;
	private InputStream is;
	//查看 流程图
	public String showProcessImage(){
		//解决不同浏览器乱码
//		try {
//			key=new String(key.getBytes("GBK"),"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		is = processDefinitionService.getImageStream(id);
		return "showImage";
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}
}
