package com.pccw.ehunter.dto;

import java.io.Serializable;

import org.jmesa.limit.RowSelect;
import org.jmesa.limit.SortSet;

public class JmesaCriteria implements Serializable {
	private static final long serialVersionUID = 1L;

	private RowSelect rowSelect;
	private SortSet sortSet;

	public RowSelect getRowSelect() {
		return rowSelect;
	}

	public void setRowSelect(RowSelect rowSelect) {
		this.rowSelect = rowSelect;
	}

	public SortSet getSortSet() {
		return sortSet;
	}

	public void setSortSet(SortSet sortSet) {
		this.sortSet = sortSet;
	}
}
