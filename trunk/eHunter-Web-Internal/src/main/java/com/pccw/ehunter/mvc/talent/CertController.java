package com.pccw.ehunter.mvc.talent;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.pccw.ehunter.dto.CertDTO;
import com.pccw.ehunter.dto.ResumeDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.validator.CertValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_DTO,
	SessionAttributeConstant.TALENT_RESUME_DTO,
	SessionAttributeConstant.TALENT_CERT_DTO
})
public class CertController extends BaseController{
	
	@Autowired
	private CertValidator certValidator;
	
	@RequestMapping("/talent/fillCert.do")
	public ModelAndView fillCert(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/certCreate");
		
		ResumeDTO resumeDto = talentDto.getResumeDto();
		
		mv.addObject(SessionAttributeConstant.TALENT_CERT_DTO , new CertDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		return mv;
	}
	
	@RequestMapping("/talent/addCertActions.do")
	public ModelAndView addCertActions(HttpServletRequest request,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto ,
			@ModelAttribute(SessionAttributeConstant.TALENT_CERT_DTO)CertDTO certDto){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/talent/addCert.do", true));
		
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		mv.addObject(ActionFlag.ACTION_FLAG, actionFlag);
		
		return mv;
	}
	
	@RequestMapping("/talent/addCert.do")
	public ModelAndView addCert(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_CERT_DTO)CertDTO certDto,
			BindingResult errors){
		ModelAndView mv = null;
		
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		if(StringUtils.isEmpty(actionFlag)){
			actionFlag = (String)request.getSession(false).getAttribute(ActionFlag.ACTION_FLAG);
		} else {
			request.getSession(false).setAttribute(ActionFlag.ACTION_FLAG, actionFlag);
		}
		
		if(ActionFlag.COMPLETE.equals(actionFlag) && isNothingInput(certDto)){
			mv = new ModelAndView(new RedirectViewExt("/talent/completeAddCert.do", true));
			mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
			return mv;
		}
		
		certValidator.validate(certDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/certCreate");
			mv.addObject(SessionAttributeConstant.TALENT_CERT_DTO , certDto);
			mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
			mv.addObject("clearField", CommonConstant.NO);
			return mv;
		}
		
		if(ActionFlag.COMPLETE.equals(actionFlag)){
			mv = new ModelAndView(new RedirectViewExt("/talent/completeAddCert.do", true));
			mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
			return mv;
		}
		
		mv = new ModelAndView("talent/certCreate");
		
		List<CertDTO> dtos = resumeDto.getCertDtos();
		dtos.add(certDto);
		
		mv.addObject(SessionAttributeConstant.TALENT_CERT_DTO , new CertDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	private boolean isNothingInput(CertDTO dto){
		boolean isNothingInput = true;
		
		if(!StringUtils.isEmpty(dto.getCertName())){
			isNothingInput = false;
		}
		
		if(dto.getGainedDateDto() != null){
			if(!StringUtils.isEmpty(dto.getGainedDateDto().getYear())
					|| !StringUtils.isEmpty(dto.getGainedDateDto().getMonth())
					|| !StringUtils.isEmpty(dto.getGainedDateDto().getDay())){
				isNothingInput = false;
			}
		}
		
		if(!StringUtils.isEmpty(dto.getDescription())){
			isNothingInput = false;
		}
		return isNothingInput;
	}
	
	@RequestMapping("/talent/completeAddCert.do")
	public ModelAndView completeAddCert(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/resumeCreate");
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
	
	@RequestMapping("/talent/preEditCert.do")
	public ModelAndView preEditCert(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/certAmend");
		String id = request.getParameter("_id");
		request.getSession(false).setAttribute("certId", id);

		List<CertDTO> dtos = resumeDto.getCertDtos();
		CertDTO exp = null;
		
		if(!CollectionUtils.isEmpty(dtos)){
			exp = dtos.get(Integer.parseInt(id));
		}
		
		mv.addObject(SessionAttributeConstant.TALENT_CERT_DTO , exp);
		return mv;
	}
	
	@RequestMapping("/talent/completeEditCert.do")
	public ModelAndView completeEditCert(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.TALENT_CERT_DTO)CertDTO certDto ,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto ,
			BindingResult errors){
		ModelAndView mv = new ModelAndView("talent/certCreate");
		
		certValidator.validate(certDto, errors);

		if(errors.hasErrors()){
			mv = new ModelAndView("talent/certAmend");
			mv.addObject(SessionAttributeConstant.TALENT_CERT_DTO , certDto);
			return mv;
		}
		
		String id = (String)request.getSession(false).getAttribute("certId");
		
		List<CertDTO> dtos = resumeDto.getCertDtos();
		
		if(!CollectionUtils.isEmpty(dtos)){
			dtos.set(Integer.parseInt(id), certDto);
		}
		
		mv.addObject(SessionAttributeConstant.TALENT_CERT_DTO , new CertDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
	
	@RequestMapping("/talent/backToFillCert.do")
	public ModelAndView backToFillCert(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/certCreate");
		
		mv.addObject(SessionAttributeConstant.TALENT_CERT_DTO , new CertDTO());
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO , resumeDto);
		return mv;
	}
	
	@RequestMapping("/talent/deleteCert.do")
	public ModelAndView deleteCert(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_RESUME_DTO)ResumeDTO resumeDto){
		ModelAndView mv = new ModelAndView("talent/certCreate");
		String[] expsList = request.getParameterValues("expsList");
		
		List<CertDTO> dtos = resumeDto.getCertDtos();
		List<CertDTO> removedDtos = new ArrayList<CertDTO>(0);
		
		if(expsList != null && expsList.length != 0 && !CollectionUtils.isEmpty(dtos)){
			for(String id : expsList){
				removedDtos.add(dtos.get(Integer.parseInt(id)));
			}
			
			dtos.removeAll(removedDtos);
		}
		
		resumeDto.setCertDtos(dtos);
		
		mv.addObject(SessionAttributeConstant.TALENT_RESUME_DTO, resumeDto);
		mv.addObject(SessionAttributeConstant.TALENT_CERT_DTO , new CertDTO());
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
}
