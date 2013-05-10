package cn.org.polaris.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import cn.org.polaris.constant.CabordersConstant;
import cn.org.polaris.constant.DateFormatConstant;
import cn.org.polaris.dto.biz.InformationDTO;
import cn.org.polaris.repo.Information;
import cn.org.polaris.utility.DateUtils;

public class InformationConvertor {

	public static InformationDTO toDto(Information po){
		if(po == null) return null;
		
		InformationDTO dto = new InformationDTO();
		BeanUtils.copyProperties(po, dto);
		
		dto.setCategoryDesc(CabordersConstant.getCategoryDesc(dto.getCategory()));
		dto.setCreateDateStr(DateUtils.formatDateTime(DateFormatConstant.DATE_YYYY_MM_DD, dto.getCreateDateTime()));
		
		return dto;
	}
	
	public static Information toPo(InformationDTO dto){
		if(dto == null) return null;
		
		Information po = new Information();
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
	
	public static List<InformationDTO> toDtos(List<Information> pos){
		List<InformationDTO> dtos = new ArrayList<InformationDTO>();
		
		if(!CollectionUtils.isEmpty(pos)){
			for(Information po : pos){
				dtos.add(toDto(po));
			}
		}
		
		return dtos;
	}
}
