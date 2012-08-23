package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.Resume;
import com.pccw.ehunter.dto.ResumeDTO;

public class ResumeConvertor {
	
	public static ResumeDTO toDto(Resume po){
		if(po == null) return null;
		
		ResumeDTO dto = new ResumeDTO();
		BeanUtils.copyProperties(po, dto);
		
		dto.setSelfEvaluationDto(SelfEvaluationConvertor.toDto(po.getSelfEvaluation()));
		dto.setIntentionDto(JobIntentionConvertor.toDto(po.getIntention()));
		
		dto.setCertDtos(CertConvertor.toDtos(po.getCerts()));
		dto.setEduExpDtos(EducationExperienceConvertor.toDtos(po.getEduExps()));
		dto.setJobExpDtos(JobExperienceConvertor.toDtos(po.getJobExps()));
		dto.setLanguageDtos(LanguageAbilityConvertor.toDtos(po.getLanguages()));
		dto.setPrjExpDtos(ProjectExperienceConvertor.toDtos(po.getPrjExps()));
		dto.setSkillDtos(ProfessionalSkillConvertor.toDtos(po.getSkills()));
		dto.setTrnExpDtos(TrainingExperienceConvertor.toDtos(po.getTrnExps()));
		
		return dto;
	}
	
	public static Resume toPo(ResumeDTO dto){
		if(dto == null) return null;
		
		Resume po = new Resume();
		BeanUtils.copyProperties(dto, po);
		
		po.setSelfEvaluation(SelfEvaluationConvertor.toPo(dto.getSelfEvaluationDto()));
		po.setIntention(JobIntentionConvertor.toPo(dto.getIntentionDto()));
		
		po.setCerts(CertConvertor.toPos(dto.getCertDtos()));
		po.setEduExps(EducationExperienceConvertor.toPos(dto.getEduExpDtos()));
		po.setJobExps(JobExperienceConvertor.toPos(dto.getJobExpDtos()));
		po.setLanguages(LanguageAbilityConvertor.toPos(dto.getLanguageDtos()));
		po.setPrjExps(ProjectExperienceConvertor.toPos(dto.getPrjExpDtos()));
		po.setSkills(ProfessionalSkillConvertor.toPos(dto.getSkillDtos()));
		po.setTrnExps(TrainingExperienceConvertor.toPos(dto.getTrnExpDtos()));
		
		return po;
	}
}
