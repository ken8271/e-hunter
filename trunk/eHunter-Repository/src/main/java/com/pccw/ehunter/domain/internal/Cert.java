package com.pccw.ehunter.domain.internal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_TLNT_CERT")
public class Cert extends BaseEntity {
	private CertPK pk;
	private String certName;
	private Date gainedDate;
	private String description;

	@EmbeddedId
	public CertPK getPk() {
		return pk;
	}

	public void setPk(CertPK pk) {
		this.pk = pk;
	}

	@Column(name="CERT_NM")
	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	@Column(name="GAIN_DTTM")
	public Date getGainedDate() {
		return gainedDate;
	}

	public void setGainedDate(Date gainedDate) {
		this.gainedDate = gainedDate;
	}

	@Column(name="CERT_DESC")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
