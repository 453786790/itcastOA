package cn.itcast.oa.domain;

import java.util.Set;

public class Department {
	public Integer id;
	public Department parent;
	public Set<Department> chidren;
	public String name;
	public String description;
	public Set<User> users;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Department getParent() {
		return parent;
	}
	public void setParent(Department parent) {
		this.parent = parent;
	}
	public Set<Department> getChidren() {
		return chidren;
	}
	public void setChidren(Set<Department> chidren) {
		this.chidren = chidren;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
