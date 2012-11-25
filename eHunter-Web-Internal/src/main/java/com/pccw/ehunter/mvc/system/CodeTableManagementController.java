package com.pccw.ehunter.mvc.system;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.limit.Limit;
import org.jmesa.model.PageItems;
import org.jmesa.model.TableModel;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.CodeTableDTO;
import com.pccw.ehunter.dto.PagedCriteria;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.CodeTableService;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.URLUtils;
import com.pccw.ehunter.validator.CodeTableValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.CODE_TABLE_PAGED_CRITERIA_DTO,
	SessionAttributeConstant.CODE_TABLE_DTO
})
public class CodeTableManagementController extends BaseController{
	
	@Autowired
	private CodeTableService codeTableService;
	
	@Autowired
	private CodeTableValidator codetableValidator;

	@RequestMapping("/system/initCodetablesSearch.do")
	public ModelAndView initCodetablesSearch(HttpServletRequest request){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/system/searchCodetables.do", true));
		
		mv.addObject(SessionAttributeConstant.CODE_TABLE_PAGED_CRITERIA_DTO, new PagedCriteria());
		return mv;
	}
	
	@RequestMapping("/system/searchCodetables.do")
	public ModelAndView searchCodetables(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.CODE_TABLE_PAGED_CRITERIA_DTO)PagedCriteria pagedCriteria){
		ModelAndView mv = new ModelAndView("system/codetableManagement");
		
		handlePagedSearch(request , mv , pagedCriteria);
		
		mv.addObject(SessionAttributeConstant.CODE_TABLE_PAGED_CRITERIA_DTO, pagedCriteria);
		return mv;
	}

	private void handlePagedSearch(HttpServletRequest request, ModelAndView mv,final PagedCriteria pagedCriteria) {
		TableModel model = new TableModel("_jmesa_cd_tbl", request);
		model.autoFilterAndSort(false);
		model.setStateAttr("restore");
		
		model.setItems(new PageItems() {
			
			@Override
			public int getTotalRows(Limit limit) {
				return codeTableService.getCodetablesCountByCriteria(pagedCriteria);
			}
			
			@Override
			public Collection<CodeTableDTO> getItems(Limit limit) {
				int rowStart = limit.getRowSelect().getRowStart();
				int rowEnd = limit.getRowSelect().getRowEnd();
				
				pagedCriteria.getPageFilter().setRowStart(rowStart);
				pagedCriteria.getPageFilter().setRowEnd(rowEnd);
				
				return codeTableService.getCodetablesByCriteria(pagedCriteria);
			}
		});
		
		model.setTable(getHtml(request));
		
		mv.addObject(SessionAttributeConstant.LIST_OF_CODE_TABLE, model.render());
	}

	private Table getHtml(final HttpServletRequest request) {
		Table table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.setSortable(false);
		table.setRow(row);
		
		HtmlColumn id = new HtmlColumn("id");
		id.setWidth("15%");
		id.setTitle("代码表编号");
		id.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				CodeTableDTO dto = (CodeTableDTO)item;
				
				StringBuffer viewUrl = new StringBuffer();
				viewUrl.append(request.getContextPath());
				viewUrl.append("/system/viewCodetableDetail.do?_id=" + dto.getId());
				
				HtmlBuilder builder = new HtmlBuilder();
				builder.ahref(URLUtils.getHDIVUrl(request, viewUrl.toString()), dto.getId());
				
				return builder.toString();
			}
		});
		row.addColumn(id);
		
		HtmlColumn name = new HtmlColumn("name");
		name.setWidth("20%");
		name.setTitle("名称");
		name.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				CodeTableDTO dto = (CodeTableDTO)item;
				
				HtmlBuilder builder = new HtmlBuilder();
				builder.ahref(URLUtils.getHDIVUrl(request, request.getContextPath() + dto.getViewUrl()), dto.getName());
				
				return builder.toString();
			}
		});
		row.addColumn(name);
		
		HtmlColumn description = new HtmlColumn("description");
		description.setWidth("50%");
		description.setTitle("描述");
		row.addColumn(description);
		
		HtmlColumn reference = new HtmlColumn("reference");
		reference.setWidth("15%");
		reference.setTitle("数据库表名");
		row.addColumn(reference);
		
		return table;
	}
	
	@RequestMapping("/system/viewCodetableDetail.do")
	public ModelAndView viewCodetableDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("system/viewCodetableDetail");
		
		String id = request.getParameter("_id");
		
		CodeTableDTO dto = codeTableService.getCodetableByID(id);
		
		if(dto == null){
			dto = new CodeTableDTO();
			dto.setId(id);
		}
		
		mv.addObject(SessionAttributeConstant.CODE_TABLE_DTO, dto);
		return mv;
	}
	
	@RequestMapping("/system/preEditCodetable.do")
	public ModelAndView preEditCodetable(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("system/codetableAmend");
		
		String id = request.getParameter("_id");
		
		CodeTableDTO dto = codeTableService.getCodetableByID(id);
		
		if(dto == null){
			dto = new CodeTableDTO();
			dto.setId(id);
		}
		
		mv.addObject(SessionAttributeConstant.CODE_TABLE_DTO, dto);
		return mv;
	}
	
	@RequestMapping("/system/updateCodetable.do")
	public ModelAndView updateCodetable(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.CODE_TABLE_DTO)CodeTableDTO codetableDto , BindingResult errors){
		ModelAndView mv = new ModelAndView("system/viewCodetableDetail");
		
		codetableValidator.validate(codetableDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("system/codetableAmend");
			mv.addObject(SessionAttributeConstant.CODE_TABLE_DTO, codetableDto);
			return mv;
		}
		
		codeTableService.updateCodetable(codetableDto);
		
		mv.addObject(SessionAttributeConstant.CODE_TABLE_DTO, codetableDto);
		
		return mv;
	}
	
	@RequestMapping("/system/listCodes.do")
	public ModelAndView listCodes(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("system/listOfCodes");
		
		return mv;
	}
}
