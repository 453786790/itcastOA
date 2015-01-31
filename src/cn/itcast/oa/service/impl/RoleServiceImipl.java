package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.dao.IRoleDao;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.service.IRoleService;
@Service
@Transactional
public class RoleServiceImipl implements IRoleService {
	@Resource
    private IRoleDao roleDao;
	public List<Role> findAll() {
		return roleDao.findAll();
	}
	public void delete(Integer id) {
		roleDao.delete(id);
	}
	public void save(Role model) {
		roleDao.save(model);
	}
	public Role getById(Integer id) {
		return roleDao.getById(id);
	}
	public void update(Role role) {
		roleDao.update(role);
	}
	public List<Role> getByIds(Integer[] roleIds) {
		return roleDao.getByIds(roleIds);
	}

}
