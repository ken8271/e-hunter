package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.internal.Cert;
import com.pccw.ehunter.domain.internal.CertPK;
import com.pccw.ehunter.dto.CertDTO;

public class CertConvertor {
	
	public static CertDTO toDto(Cert po){
		if(po == null) return null;
		
		CertDTO dto = new CertDTO();
		
		BeanUtils.copyProperties(po, dto);
		dto.setItemNumber(po.getPk().getItemNumber());
		dto.setGainedDateDto(SimpleDateConvertor.toSimpleDate(po.getGainedDate()));
		
		return dto;
	}
	
	public static Cert toPo(CertDTO dto){
		if(dto == null) return null;
		
		Cert po = new Cert();
		BeanUtils.copyProperties(dto, po);
		
		CertPK pk = new CertPK();
		pk.setItemNumber(dto.getItemNumber());
		po.setPk(pk);
		
		po.setGainedDate(SimpleDateConvertor.toDate(dto.getGainedDateDto()));
		
		return po;
	}
	
	public static List<CertDTO> toDtos(List<Cert> pos){
		if(CollectionUtils.isEmpty(pos)) return null;
		
		List<CertDTO> dtos = new ArrayList<CertDTO>();
		
		for(Cert po : pos){
			dtos.add(toDto(po));
		}
		
		return dtos;
	}
	
	public static List<Cert> toPos(List<CertDTO> dtos){
		if(CollectionUtils.isEmpty(dtos)) return null;
		
		List<Cert> pos = new ArrayList<Cert>();
		
		for(CertDTO dto : dtos){
			pos.add(toPo(dto));
		}
		
		return pos;
	}
}
