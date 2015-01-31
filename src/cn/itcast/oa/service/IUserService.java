package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.User;

public interface IUserService {

	List<User> findAll();

	void delete(Integer id);

	void save(User model);

	User getById(Integer id);

	void update(User user);

	boolean findLoginNameExsit(String loginName);

	User login(User model);

}
