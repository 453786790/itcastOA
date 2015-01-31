package cn.itcast.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.dao.IApproveInfoDao;
import cn.itcast.oa.domain.ApproveInfo;
@Repository
public class ApproveInfoDaoImpl extends BaseDaoImpl<ApproveInfo> implements
		IApproveInfoDao {

	@Override
	public List<ApproveInfo> findApproveListByApplicationId(Long applicationId) {
		String hql="FROM ApproveInfo ai where ai.application.id = ?";
		return this.getSession().createQuery(hql).setParameter(0,applicationId).list();
		
	}


}
