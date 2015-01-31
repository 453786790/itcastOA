package cn.itcast.oa.dao;

import java.util.List;

import cn.itcast.oa.base.IBaseDao;
import cn.itcast.oa.domain.Department;

public interface IDeparementDao extends IBaseDao<Department>{

	List<Department> findTopList();

	List<Department> findChildrenList(Integer parentId);

}
