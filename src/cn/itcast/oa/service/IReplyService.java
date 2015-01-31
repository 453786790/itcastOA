package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.utils.HqlHelper;

public interface IReplyService {

	List<Reply> findReplyByTopic(Topic model);

	void save(Reply model);

	PageBean findPageBean(int currentPage, Topic model);

	PageBean getPageBean(int currentPage, HqlHelper hqlHelper);

}
