package com.hack4b.util;

/**
 * 翻页小助手，用于计算分页数据
 * @author lejie
 *
 */
public class Pager {
	private int currentPage;  //当前页数
	private int pageSize;	//每页显示数量
	private int totalRows;  //总记录数
	private boolean hasPrevious;  //是否存在上页
	private boolean hasNext;  //是否存在下页
	
	/**
	 * 自动计算分页数据
	 * @param currentPage 当前页数
	 * @param pageSize 页面大小
	 * @param totalRows 总记录数
	 */
	public Pager(int currentPage,int pageSize,int totalRows) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalRows = totalRows;
	}
	
	/**
	 * 获取总页数
	 * @return
	 */
	public int getTotalPageNum(){
		int pageNum = totalRows/pageSize;
		if(totalRows%pageSize!=0){
			pageNum++;
		}
		return pageNum;
	}
	
	/**
	 * 是否存在上页
	 * @return
	 */
	public boolean isPrevious(){
		if(currentPage==1){
			return false;
		}
		return true;
	}
	
	/**
	 * 是否存在下一页
	 * @return
	 */
	public boolean isNext(){
		if(currentPage==getTotalPageNum()){
			return false;
		}
		return true;
	}
}
