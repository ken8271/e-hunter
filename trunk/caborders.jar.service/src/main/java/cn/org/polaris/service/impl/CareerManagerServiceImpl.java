package cn.org.polaris.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.org.polaris.constant.TransactionIndicator;
import cn.org.polaris.convertor.ReleasedPositionConvertor;
import cn.org.polaris.dao.CareerManagerDAO;
import cn.org.polaris.dto.biz.PositionPagedCriteria;
import cn.org.polaris.dto.biz.ReleasedPositionDTO;
import cn.org.polaris.service.CareerManagerService;
import cn.org.polaris.utility.BaseDtoUtility;
import cn.org.polaris.utility.IDGenerator;
import cn.org.polaris.utility.StringUtils;

@Service("careerManagerService")
@Transactional
public class CareerManagerServiceImpl implements CareerManagerService{
	
	@Autowired
	private CareerManagerDAO careerManagerDao;

	@Override
	@Transactional(readOnly=true)
	public int getPositionsCountByCriteria(PositionPagedCriteria pagedCriteria) {
		return careerManagerDao.getPositionsCountByCriteria(pagedCriteria);
	}

	@Override
	@Transactional(readOnly=true)
	public List<ReleasedPositionDTO> getPositionsByCriteria(PositionPagedCriteria pagedCriteria) {
		return ReleasedPositionConvertor.toDtos(careerManagerDao.getPositionsByCriteria(pagedCriteria));
	}

	@Override
	@Transactional(readOnly=true)
	public ReleasedPositionDTO getPositionByID(String id) {
		return ReleasedPositionConvertor.toDto(careerManagerDao.getPositionByID(id));
	}

	@Override
	@Transactional
	public void releasePosition(ReleasedPositionDTO dto) {
		dto.setSystemRefPosition(IDGenerator.generateUUID());
		BaseDtoUtility.setCommonProperties(dto, TransactionIndicator.INSERT);
		careerManagerDao.releasePosition(ReleasedPositionConvertor.toPo(dto));
	}

	@Override
	@Transactional
	public void deletePositionByIDs(String[] ids) {
		if(!StringUtils.isEmpty(ids)){
			for(String id : ids){
				careerManagerDao.deletePositionByID(id);
			}
		}
	}

}
