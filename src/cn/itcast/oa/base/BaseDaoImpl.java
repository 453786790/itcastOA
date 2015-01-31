package cn.itcast.oa.base;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.utils.HqlHelper;

//通用dao
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements IBaseDao<T> {
     protected Class<T> clazz;
     @Resource
     protected SessionFactory sessionFactory;
     public BaseDaoImpl(){
    	 ParameterizedType genericSuperclass = (ParameterizedType)this.getClass().getGenericSuperclass();
    	 Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
    	 clazz = (Class)actualTypeArguments[0];
     }
     
	public void delete(Integer id) {
		this.getSession().delete(this.getSession().get(clazz, id));
	}
	

	public List<T> findAll() {
		String hql="FROM "+clazz.getSimpleName();
		return this.getSession().createQuery(hql).list();
	}

	public T getById(Integer id) {
		if(id==null)
			return null;//添加顶级部门时异常
		return (T)this.getSession().get(clazz,id);
	}

	public List<T> getByIds(Integer[] ids) {
		String hql=" FROM "+ clazz.getSimpleName() +" where id in (:ids) ";
		return this.getSession().createQuery(hql).setParameterList("ids", ids).list();
	}

	public void save(T entity) {
		this.getSession().save(entity);
	}
	

	public void update(T entity) {
		this.getSession().update(entity);
	}
    public Session getSession(){
    	return sessionFactory.getCurrentSession();
    }

	public PageBean getPageBean(int currentPage, HqlHelper hq) {
		
		int pageSize=getPageSize();
		
		String countHQL = hq.getCountHQL();
		List<Object> args = hq.getArgs();
		String listHQL = hq.getListHQL();
		
		Query query = this.getSession().createQuery(countHQL);
		if(args != null && args.size()>0){
			int index=0;
			for(Object o:args){
				query.setParameter(index++,o);
			}
		}
		Long recordCount = (Long)query.uniqueResult();
		
		query = this.getSession().createQuery(listHQL);
		if(args != null && args.size()>0){
			int index=0;
			for(Object o:args){
				query.setParameter(index++,o);
			}
		}
		
		int bagin=(currentPage-1)*pageSize;
		query.setFirstResult(bagin);
		query.setMaxResults(pageSize);
		List recordList = query.list();
		
		return new PageBean(currentPage,pageSize,recordCount.intValue(),recordList);
	}
    //读取配置文件，获取pageSize
	private int getPageSize() {
		int pageSize=10;
		Properties pro=new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("page.properties");
		try {
			pro.load(in);
			String pageSizeStr=(String)pro.get("pageSize");
			pageSize=Integer.valueOf(pageSizeStr);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pageSize;
	}
}
