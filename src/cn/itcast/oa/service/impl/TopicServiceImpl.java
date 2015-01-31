package cn.itcast.oa.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.ITopicService;
@Service
@Transactional
public class TopicServiceImpl implements ITopicService {
	@Resource
    private ITopicDao topicDao;
	@Override
	public List<Topic> findTopicByForum(Forum model) {
		return topicDao.findTopicByForum(model);
	}
	@Override
	public void save(Topic model) {

		model.setDeleted(0);
		model.setLastUpdateTime(new Date());//最后 更新时间 
		model.setPostTime(model.getLastUpdateTime());//发表主题 时间 
		model.setReplyCount(0);//当前回复为0
		model.setType(0);//默认普通贴
		
		topicDao.save(model);//持久对象，以下代码 会自动 更新到数据库
		
		//更新版块
		Forum forum = model.getForum();//获取当前主题 所在版块
		forum.setTopicCount(forum.getTopicCount()+1);//主题 数量加1
		forum.setAritcleCount(forum.getAritcleCount()+1);//文章 数量 加1
		forum.setLastTopic(model);
	}
	@Override
	public Topic getById(Integer id) {
		return topicDao.getById(id);
	}

}
