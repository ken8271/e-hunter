package cn.org.polaris.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.org.polaris.convertor.ReleasedPositionConvertor;
import cn.org.polaris.dao.CareerManagerDAO;
import cn.org.polaris.dto.PagedCriteria;
import cn.org.polaris.dto.biz.ReleasedPositionDTO;
import cn.org.polaris.service.CareerManagerService;

@Service("careerManagerService")
@Transactional
public class CareerManagerServiceImpl implements CareerManagerService{
	
	@Autowired
	private CareerManagerDAO careerManagerDao;

	@Override
	@Transactional(readOnly=true)
	public int getPositionsCountByCriteria(PagedCriteria pagedCriteria) {
		return careerManagerDao.getPositionsCountByCriteria(pagedCriteria);
	}

	@Override
	@Transactional(readOnly=true)
	public List<ReleasedPositionDTO> getPositionsByCriteria(PagedCriteria pagedCriteria) {
		return ReleasedPositionConvertor.toDtos(careerManagerDao.getPositionsByCriteria(pagedCriteria));
	}

	@Override
	@Transactional(readOnly=true)
	public ReleasedPositionDTO getPositionByID(String id) {
		return ReleasedPositionConvertor.toDto(careerManagerDao.getPositionByID(id));
	}

}
