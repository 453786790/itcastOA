package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.utils.HqlHelper;

public interface IForumService {

	List<Forum> findAll();

	Forum getById(Integer id);

	PageBean getPageBean(int currentPage, HqlHelper hqlHelper);


}
