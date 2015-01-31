package cn.itcast.oa.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.impl.ITopicDao;
@Repository
@SuppressWarnings("unchecked")
public class TopicDaoImpl extends BaseDaoImpl<Topic> implements ITopicDao {
//根据版块查询主题 列表 ,加入排序 置顶贴在最上边剩下的按时间 降序
	// select id ,title ,postTime,type from itcast_topic order by (case type when 2 then 2 else 0 end) desc,postTime desc;
	@Override
	public List<Topic> findTopicByForum(Forum model) {
//		String hql="FROM Topic t WHERE t.forum = ? ";
		String hql="FROM Topic t WHERE t.forum = ? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC ,t.postTime DESC";
		return this.getSession().createQuery(hql).setParameter(0, model).list();
	}

}
