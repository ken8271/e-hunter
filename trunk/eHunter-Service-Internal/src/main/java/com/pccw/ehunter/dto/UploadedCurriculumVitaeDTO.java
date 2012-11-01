package com.pccw.ehunter.dto;

import java.io.File;

public class UploadedCurriculumVitaeDTO extends BaseDTO {
	private static final long serialVersionUID = 6401596643520265519L;

	private String cvID;
	private String talentID;
	private String cvName;

	private String baseUploadDir;
	private String baseSwfDir;
	private String relativeUploadPath;
	private String relativeSwfPath;

	private String language;
	private String size;
	private String type;
	private String encrypted;
	private String converted;

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

	public String getBaseUploadDir() {
		return baseUploadDir;
	}

	public void setBaseUploadDir(String baseUploadDir) {
		this.baseUploadDir = baseUploadDir;
	}

	public String getBaseSwfDir() {
		return baseSwfDir;
	}

	public void setBaseSwfDir(String baseSwfDir) {
		this.baseSwfDir = baseSwfDir;
	}

	public String getRelativeUploadPath() {
		return relativeUploadPath;
	}

	public void setRelativeUploadPath(String relativeUploadPath) {
		this.relativeUploadPath = relativeUploadPath;
	}

	public String getRelativeSwfPath() {
		return relativeSwfPath;
	}

	public void setRelativeSwfPath(String relativeSwfPath) {
		this.relativeSwfPath = relativeSwfPath;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCvID() {
		return cvID;
	}

	public void setCvID(String cvID) {
		this.cvID = cvID;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEncrypted() {
		return encrypted;
	}

	public void setEncrypted(String encrypted) {
		this.encrypted = encrypted;
	}

	public String getConverted() {
		return converted;
	}

	public void setConverted(String converted) {
		this.converted = converted;
	}

	public String getUploadPath() {
		return baseUploadDir + relativeUploadPath;
	}

	public String getSwfPath() {
		return baseSwfDir + relativeSwfPath;
	}

	private String getUploadDir() {
		return baseUploadDir + relativeUploadPath.substring(0,relativeUploadPath.lastIndexOf(File.separator));
	}

	private String getSwfDir() {
		return baseSwfDir + relativeSwfPath.substring(0,relativeSwfPath.lastIndexOf(File.separator));
	}

	public void prepareDiskDirs() {
		File ud = new File(getUploadDir());
		if (!ud.exists()) {
			ud.mkdirs();
		}

		File sd = new File(getSwfDir());
		if (!sd.exists()) {
			sd.mkdirs();
		}
	}
	
	public String getSwfFileName(){
		return relativeSwfPath.substring(relativeSwfPath.lastIndexOf(File.separator)+1, relativeSwfPath.length());
	}
}
