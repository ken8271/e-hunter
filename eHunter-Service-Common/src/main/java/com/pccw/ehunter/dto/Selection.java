package com.pccw.ehunter.dto;

import java.io.Serializable;

public class Selection implements Serializable {
	private static final long serialVersionUID = 1L;

	private String key;
	private boolean checked;

	public Selection(String key, boolean checked) {
		super();
		this.key = key;
		this.checked = checked;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
