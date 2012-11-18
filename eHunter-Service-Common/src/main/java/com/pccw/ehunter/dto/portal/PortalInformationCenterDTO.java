package com.pccw.ehunter.dto.portal;

import com.pccw.ehunter.dto.BaseDTO;

public class PortalInformationCenterDTO extends BaseDTO {
	private static final long serialVersionUID = -4390373543202203383L;

	private String systemRefInfo;
	private String title;
	private String content;

	public String getSystemRefInfo() {
		return systemRefInfo;
	}

	public void setSystemRefInfo(String systemRefInfo) {
		this.systemRefInfo = systemRefInfo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
