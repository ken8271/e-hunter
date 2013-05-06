package cn.org.polaris.dto.biz;

import java.util.Date;

import cn.org.polaris.dto.PagedCriteria;

public class InformationPagedCriteria extends PagedCriteria {
	private static final long serialVersionUID = -576206304041542783L;

	private String id;
	private String title;
	private String category;
	private Date fromDate;
	private Date toDate;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
}
