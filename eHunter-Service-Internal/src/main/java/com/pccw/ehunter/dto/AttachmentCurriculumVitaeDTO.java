package com.pccw.ehunter.dto;

public class AttachmentCurriculumVitaeDTO extends BaseDTO {
	private static final long serialVersionUID = 6401596643520265519L;

	private String talentID;
	private String cvName;
	private String uploadPath;
	private String swfPath;
	private String size;
	private boolean encrypted;
	private boolean converted;

	public String getTalentID() {
		return talentID;
	}

	public void setTalentID(String talentID) {
		this.talentID = talentID;
	}

	public String getCvName() {
		return cvName;
	}

	public void setCvName(String cvName) {
		this.cvName = cvName;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getSwfPath() {
		return swfPath;
	}

	public void setSwfPath(String swfPath) {
		this.swfPath = swfPath;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public boolean isEncrypted() {
		return encrypted;
	}

	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

	public boolean isConverted() {
		return converted;
	}

	public void setConverted(boolean converted) {
		this.converted = converted;
	}

}
