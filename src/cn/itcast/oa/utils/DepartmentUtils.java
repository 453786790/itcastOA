package cn.itcast.oa.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.itcast.oa.domain.Department;

public class DepartmentUtils {
	// 处理树形结构  removeId参数 主要给修改的时候添加的
	public static List<Department> getTree(List<Department> topList,Integer reomveId) {
		List<Department> treeList = new ArrayList<Department>();
		walkTree(topList, treeList, "┣",reomveId);
		return treeList;
	}

	// 递归树
	private static void walkTree(Collection<Department> topList,
			List<Department> treeList, String prefix,Integer reomveId) {
		for (Department d : topList) {
			if(reomveId!=null){
				if(d.getId().equals(reomveId))
				continue;
			}
			Department copy = new Department();
			copy.setId(d.getId());
			copy.setName(prefix+d.getName());
			treeList.add(copy);// 持久对象拷贝
			walkTree(d.getChidren(), treeList, "　" + prefix,reomveId);
		}

	}
}
