package com.pccw.ehunter.dto;

import java.io.File;
import java.io.Serializable;

public class BatchUploadDTO implements Serializable {
	private static final long serialVersionUID = 8263874477289542462L;

	private File file;
	private JmesaCheckBoxDTO successJmesaDto;
	private JmesaCheckBoxDTO failJmesaDto;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public JmesaCheckBoxDTO getSuccessJmesaDto() {
		return successJmesaDto;
	}

	public void setSuccessJmesaDto(JmesaCheckBoxDTO successJmesaDto) {
		this.successJmesaDto = successJmesaDto;
	}

	public JmesaCheckBoxDTO getFailJmesaDto() {
		return failJmesaDto;
	}

	public void setFailJmesaDto(JmesaCheckBoxDTO failJmesaDto) {
		this.failJmesaDto = failJmesaDto;
	}

}
