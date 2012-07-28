package com.pccw.ehunter.dto;

public class SortDTO {
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	
	private String property;
	private String order;
	
	public SortDTO(String property, String order) {
		super();
		this.property = property;
		this.order = order;
	}

	public SortDTO(String property) {
		super();
		this.property = property;
		this.order = ASC;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
