package cn.itcast.oa.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.dao.IReplyDao;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
@Repository
@SuppressWarnings("unchecked")
public class ReplyDaoImpl extends BaseDaoImpl<Reply> implements IReplyDao {

	public List<Reply> findReplyByTopic(Topic model) {
		String hql="FROM Reply r WHERE r.topic = ? ORDER BY r.postTime ASC";
		return this.getSession().createQuery(hql).setParameter(0, model).list();
	}

	public PageBean findPageBean(int currentPage, Topic model) {
		String hql="FROM Reply r WHERE r.topic = ? ORDER BY r.postTime ASC";
		Query query = this.getSession().createQuery(hql).setParameter(0, model);
		int begin=(currentPage-1)*10;
		
		List recordList = query.setFirstResult(begin).setMaxResults(10).list();
		//查询总记录数
		hql="SELECT COUNT(*) FROM Reply r WHERE r.topic=?";
		query = this.getSession().createQuery(hql).setParameter(0, model);
		Long recordCount =(Long) query.uniqueResult();
		
		return new PageBean(currentPage,10,recordCount.intValue(),recordList);
	}


}
