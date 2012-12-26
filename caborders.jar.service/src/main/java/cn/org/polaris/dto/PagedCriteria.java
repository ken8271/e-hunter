package cn.org.polaris.dto;

import java.io.Serializable;

public class PagedCriteria implements Serializable {
	private static final long serialVersionUID = -7371620571312236244L;

	private int rowStart;
	private int rowEnd;

	public int getRowStart() {
		return rowStart;
	}

	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}

	public int getRowEnd() {
		return rowEnd;
	}

	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}

}
