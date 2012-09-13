package com.pccw.ehunter.mvc.custom;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.limit.Limit;
import org.jmesa.model.PageItems;
import org.jmesa.model.TableModel;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
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
import com.pccw.ehunter.convertor.CustomerConvertor;
import com.pccw.ehunter.convertor.ProjectConvertor;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerEnquireDTO;
import com.pccw.ehunter.dto.CustomerPagedCriteria;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.dto.ProjectEnquireDTO;
import com.pccw.ehunter.dto.ProjectPagedCriteria;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.CustomerCommonService;
import com.pccw.ehunter.service.ProjectCommonService;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.utility.URLUtils;

@Controller
@SessionAttributes({SessionAttributeConstant.CUSTOMER_ENQUIRE_DTO})
public class CustomerEnquireController extends BaseController{
	
	@Autowired
	private CustomerCommonService custCommonService;
	
	@Autowired
	private ProjectCommonService projectCommonService;
	
	@RequestMapping(value="/customer/initCustomersSearch.do")
	public ModelAndView initCustomersSearch(HttpServletRequest reuqest){
		ModelAndView mv = new ModelAndView("customer/customerEnquiry");
		
		CustomerEnquireDTO enquireDto = new CustomerEnquireDTO();
		reuqest.getSession(false).setAttribute(SessionAttributeConstant.CUSTOMER_ENQUIRE_DTO, enquireDto);
		mv.addObject(SessionAttributeConstant.CUSTOMER_ENQUIRE_DTO, enquireDto);
		
		return mv;
	}
	
	@RequestMapping(value="/customer/customersSearch.do")
	public ModelAndView customersSearch(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.CUSTOMER_ENQUIRE_DTO)CustomerEnquireDTO enquireDto){
		ModelAndView mv = new ModelAndView("customer/customerEnquiry");
		handlePagedSearch(request , enquireDto , mv);
		return mv;
	}

	private void handlePagedSearch(HttpServletRequest request,final CustomerEnquireDTO enquireDto , ModelAndView mv) {
		TableModel model = new TableModel("_jmesa_custs", request);
		model.autoFilterAndSort(false);
		model.setStateAttr("restore");
		
		model.setItems(new PageItems() {
			
			@Override
			public int getTotalRows(Limit limit) {
				return custCommonService.getCustomersCountByCriteria(CustomerConvertor.toPagedCriteria(enquireDto));
			}
			
			@Override
			public Collection<?> getItems(Limit limit) {
				CustomerPagedCriteria pagedCriteria = CustomerConvertor.toPagedCriteria(enquireDto);
				
				int rowStart = limit.getRowSelect().getRowStart();
				int rowEnd = limit.getRowSelect().getRowEnd();
				
				pagedCriteria.getPageFilter().setRowStart(rowStart);
				pagedCriteria.getPageFilter().setRowEnd(rowEnd);
				
				return custCommonService.getCustomersByCriteria(pagedCriteria);
			}
		});
		
		model.setTable(getHtmlTable(request));
		
		mv.addObject(SessionAttributeConstant.LIST_OF_CUSTOMERS, model.render());
		mv.addObject(SessionAttributeConstant.CUSTOMER_ENQUIRE_DTO , enquireDto);
	}

	private Table getHtmlTable(final HttpServletRequest request) {
		Table table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.setSortable(false);
		table.setRow(row);
		
		HtmlColumn systemCustRefNum = new HtmlColumn("systemCustRefNum");
		systemCustRefNum.setTitle("客户编号");
		systemCustRefNum.setWidth("20%");
		systemCustRefNum.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				CustomerDTO dto = (CustomerDTO)item;
				
				StringBuffer builder = new StringBuffer();
				builder.append("<a href=\"");
				builder.append(URLUtils.getHDIVUrl(request, request.getContextPath() + "/customer/viewCustomerDetail.do?_id=" + dto.getSystemCustRefNum()));
				builder.append("\">");
				builder.append(dto.getSystemCustRefNum());
				builder.append("</a>");
				
				return builder.toString();
			}
		});
		row.addColumn(systemCustRefNum);
		
		HtmlColumn custName = new HtmlColumn("name");
		custName.setTitle("客户名称/简称");
		custName.setWidth("50%");
		custName.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String properites, int rowCount) {
				CustomerDTO dto = (CustomerDTO)item;
				
				StringBuffer builder = new StringBuffer();
				builder.append(dto.getFullName());
				
				if(!StringUtils.isEmpty(dto.getShortName())){
					builder.append("/");
					builder.append(dto.getShortName());
				}
				
				return builder.toString();
			}
		});
		row.addColumn(custName);
		
		HtmlColumn grade = new HtmlColumn("grade");
		grade.setTitle("客户等级");
		grade.setWidth("20%");
		row.addColumn(grade);
		
		HtmlColumn status = new HtmlColumn("status");
		status.setTitle("客户状态");
		status.setWidth("10%");
		status.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				return ((CustomerDTO)item).getStatus();
			}
		});
		row.addColumn(status);
		
		return table;
	}
	
	@RequestMapping(value="/customer/viewCustomerDetail.do")
	public ModelAndView viewCustomerDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("customer/viewCustomerDetail");
		
		String id = request.getParameter("_id");
		CustomerDTO customerDto = custCommonService.getCustomerByID(id);
		mv.addObject("customerDto", customerDto);
		
		ProjectEnquireDTO enquireDto = new ProjectEnquireDTO();
		enquireDto.setSystemCustRefNum(id);
		
		handleProjectsSearch(request , enquireDto , mv);
		return mv;
	}
	
	@RequestMapping(value="/customer/pop/viewCustomerDetail.do")
	public ModelAndView viewCustomerDetailPop(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("customer/viewCustomerDetail");
		
		String id = request.getParameter("_id");
		CustomerDTO customerDto = custCommonService.getCustomerByID(id);
		mv.addObject("customerDto", customerDto);
		
		return mv;
	}

	private void handleProjectsSearch(HttpServletRequest request,final ProjectEnquireDTO enquireDto, ModelAndView mv) {
		TableModel model = new TableModel("_jmesa_projects", request);
		model.autoFilterAndSort(false);
		model.setStateAttr("restore");
		
		model.setItems(new PageItems() {
			
			@Override
			public int getTotalRows(Limit limit) {
				return projectCommonService.getProjectsCountByCriteria(ProjectConvertor.toPagedCriteria(enquireDto));
			}
			
			@Override
			public Collection<?> getItems(Limit limit) {
				ProjectPagedCriteria pagedCriteria = ProjectConvertor.toPagedCriteria(enquireDto);
				
				int rowStart = limit.getRowSelect().getRowStart();
				int rowEnd = limit.getRowSelect().getRowEnd();
				
				pagedCriteria.getPageFilter().setRowStart(rowStart);
				pagedCriteria.getPageFilter().setRowEnd(rowEnd);
				
				return projectCommonService.getProjectsByCriteria(pagedCriteria);
			}
		});
		
		model.setTable(getProjectHtmlTable(request));
		
		mv.addObject(SessionAttributeConstant.LIST_OF_RPOJECTS, model.render());
	}

	private Table getProjectHtmlTable(final HttpServletRequest request) {
		Table table = new HtmlTable().width("99%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.setSortable(false);
		table.setRow(row);
		
		HtmlColumn projectId = new HtmlColumn("systemProjectRefNum");
		projectId.setTitle("项目编号");
		projectId.setWidth("30%");
		projectId.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String properties, int rowCount) {
				ProjectDTO dto = (ProjectDTO)item;
				
				StringBuffer builder = new StringBuffer();
				builder.append("<a href=\"");
				builder.append(URLUtils.getHDIVUrl(request, request.getContextPath() + "/project/viewProjectDetail.do?_id=" + dto.getSystemProjectRefNum()));
				builder.append("\" >");
				builder.append(dto.getSystemProjectRefNum());
				builder.append("</a>");
				return null;
			}
		});
		row.addColumn(projectId);
		
		HtmlColumn projectName = new HtmlColumn("projectName");
		projectName.setTitle("项目名称");
		projectName.setWidth("70%");
		row.addColumn(projectName);
		
		return table;
	}
}
