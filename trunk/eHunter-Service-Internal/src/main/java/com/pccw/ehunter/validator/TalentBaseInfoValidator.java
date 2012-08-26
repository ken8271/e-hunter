package com.pccw.ehunter.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.service.TalentRegistrationService;

@Component("talentBaseInfoValidator")
public class TalentBaseInfoValidator extends AbstractValidator{
	
	@Autowired
	private TalentRegistrationService talentRegtService;

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
		
		validateRequired(errors, "maritalStatus", dto.getMaritalStatus(), "婚姻状况");
		
		validateRequired(errors, "birthDateDto.day", dto.getBirthDateDto().getDay(), "出生日期");
		
		validateRequired(errors, "nativePlace", dto.getNativePlace(), "籍贯");
		validateStringLength(errors, "nativePlace", dto.getNativePlace(), "籍贯", 30);
		
		validateStringLength(errors, "onceLivePlace", dto.getOnceLivePlace(), "曾居地", 300);
		validateStringLength(errors, "nowLivePlace", dto.getNowLivePlace(), "现居地", 300);
		
		validateRequired(errors, "highestDegree", dto.getHighestDegree(), "最高学历");
		
		validateOnlyNumberic(errors, "homeNumberDto.phoneNumber", dto.getHomeNumberDto().getRegionCode(), "家庭电话");
		validateOnlyNumberic(errors, "homeNumberDto.phoneNumber", dto.getHomeNumberDto().getPhoneNumber(), "家庭电话");
		
		validateOnlyNumberic(errors, "companyNumberDto.phoneNumber", dto.getCompanyNumberDto().getRegionCode(), "公司电话");
		validateOnlyNumberic(errors, "companyNumberDto.phoneNumber", dto.getCompanyNumberDto().getPhoneNumber(), "公司电话");
		
		validateRequired(errors, "mobilePhoneDto1.phoneNumber", dto.getMobilePhoneDto1().getPhoneNumber(), "联系人手机");
		validateOnlyNumberic(errors, "mobilePhoneDto1.phoneNumber",  dto.getMobilePhoneDto1().getPhoneNumber(), "联系人手机");
		validateStringLength(errors, "mobilePhoneDto1.phoneNumber",  dto.getMobilePhoneDto1().getPhoneNumber(), "联系人手机", 11);
		
		validateOnlyNumberic(errors, "mobilePhoneDto2.phoneNumber",  dto.getMobilePhoneDto2().getPhoneNumber(), "联系人手机");
		validateStringLength(errors, "mobilePhoneDto2.phoneNumber",  dto.getMobilePhoneDto2().getPhoneNumber(), "联系人手机", 11);
		
		int count1 = talentRegtService.getCountByPhoneNumber(dto.getMobilePhoneDto1().getPhoneNumber());
		int count2 = talentRegtService.getCountByPhoneNumber(dto.getMobilePhoneDto2().getPhoneNumber());
		
		if(count1 > 0){
			errors.rejectValue("mobilePhoneDto1.phoneNumber", "EHT-E-0006", new String[]{"手机号码"}, "The phone number has been regited [EHT-E-0006]");
		}
		
		if(count2 > 0){
			errors.rejectValue("mobilePhoneDto2.phoneNumber", "EHT-E-0006", new String[]{"手机号码"}, "The phone number has been regited [EHT-E-0006]");
		}
		
		
		validateRequired(errors, "email", dto.getEmail(), "邮箱");
		validateEmail(errors, "email", dto.getEmail(), "邮箱");
		
		int emailCount = talentRegtService.getCountByEmail(dto.getEmail());
		if(emailCount > 0){
			errors.rejectValue("email", "EHT-E-0006", new String[]{"邮箱"}, "The email has been regited [EHT-E-0006]");
		}
		
		validateStringLength(errors, "homeAddress", dto.getHomeAddress(), "家庭地址", 300);
	}

}
