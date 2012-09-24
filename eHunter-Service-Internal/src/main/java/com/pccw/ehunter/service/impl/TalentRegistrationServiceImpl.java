package com.pccw.ehunter.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.IDNumberKeyConstant;
import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.convertor.TalentConvertor;
import com.pccw.ehunter.dao.TalentCommonDAO;
import com.pccw.ehunter.dao.TalentRegistrationDAO;
import com.pccw.ehunter.domain.internal.Talent;
import com.pccw.ehunter.dto.EducationExperienceDTO;
import com.pccw.ehunter.dto.JobExperienceDTO;
import com.pccw.ehunter.dto.JobIntentionDTO;
import com.pccw.ehunter.dto.LanguageAbilityDTO;
import com.pccw.ehunter.dto.ProfessionalSkillDTO;
import com.pccw.ehunter.dto.ProjectExperienceDTO;
import com.pccw.ehunter.dto.ResumeDTO;
import com.pccw.ehunter.dto.SelfEvaluationDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.TrainingExperienceDTO;
import com.pccw.ehunter.helper.IDGeneratorImpl;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.TalentRegistrationService;
import com.pccw.ehunter.utility.BaseDtoUtility;
import com.pccw.ehunter.utility.StringUtils;

@Service("talentRegtService")
@Transactional
public class TalentRegistrationServiceImpl implements TalentRegistrationService {
	
	@Autowired
	private TalentRegistrationDAO talentRegtDao;
	
	@Autowired
	private IDGeneratorImpl idGenerator;
	
	@Autowired
	private TalentCommonDAO talentCommonDao;
	
	private SimpleHibernateTemplate<Talent, String> commonDao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		commonDao = new SimpleHibernateTemplate<Talent, String>(sessionFactory, Talent.class);
	}
	
	@Override
	@Transactional(readOnly=true)
	public int getCountByPhoneNumber(String phone) {
		return talentRegtDao.getTalentCountByPhoneNumber(phone);
	}

	@Override
	@Transactional(readOnly=true)
	public int getCountByEmail(String email) {
		return talentRegtDao.getTalentCountByEmail(email);
	}
	
	@Override
	@Transactional
	public void completeTalentRegistration(TalentDTO dto) {
		if(dto != null){
			String talentId = idGenerator.generateID(IDNumberKeyConstant.TALENT_SEQUENCE_KEY, CommonConstant.PREFIX_TALENT_ID, 9);
			dto.setTalentID(talentId);
			BaseDtoUtility.setCommonProperties(dto, TransactionIndicator.INSERT);
			
			talentId = talentId.substring(1, talentId.length());
			if(!CollectionUtils.isEmpty(dto.getResumeDtos())){
				int index = 1;
				for(ResumeDTO resumeDto : dto.getResumeDtos()){
					resumeDto.setItemNumber(index++);
					resumeDto.setResumeID(idGenerator.generateID(IDNumberKeyConstant.TALENT_RESUME_SEQUENCE_KEY	, CommonConstant.PREFIX_RESUME_ID + talentId, 12));
					setCommonPropertiesForInsert(resumeDto);
				}
			}	
		}
		
		Talent talent = TalentConvertor.toPo(dto);
		
		talentRegtDao.saveTalent(talent);
	}

	private void setCommonPropertiesForInsert(ResumeDTO resumeDto) {
		BaseDtoUtility.setCommonProperties(resumeDto, TransactionIndicator.INSERT);
		
		SelfEvaluationDTO se = resumeDto.getSelfEvaluationDto();
		if(se != null){
			se.setResumeID(resumeDto.getResumeID());
			BaseDtoUtility.setCommonProperties(se, TransactionIndicator.INSERT);
		}
		
		JobIntentionDTO ji = resumeDto.getIntentionDto();
		if(ji != null){
			ji.setResumeID(resumeDto.getResumeID());
			BaseDtoUtility.setCommonProperties(ji, TransactionIndicator.INSERT);
		}
		
		int itemNumber = 1;
		if(!CollectionUtils.isEmpty(resumeDto.getEduExpDtos())){
			for(EducationExperienceDTO eduExpDto : resumeDto.getEduExpDtos()){
				eduExpDto.setItemNumber(itemNumber++);
				BaseDtoUtility.setCommonProperties(eduExpDto, TransactionIndicator.INSERT);
			}
			itemNumber = 1;
		}
		
		if(!CollectionUtils.isEmpty(resumeDto.getJobExpDtos())){
			for(JobExperienceDTO jobExpDto : resumeDto.getJobExpDtos()){
				jobExpDto.setItemNumber(itemNumber++);
				BaseDtoUtility.setCommonProperties(jobExpDto, TransactionIndicator.INSERT);
			}
			itemNumber = 1;
		}
		
		if(!CollectionUtils.isEmpty(resumeDto.getLanguageDtos())){
			for(LanguageAbilityDTO language : resumeDto.getLanguageDtos()){
				language.setItemNumber(itemNumber++);
				BaseDtoUtility.setCommonProperties(language, TransactionIndicator.INSERT);
			}
			itemNumber = 1;
		}
		
		if(!CollectionUtils.isEmpty(resumeDto.getPrjExpDtos())){
			for(ProjectExperienceDTO prjExpDto : resumeDto.getPrjExpDtos()){
				prjExpDto.setItemNumber(itemNumber++);
				BaseDtoUtility.setCommonProperties(prjExpDto, TransactionIndicator.INSERT);
			}
			itemNumber = 1;
		}
		
		if(!CollectionUtils.isEmpty(resumeDto.getSkillDtos())){
			for(ProfessionalSkillDTO skillDto : resumeDto.getSkillDtos()){
				skillDto.setItemNumber(itemNumber++);
				BaseDtoUtility.setCommonProperties(skillDto, TransactionIndicator.INSERT);
			}
			itemNumber = 1;
		}
		
		if(!CollectionUtils.isEmpty(resumeDto.getTrnExpDtos())){
			for(TrainingExperienceDTO trnExpDto : resumeDto.getTrnExpDtos()){
				trnExpDto.setItemNumber(itemNumber++);
				BaseDtoUtility.setCommonProperties(trnExpDto, TransactionIndicator.INSERT);
			}
			itemNumber = 1;
		}
	}

	@Override
	@Transactional
	public TalentDTO getTalentByID(String id , boolean byHibernate) {
		if(byHibernate){			
			return TalentConvertor.toDto(commonDao.findUniqueByProperty("talentID", id));
		}else {
			TalentDTO dto = new TalentDTO();
			Object o = talentCommonDao.getTalentByID(id);
			
			if(null != o){
				Object[] os = (Object[])o;
				dto.setTalentID(StringUtils.isEmpty((String)os[0]) ? "" : (String)os[0]);
				dto.setCnName(StringUtils.isEmpty((String)os[1]) ? "" : (String)os[1]);
				dto.setEnName(StringUtils.isEmpty((String)os[2]) ? "" : (String)os[2]);
			}
			
			return dto;
		}
	}

	@Override
	@Transactional
	public void udpateTalent(TalentDTO talentDto) {
		BaseDtoUtility.setCommonProperties(talentDto, TransactionIndicator.UPDATE);
		
		talentRegtDao.updateTalent(TalentConvertor.toPo(talentDto));
	}
}
