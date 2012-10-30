package com.pccw.ehunter.dto;

import java.io.File;

import com.pccw.ehunter.utility.FileUtils;

public class AttachmentCurriculumVitaeDTO extends BaseDTO {
	private static final long serialVersionUID = 6401596643520265519L;

	private String talentID;
	private String originalFileName;

	private String baseUploadDir;
	private String baseSwfDir;
	private String relativeUploadPath;
	private String relativeSwfPath;
	
	private String size;
	private boolean encrypted;
	private boolean converted;

	public String getTalentID() {
		return talentID;
	}

	public void setTalentID(String talentID) {
		this.talentID = talentID;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
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

	public String getUploadPath() {
		return baseUploadDir + relativeUploadPath;
	}

	public String getSwfPath() {
		return baseSwfDir + relativeSwfPath;
	}
	
	private String getUploadDir(){
		return baseUploadDir + relativeUploadPath.substring(0, relativeUploadPath.lastIndexOf(File.separator));
	}
	
	private String getSwfDir(){
		return baseSwfDir + relativeSwfPath.substring(0 , relativeSwfPath.lastIndexOf(File.separator));
	}
	
	public String getUploadFileExtension(){
		return FileUtils.getExtension(originalFileName);
	}
	
	public void prepareDiskDirs(){
		File ud = new File(getUploadDir());
		if(!ud.exists()){
			ud.mkdirs();
		}
		
		File sd = new File(getSwfDir());
		if(!sd.exists()){
			sd.mkdirs();
		}
	}
}
