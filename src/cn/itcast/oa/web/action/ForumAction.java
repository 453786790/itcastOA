package cn.itcast.oa.web.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.utils.HqlHelper;

@SuppressWarnings("serial")
@Controller
@Scope(value = "prototype")
public class ForumAction extends BaseAction<Forum> {
	private int viewType;//0全部 主题  1 全部精华
	/**
                 0">默认排序（按最后更新时间排序，但所有置顶帖都在前面）</option>
				"1">按最后更新时间排序</option>
				"2">按主题发表时间排序</option>
				"3">按回复数量排序</option>
	 * @return
	 */
	private int orderBy;
	
	private boolean asc;
	//升序  降序
	public String list() {
		List<Forum> forumList=forumService.findAll();
		ActionContext.getContext().put("forumList",forumList);
		return "list";
	}
	//显示单个列表 
	public String show(){
		Forum forum=forumService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(forum);
		
		HqlHelper hqlHelper = new HqlHelper(Topic.class);
		hqlHelper.addWhereCondition("o.forum = ?",model);
		
		if(viewType==1){
			//查询精华贴
			hqlHelper.addWhereCondition(" o.type = ?", 1);
		}
        
		if(orderBy==0){
			//默认
			hqlHelper.addOrderBy("( CASE o.type WHEN 2 THEN 2 ELSE 0 END )", false);
			hqlHelper.addOrderBy(" o.postTime", false);	
		}else if(orderBy==1){
			//最后 更新时间 
			hqlHelper.addOrderBy(" o.lastUpdateTime",asc);
		}else if(orderBy==2){
			//最后 更新时间 
			hqlHelper.addOrderBy(" o.postTime",asc);
		}else if(orderBy==3){
			//最后 更新时间 
			hqlHelper.addOrderBy(" o.replyCount",asc);
		}
		PageBean pageBean=forumService.getPageBean(currentPage,hqlHelper);
		ActionContext.getContext().getValueStack().push(pageBean);
//		List<Topic> topicList=topicService.findTopicByForum(model);
//		ActionContext.getContext().put("topicList", topicList);
		return "forumShow";
	}
	public int getViewType() {
		return viewType;
	}
	public void setViewType(int viewType) {
		this.viewType = viewType;
	}
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	public boolean getAsc() {
		return asc;
	}
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
}
