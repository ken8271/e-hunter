package cn.org.polaris.dto.biz;

import cn.org.polaris.dto.PagedCriteria;

public class InformationPagedCriteria extends PagedCriteria {
	private static final long serialVersionUID = -576206304041542783L;

	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
