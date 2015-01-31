package cn.itcast.oa.domain;

import java.util.Set;

public class User {
	public Integer id;
	public String loginName;
	public String name;
	public String gender;
	public String phone;
	public String email;
	public String description;
	public Department department;
	public Set<Role> roles;
	public String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// 根据权限名称，检查用户权限
	public boolean hasPrivilegeName(String name) {
		if (isAdmin()) {
			return true;
		}
		for (Role r : roles) {
			for (Privilege p : r.getPrivileges()) {
				if (p.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}

	// 判断 当前用户是否是超级管理员
	private boolean isAdmin() {
		// 用户名为admin
		return "admin".equals(loginName);
	}
   //根据访问的url进行校验
	public boolean hasPrivilegeUrl(String url) {
		if (isAdmin()) {
			return true;
		}
		//一级菜单 会有空指针异常
		for (Role r : roles) {
			for (Privilege p : r.getPrivileges()) {
				if (url.equals(p.getUrl())) {
					return true;
				}
			}
		}
		return false;
	}
}
