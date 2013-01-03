package com.pccw.ehunter.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.service.TalentCommonService;
import com.pccw.ehunter.utility.StringUtils;

@Component("talentBaseInfoValidator")
public class TalentBaseInfoValidator extends AbstractValidator{
	
	@Autowired
	private TalentCommonService talentCommonService;

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return TalentDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		TalentDTO dto = (TalentDTO)obj;
		
		validateRequired(errors, "talentSrc", dto.getTalentSrc(), "人才来源");
		
		validateRequired(errors, "cnName", dto.getCnName(), "中文名");
		validateStringLength(errors, "cnName", dto.getCnName(), "中文名", 30);
		
		validateStringLength(errors, "enName", dto.getEnName(), "英文名", 30);
		
		validateRequired(errors, "gender", dto.getGender(), "人才性别");

		//#issue 2 2012-11-27
		validateDate(errors, "birthDateDto.day", dto.getBirthDateDto(), "出生日期");
		
		validateStringLength(errors, "nativePlace", dto.getNativePlace(), "籍贯", 30);
		
		validateStringLength(errors, "onceLivePlace", dto.getOnceLivePlace(), "曾居地", 300);
		validateStringLength(errors, "nowLivePlace", dto.getNowLivePlace(), "现居地", 300);
		
		validateOnlyNumberic(errors, "homeNumberDto.phoneNumber", dto.getHomeNumberDto().getRegionCode(), "家庭电话");
		validateOnlyNumberic(errors, "homeNumberDto.phoneNumber", dto.getHomeNumberDto().getPhoneNumber(), "家庭电话");
		
		validateOnlyNumberic(errors, "companyNumberDto.phoneNumber", dto.getCompanyNumberDto().getRegionCode(), "公司电话");
		validateOnlyNumberic(errors, "companyNumberDto.phoneNumber", dto.getCompanyNumberDto().getPhoneNumber(), "公司电话");
		
		validateRequired(errors, "mobilePhoneDto1.phoneNumber", dto.getMobilePhoneDto1().getPhoneNumber(), "联系人手机");
		validateOnlyNumberic(errors, "mobilePhoneDto1.phoneNumber",  dto.getMobilePhoneDto1().getPhoneNumber(), "联系人手机");
		validateStringLength(errors, "mobilePhoneDto1.phoneNumber",  dto.getMobilePhoneDto1().getPhoneNumber(), "联系人手机", 11);
		
		validateOnlyNumberic(errors, "mobilePhoneDto2.phoneNumber",  dto.getMobilePhoneDto2().getPhoneNumber(), "联系人手机");
		validateStringLength(errors, "mobilePhoneDto2.phoneNumber",  dto.getMobilePhoneDto2().getPhoneNumber(), "联系人手机", 11);
		
		if(dto.getMobilePhoneDto1() != null && !StringUtils.isEmpty(dto.getMobilePhoneDto1().getPhoneNumber())){
			if(!isValidPhoneNumber(dto, dto.getMobilePhoneDto1().getPhoneNumber())){
				errors.rejectValue("mobilePhoneDto1.phoneNumber", "EHT-E-0006", new String[]{"手机号码"}, "The phone number has been regited [EHT-E-0006]");
			}
		}
		
		if(dto.getMobilePhoneDto2() != null && !StringUtils.isEmpty(dto.getMobilePhoneDto2().getPhoneNumber())){
			if(!isValidPhoneNumber(dto, dto.getMobilePhoneDto2().getPhoneNumber())){
				errors.rejectValue("mobilePhoneDto2.phoneNumber", "EHT-E-0006", new String[]{"手机号码"}, "The phone number has been regited [EHT-E-0006]");
			}
		}
		
		validateStringLength(errors, "QQ", dto.getQQ(), "QQ", 15);
		validateOnlyNumberic(errors, "QQ", dto.getQQ(), "QQ");
		validateStringLength(errors, "msn", dto.getMsn(), "MSN", 50);
		
		validateRequired(errors, "email", dto.getEmail(), "邮箱");
		validateEmail(errors, "email", dto.getEmail(), "邮箱");
		
		if(!StringUtils.isEmpty(dto.getEmail())){
			if(!isValidEmail(dto, dto.getEmail())){
				errors.rejectValue("email", "EHT-E-0006", new String[]{"邮箱"}, "The email has been regited [EHT-E-0006]");
			}
		}
		
		validateStringLength(errors, "homeAddress", dto.getHomeAddress(), "家庭地址", 300);
	}
	
	private boolean isValidPhoneNumber(TalentDTO target , String phone){
		boolean isValid = true;
		List<TalentDTO> tlnts = talentCommonService.getTalentsByPhoneNumber(phone);
		
		if(!CollectionUtils.isEmpty(tlnts)){
			for(TalentDTO dto : tlnts){
				if(!dto.getTalentID().equals(target.getTalentID())){
					isValid = false;
				}
			}
		}
		
		return isValid;
	}
	
	private boolean isValidEmail(TalentDTO target , String email){
		boolean isValid = true;
		List<TalentDTO> tlnts = talentCommonService.getTalentsByEmail(email);
		
		if(!CollectionUtils.isEmpty(tlnts)){
			for(TalentDTO dto : tlnts){
				if(!dto.getTalentID().equals(target.getTalentID())){
					isValid = false;
				}
			}
		}
		
		return isValid;
	}

	public void validateCreateableForBatchUpload(TalentDTO target, Errors errors) {
		validateRequired(errors, "cnName", target.getCnName(), "中文名");
		validateStringLength(errors, "cnName", target.getCnName(), "中文名", 30);
		
		validateStringLength(errors, "enName", target.getEnName(), "英文名", 30);
		
		if(target.getMobilePhoneDto1() != null && !StringUtils.isEmpty(target.getMobilePhoneDto1().getPhoneNumber())){
			if(!isValidPhoneNumber(target, target.getMobilePhoneDto1().getPhoneNumber())){
				errors.rejectValue("mobilePhoneDto1.phoneNumber", "EHT-E-0006", new String[]{"手机号码"}, "The phone number has been regited [EHT-E-0006]");
			}
		}
	}
}
