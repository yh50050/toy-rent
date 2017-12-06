package com.toy.utils;

import java.util.List;

/**
 * 分页工具类
 * 
 * @author 枫茗丿love
 *
 */
public class PagingUtils<T> {

	public static int PAGE_NUMBER = 7;

	private int startPage; // 开始页
	private int endPage; // 结束页
	private int paging; // 当前页码
	private int pageNumber; // 显示的页码
	private int contentSize; // 每页条数
	private int pageCount; // 总页数
	private int contentCount; // 总条数
	private List<T> content; // 数据集合
	private int median; // 中值

	public PagingUtils() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public PagingUtils(int paging, int contentSize, int contentCount, List<T> content) {
		this.paging = paging;

		this.contentSize = contentSize;
		// 计算总共多少页
		this.pageCount = contentCount / contentSize;
		if (contentCount % contentSize != 0)
			this.pageCount++;

		// 计算页码的显示数目
		this.pageNumber = this.pageNumber > this.pageCount ? this.pageCount : PAGE_NUMBER;

		// 计算开始页与结束页
		median = this.pageNumber / 2;
		if (this.pageCount >= this.pageNumber) {
			if (this.paging > (median + 1)) {
				this.startPage = this.paging - median;
				this.endPage = this.paging + median;
			} else {
				this.startPage = 1;
				this.endPage = this.pageNumber;
			}
			if (this.endPage > this.pageCount) {
				this.endPage = this.pageCount;
				this.startPage = this.pageCount - this.pageNumber + 1;
			}
		}else
		{
			this.startPage = 1;
			this.endPage = this.pageCount;
		}

		this.contentCount = contentCount;
		this.content = content;
	}

	public int getPaging() {
		return paging;
	}

	public void setPaging(int paging) {
		this.paging = paging;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getContentSize() {
		return contentSize;
	}

	public void setContentSize(int contentSize) {
		this.contentSize = contentSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getContentCount() {
		return contentCount;
	}

	public void setContentCount(int contentCount) {
		this.contentCount = contentCount;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public void updataDate(int paging, int contentSize, int contentCount, List<T> content) {
		this.paging = paging;

		this.contentSize = contentSize;
		// 计算总共多少页
		this.pageCount = contentCount / contentSize;
		if (contentCount % contentSize != 0)
			this.pageCount++;

		// 计算页码的显示数目
		this.pageNumber = 5;
		this.pageNumber = this.pageNumber > this.pageCount ? this.pageCount : this.pageNumber;

		// 计算开始页与结束页
		median = this.pageNumber / 2;
		if (this.paging > (median + 1)) {
			this.startPage = this.paging - median;
			this.endPage = this.paging + median;
		} else {
			this.startPage = 1;
			this.endPage = this.pageNumber;
		}
		if (this.endPage > this.pageCount) {
			this.endPage = this.pageCount;
			this.startPage = this.pageCount - this.pageNumber + 1;
		}

		this.contentCount = contentCount;
		this.content = content;
	}

}
