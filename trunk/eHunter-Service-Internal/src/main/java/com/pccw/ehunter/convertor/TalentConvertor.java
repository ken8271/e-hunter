package com.pccw.ehunter.convertor;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.internal.Resume;
import com.pccw.ehunter.domain.internal.Talent;
import com.pccw.ehunter.dto.TalentDTO;

public class TalentConvertor {
	
	public static TalentDTO toDto(Talent po){
		if(po == null) return null;
		
		TalentDTO dto = new TalentDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		dto.setBirthDateDto(SimpleDateConvertor.toSimpleDate(po.getBirthDate()));
		dto.setHomeNumberDto(TelePhoneConvertor.toDto(po.getHomeNumber()));
		dto.setCompanyNumberDto(TelePhoneConvertor.toDto(po.getCompanyNumber()));
		dto.setMobilePhoneDto1(MobilePhoneConvertor.toDto(po.getMobilePhone1()));
		dto.setMobilePhoneDto2(MobilePhoneConvertor.toDto(po.getMobilePhone2()));
		
		dto.setResumeDtos(ResumeConvertor.toDtos(po.getResumes()));
		
		return dto;
	}
	
	public static Talent toPo(TalentDTO dto){
		if(dto == null) return null;
		
		Talent po = new Talent();
		
		BeanUtils.copyProperties(dto, po);
		
		po.setBirthDate(SimpleDateConvertor.toDate(dto.getBirthDateDto()));
		po.setHomeNumber(TelePhoneConvertor.toString(dto.getHomeNumberDto()));
		po.setCompanyNumber(TelePhoneConvertor.toString(dto.getCompanyNumberDto()));
		po.setMobilePhone1(MobilePhoneConvertor.toString(dto.getMobilePhoneDto1()));
		po.setMobilePhone2(MobilePhoneConvertor.toString(dto.getMobilePhoneDto2()));
		
		List<Resume> resumes = ResumeConvertor.toPos(dto.getResumeDtos());
		if(!CollectionUtils.isEmpty(resumes)){
			for(Resume resume : resumes){
				resume.setTalent(po);
			}
			po.setResumes(resumes);
		}
		
		return po;
	}
	
}
