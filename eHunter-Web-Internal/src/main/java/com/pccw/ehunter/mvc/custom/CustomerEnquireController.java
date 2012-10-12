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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.DateFormatConstant;
import com.pccw.ehunter.constant.ModuleIndicator;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.convertor.CustomerConvertor;
import com.pccw.ehunter.convertor.ProjectConvertor;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerEnquireDTO;
import com.pccw.ehunter.dto.CustomerPagedCriteria;
import com.pccw.ehunter.dto.CustomerResponsePersonDTO;
import com.pccw.ehunter.dto.InternalUserDTO;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.dto.ProjectEnquireDTO;
import com.pccw.ehunter.dto.ProjectPagedCriteria;
import com.pccw.ehunter.dto.ProjectStatusDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.CustomerCommonService;
import com.pccw.ehunter.service.ProjectCommonService;
import com.pccw.ehunter.utility.DateUtils;
import com.pccw.ehunter.utility.SecurityUtils;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.utility.URLUtils;

@Controller
@SessionAttributes({
	SessionAttributeConstant.CUSTOMER_ENQUIRE_DTO,
	SessionAttributeConstant.CUSTOMER_DTO,
	SessionAttributeConstant.PROJECT_DTO
})
public class CustomerEnquireController extends BaseController{
	
	@Autowired
	private CustomerCommonService custCommonService;
	
	@Autowired
	private ProjectCommonService projectCommonService;
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@RequestMapping(value="/customer/initCustomersSearch.do")
	public ModelAndView initCustomersSearch(HttpServletRequest reuqest){
		ModelAndView mv = new ModelAndView("customer/customerEnquiry");
		
		CustomerEnquireDTO enquireDto = new CustomerEnquireDTO();
		reuqest.getSession(false).setAttribute(SessionAttributeConstant.CUSTOMER_ENQUIRE_DTO, enquireDto);
		mv.addObject(SessionAttributeConstant.CUSTOMER_ENQUIRE_DTO, enquireDto);
		mv.addObject(ModuleIndicator.MODULE, reuqest.getParameter(ModuleIndicator.MODULE));
		return mv;
	}
	
	@RequestMapping(value="/customer/customersSearch.do")
	public ModelAndView customersSearch(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.CUSTOMER_ENQUIRE_DTO)CustomerEnquireDTO enquireDto){
		ModelAndView mv = new ModelAndView("customer/customerEnquiry");
		handlePagedSearch(request , enquireDto , mv);
		return mv;
	}

	private void handlePagedSearch(HttpServletRequest request,final CustomerEnquireDTO enquireDto , ModelAndView mv) {
		String module = request.getParameter(ModuleIndicator.MODULE);
		
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
		
		model.setTable(getHtmlTable(request , module));
		
		mv.addObject(SessionAttributeConstant.LIST_OF_CUSTOMERS, model.render());
		mv.addObject(SessionAttributeConstant.CUSTOMER_ENQUIRE_DTO , enquireDto);
	}

	private Table getHtmlTable(final HttpServletRequest request ,final String module) {
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
							
				if(ModuleIndicator.CUSTOMER_CONTACT_HISTORY.equals(module)){
					builder.append(URLUtils.getHDIVUrl(request, request.getContextPath() + "/customer/viewCustomerContactHistory.do?_id=" + dto.getSystemCustRefNum()));
				}else {
					builder.append(URLUtils.getHDIVUrl(request, request.getContextPath() + "/customer/viewCustomerDetail.do?_id=" + dto.getSystemCustRefNum()));
				}
				
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
				return ((CustomerDTO)item).getCustomerStatus();
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
		customerDto.setTypeDto(codeTableHelper.getCompanyCategoryByCode(request, customerDto.getType()));
		customerDto.setSizeDto(codeTableHelper.getCompanySizeByCode(request, customerDto.getSize()));
		customerDto.setGradeDto(codeTableHelper.getCustomerGradeByCode(request, customerDto.getGrade()));
		customerDto.setCustomerStatusDto(codeTableHelper.getCustomerStatusByCode(request, customerDto.getCustomerStatus()));
		if(!CollectionUtils.isEmpty(customerDto.getMultiResponsePerson())){
			for(CustomerResponsePersonDTO rpDto : customerDto.getMultiResponsePerson()){
				rpDto.setPositionTypeDto(codeTableHelper.getPositionByCode(rpDto.getPositionType()));
				rpDto.setPositionCategory(rpDto.getPositionTypeDto().getTopType());
				rpDto.setPositionCategoryDto(codeTableHelper.getPositionCategoryByCode(request, rpDto.getPositionTypeDto().getTopType()));
			}
		}
		
		InternalUserDTO internalUserDto = (InternalUserDTO)SecurityUtils.getUser();
		
		if(internalUserDto == null){
			internalUserDto = new InternalUserDTO();
		}
		
		ProjectDTO projectDto = new ProjectDTO();
		projectDto.setAdviserDto(internalUserDto);
		projectDto.setCustomerDto(customerDto);
		projectDto.setSystemCustRefNum(customerDto.getSystemCustRefNum());
		mv.addObject("customerDto", customerDto);
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		
		ProjectEnquireDTO enquireDto = new ProjectEnquireDTO();
		enquireDto.setSystemCustRefNum(id);
		
		handleProjectsSearch(request , enquireDto , mv);
		return mv;
	}
	
	@RequestMapping(value="/customer/pop/viewCustomerDetail.do")
	public ModelAndView viewCustomerDetailPop(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("common/customerDetail_pop");
		
		String id = request.getParameter("_id");
		CustomerDTO customerDto = custCommonService.getCustomerByID(id);
		customerDto.setTypeDto(codeTableHelper.getCompanyCategoryByCode(request, customerDto.getType()));
		customerDto.setSizeDto(codeTableHelper.getCompanySizeByCode(request, customerDto.getSize()));
		customerDto.setGradeDto(codeTableHelper.getCustomerGradeByCode(request, customerDto.getGrade()));
		customerDto.setCustomerStatusDto(codeTableHelper.getCustomerStatusByCode(request, customerDto.getCustomerStatus()));
		if(!CollectionUtils.isEmpty(customerDto.getMultiResponsePerson())){
			for(CustomerResponsePersonDTO rpDto : customerDto.getMultiResponsePerson()){
				rpDto.setPositionTypeDto(codeTableHelper.getPositionByCode(rpDto.getPositionType()));
				rpDto.setPositionCategory(rpDto.getPositionTypeDto().getTopType());
				rpDto.setPositionCategoryDto(codeTableHelper.getPositionCategoryByCode(request, rpDto.getPositionTypeDto().getTopType()));
			}
		}
		
		InternalUserDTO internalUserDto = (InternalUserDTO)SecurityUtils.getUser();
		
		if(internalUserDto == null){
			internalUserDto = new InternalUserDTO();
		}
		
		ProjectDTO projectDto = new ProjectDTO();
		projectDto.setAdviserDto(internalUserDto);
		projectDto.setCustomerDto(customerDto);
		projectDto.setSystemCustRefNum(customerDto.getSystemCustRefNum());
		mv.addObject("customerDto", customerDto);
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		
		ProjectEnquireDTO enquireDto = new ProjectEnquireDTO();
		enquireDto.setSystemCustRefNum(id);
		
		handleProjectsSearch(request , enquireDto , mv);
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
		Table table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.setSortable(false);
		table.setRow(row);
		
		HtmlColumn projectID = new HtmlColumn("systemProjectRefNum");
		projectID.setWidth("15%");
		projectID.setTitle("项目编号");
		projectID.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				ProjectDTO dto = (ProjectDTO)item;
				StringBuffer url = new StringBuffer();
				url.append(request.getContextPath());
				url.append("/project/viewProjectDetail.do?_id=");
				url.append(dto.getSystemProjectRefNum());
				url.append("&module=" + ModuleIndicator.PROJECT_ENQUIRY);
				
				StringBuffer buffer = new StringBuffer();
				
				buffer.append("<a href=\"");
				buffer.append(URLUtils.getHDIVUrl(request, url.toString()));
				buffer.append("\">");
				buffer.append(dto.getSystemProjectRefNum());
				buffer.append("</a>");
				return buffer.toString();
			}
		});
		row.addColumn(projectID);
		
		HtmlColumn projectName = new HtmlColumn("projectName");
		projectName.setWidth("20%");
		projectName.setTitle("项目名称");
		row.addColumn(projectName);
		
		HtmlColumn customerName = new HtmlColumn("customerName");
		customerName.setWidth("35%");
		customerName.setTitle("客户公司");
		customerName.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				ProjectDTO dto = (ProjectDTO)item;
				
				if(dto.getCustomerDto() != null) {
					return dto.getCustomerDto().getFullName();
				}
				
				return StringUtils.EMPTY_STRING;
			}
		});
		row.addColumn(customerName);
		
		HtmlColumn advisr = new HtmlColumn("advisr");
		advisr.setWidth("10%");
		advisr.setTitle("项目顾问");
		advisr.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				ProjectDTO dto = (ProjectDTO)item;
				
				if(dto.getAdviserDto() != null){
					return dto.getAdviserDto().getCnName();
				}
				
				return StringUtils.EMPTY_STRING;
			}
		});
		row.addColumn(advisr);
		
		HtmlColumn status = new HtmlColumn("status");
		status.setWidth("5%");
		status.setTitle("项目状态");
		status.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				ProjectDTO dto = (ProjectDTO)item;
				
				ProjectStatusDTO statusDto = codeTableHelper.getProjectStatusByCode(request, dto.getStatus());
				
				if(statusDto != null){
					return statusDto.getDisplayName();
				}
				return StringUtils.EMPTY_STRING;
			}
		});
		row.addColumn(status);
		
		HtmlColumn createDate = new HtmlColumn("createDate");
		createDate.setWidth("15%");
		createDate.setTitle("创建时间");
		createDate.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				ProjectDTO dto = (ProjectDTO)item;
				
				if(dto.getCreateDateTime() != null){
					return DateUtils.formatDateTime(DateFormatConstant.DATE_YYYY_MM_DD, dto.getCreateDateTime());
				}
				return StringUtils.EMPTY_STRING;
			}
		});
		row.addColumn(createDate);
		
		return table;
	}
}
