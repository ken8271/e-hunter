package cn.org.polaris.dto.biz;

import java.util.Date;

import cn.org.polaris.dto.PagedCriteria;

public class PositionPagedCriteria extends PagedCriteria {
	private static final long serialVersionUID = 7787338298968681512L;

	private String id;
	private String title;
	private String workCity;
	private Date fromDate;
	private Date toDate;

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

	public String getWorkCity() {
		return workCity;
	}

	public void setWorkCity(String workCity) {
		this.workCity = workCity;
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
