package com.pccw.ehunter.mvc.talent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.pccw.ehunter.constant.ModuleIndicator;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.dto.EmploymentHistoryDTO;
import com.pccw.ehunter.dto.IndustryCategoryDTO;
import com.pccw.ehunter.dto.PositionCategoryDTO;
import com.pccw.ehunter.dto.PositionDTO;
import com.pccw.ehunter.dto.SimpleDateDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.validator.EmploymentHistoryValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_DTO,
	SessionAttributeConstant.TALENT_EMPLOYMENT_HISTORY_DTO
})
public class EmploymentHistoryController extends BaseController{
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@Autowired
	private EmploymentHistoryValidator employmentHistoryValidator;
	
	@RequestMapping("/talent/fillEmploymentHistory.do")
	public ModelAndView fillEmploymentHistory(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/employmentHistoryCreate");
		
		List<EmploymentHistoryDTO> dtos = talentDto.getEmploymentHistoryDtos();
		
		if(CollectionUtils.isEmpty(dtos)){
			dtos = new ArrayList<EmploymentHistoryDTO>();
			talentDto.setEmploymentHistoryDtos(dtos);
		}
		
		initEmploymentHistory(request , mv);
		
		mv.addObject(SessionAttributeConstant.TALENT_EMPLOYMENT_HISTORY_DTO, new EmploymentHistoryDTO());
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}

	private void initEmploymentHistory(HttpServletRequest request,ModelAndView mv) {
		List<IndustryCategoryDTO> industryCategories = codeTableHelper.getIndustryCategories(request);
		List<PositionCategoryDTO> positionCategories = codeTableHelper.getPositionCategories(request);
		
		mv.addObject(WebConstant.LIST_OF_INDUSTRY_CATEGORY, industryCategories);
		mv.addObject(WebConstant.LIST_OF_POSITION_CATEGORY, positionCategories);
	}
	
	@RequestMapping("/talent/addEmploymentHistoryActions.do")
	public ModelAndView addEmploymentHistoryActions(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_EMPLOYMENT_HISTORY_DTO)EmploymentHistoryDTO employmentHistoryDto){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/talent/addEmploymentHistory.do", true));
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		mv.addObject(ActionFlag.ACTION_FLAG, actionFlag);
		
		return mv;
	}
	
	private boolean isNothingInput(EmploymentHistoryDTO dto){
		boolean isNothingInput = true;
		
		if(dto.getBeginTimeDto() != null ){
			if(!StringUtils.isEmpty(dto.getBeginTimeDto().getYear())
					|| !StringUtils.isEmpty(dto.getBeginTimeDto().getMonth())){
				isNothingInput = false;
			}
		}
		
		if(dto.getEndTimeDto() != null ){
			if(!StringUtils.isEmpty(dto.getEndTimeDto().getYear())
					|| !StringUtils.isEmpty(dto.getEndTimeDto().getMonth())){
				isNothingInput = false;
			}
		}
		
		if(!StringUtils.isEmpty(dto.getCompanyName())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(dto.getIndustry())){
			isNothingInput = false;
		}
		
		if(!StringUtils.isEmpty(dto.getPositions())){
			isNothingInput = false;
		}
		return isNothingInput;
	}
	
	@RequestMapping("/talent/addEmploymentHistory.do")
	public ModelAndView addEmploymentHistory(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_EMPLOYMENT_HISTORY_DTO)EmploymentHistoryDTO employmentHistoryDto,
			BindingResult errors){
		ModelAndView mv = null;
		
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		if(StringUtils.isEmpty(actionFlag)){
			actionFlag = (String)request.getSession(false).getAttribute(ActionFlag.ACTION_FLAG);
		}else {
			request.getSession(false).setAttribute(ActionFlag.ACTION_FLAG, actionFlag);
		}
		
		if(ActionFlag.COMPLETE.equals(actionFlag) && isNothingInput(employmentHistoryDto)){
			mv = new ModelAndView(new RedirectViewExt("/talent/saveEmploymentHistory.do", true));
			mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
			return mv;
		}
		
		List<PositionDTO> positionDtos = new ArrayList<PositionDTO>();
		if(!StringUtils.isEmpty(employmentHistoryDto.getPositions())){
			String[] positions = StringUtils.tokenize(employmentHistoryDto.getPositions(), CommonConstant.COMMA);
			if(positions != null && positions.length != 0){
				for(String code : positions){
					positionDtos.add(codeTableHelper.getPositionByCode(code));
				}
			}
		}
		employmentHistoryDto.setPositionDtos(positionDtos);
		
		employmentHistoryValidator.validateEmploymentHistory(employmentHistoryDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/employmentHistoryCreate");
			mv.addObject(SessionAttributeConstant.TALENT_EMPLOYMENT_HISTORY_DTO, employmentHistoryDto);
			mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
			mv.addObject("clearField", CommonConstant.NO);
			return mv;
		}
		
		if(ActionFlag.COMPLETE.equals(actionFlag)){
			mv = new ModelAndView(new RedirectViewExt("/talent/saveEmploymentHistory.do", true));
			mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
			return mv;
		}
		
		mv = new ModelAndView("talent/employmentHistoryCreate");
		
		employmentHistoryDto.setIndustryCategoryDto(codeTableHelper.getIndustryCategoryByCode(request, employmentHistoryDto.getIndustryCategory()));
		employmentHistoryDto.setIndustryDto(codeTableHelper.getIndustryByCode(employmentHistoryDto.getIndustry()));
		
		List<EmploymentHistoryDTO> empHistoryDtos = talentDto.getEmploymentHistoryDtos();
		empHistoryDtos.add(employmentHistoryDto);
		
		Collections.sort(empHistoryDtos, new Comparator<EmploymentHistoryDTO>() {
			@Override
			public int compare(EmploymentHistoryDTO history0,EmploymentHistoryDTO history1) {
				
				int result = compareSimpleDate(history0.getBeginTimeDto(), history1.getBeginTimeDto());
				if(result == 0){
					if(history0.getEndTimeDto() != null && StringUtils.isEmpty(history0.getEndTimeDto().getYear())){
						return -1;
					}
					
					if(history1.getEndTimeDto() != null && StringUtils.isEmpty(history1.getEndTimeDto().getYear())){
						return 1;
					}
					return -compareSimpleDate(history0.getEndTimeDto(), history1.getEndTimeDto());
				}else {
					return -result;
				}
			}
		});
		
		mv.addObject(SessionAttributeConstant.TALENT_EMPLOYMENT_HISTORY_DTO, new EmploymentHistoryDTO());
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv ;
	}
	
	private int compareSimpleDate(SimpleDateDTO dto0 , SimpleDateDTO dto1){
		if(dto0.getYear().equals(dto1.getYear())){
			return dto0.getMonth().compareTo(dto1.getMonth());
		}else {
			return dto0.getYear().compareTo(dto1.getYear());
		}
	}
	
	@RequestMapping("/talent/saveEmploymentHistory.do")
	public ModelAndView saveEmploymentHistory(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/talent/fillTalentResume.do", true));
		mv.addObject(ModuleIndicator.MODULE, ModuleIndicator.TALENT_REGISTRATION);
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
	
	@RequestMapping("/talent/preEditEmploymentHistory.do")
	public ModelAndView preEditEmploymentHistory(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("talent/employmentHistoryAmend");
		String id = request.getParameter("_id");
		request.getSession(false).setAttribute("historyID", id);

		List<EmploymentHistoryDTO> dtos = talentDto.getEmploymentHistoryDtos();
		EmploymentHistoryDTO hst = null;
		
		if(!CollectionUtils.isEmpty(dtos)){
			hst = dtos.get(Integer.parseInt(id));
		}
		
		initEmploymentHistory(request, mv);
		mv.addObject(SessionAttributeConstant.TALENT_EMPLOYMENT_HISTORY_DTO, hst);
		return mv;
	}
	
	@RequestMapping("/talent/updateEmploymentHistory.do")
	public ModelAndView updateEmploymentHistory(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto ,
			@ModelAttribute(SessionAttributeConstant.TALENT_EMPLOYMENT_HISTORY_DTO)EmploymentHistoryDTO employmentHistoryDto ,
			BindingResult errors){
		ModelAndView mv = new ModelAndView("talent/employmentHistoryCreate");

		List<PositionDTO> positionDtos = new ArrayList<PositionDTO>();
		if(!StringUtils.isEmpty(employmentHistoryDto.getPositions())){
			String[] positions = StringUtils.tokenize(employmentHistoryDto.getPositions(), CommonConstant.COMMA);
			if(positions != null && positions.length != 0){
				for(String code : positions){
					positionDtos.add(codeTableHelper.getPositionByCode(code));
				}
			}
		}
		employmentHistoryDto.setPositionDtos(positionDtos);
		
		employmentHistoryValidator.validateEmploymentHistory(employmentHistoryDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("talent/employmentHistoryAmend");
			mv.addObject(SessionAttributeConstant.TALENT_EMPLOYMENT_HISTORY_DTO,employmentHistoryDto);
			return mv;
		}
		
		String id = (String)request.getSession(false).getAttribute("historyID");
		
		employmentHistoryDto.setIndustryCategoryDto(codeTableHelper.getIndustryCategoryByCode(request, employmentHistoryDto.getIndustryCategory()));
		employmentHistoryDto.setIndustryDto(codeTableHelper.getIndustryByCode(employmentHistoryDto.getIndustry()));
		
		List<EmploymentHistoryDTO> dtos = talentDto.getEmploymentHistoryDtos();
		
		if(!CollectionUtils.isEmpty(dtos)){
			dtos.set(Integer.parseInt(id), employmentHistoryDto);
		}
		
		Collections.sort(dtos, new Comparator<EmploymentHistoryDTO>() {
			@Override
			public int compare(EmploymentHistoryDTO history0,EmploymentHistoryDTO history1) {
				
				int result = compareSimpleDate(history0.getBeginTimeDto(), history1.getBeginTimeDto());
				if(result == 0){
					if(history0.getEndTimeDto() != null && StringUtils.isEmpty(history0.getEndTimeDto().getYear())){
						return -1;
					}
					
					if(history1.getEndTimeDto() != null && StringUtils.isEmpty(history1.getEndTimeDto().getYear())){
						return 1;
					}
					return -compareSimpleDate(history0.getEndTimeDto(), history1.getEndTimeDto());
				}else {
					return -result;
				}
			}
		});
		
		mv.addObject(SessionAttributeConstant.TALENT_EMPLOYMENT_HISTORY_DTO , new EmploymentHistoryDTO());
		mv.addObject(SessionAttributeConstant.TALENT_DTO , talentDto);
		mv.addObject("clearField", CommonConstant.YES);
		return mv;
	}
}
