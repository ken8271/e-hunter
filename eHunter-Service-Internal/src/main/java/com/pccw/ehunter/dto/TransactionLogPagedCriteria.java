package com.pccw.ehunter.dto;

import java.util.Date;

public class TransactionLogPagedCriteria extends PagedCriteria {
	private static final long serialVersionUID = 2390804390149981033L;

	private Date fromDttm;
	private Date toDttm;
	private String module;
	private String user;

	public Date getFromDttm() {
		return fromDttm;
	}

	public void setFromDttm(Date fromDttm) {
		this.fromDttm = fromDttm;
	}

	public Date getToDttm() {
		return toDttm;
	}

	public void setToDttm(Date toDttm) {
		this.toDttm = toDttm;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
