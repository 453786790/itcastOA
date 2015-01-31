package cn.itcast.oa.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.dao.IDeparementDao;
import cn.itcast.oa.domain.Department;
@Repository
@SuppressWarnings("unchecked")
public class DeparementDaoImpl extends BaseDaoImpl<Department> implements IDeparementDao {


	@Override
	public void delete(Integer id) {
		String hql="FROM Department d where d.parent.id= ?" ;
		Query setParameter = this.getSession().createQuery(hql).setParameter(0, id);
		List list = setParameter.list();
		if(list!=null&&list.size()>0){
			//存在子部门
			throw new RuntimeException("当前部门存在 子部门,不能删除");
		}else{
			//不存在 子部门
			super.delete(id);
		}
	}

	public List<Department> findTopList() {
		String hql="FROM  Department d where d.parent is NULL";
		return this.getSession().createQuery(hql).list();
	}

	public List<Department> findChildrenList(Integer parentId) {
		String hql=" FROM  Department d where d.parent.id=?";
		return this.getSession().createQuery(hql).setParameter(0, parentId).list();
	}

	
}
