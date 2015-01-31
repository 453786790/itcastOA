package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Department;

public interface IDeparementService {

	List<Department> findAll();

	void delete(Integer integer);

	Department getById(Integer parentId);

	void save(Department model);

	void update(Department department);

	List<Department> findTopList();

	List<Department> findChildrenList(Integer parentId);

}
