package com.pccw.ehunter.domain.internal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name="T_UPLD_CV")
public class UploadedCurriculumVitae extends BaseEntity {
	private static final long serialVersionUID = -8612107656755875508L;

	private String cvID;
	private String talentID;
	private String cvName;
	private String relativeUploadPath;
	private String relativeSwfPath;

	private String language;
	private String size;
	private String encrypted;
	private String converted;

	@Id
	@Column(name="SYS_REF_CV")
	public String getCvID() {
		return cvID;
	}

	public void setCvID(String cvID) {
		this.cvID = cvID;
	}

	@Column(name="SYS_REF_TLNT")
	public String getTalentID() {
		return talentID;
	}

	public void setTalentID(String talentID) {
		this.talentID = talentID;
	}

	@Column(name="CV_NM")
	public String getCvName() {
		return cvName;
	}

	public void setCvName(String cvName) {
		this.cvName = cvName;
	}

	@Column(name="UPLD_LCTN")
	public String getRelativeUploadPath() {
		return relativeUploadPath;
	}

	public void setRelativeUploadPath(String relativeUploadPath) {
		this.relativeUploadPath = relativeUploadPath;
	}

	@Column(name="SWF_LCTN")
	public String getRelativeSwfPath() {
		return relativeSwfPath;
	}

	public void setRelativeSwfPath(String relativeSwfPath) {
		this.relativeSwfPath = relativeSwfPath;
	}

	@Column(name="CV_LAN")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name="ATCH_SZ")
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name="ENCRY_FLG")
	public String getEncrypted() {
		return encrypted;
	}

	public void setEncrypted(String encrypted) {
		this.encrypted = encrypted;
	}

	@Column(name="CONVT_FLG")
	public String getConverted() {
		return converted;
	}

	public void setConverted(String converted) {
		this.converted = converted;
	}

}
