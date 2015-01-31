package cn.itcast.oa.web.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.domain.Role;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	public String list() {
		List<Role> list = roleService.findAll();
		ActionContext.getContext().put("list", list);
		return "roleList";
	}

	public String delete() {
		roleService.delete(model.getId());
		return "toList";
	}

	public String addUI() {
		return "addUI";
	}

	public String add() {
		roleService.save(model);
		return "toList";
	}

	public String editUI() {
		// 准备回显数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		return "editUI";
	}

	public String edit() {
		Role role = roleService.getById(model.getId());
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		roleService.update(role);
		return "toList";
	}

	// 跳转到设置权限页面
	public String setPrivilegeUI() {
		// 准备权限数据
//		List<Privilege> privilegeList = privilegeService.findAll();
//		查询顶级
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		ActionContext.getContext().put("topPrivilegeList", topPrivilegeList);

		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);

		Set<Privilege> privileges = role.getPrivileges();
		int index = 0;
		if (privileges != null && privileges.size() > 0) {
			privilegeIds = new Integer[privileges.size()];
			for (Privilege p : privileges) {
				privilegeIds[index++] = p.getId();
			}
		}

		return "setPrivilegeUI";
	}

	private Integer[] privilegeIds;

	public String setPrivilege() {
		Role role = roleService.getById(model.getId());
		if (privilegeIds != null && privilegeIds.length > 0) {
			List<Privilege> privilegeList = privilegeService
					.getByIds(privilegeIds);
			// 使用角色来关联角色
			role.setPrivileges(new HashSet<Privilege>(privilegeList));
		}
		roleService.update(role);
		return "toList";
	}

	public Integer[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Integer[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
}
