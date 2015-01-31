package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.utils.HqlHelper;


public interface IForumManagerService {

	List<Forum> findAll();

	void delete(Integer id);

	void save(Forum model);

	Forum getById(Integer id);

	void update(Forum forum);

	void moveUp(Integer id);

	void moveDown(Integer id);

	PageBean getBean(int currentPage, HqlHelper hqlHelper);

}
