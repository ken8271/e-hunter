package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.TalentDTO;

@Component("talentBaseInfoValidator")
public class TalentBaseInfoValidator extends AbstractValidator{

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
		
		validateRequired(errors, "email", dto.getEmail(), "邮箱");
		validateEmail(errors, "email", dto.getEmail(), "邮箱");
		
		validateStringLength(errors, "homeAddress", dto.getHomeAddress(), "家庭地址", 300);
	}

}
