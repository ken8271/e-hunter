package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.AnnualLeaveWelfareDTO;
import com.pccw.ehunter.dto.ResidentialWelfareDTO;
import com.pccw.ehunter.dto.SalaryCategoryDTO;
import com.pccw.ehunter.dto.SocietyWelfareDTO;

public interface WelfareCommonService {
	public List<AnnualLeaveWelfareDTO> getAnnualLeaveWelfares();
	public List<SalaryCategoryDTO> getSalaryCategories();
	public List<ResidentialWelfareDTO> getResidentialWelfares();
	public List<SocietyWelfareDTO> getSocietyWelfares();
}
