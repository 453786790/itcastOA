package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.dao.IApproveInfoDao;
import cn.itcast.oa.domain.ApproveInfo;
import cn.itcast.oa.service.IApproveInfoService;
@Service
@Transactional
public class ApproveInfoServiceImpl implements IApproveInfoService {
	@Resource
		private IApproveInfoDao approveInfoDao;
	@Override
	public List<ApproveInfo> findApproveListByApplicationId(Long applicationId) {
		return approveInfoDao.findApproveListByApplicationId(applicationId);
	}
	@Override
	public void save(ApproveInfo ai) {
		approveInfoDao.save(ai);		
	}

}
