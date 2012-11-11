package com.pccw.ehunter.dto;

import java.io.Serializable;
import java.util.Date;

public class DataBackupHistoryDTO implements Serializable {
	private static final long serialVersionUID = 2513321906136270815L;

	private String historyID;
	private String fileName;
	private Date backupDttm;
	private String backupChannel;
	private String backupBy;

	public String getHistoryID() {
		return historyID;
	}

	public void setHistoryID(String historyID) {
		this.historyID = historyID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getBackupDttm() {
		return backupDttm;
	}

	public void setBackupDttm(Date backupDttm) {
		this.backupDttm = backupDttm;
	}

	public String getBackupChannel() {
		return backupChannel;
	}

	public void setBackupChannel(String backupChannel) {
		this.backupChannel = backupChannel;
	}

	public String getBackupBy() {
		return backupBy;
	}

	public void setBackupBy(String backupBy) {
		this.backupBy = backupBy;
	}

}
