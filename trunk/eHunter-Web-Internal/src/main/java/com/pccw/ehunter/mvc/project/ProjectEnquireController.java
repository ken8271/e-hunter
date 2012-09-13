package com.pccw.ehunter.mvc.project;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.ParameterConstant;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.ProjectCommonService;

@Controller
public class ProjectEnquireController extends BaseController{
	
	@Autowired
	private ProjectCommonService projectCommonService;

	@RequestMapping("project/viewProjectDetail.do")
	public ModelAndView viewProjectDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("project/viewProjectDetail");
		
		String id = request.getParameter(ParameterConstant.P_ID);
		
		ProjectDTO projectDto = projectCommonService.getProjectByID(id);
		
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		return mv;
	}
}
