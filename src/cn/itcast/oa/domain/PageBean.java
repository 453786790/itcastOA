package cn.itcast.oa.domain;

import java.util.List;
@SuppressWarnings("unchecked")
public class PageBean {
	// 三类数据，页面传递过来的参数
	private int currentPage; // 当前页
	private int pageSize; // 每页显示的记录数

	// 需要查询数据库来获得的数据
	private int recordCount; // 总记录数
	private List recordList; // 当前页要显示的数据

	// 由其他4个计算获得
	private int pageCount; // 总页数
	private int beginPageIndex; // 开始页码
	private int endPageIndex; // 结束页码
	
	//由构造方法计算
	public PageBean(){
	}
	
	


	public PageBean(int currentPage, int pageSize, int recordCount,
			List recordList) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.recordList = recordList;
		
		//计算总页数 
		this.pageCount=(this.recordCount+pageSize-1)/pageSize;
		
		//计算开始页码和结束页码
		if(pageCount<=10){
			//全部显示
			this.beginPageIndex=1;
			this.endPageIndex=pageCount;
		}else{
			//只显示部分 
			this.beginPageIndex=this.currentPage-4;
			this.endPageIndex=this.currentPage+5;
			//两种边界值
			if(this.beginPageIndex<1){
				//显示前10页
				this.beginPageIndex=1;
				this.endPageIndex=10;
			}
			if(this.endPageIndex>this.pageCount){
				this.endPageIndex=this.pageCount;
				this.beginPageIndex=this.pageCount-9;
			}
		}
	}




	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List getRecordList() {
		return recordList;
	}
	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getBeginPageIndex() {
		return beginPageIndex;
	}
	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}
	public int getEndPageIndex() {
		return endPageIndex;
	}
	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
}
