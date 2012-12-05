package com.pccw.ehunter.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContentSearchResultDTO implements Serializable {
	private static final long serialVersionUID = 9019395881313474665L;

	private int totalCount;
	private List<String> matches = new ArrayList<String>();

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<String> getMatches() {
		return matches;
	}

	public void setMatches(List<String> matches) {
		this.matches = matches;
	}

}
