package cn.itcast.oa.utils;

import java.util.ArrayList;
import java.util.List;

public class HqlHelper {
	// FROM 子句
	private String fromStr;
	// WHERE 子句
	private String whereStr = "";
	// ORDER BY 子句
	private String orderByStr = "";

	// 参数收集
	private List<Object> args = new ArrayList<Object>();

	public List<Object> getArgs() {
		return args;
	}

	public HqlHelper() {

	}

	// 在构造方法中拼接FROM子句
	public HqlHelper(Class clazz) {
		this.fromStr = " FROM " + clazz.getSimpleName() + " o ";
	}

	// 拼接where子句
	public void addWhereCondition(String condition, Object... args) {
		if (this.whereStr.length() == 0) {
			// 第一次拼接where 条件
			this.whereStr = " WHERE "+condition;
		} else {
			// 第二次拼接where 条件
			this.whereStr += " AND " + condition;
		}
		if (args != null && args.length > 0) {
			for (Object o : args) {
				this.args.add(o);
			}
		}
	}

	// 拼接order子句
	public void addOrderBy(String orderBy, Boolean asc) {
		if (this.orderByStr.length() == 0) {
			// 第一次
			this.orderByStr = " ORDER BY " + orderBy
					+ (asc ? " ASC " : " DESC ");
		} else {
			// 第二次
			this.orderByStr += " , " + orderBy + (asc ? " ASC " : " DESC ");
		}
	}

	// 获取 查询数据集合的hql
	public String getListHQL() {
		return this.fromStr + this.whereStr + this.orderByStr;
	}
	//获取统计 记录数的hql
	public String getCountHQL() {
		return " SELECT COUNT(*) " + this.fromStr + this.whereStr;
	}
}
