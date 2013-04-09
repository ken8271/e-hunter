package cn.org.polaris.dto;

import java.io.Serializable;

public class RowSelect implements Serializable{
	private static final long serialVersionUID = 1167680584545196626L;

	private int page;
	private int maxRows;
	private int rowEnd;
	private int rowStart;
	private int totalRows;

	public RowSelect(int page, int maxRows, int totalRows) {
		this.maxRows = maxRows;
		this.totalRows = totalRows;
		init(page);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		init(page);
	}

	public int getRowStart() {
		return rowStart;
	}

	public int getRowEnd() {
		return rowEnd;
	}

	public int getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
		init(page);
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		init(page);
	}

	private void init(int page) {
		page = getValidPage(page, maxRows, totalRows);

		int rs = (page - 1) * maxRows;

		int re = rs + maxRows;

		if (re > totalRows) {
			re = totalRows;
		}

		this.page = page;
		this.rowStart = rs;
		this.rowEnd = re;
	}

	private int getValidPage(int page, int maxRows, int totalRows) {
		while (!isValidPage(page, maxRows, totalRows)) {
			--page;
		}
		return page;
	}

	private boolean isValidPage(int page, int maxRows, int totalRows) {
		if (page == 1) {
			return true;
		}

		int rs = (page - 1) * maxRows;
		int re = rs + maxRows;
		if (re > totalRows) {
			re = totalRows;
		}
		return re > rs;
	}
}
