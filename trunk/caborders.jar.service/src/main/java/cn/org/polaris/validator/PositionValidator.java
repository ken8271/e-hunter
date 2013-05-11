package cn.org.polaris.validator;

import org.springframework.stereotype.Component;

import cn.org.polaris.dto.biz.ReleasedPositionDTO;
import cn.org.polaris.error.Errors;

@Component("positionValidator")
public class PositionValidator extends AbstractValidator{

	@Override
	public Errors doInternalValidate(Object target, Errors errors) {
		ReleasedPositionDTO dto = (ReleasedPositionDTO)target;
		
		validateRequired(errors, "title" , dto.getTitle() , "标题");
		validateRequired(errors , "content" , dto.getContent() , "职位描述");
		
		validateStringLength(errors , "title" , dto.getTitle() , "标题" , 100);
		validateStringLength(errors , "workCity" , dto.getWorkCity() , "工作城市" , 50);
		validateStringLength(errors , "content" , dto.getContent() , "职位描述" , 65535);
		
		return errors;
	}

}
