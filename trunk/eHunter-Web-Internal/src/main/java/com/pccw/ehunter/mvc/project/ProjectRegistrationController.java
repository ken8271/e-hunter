package com.pccw.ehunter.mvc.project;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.InternalUserDTO;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.SecurityUtils;

@Controller
@SessionAttributes({
	SessionAttributeConstant.PROJECT_DTO
})
public class ProjectRegistrationController extends BaseController{
	
	@RequestMapping("/project/initNewProject.do")
	public ModelAndView initNewProject(HttpServletRequest request){
		return new ModelAndView(new RedirectViewExt("/project/fillProjectInfo.do", true));
	}
	
	@RequestMapping("/project/fillProjectInfo.do")
	public ModelAndView fillProjectInfo(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("project/projectCreate");
		
		initializeProject(mv);
		
		return mv;
	}

	private void initializeProject(ModelAndView mv) {
		InternalUserDTO internalUserDto = (InternalUserDTO)SecurityUtils.getUser();
		
		if(internalUserDto == null){
			internalUserDto = new InternalUserDTO();
		}
		
		ProjectDTO projectDto = new ProjectDTO();
		projectDto.setAdviserDto(internalUserDto);
		projectDto.setCustomerDto(new CustomerDTO());
		
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
	}
}
