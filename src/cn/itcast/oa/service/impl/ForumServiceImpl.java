package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.dao.IForumDao;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.service.IForumService;
import cn.itcast.oa.utils.HqlHelper;
@Service
@Transactional
public class ForumServiceImpl implements
		IForumService   {
	@Resource
    private IForumDao forumDao;
	@Override
	public List<Forum> findAll() {
		return forumDao.findAll();
	}
	public Forum getById(Integer id) {
		return forumDao.getById(id);
	}
	public PageBean getPageBean(int currentPage, HqlHelper hqlHelper) {
		return forumDao.getPageBean(currentPage, hqlHelper);
	}

}
