package cn.itcast.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.dao.IForumManagerDao;
import cn.itcast.oa.domain.Forum;
@SuppressWarnings("unchecked")
@Repository
public class ForumManagerDaoImpl extends BaseDaoImpl<Forum> implements
		IForumManagerDao {
	//重写 保存方法 ，position==id
	public void save(Forum entity) {
		super.save(entity);
		entity.setPosition(entity.getId());
	}


	public List<Forum> findAll() {
		String hql="FROM Forum f ORDER BY f.position";
		return this.getSession().createQuery(hql).list();
	}


	public void moveUp(Integer id) {
		Forum f1 = super.getById(id);
		Integer p1 = f1.getPosition();
		String hql="FROM Forum f WHERE  f.position < ? ORDER BY f.position DESC";
		Forum f2 =(Forum)this.getSession().createQuery(hql).setParameter(0,p1).setFirstResult(0).setMaxResults(1).uniqueResult();
		
		f1.setPosition(f2.getPosition());
		f2.setPosition(p1);
	}
	public void moveDown(Integer id) {
		Forum f1 = super.getById(id);
		Integer p1 = f1.getPosition();
		String hql="FROM Forum f WHERE  f.position > ? ORDER BY f.position ASC";
		Forum f2 =(Forum)this.getSession().createQuery(hql).setParameter(0,p1).setFirstResult(0).setMaxResults(1).uniqueResult();
		
		f1.setPosition(f2.getPosition());
		f2.setPosition(p1);
	}
	
}
