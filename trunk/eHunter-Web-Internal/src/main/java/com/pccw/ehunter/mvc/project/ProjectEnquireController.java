package com.pccw.ehunter.mvc.project;

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

import com.pccw.ehunter.constant.UrlConstant;
import com.pccw.ehunter.constant.DateFormatConstant;
import com.pccw.ehunter.constant.ParameterConstant;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.convertor.ProjectConvertor;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.dto.ProjectEnquireDTO;
import com.pccw.ehunter.dto.ProjectPagedCriteria;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.ProjectCommonService;
import com.pccw.ehunter.utility.DateUtils;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.utility.URLUtils;

@Controller
@SessionAttributes({
	SessionAttributeConstant.PROJECT_ENQUIRE_DTO,
	SessionAttributeConstant.PROJECT_DTO
})
public class ProjectEnquireController extends BaseController{
	
	@Autowired
	private ProjectCommonService projectCommonService;
	
	@RequestMapping("/project/initProjectsSearch.do")
	public ModelAndView initProjectsSearch(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("project/projectEnquiry");
		
		mv.addObject(SessionAttributeConstant.PROJECT_ENQUIRE_DTO, new ProjectEnquireDTO());
		return mv;
	}
	
	@RequestMapping("/project/projectsSearch.do")
	public ModelAndView projectsSearch(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.PROJECT_ENQUIRE_DTO)ProjectEnquireDTO prjEnquireDto){
		ModelAndView mv = new ModelAndView("project/projectEnquiry");
		
		handlePagedSearch(request , prjEnquireDto , mv);
		
		mv.addObject(SessionAttributeConstant.PROJECT_ENQUIRE_DTO, prjEnquireDto);
		return mv;
	}

	private void handlePagedSearch(final HttpServletRequest request,final ProjectEnquireDTO enquireDto, ModelAndView mv) {
		TableModel model = new TableModel("_jmesa_project", request);
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
		
		model.setTable(getHtml(request));
		
		mv.addObject(SessionAttributeConstant.LIST_OF_RPOJECTS, model.render());
	}

	private Table getHtml(final HttpServletRequest request) {
		Table table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.setSortable(false);
		table.setRow(row);
		
		HtmlColumn projectID = new HtmlColumn("systemProjectRefNum");
		projectID.setWidth("width:10% nowrap");
		projectID.setTitle("项目编号");
		projectID.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				ProjectDTO dto = (ProjectDTO)item;
				StringBuffer url = new StringBuffer();
				url.append(request.getContextPath());
				url.append("/project/viewProjectDetail.do?_id=");
				url.append(dto.getSystemProjectRefNum());
				url.append("&" + UrlConstant.BACK_URL_PARAM + "=");
				url.append(UrlConstant.PROJECT_SEARCH);
				
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
		projectName.setWidth("width:30% nowrap");
		projectName.setTitle("项目名称");
		row.addColumn(projectName);
		
		HtmlColumn customerName = new HtmlColumn("customerName");
		customerName.setWidth("width:40% nowrap");
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
		advisr.setWidth("width:10% nowrap");
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
		
		HtmlColumn createDate = new HtmlColumn("createDate");
		createDate.setWidth("width:10% nowrap");
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

	@RequestMapping("/project/viewProjectDetail.do")
	public ModelAndView viewProjectDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("project/viewProjectDetail");
		
		String id = request.getParameter(ParameterConstant.P_ID);
		
		ProjectDTO projectDto = projectCommonService.getProjectByID(id);
		
		String back = request.getParameter(UrlConstant.BACK_URL_PARAM);
		
		if(StringUtils.isEmpty(back)){
			back = (String)request.getSession(false).getAttribute(UrlConstant.BACK_URL_PARAM);
		}else {
			request.getSession(false).setAttribute(UrlConstant.BACK_URL_PARAM, back);
		}
		
		if(UrlConstant.CUSTOMER_VIEW.equals(back)){
			mv.addObject(SessionAttributeConstant.BACK_URL, URLUtils.getHDIVUrl(request, request.getContextPath() + "/customer/viewCustomerDetail.do?_id=" + projectDto.getCustomerDto().getSystemCustRefNum()));
		}else if(UrlConstant.PROJECT_CREATE.equals(back)){
			mv.addObject(SessionAttributeConstant.BACK_URL, URLUtils.getHDIVUrl(request, request.getContextPath() + "/project/completeProjectRegistration.do"));
		}else if(UrlConstant.PROJECT_SEARCH.equals(back)){
			mv.addObject(SessionAttributeConstant.BACK_URL, URLUtils.getHDIVUrl(request, request.getContextPath() + "/project/projectsSearch.do"));
		}
		
		mv.addObject(UrlConstant.BACK_URL_PARAM, back);
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		return mv;
	}
}
