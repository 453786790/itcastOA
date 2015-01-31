package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Privilege;

public interface IPrivilegeService {

	List<Privilege> findAll();

	List<Privilege> getByIds(Integer[] privilegeIds);

	List<Privilege> findTopList();

	List<String> findAllUrl();

}
