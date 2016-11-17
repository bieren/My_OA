package com.my_oa.orm;


import java.util.List;

public class Page<T> {

	private int pageNo;// 当前页
	private int pageSize = 5;// 每页记录数

	private int totalElements;// 总记录数
	private List<T> content;

	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Page(int pageNo, int pageSize, int totalElements, List<T> content) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.content = content;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			pageNo = 1;
		}

		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;

		// 校验pageNo的合法性
		if (this.pageSize > this.getTotalPages()) {

			this.pageSize = this.getTotalPages();
		}

	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	// 返回总页数
	public int getTotalPages() {
		int totalPages = this.totalElements % this.pageSize == 0 ? this.totalElements / this.pageSize
				: this.totalElements / this.pageSize + 1;

		return totalPages;
	}

	
	/**
	 * 是否有上一页
	 * @return
	 */
	public boolean isHasPrev() {

		return this.pageNo > 1;
	}

	/**
	 * 是否有下一页
	 * @return
	 */
	public boolean isHasNext() {

		return this.pageNo < this.getTotalPages();
	}
	
	/**
	 * 存在上一页的时候，获取上一页页数
	 * @return
	 */
	public int getPrev(){
		if(this.isHasPrev()){
			return this.pageNo - 1;
		}
		return this.pageNo;
	}
	
	/**
	 * 存在下一页的时候，获取下一页页数
	 * @return
	 */
	public int getNext(){
		
		if(this.isHasNext()){
			return this.pageNo + 1;
		}
		return this.pageNo;
	}
	
}
