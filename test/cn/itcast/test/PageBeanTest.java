package cn.itcast.test;

import org.junit.Test;

import cn.itcast.oa.domain.PageBean;

public class PageBeanTest {
	@Test
	public void test1() {
		PageBean pb = new PageBean(10, 10, 300, null);
		System.out.println("总页数" + pb.getPageCount());
		System.out.println("开始页码" + pb.getBeginPageIndex());
		System.out.println("结束页码" + pb.getEndPageIndex());
	}

	@Test
	public void test2() {
		PageBean pb = new PageBean(2, 10, 300, null);
		System.out.println("总页数" + pb.getPageCount());
		System.out.println("开始页码" + pb.getBeginPageIndex());
		System.out.println("结束页码" + pb.getEndPageIndex());
	}
	@Test
	public void test3() {
		PageBean pb = new PageBean(27, 10, 300, null);
		System.out.println("总页数" + pb.getPageCount());
		System.out.println("开始页码" + pb.getBeginPageIndex());
		System.out.println("结束页码" + pb.getEndPageIndex());
	}
	@Test
	public void test4() {
		PageBean pb = new PageBean(1, 10, 11, null);
		System.out.println("总页数" + pb.getPageCount());
		System.out.println("开始页码" + pb.getBeginPageIndex());
		System.out.println("结束页码" + pb.getEndPageIndex());
	}
}
