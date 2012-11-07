package com.pccw.ehunter.domain.internal;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_USR_TX_LOG")
public class TransactionLog implements Serializable {
	private static final long serialVersionUID = -3658962029216448577L;

	private String id;
	private String userID;
	private Date transactionDatetime;
	private String functionIndicator;
	private String transactionMsg;

	@Id
	@Column(name="TX_LOG_ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="USR_REC_ID")
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Column(name="TX_DTTM")
	public Date getTransactionDatetime() {
		return transactionDatetime;
	}

	public void setTransactionDatetime(Date transactionDatetime) {
		this.transactionDatetime = transactionDatetime;
	}

	@Column(name="FUNC")
	public String getFunctionIndicator() {
		return functionIndicator;
	}

	public void setFunctionIndicator(String functionIndicator) {
		this.functionIndicator = functionIndicator;
	}

	@Column(name="TX_MSG")
	public String getTransactionMsg() {
		return transactionMsg;
	}

	public void setTransactionMsg(String transactionMsg) {
		this.transactionMsg = transactionMsg;
	}

}
