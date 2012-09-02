package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.CityDTO;
import com.pccw.ehunter.dto.ProvinceDTO;

public interface RegionCommonService {
	public List<ProvinceDTO> getProvinces();
	public List<CityDTO> getCitiesByProvinceCode(String code);
	public CityDTO getCityByCode(String code);
}
