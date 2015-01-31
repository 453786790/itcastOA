package cn.itcast.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cn.itcast.oa.domain.Department;

/**
 * 说明：不能使用多层循环的方式，因为需要能支持任意层。
 */
public class TreeViewPractice {
	/**
	 * 结构如下：
	 * 
	 * <pre>
	 * ┣市场部
	 *    ┣宣传部
	 *    ┣业务部
	 *       ┣业务一部
	 *       ┣业务二部
	 * ┣开发部
	 *    ┣开发一部
	 *    ┣开发二部
	 * </pre>
	 * 
	 * @return 所有最顶层的部门的列表
	 */
	public static List<Department> findTopLevelDepartmentList() {
		Department dept_1_1 = new Department();
		dept_1_1.setId(11);
		dept_1_1.setName("宣传部");

		Department dept_1_2 = new Department();
		dept_1_2.setId(12);
		dept_1_2.setName("业务部");

		Department dept_1_2_1 = new Department();
		dept_1_2_1.setId(121);
		dept_1_2_1.setName("业务一部");

		Department dept_1_2_2 = new Department();
		dept_1_2_2.setId(122);
		dept_1_2_2.setName("业务二部");

		dept_1_2_1.setParent(dept_1_2);
		dept_1_2_2.setParent(dept_1_2);
		Set<Department> children_0 = new LinkedHashSet<Department>();
		children_0.add(dept_1_2_1);
		children_0.add(dept_1_2_2);
		dept_1_2.setChidren(children_0);

		// ================================

		Department dept_1 = new Department();
		dept_1.setId(1);
		dept_1.setName("市场部");

		dept_1_1.setParent(dept_1);
		dept_1_2.setParent(dept_1);
		Set<Department> children_1 = new LinkedHashSet<Department>();
		children_1.add(dept_1_1);
		children_1.add(dept_1_2);
		dept_1.setChidren(children_1);

		// ---

		Department dept_2_1 = new Department();
		dept_2_1.setId(21);
		dept_2_1.setName("开发一部");

		Department dept_2_2 = new Department();
		dept_2_2.setId((22));
		dept_2_2.setName("开发二部");

		Department dept_2 = new Department();
		dept_2.setId(2);
		dept_2.setName("开发部");

		dept_2_1.setParent(dept_2);
		dept_2_2.setParent(dept_2);
		Set<Department> children_2 = new LinkedHashSet<Department>();
		children_2.add(dept_2_1);
		children_2.add(dept_2_2);
		dept_2.setChidren(children_2);

		// ---┣

		List<Department> depts = new ArrayList<Department>();
		depts.add(dept_1);
		depts.add(dept_2);
		return depts;
	}

	public static void test1() {
		List<Department> findTopLevelDepartmentList = findTopLevelDepartmentList();
		for (Department d : findTopLevelDepartmentList) {
			// System.out.println(d.getName());
			showTree(d, "┣");
		}

	}

	public static void showTree(Department d, String prefix) {
		System.out.println(prefix + d.getName());
		Set<Department> chidren = d.getChidren();
		if (chidren != null) {
			for (Department c : chidren) {
				showTree(c, "    " + prefix);
			}
		}
	}

	public static void test2() {
		List<Department> findTopLevelDepartmentList = findTopLevelDepartmentList();
		showTree2(findTopLevelDepartmentList, "┣");
	}

	public static void showTree2(
			Collection<Department> findTopLevelDepartmentList, String prefix) {
		for (Department d : findTopLevelDepartmentList) {
			System.out.println(prefix + d.getName());
			Set<Department> children = d.getChidren();
			if (children != null)
				showTree2(children, "  " + prefix);

		}
	}

	public static void main(String[] args) {
		// 循环两次
		// test1();
		// 循环一次
		test2();
	}
}
