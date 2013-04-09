package cn.org.polaris.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import cn.org.polaris.dto.biz.ReleasedPositionDTO;
import cn.org.polaris.repo.ReleasedPosition;

public class ReleasedPositionConvertor {
	public static ReleasedPositionDTO toDto(ReleasedPosition po){
		if(po == null) return null;
		
		ReleasedPositionDTO dto = new ReleasedPositionDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static ReleasedPosition toPo(ReleasedPositionDTO dto){
		if(dto == null) return null;
		
		ReleasedPosition po = new ReleasedPosition();
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
	
	public static List<ReleasedPositionDTO> toDtos(List<ReleasedPosition> pos){
		List<ReleasedPositionDTO> dtos = new ArrayList<ReleasedPositionDTO>();
		
		if(!CollectionUtils.isEmpty(pos)){
			for(ReleasedPosition po : pos){
				dtos.add(toDto(po));
			}
		}
		
		return dtos;
	}
}
