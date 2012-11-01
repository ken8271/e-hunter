package com.pccw.ehunter.service;

import com.pccw.ehunter.dto.UploadedCurriculumVitaeDTO;

public interface CurriculumVitaeUploadService {
	public void saveUploadedCurriculumVitae(UploadedCurriculumVitaeDTO dto);
	public UploadedCurriculumVitaeDTO getUploadedCurriculumVitaeByID(String id);
	public void deleteCurriculumVitae(UploadedCurriculumVitaeDTO dto);
}
