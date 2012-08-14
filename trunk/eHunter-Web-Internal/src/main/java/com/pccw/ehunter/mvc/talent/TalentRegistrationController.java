package com.pccw.ehunter.mvc.talent;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.ActionFlag;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.dto.BaseLabelValueDTO;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.EducationExperienceDTO;
import com.pccw.ehunter.dto.SubjectTypeDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.TalentSourceDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.TalentCommonService;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.validator.TalentBaseInfoValidator;

@Controller
@SessionAttributes({SessionAttributeConstant.TALENT_DTO})
public class TalentRegistrationController extends BaseController{
	
	@Autowired
	private TalentCommonService talentCommonService;
	
	@Autowired
	private TalentBaseInfoValidator talentBaseInfoValidator;

	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@RequestMapping("/talent/initAddTalent.do")
	public ModelAndView initAddTalent(HttpServletRequest request){
		return new ModelAndView(new RedirectViewExt("/talent/fillTalentBaseInfo.do", true));
	}
	
	@RequestMapping("/talent/fillTalentBaseInfo.do")
	public ModelAndView fillTalentBaseInfo(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/fillTalentBaseInfo");
		
		initTalent(request , mv);
		
		return mv;
	}

	private void initTalent(HttpServletRequest request , ModelAndView mv) {		
		List<TalentSourceDTO> talentSources = codeTableHelper.getAllTalentSources(request);
		List<DegreeDTO> degrees = codeTableHelper.getAllDegrees(request);
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, new TalentDTO());
		mv.addObject(WebConstant.LIST_OF_TALENT_SOURCE, talentSources);
		mv.addObject(WebConstant.LIST_OF_DEGREE, degrees);
	}
	
	@RequestMapping("/talent/addTalentActions.do")
	public ModelAndView addTalentActions(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		if(!StringUtils.isEmpty(actionFlag)){
			request.getSession(false).setAttribute(ActionFlag.ACTION_FLAG, actionFlag);
		}else {
			actionFlag = (String)request.getSession(false).getAttribute(ActionFlag.ACTION_FLAG);
		}
		
		if(ActionFlag.NEW_EDU_EXP.equals(actionFlag)){
			return new ModelAndView(new RedirectViewExt("/talent/fillEducationExperience.do", true));
		}else if(ActionFlag.SUBMIT.equals(actionFlag)){
			return new ModelAndView(new RedirectViewExt("/talent/saveTalentBaseInfo.do", true));
		}else {
			return new ModelAndView("talent/fillTalentBaseInfo");
		}
	}
	
	@RequestMapping("/talent/saveTalentBaseInfo.do")
	public ModelAndView saveTalentBaseInfo(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto , BindingResult errors){
		ModelAndView mv = new ModelAndView("");
		
		talentBaseInfoValidator.validate(talentDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/fillTalentBaseInfo");
			mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
			return mv;
		}
		return mv;
	}
	
	@RequestMapping("/talent/fillEducationExperience.do")
	public ModelAndView fillEducationExperience(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/fillEduExp");
		
		List<EducationExperienceDTO> eduExpDtos = talentDto.getEduExpDtos();
		
		if(CollectionUtils.isEmpty(eduExpDtos)){
			eduExpDtos = new ArrayList<EducationExperienceDTO>(0);
			talentDto.setEduExpDtos(eduExpDtos);
		}

		initEducationExperience(request , mv);
		
		mv.addObject(SessionAttributeConstant.TLENT_EDUCATION_EXPERIENCE_DTO, new EducationExperienceDTO());
		mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
		
		return mv;
	}

	private void initEducationExperience(HttpServletRequest request , ModelAndView mv) {		
		List<SubjectTypeDTO> subjectTypes = codeTableHelper.getAllSubjectTypes(request);
		List<DegreeDTO> degrees = codeTableHelper.getAllDegrees(request);
		
		mv.addObject(WebConstant.LIST_OF_SUBJECT_TYPE, subjectTypes);
		mv.addObject(WebConstant.LIST_OF_DEGREE, degrees);
	}
	
	@RequestMapping("/talent/addEducationExperience.do")
	public ModelAndView addEducationExperience(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto,
			@ModelAttribute(SessionAttributeConstant.TLENT_EDUCATION_EXPERIENCE_DTO)EducationExperienceDTO eduExpDto){
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		if(!StringUtils.isEmpty(actionFlag)){
			request.getSession(false).setAttribute(ActionFlag.ACTION_FLAG, actionFlag);
		}else {
			actionFlag = (String)request.getSession(false).getAttribute(ActionFlag.ACTION_FLAG);
		}
		
		ModelAndView mv = new ModelAndView("talent/fillEduExp");
		
		List<EducationExperienceDTO> eduExps = talentDto.getEduExpDtos();
		eduExps.add(eduExpDto);
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		mv.addObject(SessionAttributeConstant.TLENT_EDUCATION_EXPERIENCE_DTO, new EducationExperienceDTO());
		return mv;
	}
	
	@RequestMapping("/talent/loadSubjects.do")
	public ModelAndView loadSubjects(HttpServletRequest request , HttpServletResponse response){
		PrintWriter out = null;
		XMLWriter writer = null;
		
		try {
			response.setContentType("text/xml;charset=UTF-8");
			
			String typeCode = request.getParameter("_id");
			List<BaseLabelValueDTO> lvs = talentCommonService.loadSubjectsByType(typeCode);
			
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("xml");
			
			if(!CollectionUtils.isEmpty(lvs)){
				for(BaseLabelValueDTO lv : lvs){
					Element post = root.addElement("subject");
					post.addElement("label").setText(lv.getLabel());
					post.addElement("value").setText(lv.getValue());
				}
			}
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			
			out = response.getWriter();
			writer = new XMLWriter(out, format);
			
			writer.write(doc);
			
			if(writer != null){
				writer.close();
			}
		} catch (Exception e) {
			logger.error(">>>>> Exception Catched(loadSubjects) : " + e.getMessage());
		} finally {
			if(null != out){
				out.close();
			}
		}
		
		return null;
	}
}
