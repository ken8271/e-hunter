package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.CustomerCompany;
import com.pccw.ehunter.domain.internal.InternalUser;
import com.pccw.ehunter.domain.internal.PositionDescription;
import com.pccw.ehunter.domain.internal.PositionRequirement;
import com.pccw.ehunter.domain.internal.Project;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.dto.ProjectEnquireDTO;
import com.pccw.ehunter.dto.ProjectPagedCriteria;
import com.pccw.ehunter.utility.StringUtils;

public class ProjectConvertor {
	
	public static ProjectPagedCriteria toPagedCriteria(ProjectEnquireDTO enquireDto){
		ProjectPagedCriteria pagedCriteria = new ProjectPagedCriteria();
		
		if(null == enquireDto){
			return pagedCriteria;
		}
		
		BeanUtils.copyProperties(enquireDto, pagedCriteria);
		
		if(enquireDto.getFromDateDto() != null && !StringUtils.isEmpty(enquireDto.getFromDateDto().getDay())){
			pagedCriteria.setFromDate(SimpleDateConvertor.toDate(enquireDto.getFromDateDto()));
		}
		
		if(enquireDto.getToDateDto() != null && !StringUtils.isEmpty(enquireDto.getToDateDto().getDay())){
			pagedCriteria.setToDate(SimpleDateConvertor.toDate(enquireDto.getToDateDto()));
		}
		
		if(enquireDto.getJmesaDto() != null && enquireDto.getJmesaDto().getRowSelect() != null){
			pagedCriteria.getPageFilter().setRowStart(enquireDto.getJmesaDto().getRowSelect().getRowStart());
			pagedCriteria.getPageFilter().setRowEnd(enquireDto.getJmesaDto().getRowSelect().getRowEnd());
		}
		
		return pagedCriteria;
	}
	
	public static ProjectDTO toDto(Project po){
		if(po == null) return null;
		
		ProjectDTO dto = new ProjectDTO();
		BeanUtils.copyProperties(po, dto);
		
		dto.setCustomerDto(CustomerConvertor.toDto(po.getCustomer()));
		dto.setSystemCustRefNum(dto.getCustomerDto().getSystemCustRefNum());
		
		dto.setAdviserDto(InternalUserConvertor.toDto(po.getAdviser()));
		
		dto.setPostDescDto(PositionDescriptionConvertor.toDto(po.getPostDescription()));
		dto.setPostRequireDto(PositionRequirementConvertor.toDto(po.getPostRequirement()));
		
		return dto;
	}
	
	public static Project toPo(ProjectDTO dto , String transactionIndicator){
		if(dto==null) return null;
		
		Project po = new Project();
		BeanUtils.copyProperties(dto, po);
		
		CustomerCompany cc = new CustomerCompany();
		cc.setSystemCustRefNum(dto.getSystemCustRefNum());
		po.setCustomer(cc);
		
		InternalUser iu = new InternalUser();
		iu.setUserRecId(dto.getAdviserDto().getUserRecId());
		po.setAdviser(iu);
		
		PositionDescription pd = PositionDescriptionConvertor.toPo(dto.getPostDescDto(), transactionIndicator);
		pd.setProject(po);
		po.setPostDescription(pd);
		
		PositionRequirement pr = PositionRequirementConvertor.toPo(dto.getPostRequireDto());
		pr.setProject(po);
		po.setPostRequirement(pr);
		
		return po;
	}
}
