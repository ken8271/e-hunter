package com.pccw.ehunter.service;

public interface TransactionLogService {
	public void logTransaction(String function , String msg);
	public void logTransaction(String function , String msg , String user);
}
