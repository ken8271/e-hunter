package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.SubjectCategoryDTO;
import com.pccw.ehunter.dto.SubjectDTO;

public interface SubjectCommonService {
	public List<SubjectCategoryDTO> getAllSubjectTypes();
	public List<SubjectDTO> getSubjectsByType(String typeCode);
	public SubjectDTO getSubjectByCode(String code);
}
