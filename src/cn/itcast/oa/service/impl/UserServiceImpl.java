package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.dao.IUserDao;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;

	public List<User> findAll() {
		return userDao.findAll();
	}

	public void delete(Integer id) {
		userDao.delete(id);
	}

	public void save(User model) {
		userDao.save(model);		
	}

	public User getById(Integer id) {
		return userDao.getById(id);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public boolean findLoginNameExsit(String loginName) {
		return userDao.findLoginNameExsit(loginName);
	}

	@Override
	public User login(User model) {
		return userDao.login(model);
	}
}
