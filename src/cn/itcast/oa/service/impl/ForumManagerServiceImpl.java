package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.dao.IForumManagerDao;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.service.IForumManagerService;
import cn.itcast.oa.utils.HqlHelper;

@Transactional
@Service
public class ForumManagerServiceImpl implements IForumManagerService {
	@Resource
	private IForumManagerDao forumManagerDao;

	@Override
	public List<Forum> findAll() {
		return forumManagerDao.findAll();
	}

	@Override
	public void delete(Integer id) {
		forumManagerDao.delete(id);		
	}

	@Override
	public void save(Forum model) {
		forumManagerDao.save(model);		
	}

	@Override
	public Forum getById(Integer id) {
		return forumManagerDao.getById(id);
	}

	@Override
	public void update(Forum forum) {
		forumManagerDao.update(forum);		
	}

	public void moveUp(Integer id) {
		forumManagerDao.moveUp(id);
	}

	public void moveDown(Integer id) {
		forumManagerDao.moveDown(id);		
	}

	@Override
	public PageBean getBean(int currentPage, HqlHelper hqlHelper) {
		return forumManagerDao.getPageBean(currentPage, hqlHelper);
	}
}
