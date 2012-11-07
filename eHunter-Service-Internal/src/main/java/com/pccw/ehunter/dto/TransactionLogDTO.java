package com.pccw.ehunter.dto;

import java.io.Serializable;
import java.util.Date;

public class TransactionLogDTO implements Serializable {
	private static final long serialVersionUID = -361878577152006664L;

	private String id;
	private String userID;
	private Date transactionDatetime;
	private String functionIndicator;
	private String transactionMsg;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Date getTransactionDatetime() {
		return transactionDatetime;
	}

	public void setTransactionDatetime(Date transactionDatetime) {
		this.transactionDatetime = transactionDatetime;
	}

	public String getFunctionIndicator() {
		return functionIndicator;
	}

	public void setFunctionIndicator(String functionIndicator) {
		this.functionIndicator = functionIndicator;
	}

	public String getTransactionMsg() {
		return transactionMsg;
	}

	public void setTransactionMsg(String transactionMsg) {
		this.transactionMsg = transactionMsg;
	}

}
