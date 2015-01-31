package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.ApproveInfo;

public interface IApproveInfoService {

	List<ApproveInfo> findApproveListByApplicationId(Long applicationId);

	void save(ApproveInfo ai);

}
