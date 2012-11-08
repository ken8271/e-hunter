package com.pccw.ehunter.dto;

import java.io.Serializable;

public class TransactionLogEnquireDTO implements Serializable{
	private static final long serialVersionUID = -6552393704053411757L;
	
	private SimpleDateTimeDTO fromDto;
	private SimpleDateTimeDTO toDto;
	private String module;
	private String user;

	private JmesaCheckBoxDTO jmesaDto;

	public SimpleDateTimeDTO getFromDto() {
		return fromDto;
	}

	public void setFromDto(SimpleDateTimeDTO fromDto) {
		this.fromDto = fromDto;
	}

	public SimpleDateTimeDTO getToDto() {
		return toDto;
	}

	public void setToDto(SimpleDateTimeDTO toDto) {
		this.toDto = toDto;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public JmesaCheckBoxDTO getJmesaDto() {
		return jmesaDto;
	}

	public void setJmesaDto(JmesaCheckBoxDTO jmesaDto) {
		this.jmesaDto = jmesaDto;
	}

}
