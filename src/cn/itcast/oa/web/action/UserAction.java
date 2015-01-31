package cn.itcast.oa.web.action;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.utils.DepartmentUtils;
import cn.itcast.oa.utils.MD5Utils;

@SuppressWarnings("serial")
@Controller
@Scope(value="prototype")
public class UserAction extends BaseAction<User> {
	public String list() {
		List<User> list=userService.findAll();
		ActionContext.getContext().put("userList", list);
		return "list";
	}
	public String delete(){
		userService.delete(model.getId());
		return "toList";
	}
	public String addUI(){
		//准备数据，岗位列表 
		List<Department> topList = deparementService.findTopList();
		List<Department> treeList = DepartmentUtils.getTree(topList, null);
		
		List<Role> roleList = roleService.findAll();
		
		ActionContext.getContext().put("departmentList", treeList);
		ActionContext.getContext().put("roleList", roleList);
		
		return "addUI";
	}
	private Integer departmentId;
	private Integer[] roleIds;
	//用户添加
	public String add(){
		if(departmentId!=null){
			Department deparement=deparementService.getById(departmentId);
			model.setDepartment(deparement);//用户关联部门 
		}
		if(roleIds!=null&&roleIds.length>0){
			List<Role> roleList=roleService.getByIds(roleIds);//用户关联岗位
			model.setRoles(new HashSet<Role>(roleList));
		}
		userService.save(model);
		return "toList";
		
	}
	public Integer[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	   //跳转到用户修改ui
    public String editUI(){
    	User user=userService.getById(model.getId());
    	ActionContext.getContext().getValueStack().push(user);
    	List<Department> topList = deparementService.findTopList();
    	List<Department> treeList = DepartmentUtils.getTree(topList, null);
    	
    	ActionContext.getContext().put("departmentList", treeList);
    	
    	List<Role> roleList = roleService.findAll();
    	ActionContext.getContext().put("roleList", roleList);
    	
    	
    	//选 中状态 
    	if(user.getDepartment()!=null)
    	   departmentId=user.getDepartment().getId();
    	
    	Set<Role> roles = user.getRoles();
    	if(roles!=null&&roles.size()>0){
    		roleIds=new Integer[roles.size()];
    		int index=0;
    		for(Role r:roles){
    			roleIds[index++]=r.getId();
    		}
    	}
    	return "editUI";
    }
    //修改用户信息并保存
    public String edit(){
    	//先查询 再修改
    	User user = userService.getById(model.getId());
    	user.setName(model.getName());
    	user.setDescription(model.getDescription());
    	user.setEmail(model.getEmail());
    	user.setGender(model.getGender());
    	user.setLoginName(model.getLoginName());
    	user.setPhone(model.getPhone());
    	
    	Department department = deparementService.getById(departmentId);
    	user.setDepartment(department);
    	
    	if(roleIds!=null&&roleIds.length>0){
    		List<Role> roleList = roleService.getByIds(roleIds);
    		user.setRoles(new HashSet<Role>(roleList));
    	}else{
    		user.setRoles(null);
    	}
    	
    	userService.update(user);
    	
    	return "toList";
    }
    public String initPassword(){
    	//先查询再修改
    	User user = userService.getById(model.getId());
    	user.setPassword(MD5Utils.md5("1234"));
    	userService.update(user);
    	return "toList";
    }
    public String findLoginNameByAjax(){
    	boolean b=userService.findLoginNameExsit(model.getLoginName());
    	HttpServletResponse response = ServletActionContext.getResponse();
    	response.setContentType("text/html;chartset=utf-8");
    	if(b){
    		try {
				response.getWriter().print("1");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}else {
    		try {
				response.getWriter().print("0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	return NONE;
    }
    public String login(){
    	User user=null;
    	if(model!=null){
    		 user=userService.login(model);
    	}
    	if(user!=null){
    		//登录成功
    		//将用户放入session中,然后跳转到主页面
    		ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
    		return "index";
    	}else{
    		//登录失败,显示错误消息
    		this.addActionError("用户名或者密码错误");
    		return "loginUI";
    	}
    }
    public String logout(){
    	//清空session
    	ServletActionContext.getRequest().getSession().removeAttribute("loginUser");
    	return "loginUI";
    }
}
