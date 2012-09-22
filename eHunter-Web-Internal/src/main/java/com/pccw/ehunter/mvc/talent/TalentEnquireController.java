package com.pccw.ehunter.mvc.talent;


import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.limit.Limit;
import org.jmesa.model.PageItems;
import org.jmesa.model.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.ActionFlag;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.convertor.TalentConvertor;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.TalentEnquireDTO;
import com.pccw.ehunter.dto.TalentPagedCriteria;
import com.pccw.ehunter.dto.TalentSourceDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.service.TalentCommonService;
import com.pccw.ehunter.service.TalentRegistrationService;
import com.pccw.ehunter.utility.URLUtils;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_DTO,
	SessionAttributeConstant.TALENT_ENQUIRE_DTO
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
				
				return talentCommonService.getTalentsByCriteria(request, pagedCriteria);
			}
		});
		
		model.setTable(getHtmlTable(request , enquireDto.getJmesaDto(), tableId , true , MODULE_TALENT_MGMT));
		
		mv.addObject(SessionAttributeConstant.LIST_OF_TALENT, model.render());
	}

	

	@RequestMapping("/talent/viewTalentDetail.do")
	public ModelAndView viewTalentDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/viewTalentDetail");
		
		String id = request.getParameter("_id");
		String type = request.getParameter("type");
		
		TalentDTO talentDto = talentRegtService.getTalentByID(id , true);
		
		TalentSourceDTO src = codeTableHelper.getTalentSource(request, talentDto.getTalentSrc());
		talentDto.setTalentSrcDto(src);
		
		DegreeDTO degreeDto = codeTableHelper.getDegreeByCode(request, talentDto.getHighestDegree());
		talentDto.setDegreeDto(degreeDto);
		
		if(ActionFlag.CREATE.equals(type)){
			mv.addObject("backUrl", URLUtils.getHDIVUrl(request, request.getContextPath() + "/talent/completeTalentRegistration.do"));
		}else if(ActionFlag.SEARCH.equals(type)){
			mv.addObject("backUrl", URLUtils.getHDIVUrl(request, request.getContextPath() + "/talent/talentsSearch.do"));
		}
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
}
