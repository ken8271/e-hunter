package com.pccw.ehunter.mvc.talent;


import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.limit.Limit;
import org.jmesa.model.PageItems;
import org.jmesa.model.TableModel;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.editor.HeaderEditor;
import org.jmesa.view.html.HtmlBuilder;
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
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.convertor.TalentConvertor;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.JmesaCheckBoxDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.TalentEnquireDTO;
import com.pccw.ehunter.dto.TalentPagedCriteria;
import com.pccw.ehunter.dto.TalentSourceDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.TalentCommonService;
import com.pccw.ehunter.service.TalentRegistrationService;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.utility.URLUtils;

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
		
		model.setTable(getHtmlTable(request , enquireDto.getJmesaDto(), tableId));
		
		mv.addObject(SessionAttributeConstant.LIST_OF_TALENT, model.render());
	}

	private Table getHtmlTable(final HttpServletRequest request ,final JmesaCheckBoxDTO jmesaDto , final String tableId) {
		Table table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.setSortable(false);
		table.setRow(row);
		

		HtmlColumn select = new HtmlColumn("select");
		select.setWidth("5%");
		select.setStyle("align:center");
		select.setHeaderEditor(new HeaderEditor() {
			
			@Override
			public Object getValue() {
				HtmlBuilder builder = new HtmlBuilder();
				
				String checkbox = null;
				if(jmesaDto.isSelectAll()){
					checkbox = "<input type=\"checkbox\" style=\"align:left\" onclick=\"javascript:selectAllByTableId('messageBox')\" checked=\"checked\"/>";
				}else {
					checkbox = "<input type=\"checkbox\" style=\"align:left\" onclick=\"javascript:selectAllByTableId('messageBox')\" />";
				}
				
				return builder.append(checkbox).toString();
			}
		});
		select.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				TalentDTO dto = (TalentDTO)item;
				
				StringBuffer checkbox = new StringBuffer();
				if(jmesaDto.isSelectAll() || jmesaDto.isSelected(dto.getTalentID())){	
					checkbox.append("<input name=\"jmesaDto.select\" type=\"checkbox\" checked=\"checked\" value=\"");
				}else {
					checkbox.append("<input name=\"jmesaDto.select\" type=\"checkbox\" value=\"");
				}
				checkbox.append(dto.getTalentID());
				checkbox.append("\" />");
				checkbox.append("<input name=\"jmesaDto.currSelect\" type=\"checkbox\" value=\"");
				checkbox.append(dto.getTalentID());
				checkbox.append("\" checked=\"checked\" style=\"display:none\" />&nbsp;&nbsp;");
				
				return checkbox.toString();
			}
		});
		row.addColumn(select);
		
		HtmlColumn talentId = new HtmlColumn("talentID");
		talentId.setWidth("width:20% nowrap");
		talentId.setTitle("人才编号");
		talentId.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				TalentDTO dto = (TalentDTO)item;
				StringBuffer url = new StringBuffer();
				url.append(request.getContextPath());
				url.append("/talent/viewTalentDetail.do?_id=");
				url.append(dto.getTalentID());
				
				StringBuffer buffer = new StringBuffer();
				
				buffer.append("<a href=\"");
				buffer.append(URLUtils.getHDIVUrl(request, url.toString()));
				buffer.append("\">");
				buffer.append(dto.getTalentID());
				buffer.append("</a>");
				return buffer.toString();
			}
		});
		row.addColumn(talentId);
		
		HtmlColumn name = new HtmlColumn("name");
		name.setWidth("width:20% nowrap");
		name.setTitle("姓名");
		name.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				TalentDTO dto = (TalentDTO)item;
				StringBuffer buffer = new StringBuffer();
				buffer.append(dto.getCnName());
				
				if(!StringUtils.isEmpty(dto.getEnName())){
					buffer.append("&nbsp;(");
					buffer.append(dto.getEnName());
					buffer.append(")");
				}
				return  buffer.toString();
			}
		});
		row.addColumn(name);
		
		HtmlColumn degree = new HtmlColumn("degree");
		degree.setWidth("width:20% nowrap");
		degree.setTitle("最高学历");
		degree.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				TalentDTO dto = (TalentDTO)item;
				
				if(dto.getDegreeDto() != null){
					return dto.getDegreeDto().getDisplayName();
				}else {
					return "";
				}
			}
		});
		row.addColumn(degree);
		
		HtmlColumn place = new HtmlColumn("place");
		place.setWidth("width:32% nowrap");
		place.setTitle("现居地");
		place.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				return ((TalentDTO)item).getNowLivePlace();
			}
		});
		row.addColumn(place);
		
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
