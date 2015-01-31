package cn.itcast.oa.domain;

import java.util.Set;

public class Forum {
	public Integer id;
	public String name;
	public String description;
	public Integer position;
	public Set<Topic> topics;
	public Integer topicCount=0;//主题 数量   默认值 为0
	public Integer aritcleCount=0;//文章 数量 
	public Topic lastTopic;//最后发表的	主题 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Set<Topic> getTopics() {
		return topics;
	}
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}
	public Integer getTopicCount() {
		return topicCount;
	}
	public void setTopicCount(Integer topicCount) {
		this.topicCount = topicCount;
	}
	public Integer getAritcleCount() {
		return aritcleCount;
	}
	public void setAritcleCount(Integer aritcleCount) {
		this.aritcleCount = aritcleCount;
	}
	public Topic getLastTopic() {
		return lastTopic;
	}
	public void setLastTopic(Topic lastTopic) {
		this.lastTopic = lastTopic;
	}
}
