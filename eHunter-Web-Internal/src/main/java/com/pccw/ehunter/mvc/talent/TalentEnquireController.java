package com.pccw.ehunter.mvc.talent;


import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.limit.Limit;
import org.jmesa.model.PageItems;
import org.jmesa.model.TableModel;
import org.jmesa.view.component.Table;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.convertor.TalentConvertor;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.TalentEnquireDTO;
import com.pccw.ehunter.dto.TalentPagedCriteria;
import com.pccw.ehunter.dto.TalentSourceDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.TalentCommonService;
import com.pccw.ehunter.service.TalentRegistrationService;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_DTO,
	SessionAttributeConstant.TALENT_ENQUIRE_DTO
})
public class TalentEnquireController extends BaseController{
	
	@Autowired
	private TalentRegistrationService talentRegtService;
	
	@Autowired
	private TalentCommonService talentCommonService;
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@RequestMapping("/talent/initTalentsSearch.do")
	public ModelAndView initTalentsSearch(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/talentEnquiry");
		
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
		TableModel model = new TableModel("_jmesa_tlnts", request);
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
		
		model.setTable(getHtmlTable(request , true));
		
		mv.addObject(SessionAttributeConstant.LIST_OF_TALENT, model.render());
	}

	private Table getHtmlTable(HttpServletRequest request, boolean needCheckbox) {
		Table table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.setSortable(false);
		table.setRow(row);
		
		if(needCheckbox){
			HtmlColumn checkbox = new HtmlColumn();
		}
		HtmlColumn talentId = new HtmlColumn("talentID");
		HtmlColumn name = new HtmlColumn("name");
		HtmlColumn degree = new HtmlColumn("degree");
		HtmlColumn place = new HtmlColumn("place");
		
		return table;
	}

	@RequestMapping("/talent/viewTalentDetail.do")
	public ModelAndView viewTalentDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/viewTalentDetail");
		
		String id = request.getParameter("_id");
		
		TalentDTO talentDto = talentRegtService.getTalentByID(id);
		
		TalentSourceDTO src = codeTableHelper.getTalentSource(request, talentDto.getTalentSrc());
		talentDto.setTalentSrcDto(src);
		
		DegreeDTO degreeDto = codeTableHelper.getDegreeByCode(request, talentDto.getHighestDegree());
		talentDto.setDegreeDto(degreeDto);
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentDto);
		return mv;
	}
}
