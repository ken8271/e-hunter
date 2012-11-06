package com.pccw.ehunter.mvc.system;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.PagedCriteria;
import com.pccw.ehunter.dto.SystemParameterDTO;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.SystemParameterService;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.URLUtils;
import com.pccw.ehunter.validator.SystemParameterValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.SYSTEM_PARAMETER_DTO,
	SessionAttributeConstant.SYSTEM_PARAMETER_CRITERIA_DTO
})
public class SystemParameterManagementController extends BaseController{

	@Autowired
	private SystemParameterService systemParameterService;
	
	@Autowired
	private SystemParameterValidator systemParameterValidator;
	
	@RequestMapping("/system/initSystemParametersSearch.do")
	public ModelAndView initSystemParametersSearch(HttpServletRequest request){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/system/listSystemParameters.do", true));
		
		mv.addObject(SessionAttributeConstant.SYSTEM_PARAMETER_CRITERIA_DTO, new PagedCriteria());
		return mv;
	}
	
	@RequestMapping("/system/listSystemParameters.do")
	public ModelAndView listSystemParameters(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.SYSTEM_PARAMETER_CRITERIA_DTO)PagedCriteria pagedCriteria){
		ModelAndView mv = new ModelAndView("system/listOfSystemParameter");
		
		handlePagedSearch(request , mv , pagedCriteria);
		
		mv.addObject(SessionAttributeConstant.SYSTEM_PARAMETER_CRITERIA_DTO, pagedCriteria);
		return mv;
	}

	private void handlePagedSearch(HttpServletRequest request, ModelAndView mv,final PagedCriteria pagedCriteria) {
		TableModel model = new TableModel("_jmesa_system_parameter", request);
		model.autoFilterAndSort(false);
		model.setStateAttr("restore");
		
		model.setItems(new PageItems() {
			
			@Override
			public int getTotalRows(Limit limit) {
				return systemParameterService.getParametersCountByCriteria(pagedCriteria);
			}
			
			@Override
			public Collection<SystemParameterDTO> getItems(Limit limit) {
				int rowStart = limit.getRowSelect().getRowStart();
				int rowEnd = limit.getRowSelect().getRowEnd();
				
				pagedCriteria.getPageFilter().setRowStart(rowStart);
				pagedCriteria.getPageFilter().setRowEnd(rowEnd);
				
				return systemParameterService.getParametersByCriteria(pagedCriteria);
			}
		});
		
		model.setTable(getHtmlTable(request));
		
		mv.addObject(SessionAttributeConstant.LIST_OF_SYSTEM_PARAMETER, model.render());
	}

	private Table getHtmlTable(final HttpServletRequest request) {
		Table table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.setSortable(false);
		table.setRow(row);
		
		HtmlColumn key = new HtmlColumn("parameterKey");
		key.setWidth("20%");
		key.setTitle("参数代号");
		row.addColumn(key);
		
		HtmlColumn type = new HtmlColumn("valueType");
		type.setWidth("15%");
		type.setTitle("参数类型");
		row.addColumn(type);
		
		HtmlColumn value = new HtmlColumn("value");
		value.setWidth("15%");
		value.setTitle("参数值");
		row.addColumn(value);
		
		HtmlColumn description = new HtmlColumn("description");
		description.setWidth("40%");
		description.setTitle("描述");
		row.addColumn(description);
		
		HtmlColumn operator = new HtmlColumn("operator");
		operator.setWidth("10%");
		operator.setHeaderEditor(new HeaderEditor() {
			@Override
			public Object getValue() {
				HtmlBuilder html = new HtmlBuilder();
				html.append(
						"<table width='100%'>"
						  + "<tr>"
						  	+ "<td width='100%' align='center'>" + "操作" + "</td>"
						  + "</tr>"
					  + "</table>");
				return html.toString();
			}
		});
		operator.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				SystemParameterDTO dto = (SystemParameterDTO)item;
				StringBuffer editUrl = new StringBuffer();
				editUrl.append(request.getContextPath());
				editUrl.append("/system/preEditSystemParameter.do?_id=" + dto.getParameterKey());
				
				StringBuffer deleteUrl = new StringBuffer();
				deleteUrl.append(request.getContextPath());
				deleteUrl.append("/system/deleteSystemParameter.do?_id=" + dto.getParameterKey());
				
				HtmlBuilder imgs = new HtmlBuilder();
				imgs.img().src(request.getContextPath() + "/images/icon/edit.gif").title("编辑").style("vertical-align: middle;cursor: pointer;").onclick("location.href='" + URLUtils.getHDIVUrl(request, editUrl.toString())+ "'").end().nbsp();
				imgs.img().src(request.getContextPath() + "/images/icon/delete.gif").title("删除").style("vertical-align: middle;cursor: pointer;").onclick("deleteConirmation('" + URLUtils.getHDIVUrl(request, deleteUrl.toString()) +"')").end();
				
				HtmlBuilder html = new HtmlBuilder();
				html.append(
						"<table width='100%'>"
						  + "<tr>"
						  	+ "<td width='100%' align='center'>" + imgs.toString() + "</td>"
						  + "</tr>"
					  + "</table>");
				
				return html.toString();
			}
		});
		row.addColumn(operator);
		
		return table;
	}
	
	@RequestMapping("/system/preEditSystemParameter.do")
	public ModelAndView preEditSystemParameter(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("system/systemParameterMaintenance");
		
		String key = request.getParameter("_id");
		
		SystemParameterDTO dto = systemParameterService.getSystemParameterByKey(key);
		
		if(dto == null){
			dto = new SystemParameterDTO();
			dto.setParameterKey(key);
		}
		
		mv.addObject(SessionAttributeConstant.SYSTEM_PARAMETER_DTO, dto);
		return mv;
	}
	
	@RequestMapping("/system/updateSystemParameter.do")
	public ModelAndView updateSystemParameter(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.SYSTEM_PARAMETER_DTO)SystemParameterDTO systemParameterDto, BindingResult errors){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/system/listSystemParameters.do", true));
		
		systemParameterValidator.validateUpadate(systemParameterDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("system/systemParameterMaintenance");
			mv.addObject(SessionAttributeConstant.SYSTEM_PARAMETER_DTO, systemParameterDto);
			return mv;
		}
		
		systemParameterService.updateSystemParameter(systemParameterDto);
		
		return mv;
	}
	
	@RequestMapping("/system/deleteSystemParameter.do")
	public ModelAndView deleteSystemParameter(HttpServletRequest request){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/system/listSystemParameters.do", true));
		
		String key = request.getParameter("_id");
		
		SystemParameterDTO dto = systemParameterService.getSystemParameterByKey(key);
		
		if(dto == null){
			dto = new SystemParameterDTO();
			dto.setParameterKey(key);
		}
		
		systemParameterService.deleteSystemParameter(dto);
		
		return mv;
	}
	
	@RequestMapping("/system/preCreateSystemParameter.do")
	public ModelAndView preCreateSystemParameter(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("system/systemParameterCreate");
		
		mv.addObject(SessionAttributeConstant.SYSTEM_PARAMETER_DTO, new SystemParameterDTO());
		return mv;
	}
	
	@RequestMapping("/system/submitSystemParameter.do")
	public ModelAndView submitSystemParameter(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.SYSTEM_PARAMETER_DTO)SystemParameterDTO systemParameterDto , BindingResult errors){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/system/listSystemParameters.do", true));
		
		systemParameterValidator.validateSave(systemParameterDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("system/systemParameterCreate");
			mv.addObject(SessionAttributeConstant.SYSTEM_PARAMETER_DTO, systemParameterDto);
			return mv;
		}
		
		systemParameterService.saveSystemParameter(systemParameterDto);
		
		return mv;
	}
}
