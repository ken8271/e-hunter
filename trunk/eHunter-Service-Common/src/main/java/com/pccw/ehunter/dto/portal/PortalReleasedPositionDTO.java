package com.pccw.ehunter.dto.portal;

import com.pccw.ehunter.dto.BaseDTO;

public class PortalReleasedPositionDTO extends BaseDTO {
	private static final long serialVersionUID = 7614929109232605900L;

	private String systemRefPosition;
	private String title;
	private String content;
	private String workProvince;
	private String workCity;

	public String getSystemRefPosition() {
		return systemRefPosition;
	}

	public void setSystemRefPosition(String systemRefPosition) {
		this.systemRefPosition = systemRefPosition;
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

	public String getWorkProvince() {
		return workProvince;
	}

	public void setWorkProvince(String workProvince) {
		this.workProvince = workProvince;
	}

	public String getWorkCity() {
		return workCity;
	}

	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}

}
