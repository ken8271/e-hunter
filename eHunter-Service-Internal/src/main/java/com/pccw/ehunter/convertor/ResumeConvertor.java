package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.internal.Cert;
import com.pccw.ehunter.domain.internal.EducationExperience;
import com.pccw.ehunter.domain.internal.JobExperience;
import com.pccw.ehunter.domain.internal.JobIntention;
import com.pccw.ehunter.domain.internal.LanguageAbility;
import com.pccw.ehunter.domain.internal.ProfessionalSkill;
import com.pccw.ehunter.domain.internal.ProjectExperience;
import com.pccw.ehunter.domain.internal.Resume;
import com.pccw.ehunter.domain.internal.SelfEvaluation;
import com.pccw.ehunter.domain.internal.TrainingExperience;
import com.pccw.ehunter.dto.ResumeDTO;
import com.pccw.ehunter.utility.StringUtils;

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
		
		SelfEvaluation se = SelfEvaluationConvertor.toPo(dto.getSelfEvaluationDto());
		if(se != null && !StringUtils.isEmpty(se.getContent())){
			se.setResume(po);
			po.setSelfEvaluation(se);
		}
		
		JobIntention ji = JobIntentionConvertor.toPo(dto.getIntentionDto());
		if(ji != null && !StringUtils.isEmpty(ji.getEmploymentCategory())){
			ji.setResume(po);
			po.setIntention(ji);
		}
		
		List<Cert> certs = CertConvertor.toPos(dto.getCertDtos());
		if(!CollectionUtils.isEmpty(certs)){
			for(Cert cert : certs){
				cert.getPk().setResume(po);
			}
			po.setCerts(certs);
		}
		
		List<EducationExperience> eduExps = EducationExperienceConvertor.toPos(dto.getEduExpDtos());
		if(!CollectionUtils.isEmpty(eduExps)){
			for(EducationExperience eduExp : eduExps){
				eduExp.getPk().setResume(po);
			}
			po.setEduExps(eduExps);
		}
		
		List<JobExperience> jobExps = JobExperienceConvertor.toPos(dto.getJobExpDtos());
		if(!CollectionUtils.isEmpty(jobExps)){
			for(JobExperience jobExp : jobExps){
				jobExp.getPk().setResume(po);
			}
			po.setJobExps(jobExps);
		}
		
		List<LanguageAbility> languages = LanguageAbilityConvertor.toPos(dto.getLanguageDtos());
		if(!CollectionUtils.isEmpty(languages)){
			for(LanguageAbility language : languages){
				language.getPk().setResume(po);
			}
			po.setLanguages(languages);
		}
		
		List<ProjectExperience> prjExps = ProjectExperienceConvertor.toPos(dto.getPrjExpDtos());
		if(!CollectionUtils.isEmpty(prjExps)){
			for(ProjectExperience prjExp : prjExps){
				prjExp.getPk().setResume(po);
			}
			po.setPrjExps(prjExps);
		}
		
		List<ProfessionalSkill> skills = ProfessionalSkillConvertor.toPos(dto.getSkillDtos());
		if(!CollectionUtils.isEmpty(skills)){
			for(ProfessionalSkill skill : skills){
				skill.getPk().setResume(po);
			}
			po.setSkills(skills);
		}
		
		List<TrainingExperience> trnExps = TrainingExperienceConvertor.toPos(dto.getTrnExpDtos());
		if(!CollectionUtils.isEmpty(trnExps)){
			for(TrainingExperience trnExp : trnExps){
				trnExp.getPk().setResume(po);
			}
			po.setTrnExps(trnExps);
		}
		
		return po;
	}
	
	public static List<ResumeDTO> toDtos(List<Resume> pos){
		if(CollectionUtils.isEmpty(pos)) return null;
		
		List<ResumeDTO> dtos = new ArrayList<ResumeDTO>();
		for(Resume po : pos){
			dtos.add(toDto(po));
		}
		
		return dtos;
	}
	
	public static List<Resume> toPos(List<ResumeDTO> dtos){
		if(CollectionUtils.isEmpty(dtos)) return null;
		
		List<Resume> pos = new ArrayList<Resume>();
		
		for(ResumeDTO dto : dtos){
			pos.add(toPo(dto));
		}
		
		return pos;
	}
}
