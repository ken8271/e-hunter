package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.CityConvertor;
import com.pccw.ehunter.convertor.ProvinceConvertor;
import com.pccw.ehunter.domain.common.City;
import com.pccw.ehunter.domain.common.Province;
import com.pccw.ehunter.dto.CityDTO;
import com.pccw.ehunter.dto.ProvinceDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.RegionCommonService;

@Service("regionCommonService")
public class RegionCommonServiceImpl implements RegionCommonService {
	
	private SimpleHibernateTemplate<Province, String> provinceDao;
	
	private SimpleHibernateTemplate<City, String> cityDao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		provinceDao = new SimpleHibernateTemplate<Province, String>(sessionFactory, Province.class);
		cityDao = new SimpleHibernateTemplate<City, String>(sessionFactory, City.class);
	}

	@Override
	@Transactional(readOnly=true)
	public List<ProvinceDTO> getProvinces() {
		List<Province> provinces = provinceDao.findAllUndeleted("displaySeqNumber");
		List<ProvinceDTO> dtos = new ArrayList<ProvinceDTO>();
		
		if(CollectionUtils.isEmpty(provinces)){
			return dtos;
		}
		
		for(Province po : provinces){
			dtos.add(ProvinceConvertor.toDto(po));
		}
		
		return dtos;
	}

	@Override
	@Transactional(readOnly=true)
	public List<CityDTO> getCitiesByProvinceCode(String code) {
		List<City> citys = cityDao.findUndeletedByProperty("provinceCode", code);
		List<CityDTO> dtos = new ArrayList<CityDTO>();
		
		if(CollectionUtils.isEmpty(citys)){
			return dtos;
		}
		
		for(City city : citys){
			dtos.add(CityConvertor.toDto(city));
		}
		
		return dtos;
	}

	@Override
	@Transactional(readOnly=true)
	public CityDTO getCityByCode(String code) {
		return CityConvertor.toDto(cityDao.findUniqueByProperty("cityCode", code));
	}

}
