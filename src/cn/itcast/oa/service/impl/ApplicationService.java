package cn.itcast.oa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.dao.IApplicationDao;
import cn.itcast.oa.domain.Application;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.service.IApplicationService;
import cn.itcast.oa.utils.HqlHelper;

@Service
@Transactional
public class ApplicationService implements IApplicationService {
	@Resource
	private IApplicationDao applicationDao;

	public void save(Application application) {
		applicationDao.save(application);
	}

	@Override
	public PageBean getPageBean(int currentPage, HqlHelper hqlHelper) {
		return applicationDao.getPageBean(currentPage, hqlHelper);
	}

	@Override
	public Application getById(Long applicationId) {
		return applicationDao.getById(applicationId);
	}


}
