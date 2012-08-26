package com.pccw.ehunter.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity implements Serializable{
	private static final long serialVersionUID = 8151455261677162678L;
	
	private String createBy;
	private Date createDateTime;
	private String lastUpdateBy;
	private Date lastUpdateDateTime;
	private String lastTransactionIndicator;

	@Basic
	@Column(name="CR_BY")
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Basic
	@Column(name="CR_DTTM")
	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	@Basic
	@Column(name="LST_UPD_BY")
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	@Basic
	@Column(name="LST_UPD_DTTM")
	public Date getLastUpdateDateTime() {
		return lastUpdateDateTime;
	}

	public void setLastUpdateDateTime(Date lastUpdateDateTime) {
		this.lastUpdateDateTime = lastUpdateDateTime;
	}

	@Basic
	@Column(name="LST_TX_ACTN")
	public String getLastTransactionIndicator() {
		return lastTransactionIndicator;
	}

	public void setLastTransactionIndicator(String lastTransactionIndicator) {
		this.lastTransactionIndicator = lastTransactionIndicator;
	}

}
