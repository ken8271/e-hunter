package com.pccw.ehunter.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.constant.ParameterConstant;
import com.pccw.ehunter.convertor.SimpleDateTimeConvertor;
import com.pccw.ehunter.dto.SystemParameterDTO;
import com.pccw.ehunter.dto.TransactionLogEnquireDTO;
import com.pccw.ehunter.service.SystemParameterService;
import com.pccw.ehunter.utility.DateUtils;
import com.pccw.ehunter.utility.StringUtils;

@Component("txlogEnqireValidator")
public class TransactionLogEnquireValidator extends AbstractValidator{
	
	@Autowired
	private SystemParameterService systemParameterService;

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return TransactionLogEnquireDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TransactionLogEnquireDTO dto = (TransactionLogEnquireDTO)target;
		
		if(dto.getFromDto() != null 
				&& !(StringUtils.isEmpty(dto.getFromDto().getYear())
				&& StringUtils.isEmpty(dto.getFromDto().getMonth())
				&& StringUtils.isEmpty(dto.getFromDto().getDay())
				&& StringUtils.isEmpty(dto.getFromDto().getHour())
				&& StringUtils.isEmpty(dto.getFromDto().getMinute()))){
			validateDateTime(errors, dto.getFromDto().getYear(), dto.getFromDto().getMonth(), dto.getFromDto().getDay(), dto.getFromDto().getHour(), dto.getFromDto().getMinute(), "fromDto.day", "起始时间");
		}
		
		if(dto.getToDto() != null 
				&& !(StringUtils.isEmpty(dto.getToDto().getYear())
				&& StringUtils.isEmpty(dto.getToDto().getMonth())
				&& StringUtils.isEmpty(dto.getToDto().getDay())
				&& StringUtils.isEmpty(dto.getToDto().getHour())
				&& StringUtils.isEmpty(dto.getToDto().getMinute()))){
			validateDateTime(errors, dto.getToDto().getYear(), dto.getToDto().getMonth(), dto.getToDto().getDay(), dto.getToDto().getHour(), dto.getToDto().getMinute(), "toDto.day", "结束时间");
		}

		compareDateTime(errors, "toDto.day", dto.getFromDto(), dto.getToDto(), "起始时间", "结束时间");
		
		int daysBetween = DateUtils.getIntervalDays(SimpleDateTimeConvertor.toDate(dto.getFromDto()), SimpleDateTimeConvertor.toDate(dto.getToDto()));
		int range = getMaxRange();
		if(daysBetween >= 0 && daysBetween > range){
			errors.rejectValue("toDto.day","EHT-E-0015", new String[]{String.valueOf(range)},"The enquiry date range must within " + range + " day(s). Please shorten the range and search again. [EHT-E-0015]"); 
		}
	}

	private int getMaxRange(){ 
		SystemParameterDTO dto = systemParameterService.getSystemParameterByKey(ParameterConstant.KEY_TX_LOG_MAX_RANGE);
		
		if(dto != null){
			return Integer.valueOf(dto.getValue());
		}
		
		return ParameterConstant.DEFAULE_TX_LOG_MAX_RANGE;
	}
}
