package com.pccw.ehunter.domain.internal;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_BCUP_HST")
public class DataBackupHistory implements Serializable{
	private static final long serialVersionUID = -9051231200144557701L;
	
	private String historyID;
	private String fileName;
	private Date backupDttm;
	private String backupChannel;
	private String backupBy;

	@Id
	@Column(name="BCUP_HST_ID")
	public String getHistoryID() {
		return historyID;
	}

	public void setHistoryID(String historyID) {
		this.historyID = historyID;
	}

	@Column(name="BCUP_NM")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name="BCUP_TM")
	public Date getBackupDttm() {
		return backupDttm;
	}

	public void setBackupDttm(Date backupDttm) {
		this.backupDttm = backupDttm;
	}

	@Column(name="BCUP_CHNL")
	public String getBackupChannel() {
		return backupChannel;
	}

	public void setBackupChannel(String backupChannel) {
		this.backupChannel = backupChannel;
	}

	@Column(name="BCUP_BY")
	public String getBackupBy() {
		return backupBy;
	}

	public void setBackupBy(String backupBy) {
		this.backupBy = backupBy;
	}
}
