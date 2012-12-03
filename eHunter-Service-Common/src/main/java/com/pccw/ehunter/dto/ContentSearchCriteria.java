package com.pccw.ehunter.dto;

import java.io.Serializable;

public class ContentSearchCriteria implements Serializable {
	private static final long serialVersionUID = -3628230972309306969L;

	public static String RANGE_CRITERIA = "R";
	public static String TERM_CRITERIA = "T";

	private String type;
	private String value;

	public ContentSearchCriteria() {
	}

	public ContentSearchCriteria(String type, String value) {
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
