package cn.itcast.oa.web.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

@SuppressWarnings("serial")
@Controller
@Scope(value = "prototype")
public class ReplyAction extends BaseAction<Reply> {
	private Integer topicId;

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	// 发表 回复
	public String add() {
		Topic topic = topicService.getById(topicId);

		model.setTopic(topic);// 回复关联主题
		model.setIpAddress(getIpAddress());
		model.setAuthor(getCurrentUser());

		replyService.save(model);
		return "topicShow";
	}
//跳转到回复页面
	public String addUI() {
		Topic topic = topicService.getById(topicId);
		ActionContext.getContext().getValueStack().push(topic);
		return "addUI";
	}

}
