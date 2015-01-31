package cn.itcast.oa.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.dao.IForumDao;
import cn.itcast.oa.domain.Forum;
@Repository
@SuppressWarnings("unchecked")
public class ForumDaoImpl extends BaseDaoImpl<Forum> implements IForumDao {

	
	public List<Forum> findAll() {
		
		String hql="FROM Forum f ORDER BY f.position ASC";
		return this.getSession().createQuery(hql).list();
	}

}
