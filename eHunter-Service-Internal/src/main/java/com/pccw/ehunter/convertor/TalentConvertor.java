package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.internal.EmploymentHistory;
import com.pccw.ehunter.domain.internal.Talent;
import com.pccw.ehunter.dto.EmploymentHistoryDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.TalentEnquireDTO;
import com.pccw.ehunter.dto.TalentPagedCriteria;
import com.pccw.ehunter.utility.StringUtils;

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
		
//		dto.setResumeDtos(ResumeConvertor.toDtos(po.getResumes()));
		dto.setEmploymentHistoryDtos(EmploymentHistoryConvertor.toDtos(po.getEmploymentHistories()));
		dto.setCvDtos(UploadedCurriculumVitaeConvertor.toDtos(po.getCvs()));
		
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
		
//		List<Resume> resumes = ResumeConvertor.toPos(dto.getResumeDtos());
//		if(!CollectionUtils.isEmpty(resumes)){
//			for(Resume resume : resumes){
//				resume.setTalent(po);
//			}
//			po.setResumes(resumes);
//		}
		
		List<EmploymentHistory> histories = EmploymentHistoryConvertor.toPos(dto.getEmploymentHistoryDtos());
		if(!CollectionUtils.isEmpty(histories)){
			for(EmploymentHistory history : histories){
				history.getPk().setTalent(po);
			}
			po.setEmploymentHistories(histories);
		}
		
		return po;
	}
	
	public static TalentPagedCriteria toPagedCriteria(TalentEnquireDTO enquireDto){
		TalentPagedCriteria pagedCriteria = new TalentPagedCriteria();
		
		if(enquireDto == null){
			return pagedCriteria;
		}
		
		BeanUtils.copyProperties(enquireDto, pagedCriteria);
		
		if(enquireDto.getJmesaDto() != null && enquireDto.getJmesaDto().getRowSelect() != null){
			pagedCriteria.getPageFilter().setRowStart(enquireDto.getJmesaDto().getRowSelect().getRowStart());
			pagedCriteria.getPageFilter().setRowEnd(enquireDto.getJmesaDto().getRowSelect().getRowEnd());
		}
		
		return pagedCriteria;
	}
	
	public static List<TalentDTO> toDtos(List<String[]> list){
		if(CollectionUtils.isEmpty(list)) return null;
		
		List<TalentDTO> dtos = new ArrayList<TalentDTO>();
		
		TalentDTO dto = null;
		EmploymentHistoryDTO hst = null;
		for(String[] arr : list){
			if(StringUtils.isEmpty(arr)) continue;
			
			dto = new TalentDTO();
			hst = new EmploymentHistoryDTO();
			for(int i=0 ; i<arr.length ; i++){
				dto.setCnName(StringUtils.isEmpty(arr[0]) ? StringUtils.EMPTY_STRING : arr[0]);
				dto.setEnName(StringUtils.isEmpty(arr[1]) ? StringUtils.EMPTY_STRING : arr[1]);
				dto.setMobilePhoneDto1(MobilePhoneConvertor.toDto(StringUtils.isEmpty(arr[2]) ? StringUtils.EMPTY_STRING : arr[2]));
				hst = new EmploymentHistoryDTO();
				hst.setCompanyName(StringUtils.isEmpty(arr[3]) ? StringUtils.EMPTY_STRING : arr[3]);
				hst.setPositions(StringUtils.isEmpty(arr[4]) ? StringUtils.EMPTY_STRING : arr[4]);
				dto.getEmploymentHistoryDtos().add(hst);
				dto.setNowLivePlace(StringUtils.isEmpty(arr[5]) ? StringUtils.EMPTY_STRING : arr[5]);
			}
			
			dtos.add(dto);
		}
		
		return dtos;
	}
	
}
