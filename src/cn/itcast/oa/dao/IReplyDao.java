package cn.itcast.oa.dao;

import java.util.List;

import cn.itcast.oa.base.IBaseDao;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

public interface IReplyDao extends IBaseDao<Reply> {

	List<Reply> findReplyByTopic(Topic model);

	PageBean findPageBean(int currentPage, Topic model);

}
