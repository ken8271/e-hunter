package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.SubjectDTO;

public interface SubjectService {
	public List<SubjectDTO> getSubjectsByType(String typeCode);
}
