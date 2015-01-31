package cn.itcast.oa.base;

import java.util.List;

import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.utils.HqlHelper;

//dao
public interface IBaseDao<T> {
	public void save(T entity);

	public void delete(Integer id);

	public void update(T entity);

	public T getById(Integer id);

	public List<T> findAll();

	public List<T> getByIds(Integer[] ids);
	
	public PageBean getPageBean(int currentPage,HqlHelper hq);
}
