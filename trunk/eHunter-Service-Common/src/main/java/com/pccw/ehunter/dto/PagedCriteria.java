package com.pccw.ehunter.dto;

import java.io.Serializable;

public class PagedCriteria implements Serializable {
	private static final long serialVersionUID = 1L;

	private PageFilter pageFilter = new PageFilter();
	private PageSort pageSort = new PageSort();

	public PageFilter getPageFilter() {
		return pageFilter;
	}

	public void setPageFilter(PageFilter pageFilter) {
		this.pageFilter = pageFilter;
	}

	public PageSort getPageSort() {
		return pageSort;
	}

	public void setPageSort(PageSort pageSort) {
		this.pageSort = pageSort;
	}
}
