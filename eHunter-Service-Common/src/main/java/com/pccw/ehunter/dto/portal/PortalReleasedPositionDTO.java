package com.pccw.ehunter.dto.portal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pccw.ehunter.dto.BaseDTO;
import com.pccw.ehunter.dto.CityDTO;
import com.pccw.ehunter.dto.ProvinceDTO;

public class PortalReleasedPositionDTO extends BaseDTO {
	private static final long serialVersionUID = 7614929109232605900L;

	private String systemRefPosition;
	private String title;
	private String content;
	private String workCityStr;
	
	private Map<String, CityDTO> cityDtos = new HashMap<String, CityDTO>();
	private List<ProvinceDTO> provinceDtos = new ArrayList<ProvinceDTO>();

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

	public String getWorkCityStr() {
		return workCityStr;
	}

	public void setWorkCityStr(String workCityStr) {
		this.workCityStr = workCityStr;
	}

	public Map<String, CityDTO> getCityDtos() {
		return cityDtos;
	}

	public void setCityDtos(Map<String, CityDTO> cityDtos) {
		this.cityDtos = cityDtos;
	}

	public List<ProvinceDTO> getProvinceDtos() {
		return provinceDtos;
	}

	public void setProvinceDtos(List<ProvinceDTO> provinceDtos) {
		this.provinceDtos = provinceDtos;
	}

}
