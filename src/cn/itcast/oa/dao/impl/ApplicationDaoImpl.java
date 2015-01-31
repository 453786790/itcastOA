package cn.itcast.oa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.dao.IApplicationDao;
import cn.itcast.oa.domain.Application;
@Repository
public class ApplicationDaoImpl  extends BaseDaoImpl<Application> implements IApplicationDao{

	@Override
	public Application getById(Long applicationId) {
		return (Application)this.getSession().get(Application.class,applicationId);
	}

}
