package cn.itcast.oa.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.utils.DepartmentUtils;

@SuppressWarnings("serial")
@Controller
@Scope(value = "prototype")
public class DepartmentAction extends BaseAction<Department> {
	public String list() {
		//List<Department> list = deparementService.findAll();
		List<Department> list;
		if(parentId==null){
			//查询顶级部门
			list=deparementService.findTopList();
		}else{
			//查询下级部门
			list=deparementService.findChildrenList(parentId);
			//添加返回按钮，查询部门 ，返回上一级使用
			Department parent = deparementService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
			
		}
		ActionContext.getContext().put("list", list);
		return "deparementList";
	}

	public String delete() {
		try {
			deparementService.delete(model.getId());
		} catch (Exception e) {
			ServletActionContext.getRequest().setAttribute("msg",
					e.getMessage());
			// this.addActionError(e.getMessage());
			return "toList2";
		}
		return "toList";
	}

	private Integer parentId;

	public String addUI() {
		// 准备部门
		//List<Department> list = deparementService.findAll();
		//查询顶级列表
		List<Department> topList = deparementService.findTopList();
		List<Department> treeList=DepartmentUtils.getTree(topList,null);
		ActionContext.getContext().put("departmentList", treeList);
		return "addUI";
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public String add(){
		Department parent=deparementService.getById(parentId);
		//关联上级部门
		model.setParent(parent);
		deparementService.save(model);
		return "toList";
	}
	public String editUI(){
		Department department = deparementService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
//		List<Department> list = deparementService.findAll();
		
		//修改改造
		List<Department> topList = deparementService.findTopList();
		List<Department> treeList=DepartmentUtils.getTree(topList,model.getId());
		ActionContext.getContext().put("departmentList", treeList);
		//修改的部门可能是顶级部门
		if(department.getParent()!=null){
			parentId=department.getParent().getId();
		}
		return "editUI";
	}
	public String edit(){
		Department department = deparementService.getById(model.getId());
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		if(parentId!=null){
			Department parent = deparementService.getById(parentId);
		    department.setParent(parent);
		}else{
			  department.setParent(null);//不选择部门的时候 
		}
		deparementService.update(department);
		return "toList";
	}
}
