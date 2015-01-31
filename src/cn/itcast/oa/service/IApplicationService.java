package cn.itcast.oa.service;

import cn.itcast.oa.domain.Application;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.utils.HqlHelper;

public interface IApplicationService {

	void save(Application application);

	PageBean getPageBean(int currentPage, HqlHelper hqlHelper);


	Application getById(Long applicationId);

}
