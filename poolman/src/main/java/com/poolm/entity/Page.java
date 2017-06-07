package com.poolm.entity;

import com.poolm.util.TextHelper;

/**
 * 描述:分页属性类
 * 
 * **/
public class Page {

	/**
	 * 每页显示的记录数
	 * 
	 * 
	 */
	private Integer pageSize = null;

	/**
	 * 当前页码
	 * 
	 * 
	 */
	private Integer pageNo = null;

	/**
	 * 总页数
	 * 
	 * 
	 */
	private Integer pageCount = -1;

	/**
	 * 数据总条数
	 * 
	 * 
	 */
	private Integer totalCount = null;

	/**
	 * 总人数
	 *
	 *
	 */

	private Integer peopleCount = null;

	/**
	 * 生序还是降序
	 */
	private String orderBy = null;
	/**
	 * 排序的字段
	 */
	private String orderName = null;

	/**
	 * 检索关键字
	 */
	private String query = null;

	/**
	 * 过滤后的数据总条数
	 *
	 *
	 */
	private Integer selectCount = null;

	/**
	 * 菜单总数
	 */
	private Integer attendCount = null;

	/**
	 * 
	 */
	private String scratch = null;

	public Page() {
	}

	public Page(Object pageNo, Object pageSize, Object pageCount) {
		if (TextHelper.isNullOrEmpty(pageNo)) {
			this.pageNo = 1;
		} else {
			this.pageNo = Integer.parseInt(pageNo.toString());
		}
		if (TextHelper.isNullOrEmpty(pageCount)) {
			this.pageCount = -1;
		} else {
			this.pageCount = Integer.parseInt(pageCount.toString());
		}
		if (TextHelper.isNullOrEmpty(pageSize)) {
			this.pageSize = 10;
		} else {
			this.pageSize = Integer.parseInt(pageSize.toString());
		}
		if (this.pageNo <= 0)
			this.pageNo = 1;
	}

	public Page(Object pageNo, Object pageSize, Object pageCount,
			Object totalCount) {
		this.pageNo = Integer.parseInt(pageNo.toString());
		this.pageSize = Integer.parseInt(pageSize.toString());
		this.pageCount = Integer.parseInt(pageCount.toString());
		if (this.pageNo <= 0)
			pageNo = 1;
		this.totalCount = Integer.parseInt(totalCount.toString());
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderName() {
		return orderName;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	public Integer getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getSelectCount() {
		return selectCount;
	}

	public void setSelectCount(Integer selectCount) {
		this.selectCount = selectCount;
	}

	public Integer getAttendCount() {
		return attendCount;
	}

	public void setAttendCount(Integer attendCount) {
		this.attendCount = attendCount;
	}

	public String getScratch() {
		return scratch;
	}

	public void setScratch(String scratch) {
		this.scratch = scratch;
	}

	/**
	 * 
	 * Description: 计算分页<br/>
	 * 
	 * @author oscar_000
	 * @param page
	 * @param totalCount
	 */
	public static void countPageSum(Page page, int totalCount) {
		page.setTotalCount(totalCount);
		if (totalCount % page.getPageSize() != 0) {
			page.setPageCount(totalCount / page.getPageSize() + 1);
		} else {
			page.setPageCount(totalCount / page.getPageSize());
		}
		if (page.getPageNo() > page.getPageCount()) {
			page.setPageNo(1);
		}
	}
}
