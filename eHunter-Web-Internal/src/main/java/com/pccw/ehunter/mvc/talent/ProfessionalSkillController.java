package com.pccw.ehunter.mvc.talent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.validator.ProfessionalSkillValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_DTO ,
	SessionAttributeConstant.TALENT_RESUME_DTO ,
	SessionAttributeConstant.TALENT_PROFESSIONAL_SKILL_DTO
})
public class ProfessionalSkillController extends BaseController{
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@Autowired
	private ProfessionalSkillValidator skillValidator;

}
