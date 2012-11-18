package com.pccw.ehunter.domain.portal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_INFO_CENTR")
public class PortalInformationCenter extends BaseEntity {
	private static final long serialVersionUID = -7469623480701519208L;

	private String systemRefInfo;
	private String title;
	private String content;

	@Id
	@Column(name = "SYS_REF_INFO")
	public String getSystemRefInfo() {
		return systemRefInfo;
	}

	public void setSystemRefInfo(String systemRefInfo) {
		this.systemRefInfo = systemRefInfo;
	}

	@Column(name="INFO_TITL")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "INFO_CTNT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
