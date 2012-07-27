package com.pccw.ehunter.dto;

import java.io.Serializable;
import java.util.Date;

public class BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String createBy;
	private Date createDateTime;
	private String lastUpdateBy;
	private Date lastUpdateDateTime;
	private String lastUpdateTransaction;

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Date getLastUpdateDateTime() {
		return lastUpdateDateTime;
	}

	public void setLastUpdateDateTime(Date lastUpdateDateTime) {
		this.lastUpdateDateTime = lastUpdateDateTime;
	}

	public String getLastUpdateTransaction() {
		return lastUpdateTransaction;
	}

	public void setLastUpdateTransaction(String lastUpdateTransaction) {
		this.lastUpdateTransaction = lastUpdateTransaction;
	}

}
