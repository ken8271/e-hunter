package cn.org.polaris.validator;

import org.springframework.stereotype.Component;

import cn.org.polaris.dto.biz.InformationDTO;
import cn.org.polaris.error.Errors;

@Component("infoValidator")
public class InformationValidator extends AbstractValidator{

	@Override
	public  Errors doInternalValidate(Object target , Errors errors) {
		InformationDTO dto = (InformationDTO)target;
		
		validateRequired(errors , "category" , dto.getCategory() , "资讯类型");
		validateRequired(errors , "title" , dto.getTitle() , "资讯标题");
		validateRequired(errors , "content" , dto.getContent() , "资讯内容");
		
		validateStringLength(errors , "title" , dto.getTitle() , "资讯标题" , 100);
		validateStringLength(errors , "content" , dto.getContent() , "资讯内容" , 65535);
		
		return errors;
	}
}
