package cn.itcast.oa.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.dao.IReplyDao;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.IReplyService;
import cn.itcast.oa.utils.HqlHelper;

@Service
@Transactional
public class ReplyServiceImpl implements IReplyService {
	@Resource
	private IReplyDao replyDao;

	public List<Reply> findReplyByTopic(Topic model) {
		return replyDao.findReplyByTopic(model);
	}

	public void save(Reply model) {
		model.setDeleted(0);
		model.setPostTime(new Date());
		replyDao.save(model);

		// 更新版块
		Forum forum = model.getTopic().getForum();
		forum.setAritcleCount(forum.getAritcleCount() + 1);

		Topic topic = model.getTopic();
		topic.setReplyCount(topic.getReplyCount() + 1);
		topic.setLastReply(model);// 设置主题 最后回复
		topic.setLastUpdateTime(model.getPostTime());
	}

	// 分页查询
	public PageBean findPageBean(int currentPage, Topic model) {
		return replyDao.findPageBean(currentPage, model);
	}

	@Override
	public PageBean getPageBean(int currentPage, HqlHelper hqlHelper) {
		return replyDao.getPageBean(currentPage, hqlHelper);
	}

}
