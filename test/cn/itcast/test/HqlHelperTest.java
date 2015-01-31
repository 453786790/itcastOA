package cn.itcast.test;

import org.junit.Test;

import cn.itcast.oa.domain.User;
import cn.itcast.oa.utils.HqlHelper;

public class HqlHelperTest {
	@Test
	public void test1() {
      //	查询 user  t条件 name age  排序age   
		HqlHelper hqlHelper = new HqlHelper(User.class);
		hqlHelper.addWhereCondition("o.name = ?","张三");
		hqlHelper.addWhereCondition("o.age > ?",30);
		hqlHelper.addOrderBy("o.name",true);
		hqlHelper.addOrderBy("o.age",true);
		System.out.println("ListHQL:"+hqlHelper.getListHQL());
		System.out.println("CountHQL:"+hqlHelper.getCountHQL());
		System.out.println("args:"+hqlHelper.getArgs());
	}
}
