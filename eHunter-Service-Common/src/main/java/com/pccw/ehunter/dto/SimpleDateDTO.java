package com.pccw.ehunter.dto;

import java.io.Serializable;

public class SimpleDateDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String year;
	private String month;
	private String day;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

}
