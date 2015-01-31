package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Role;

public interface IRoleService {

	List<Role> findAll();

	void delete(Integer id);

	void save(Role model);

	Role getById(Integer id);

	void update(Role role);

	List<Role> getByIds(Integer[] roleIds);
}
