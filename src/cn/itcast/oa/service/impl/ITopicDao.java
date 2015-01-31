package cn.itcast.oa.service.impl;

import java.util.List;

import cn.itcast.oa.base.IBaseDao;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;

public interface ITopicDao extends IBaseDao<Topic> {

	List<Topic> findTopicByForum(Forum model);

}
