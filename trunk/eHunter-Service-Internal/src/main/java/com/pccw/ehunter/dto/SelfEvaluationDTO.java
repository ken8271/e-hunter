package com.pccw.ehunter.dto;

public class SelfEvaluationDTO extends BaseDTO{
	private static final long serialVersionUID = 1L;
	
	private String resumeID;
	private String content;

	public String getResumeID() {
		return resumeID;
	}

	public void setResumeID(String resumeID) {
		this.resumeID = resumeID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
