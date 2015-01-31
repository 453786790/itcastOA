package cn.itcast.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.dao.IUserDao;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.utils.MD5Utils;
@Repository
@SuppressWarnings("unchecked")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{

	public boolean findLoginNameExsit(String loginName) {
		String hql="SELECT COUNT(id) FROM User WHERE loginName=?";
		Long i = (Long)this.getSession().createQuery(hql).setParameter(0, loginName).uniqueResult();
		if(i>0){
			return true;
		}
		return false;
	}
	public User login(User model) {
		String hql="FROM User u WHERE u.loginName=? AND u.password=?";
		List<User> list = this.getSession().createQuery(hql).setParameter(0,model.getLoginName()).setParameter(1, MD5Utils.md5(model.getPassword())).list();
		if(list!=null&&list.size()>0){
			return list.get(0);
			
		}
		return null;
	}

}
