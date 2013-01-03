package com.pccw.ehunter.mvc.talent;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.limit.Limit;
import org.jmesa.model.PageItems;
import org.jmesa.model.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.ModuleIndicator;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.convertor.TalentConvertor;
import com.pccw.ehunter.dto.CandidateDTO;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.EmploymentHistoryDTO;
import com.pccw.ehunter.dto.PositionDTO;
import com.pccw.ehunter.dto.ProjectEnquireDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.TalentEnquireDTO;
import com.pccw.ehunter.dto.TalentPagedCriteria;
import com.pccw.ehunter.dto.TalentSourceDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.service.TalentCommonService;
import com.pccw.ehunter.service.TalentRegistrationService;
import com.pccw.ehunter.utility.StringUtils;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_DTO,
	SessionAttributeConstant.TALENT_ENQUIRE_DTO,
	SessionAttributeConstant.PROJECT_ENQUIRE_DTO
})
public class TalentEnquireController extends BaseCandidateController{
	
	@Autowired
	private TalentRegistrationService talentRegtService;
	
	@Autowired
	private TalentCommonService talentCommonService;
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@RequestMapping("/talent/initTalentsSearch.do")
	public ModelAndView initTalentsSearch(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/talentEnquiry");
		
		mv.addObject(WebConstant.LIST_OF_TALENT_SOURCE, codeTableHelper.getTalentSources(request));
		mv.addObject(SessionAttributeConstant.TALENT_ENQUIRE_DTO, new TalentEnquireDTO());
		return mv;
	}
	
	@RequestMapping("/talent/talentsSearch.do")
	public ModelAndView talentsSearch(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_ENQUIRE_DTO)TalentEnquireDTO enquireDto){
		ModelAndView mv = new ModelAndView("talent/talentEnquiry");
		
		handlePagedSearch(request , mv , enquireDto);
		mv.addObject(SessionAttributeConstant.TALENT_ENQUIRE_DTO, enquireDto);
		return mv;
	}
	
	private void handlePagedSearch(final HttpServletRequest request, ModelAndView mv,final TalentEnquireDTO enquireDto) {
		final String tableId = "_jmesa_tlnts";
		TableModel model = new TableModel(tableId, request);
		model.autoFilterAndSort(false);
		model.setStateAttr("restore");
		
		model.setItems(new PageItems() {
			
			@Override
			public int getTotalRows(Limit limit) {
				return talentCommonService.getTalentsCountByCriteria(TalentConvertor.toPagedCriteria(enquireDto));
			}
			
			@Override
			public Collection<?> getItems(Limit limit) {
				TalentPagedCriteria pagedCriteria = TalentConvertor.toPagedCriteria(enquireDto);
				
				int rowStart = limit.getRowSelect().getRowStart();
				int rowEnd = limit.getRowSelect().getRowEnd();
				
				pagedCriteria.getPageFilter().setRowStart(rowStart);
				pagedCriteria.getPageFilter().setRowEnd(rowEnd);
				
				return talentCommonService.getTalentsByCriteria(pagedCriteria);
			}
		});
		
		model.setTable(getHtmlTable(request , enquireDto.getJmesaDto(), tableId , true , MODULE_TALENT_MGMT));
		
		mv.addObject(SessionAttributeConstant.LIST_OF_TALENT, model.render());
	}

	

	@RequestMapping("/talent/viewTalentDetail.do")
	public ModelAndView viewTalentDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/viewTalentDetail");
		
		String id = request.getParameter("_id");
		String module = request.getParameter(ModuleIndicator.MODULE);
		
		if(StringUtils.isEmpty(module)){
			module = (String)request.getSession(false).getAttribute(ModuleIndicator.MODULE);
		}else {
			request.getSession(false).setAttribute(ModuleIndicator.MODULE, module);
		}
		
		TalentDTO talentDto = talentRegtService.getTalentByID(id , true);
		
		TalentSourceDTO src = codeTableHelper.getTalentSource(request, talentDto.getTalentSrc());
		talentDto.setTalentSrcDto(src);
		
		DegreeDTO degreeDto = codeTableHelper.getDegreeByCode(request, talentDto.getHighestDegree());
		talentDto.setDegreeDto(degreeDto);
		
		List<EmploymentHistoryDTO> historyDtos = talentDto.getEmploymentHistoryDtos();
		if(!CollectionUtils.isEmpty(historyDtos)){
			for(EmploymentHistoryDTO historyDto : historyDtos){
				initEmploymentHistory(request, historyDto);
			}
			EmploymentHistoryDTO dto = historyDtos.get(0);
			if(dto.getEndTimeDto() == null || StringUtils.isEmpty(dto.getEndTimeDto().getYear())){				
				talentDto.setEmploymentHistoryDto(dto);
			}
		}
		
		List<CandidateDTO> cddtDtos = talentCommonService.getParticipatedProjectsByTalentID2(id);
		if(!CollectionUtils.isEmpty(cddtDtos)){
			for(CandidateDTO c : cddtDtos){
				c.setCandidateStatusDto(codeTableHelper.getCandidateStatusByCode(request, c.getCandidateStatus()));
				c.getProjectDto().setStatusDto(codeTableHelper.getProjectStatusByCode(request, c.getProjectDto().getStatus()));
			}
		}

		transactionLogService.logTransaction(ModuleIndicator.TALENT, getMessage("tx.log.talent.view" , new String[]{talentDto.getTalentID()}));
		
		mv.addObject(ModuleIndicator.MODULE, module);
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		mv.addObject(SessionAttributeConstant.LIST_OF_RPOJECTS , cddtDtos);
		mv.addObject(SessionAttributeConstant.PROJECT_ENQUIRE_DTO, new ProjectEnquireDTO());
		return mv;
	}
	
	private void initEmploymentHistory(HttpServletRequest request , EmploymentHistoryDTO historyDto){
		//#7 2013-01-03
		if(!StringUtils.isEmpty(historyDto.getIndustry())){			
			historyDto.setIndustryDto(codeTableHelper.getIndustryByCode(historyDto.getIndustry()));
			historyDto.setIndustryCategory(historyDto.getIndustryDto().getCategoryCode());
			historyDto.setIndustryCategoryDto(codeTableHelper.getIndustryCategoryByCode(request, historyDto.getIndustryCategory()));
		}
		List<PositionDTO> positionDtos = new ArrayList<PositionDTO>();
		if(!StringUtils.isEmpty(historyDto.getPositions())){
			String[] positions = StringUtils.tokenize(historyDto.getPositions(), CommonConstant.COMMA);
			if(positions != null && positions.length != 0){
				for(String code : positions){
					positionDtos.add(codeTableHelper.getPositionByCode(code));
				}
			}
		}
		historyDto.setPositionDtos(positionDtos);
	}
	
	@RequestMapping("/talent/pop/viewTalentDetail.do")
	public ModelAndView viewPopUpTalentDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("common/talentDetail_pop");
		
		String id = request.getParameter("_id");
		String module = request.getParameter(ModuleIndicator.MODULE);
		
		if(StringUtils.isEmpty(module)){
			module = (String)request.getSession(false).getAttribute(ModuleIndicator.MODULE);
		}else {
			request.getSession(false).setAttribute(ModuleIndicator.MODULE, module);
		}
		
		TalentDTO talentDto = talentRegtService.getTalentByID(id , true);
		
		TalentSourceDTO src = codeTableHelper.getTalentSource(request, talentDto.getTalentSrc());
		talentDto.setTalentSrcDto(src);
		
		DegreeDTO degreeDto = codeTableHelper.getDegreeByCode(request, talentDto.getHighestDegree());
		talentDto.setDegreeDto(degreeDto);
		
		List<EmploymentHistoryDTO> historyDtos = talentDto.getEmploymentHistoryDtos();
		if(!CollectionUtils.isEmpty(historyDtos)){
			for(EmploymentHistoryDTO historyDto : historyDtos){
				initEmploymentHistory(request, historyDto);
			}
			EmploymentHistoryDTO dto = historyDtos.get(0);
			if(dto.getEndTimeDto() == null || StringUtils.isEmpty(dto.getEndTimeDto().getYear())){				
				talentDto.setEmploymentHistoryDto(dto);
			}
		}

		transactionLogService.logTransaction(ModuleIndicator.TALENT, getMessage("tx.log.talent.view" , new String[]{talentDto.getTalentID()}));
		
		mv.addObject(ModuleIndicator.MODULE, module);
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		mv.addObject(SessionAttributeConstant.PROJECT_ENQUIRE_DTO, new ProjectEnquireDTO());
		return mv;
	}
}
