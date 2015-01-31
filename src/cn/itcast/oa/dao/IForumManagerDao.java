package cn.itcast.oa.dao;

import cn.itcast.oa.base.IBaseDao;
import cn.itcast.oa.domain.Forum;

public interface IForumManagerDao extends IBaseDao<Forum> {
	public void moveUp(Integer id);

	public void moveDown(Integer id);
}
