package cn.itcast.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
public class Application {
	
	public static final String STATUS_RUNNIG="审批中";
	public static final String STATUS_APPROVED="已通过";
	public static final String STATUS_UNAPPROVED="未通过";
	
	
	public Long id;					//jbpm实体类要求的类型
	public String title;             //标题
	public User applicate;           //人
	public Date applyTime;           //时间
	public String status;            //状态
	public Template template;        //模版
	public String filePath;          //文件存储路径 
	public Set<ApproveInfo> approveInfos=new HashSet<ApproveInfo>();//申批集合


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getApplicate() {
		return applicate;
	}

	public void setApplicate(User applicate) {
		this.applicate = applicate;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Set<ApproveInfo> getApproveInfos() {
		return approveInfos;
	}

	public void setApproveInfos(Set<ApproveInfo> approveInfos) {
		this.approveInfos = approveInfos;
	}
}
