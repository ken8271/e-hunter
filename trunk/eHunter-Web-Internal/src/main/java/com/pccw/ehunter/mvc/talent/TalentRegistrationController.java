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
import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.EducationExperienceDTO;
import com.pccw.ehunter.dto.JobExperienceDTO;
import com.pccw.ehunter.dto.SubjectDTO;
import com.pccw.ehunter.dto.SubjectTypeDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.TalentSourceDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.validator.EducationExperienceValidator;
import com.pccw.ehunter.validator.TalentBaseInfoValidator;

@Controller
@SessionAttributes({SessionAttributeConstant.TALENT_DTO,
	                SessionAttributeConstant.TLENT_EDUCATION_EXPERIENCE_DTO})
public class TalentRegistrationController extends BaseController{
	
	@Autowired
	private TalentBaseInfoValidator talentBaseInfoValidator;

	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@Autowired
	private EducationExperienceValidator eduExpValidator;
	
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
		} else if(ActionFlag.JOB_EXP.equals(actionFlag)){
			return new ModelAndView(new RedirectViewExt("/talent/fillJobExperience.do", true));
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
	
	@RequestMapping("/talent/addEduExpActions.do")
	public ModelAndView addEduExpActions(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto,
			@ModelAttribute(SessionAttributeConstant.TLENT_EDUCATION_EXPERIENCE_DTO)EducationExperienceDTO eduExpDto){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/talent/addEduExp.do", true));
		
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		mv.addObject(ActionFlag.ACTION_FLAG, actionFlag);
		return mv;
	}
	
	@RequestMapping("/talent/addEduExp.do")
	public ModelAndView addEducationExperience(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto,
			@ModelAttribute(SessionAttributeConstant.TLENT_EDUCATION_EXPERIENCE_DTO)EducationExperienceDTO eduExpDto,
			BindingResult errors){
		ModelAndView mv = null;
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		if(!StringUtils.isEmpty(actionFlag)){
			request.getSession(false).setAttribute(ActionFlag.ACTION_FLAG, actionFlag);
		}else {
			actionFlag = (String)request.getSession(false).getAttribute(ActionFlag.ACTION_FLAG);
		}
		
		if(ActionFlag.COMPLETE.equals(actionFlag)){
			boolean isNothingInput = true;
			if(eduExpDto.getFromDateDto() != null){
				if(!StringUtils.isEmpty(eduExpDto.getFromDateDto().getYear())
						|| !StringUtils.isEmpty(eduExpDto.getFromDateDto().getMonth())
						|| !StringUtils.isEmpty(eduExpDto.getFromDateDto().getDay())){
					isNothingInput = false;
				}
			}
			
			if(eduExpDto.getToDateDto() != null){
				if(!StringUtils.isEmpty(eduExpDto.getToDateDto().getYear())
						|| !StringUtils.isEmpty(eduExpDto.getToDateDto().getMonth())
						|| !StringUtils.isEmpty(eduExpDto.getToDateDto().getDay())){
					isNothingInput = false;
				}
			}
			
			if(!StringUtils.isEmpty(eduExpDto.getSchool())){
				isNothingInput = false;
			}
			
			if(!StringUtils.isEmpty(eduExpDto.getMajorType())){
				isNothingInput = false;
			}
			
			if(!StringUtils.isEmpty(eduExpDto.getMajor())){
				isNothingInput = false;
			}
			
			if(!StringUtils.isEmpty(eduExpDto.getDegree())){
				isNothingInput = false;
			}
			
			if(isNothingInput){
				mv = new ModelAndView(new RedirectViewExt("/talent/completeAddEduExp.do", true));
				mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
				return mv;
			}
		}
		
		eduExpValidator.validate(eduExpDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/fillEduExp");
			mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
			mv.addObject(SessionAttributeConstant.TLENT_EDUCATION_EXPERIENCE_DTO, eduExpDto);
			mv.addObject("clearField", CommonConstant.NO);
			return mv;
		}
		
		if(ActionFlag.COMPLETE.equals(actionFlag)){
			mv = new ModelAndView(new RedirectViewExt("/talent/completeAddEduExp.do", true));
			mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
			return mv;
		}
		
		mv = new ModelAndView("talent/fillEduExp");
		
		SubjectDTO subject = codeTableHelper.getSubjectByCode(eduExpDto.getMajor());
		eduExpDto.setMajorDto(subject);
		
		DegreeDTO degree = codeTableHelper.getDegreeByCode(request, eduExpDto.getDegree());
		eduExpDto.setDegreeDto(degree);
		
		List<EducationExperienceDTO> eduExps = talentDto.getEduExpDtos();
		eduExps.add(eduExpDto);
		
		request.getSession(false).removeAttribute(SessionAttributeConstant.TLENT_EDUCATION_EXPERIENCE_DTO);
		eduExpDto = new EducationExperienceDTO();
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		mv.addObject(SessionAttributeConstant.TLENT_EDUCATION_EXPERIENCE_DTO, eduExpDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	@RequestMapping("/talent/completeAddEduExp.do")
	public ModelAndView completeAddEduExp(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/fillTalentBaseInfo");
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		
		return mv;
	}
	
	@RequestMapping("/talent/preEditEduExp.do")
	public ModelAndView preEditEduExp(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/editEduExp");
		String id = request.getParameter("_id");
		request.getSession(false).setAttribute("_education_id", id);
		List<EducationExperienceDTO> dtos = talentDto.getEduExpDtos();
		EducationExperienceDTO exp = null;
		
		if(!CollectionUtils.isEmpty(dtos)){
			exp = dtos.get(Integer.parseInt(id));
		}
		
		mv.addObject(SessionAttributeConstant.TLENT_EDUCATION_EXPERIENCE_DTO, exp);
		return mv;
	}
	
	@RequestMapping("/talent/completeEditEduExp.do")
	public ModelAndView completeEditEduExp(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto,
			@ModelAttribute(SessionAttributeConstant.TLENT_EDUCATION_EXPERIENCE_DTO)EducationExperienceDTO eduExpDto,
			BindingResult errors){
		ModelAndView mv = new ModelAndView("talent/fillEduExp");
		
		eduExpValidator.validate(eduExpDto, errors);
		logger.debug("-------->>> MAJOR : " + eduExpDto.getMajorType() + "/" + eduExpDto.getMajor());
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/editEduExp");
			mv.addObject(SessionAttributeConstant.TLENT_EDUCATION_EXPERIENCE_DTO, eduExpDto);
			return mv;
		}
		
		String id = (String)request.getSession(false).getAttribute("_education_id");
		SubjectDTO subject = codeTableHelper.getSubjectByCode(eduExpDto.getMajor());
		eduExpDto.setMajorDto(subject);
		
		DegreeDTO degree = codeTableHelper.getDegreeByCode(request, eduExpDto.getDegree());
		eduExpDto.setDegreeDto(degree);
		
		List<EducationExperienceDTO> dtos = talentDto.getEduExpDtos();
		if(!CollectionUtils.isEmpty(dtos)){
			dtos.set(Integer.parseInt(id), eduExpDto);
		}
		
		eduExpDto = new EducationExperienceDTO();
		mv.addObject(SessionAttributeConstant.TLENT_EDUCATION_EXPERIENCE_DTO, eduExpDto);
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	@RequestMapping("/talent/deleteEducationExperience.do")
	public ModelAndView deleteEducationExperience(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/fillEduExp");
		String[] expsList = request.getParameterValues("expsList");
		List<EducationExperienceDTO> dtos = talentDto.getEduExpDtos();
		List<EducationExperienceDTO> removeDtos = new ArrayList<EducationExperienceDTO>(0);
		
		if(expsList != null && expsList.length != 0 && !CollectionUtils.isEmpty(dtos)){
			for(String id : expsList){
				removeDtos.add(dtos.get(Integer.parseInt(id)));
			}
			
			dtos.removeAll(removeDtos);
		}
		
		talentDto.setEduExpDtos(dtos);
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		mv.addObject(SessionAttributeConstant.TLENT_EDUCATION_EXPERIENCE_DTO, new EducationExperienceDTO());
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	@RequestMapping("/talent/backToFillEduExp.do")
	public ModelAndView backToFillEduExp(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/fillEduExp");

		initEducationExperience(request , mv);
		
		mv.addObject(SessionAttributeConstant.TLENT_EDUCATION_EXPERIENCE_DTO, new EducationExperienceDTO());
		mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
		return mv;
	}
	
	@RequestMapping("/talent/loadSubjects.do")
	public ModelAndView loadSubjects(HttpServletRequest request , HttpServletResponse response){
		PrintWriter out = null;
		XMLWriter writer = null;
		
		try {
			response.setContentType("text/xml;charset=UTF-8");
			
			String typeCode = request.getParameter("_id");
			List<SubjectDTO> subjects = codeTableHelper.getSubjectsByType(typeCode);
			
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("xml");
			
			if(!CollectionUtils.isEmpty(subjects)){
				for(SubjectDTO dto : subjects){
					Element post = root.addElement("subject");
					post.addElement("label").setText(dto.getDisplayName());
					post.addElement("value").setText(dto.getSubjectCode());
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
	
	//fill job experience 
	@RequestMapping("/talent/fillJobExperience.do")
	public ModelAndView fillJobExperience(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/fillJobExperience");
		
		List<JobExperienceDTO> jobExpDtos = talentDto.getJobExpDtos();
		
		if(CollectionUtils.isEmpty(jobExpDtos)){
			jobExpDtos = new ArrayList<JobExperienceDTO>(0);
			talentDto.setJobExpDtos(jobExpDtos);
		}

		initJobExperience(request , mv);
		
		mv.addObject(SessionAttributeConstant.TALENT_JOB_EXPERIENCE_DTO, new JobExperienceDTO());
		mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
		
		return mv;
	}

	private void initJobExperience(HttpServletRequest request, ModelAndView mv) {
		
	}
}
