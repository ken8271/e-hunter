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
import com.pccw.ehunter.dto.ResumeDTO;
import com.pccw.ehunter.dto.SubjectCategoryDTO;
import com.pccw.ehunter.dto.SubjectDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.validator.EducationExperienceValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_DTO,
	SessionAttributeConstant.TALENT_RESUME_DTO,
	SessionAttributeConstant.TALENT_EDUCATION_EXPERIENCE_DTO
})
public class EducationExperienceController extends BaseController{
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@Autowired
	private EducationExperienceValidator eduExpValidator;
	
	@RequestMapping("/talent/fillEducationExperience.do")
	public ModelAndView fillEducationExperience(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/educationExperienceCreate");
		
		ResumeDTO resumeDto = talentDto.getResumeDto();
		
		List<EducationExperienceDTO> eduExpDtos = resumeDto.getEduExpDtos();
		
		if(CollectionUtils.isEmpty(eduExpDtos)){
			eduExpDtos = new ArrayList<EducationExperienceDTO>(0);
			resumeDto.setEduExpDtos(eduExpDtos);
		}

		initEducationExperience(request , mv);
		
		mv.addObject(SessionAttributeConstant.TALENT_EDUCATION_EXPERIENCE_DTO, new EducationExperienceDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO , resumeDto);
		
		return mv;
	}
	
	private void initEducationExperience(HttpServletRequest request , ModelAndView mv) {		
		List<SubjectCategoryDTO> subjectTypes = codeTableHelper.getAllSubjectTypes(request);
		List<DegreeDTO> degrees = codeTableHelper.getAllDegrees(request);
		
		mv.addObject(WebConstant.LIST_OF_SUBJECT_TYPE, subjectTypes);
		mv.addObject(WebConstant.LIST_OF_DEGREE, degrees);
	}
	
	@RequestMapping("/talent/addEducationExperienceActions.do")
	public ModelAndView addEduExpActions(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_EDUCATION_EXPERIENCE_DTO)EducationExperienceDTO eduExpDto){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/talent/addEducationExperience.do", true));
		
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		mv.addObject(ActionFlag.ACTION_FLAG, actionFlag);
		return mv;
	}
	
	@RequestMapping("/talent/addEducationExperience.do")
	public ModelAndView addEducationExperience(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_EDUCATION_EXPERIENCE_DTO)EducationExperienceDTO eduExpDto,
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
				mv = new ModelAndView(new RedirectViewExt("/talent/completeAddEducationExperience.do", true));
				mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
				return mv;
			}
		}
		
		eduExpValidator.validate(eduExpDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/educationExperienceCreate");
			mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
			mv.addObject(SessionAttributeConstant.TALENT_EDUCATION_EXPERIENCE_DTO, eduExpDto);
			mv.addObject("clearField", CommonConstant.NO);
			return mv;
		}
		
		if(ActionFlag.COMPLETE.equals(actionFlag)){
			mv = new ModelAndView(new RedirectViewExt("/talent/completeAddEducationExperience.do", true));
			mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
			return mv;
		}
		
		mv = new ModelAndView("talent/educationExperienceCreate");
		
		SubjectDTO subject = codeTableHelper.getSubjectByCode(eduExpDto.getMajor());
		eduExpDto.setMajorDto(subject);
		
		DegreeDTO degree = codeTableHelper.getDegreeByCode(request, eduExpDto.getDegree());
		eduExpDto.setDegreeDto(degree);
		
		List<EducationExperienceDTO> eduExps = resumeDto.getEduExpDtos();
		eduExps.add(eduExpDto);
			
		request.getSession(false).removeAttribute(SessionAttributeConstant.TALENT_EDUCATION_EXPERIENCE_DTO);
		eduExpDto = new EducationExperienceDTO();
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject(SessionAttributeConstant.TALENT_EDUCATION_EXPERIENCE_DTO, eduExpDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	@RequestMapping("/talent/completeAddEducationExperience.do")
	public ModelAndView completeAddEduExp(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
	
	@RequestMapping("/talent/preEditEducationExperience.do")
	public ModelAndView preEditEduExp(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/educationExperienceAmend");
		String id = request.getParameter("_id");
		request.getSession(false).setAttribute("_education_id", id);
		List<EducationExperienceDTO> dtos = resumeDto.getEduExpDtos();
		EducationExperienceDTO exp = null;
		
		if(!CollectionUtils.isEmpty(dtos)){
			exp = dtos.get(Integer.parseInt(id));
		}
		
		mv.addObject(SessionAttributeConstant.TALENT_EDUCATION_EXPERIENCE_DTO, exp);
		return mv;
	}
	
	@RequestMapping("/talent/completeEditEducationExperience.do")
	public ModelAndView completeEditEduExp(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_EDUCATION_EXPERIENCE_DTO)EducationExperienceDTO eduExpDto,
			BindingResult errors){
		ModelAndView mv = new ModelAndView("talent/educationExperienceCreate");
		
		eduExpValidator.validate(eduExpDto, errors);

		if(errors.hasErrors()){
			mv = new ModelAndView("talent/educationExperienceAmend");
			mv.addObject(SessionAttributeConstant.TALENT_EDUCATION_EXPERIENCE_DTO, eduExpDto);
			return mv;
		}
		
		String id = (String)request.getSession(false).getAttribute("_education_id");
		SubjectDTO subject = codeTableHelper.getSubjectByCode(eduExpDto.getMajor());
		eduExpDto.setMajorDto(subject);
		
		DegreeDTO degree = codeTableHelper.getDegreeByCode(request, eduExpDto.getDegree());
		eduExpDto.setDegreeDto(degree);
		
		List<EducationExperienceDTO> dtos = resumeDto.getEduExpDtos();
		if(!CollectionUtils.isEmpty(dtos)){
			dtos.set(Integer.parseInt(id), eduExpDto);
		}
		
		eduExpDto = new EducationExperienceDTO();
		mv.addObject(SessionAttributeConstant.TALENT_EDUCATION_EXPERIENCE_DTO, eduExpDto);
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	@RequestMapping("/talent/deleteEducationExperience.do")
	public ModelAndView deleteEducationExperience(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/educationExperienceCreate");
		String[] expsList = request.getParameterValues("expsList");
		List<EducationExperienceDTO> dtos = resumeDto.getEduExpDtos();
		List<EducationExperienceDTO> removeDtos = new ArrayList<EducationExperienceDTO>(0);
		
		if(expsList != null && expsList.length != 0 && !CollectionUtils.isEmpty(dtos)){
			for(String id : expsList){
				removeDtos.add(dtos.get(Integer.parseInt(id)));
			}
			
			dtos.removeAll(removeDtos);
		}
		
		resumeDto.setEduExpDtos(dtos);
		
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject(SessionAttributeConstant.TALENT_EDUCATION_EXPERIENCE_DTO, new EducationExperienceDTO());
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	@RequestMapping("/talent/backToFillEducationExperience.do")
	public ModelAndView backToFillEduExp(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/educationExperienceCreate");

		initEducationExperience(request , mv);
		
		mv.addObject(SessionAttributeConstant.TALENT_EDUCATION_EXPERIENCE_DTO, new EducationExperienceDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO , resumeDto);
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
}
