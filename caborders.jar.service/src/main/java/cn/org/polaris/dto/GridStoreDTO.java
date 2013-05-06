package cn.org.polaris.dto;

import java.io.Serializable;
import java.util.List;

public class GridStoreDTO implements Serializable {
	private static final long serialVersionUID = 4512137341804723093L;

	private int totalCount;
	private List<? extends Object> list;

	public GridStoreDTO() {
		super();
	}

	public GridStoreDTO(int totalCount, List<? extends Object> list) {
		super();
		this.totalCount = totalCount;
		this.list = list;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<? extends Object> getList() {
		return list;
	}

	public void setList(List<? extends Object> list) {
		this.list = list;
	}

}
