package cn.itcast.oa.web.action;



import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.utils.HqlHelper;

@SuppressWarnings("serial")
@Controller
public class topicAction extends BaseAction<Topic> {
	// 版块id
	private Integer forumId;

	public String addUI() {
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().getValueStack().push(forum);
		return "addUI";
	}

	public Integer getForumId() {
		return forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}

	// 发表新主题
	public String add() {
		Forum forum = forumService.getById(forumId);
		
		model.setForum(forum);// 主题 关联版块
		model.setAuthor(getCurrentUser());// 获取当前登录用户
		model.setIpAddress(getIpAddress());
		
		topicService.save(model);
		return "forumShow";//主题 列表 
	}
	//回复列表
	public String show(){
		Topic topic=topicService.getById(model.getId());
		//分页查询
	//	PageBean pageBean=replyService.findPageBean(currentPage,model);
		
		//收集数据
		HqlHelper hqlHelper = new HqlHelper(Reply.class);
		hqlHelper.addWhereCondition("o.topic = ?",model);
		hqlHelper.addOrderBy("o.postTime",true);
		
		PageBean pageBean=replyService.getPageBean(currentPage,hqlHelper);
		ActionContext.getContext().getValueStack().push(topic);
		ActionContext.getContext().getValueStack().push(pageBean);
		
//		List<Reply> replyList=replyService.findReplyByTopic(model);
//		ActionContext.getContext().put("replyList", replyList);
		return "topicShow";
	}


}
