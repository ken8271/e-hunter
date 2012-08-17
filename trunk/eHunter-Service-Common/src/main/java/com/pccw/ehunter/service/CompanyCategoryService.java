package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.CompanyCategoryDTO;
import com.pccw.ehunter.dto.CompanySizeDTO;

public interface CompanyCategoryService {
	public List<CompanyCategoryDTO> getCompanyCategories();
	public CompanyCategoryDTO getCompanyCategoryByCode(String code);
	
	public List<CompanySizeDTO> getCompanySizes();
	public CompanySizeDTO getCompanySizeByCode(String code);
}
