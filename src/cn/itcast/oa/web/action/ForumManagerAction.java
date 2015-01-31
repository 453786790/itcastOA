package cn.itcast.oa.web.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.utils.HqlHelper;

@SuppressWarnings("serial")
@Controller
@Scope(value = "prototype")
public class ForumManagerAction extends BaseAction<Forum> {
	public String list() {
//		List<Forum> list=forumManagerService.findAll();
//		ActionContext.getContext().put("forumList", list);
		
		HqlHelper hqlHelper = new HqlHelper(Forum.class);
		hqlHelper.addOrderBy("o.position", true);
		PageBean pageBean=forumManagerService.getBean(currentPage,hqlHelper);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "list";
	}

	public String addUI() {
		return "addUI";
	}

	public String add() {
		forumManagerService.save(model);
		return "toList";
	}

	public String editUI() {
		Forum forum=forumManagerService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(forum);
		return "editUI";
	}
	public String edit(){
		Forum forum = forumManagerService.getById(model.getId());
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());
		forumManagerService.update(forum);
		return "toList";
	}
	public String delete(){
		forumManagerService.delete(model.getId());
		return "toList";
	}
	public String moveUp(){
		forumManagerService.moveUp(model.getId());
		return "toList";
	}
	public String moveDown(){
		forumManagerService.moveDown(model.getId());
		return "toList"; 
	}
}
