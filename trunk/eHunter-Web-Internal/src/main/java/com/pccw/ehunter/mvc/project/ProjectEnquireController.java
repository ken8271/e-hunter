package com.pccw.ehunter.mvc.project;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
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

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.ModuleIndicator;
import com.pccw.ehunter.constant.DateFormatConstant;
import com.pccw.ehunter.constant.ParameterConstant;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.convertor.ProjectConvertor;
import com.pccw.ehunter.dto.AnnualLeaveWelfareDTO;
import com.pccw.ehunter.dto.CityDTO;
import com.pccw.ehunter.dto.IndustryDTO;
import com.pccw.ehunter.dto.PositionDescriptionDTO;
import com.pccw.ehunter.dto.PositionRequirementDTO;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.dto.ProjectEnquireDTO;
import com.pccw.ehunter.dto.ProjectPagedCriteria;
import com.pccw.ehunter.dto.ProjectStatusDTO;
import com.pccw.ehunter.dto.ResidentialWelfareDTO;
import com.pccw.ehunter.dto.SalaryCategoryDTO;
import com.pccw.ehunter.dto.SocietyWelfareDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
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
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@RequestMapping("/project/initProjectsSearch.do")
	public ModelAndView initProjectsSearch(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("project/projectEnquiry");
		
		mv.addObject(WebConstant.LIST_OF_PROJECT_STATUS, codeTableHelper.getProjectStatus(request));
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

	@RequestMapping("/project/viewProjectDetail.do")
	public ModelAndView viewProjectDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("project/viewProjectDetail");
		
		String id = request.getParameter(ParameterConstant.P_ID);
		
		ProjectDTO projectDto = projectCommonService.getProjectByID(id);
		
		projectDto.setStatusDto(codeTableHelper.getProjectStatusByCode(request, projectDto.getStatus()));
		
		if(projectDto.getPostDescDto() != null){
			PositionDescriptionDTO postDescDto = projectDto.getPostDescDto();
			
			postDescDto.setPositionDto(codeTableHelper.getPositionByCode(projectDto.getPostDescDto().getPosition()));
			postDescDto.setPositionCategory(projectDto.getPostDescDto().getPositionDto().getTopType());
			postDescDto.setPositionCategoryDto(codeTableHelper.getPositionCategoryByCode(request, projectDto.getPostDescDto().getPositionCategory()));
			
			List<CityDTO> cityDtos = new ArrayList<CityDTO>();
			if(!StringUtils.isEmpty(postDescDto.getCities())){
				String[] cities = StringUtils.tokenize(postDescDto.getCities(), CommonConstant.COMMA);
				if(cities != null && cities.length != 0){
					for(String cityCode : cities){
						cityDtos.add(codeTableHelper.getCityByCode(cityCode));
					}
				}
			}
			postDescDto.setCityDtos(cityDtos);
			
			List<SalaryCategoryDTO> scDtos = postDescDto.getSalaryCategoryDtos();
			String[] scs = StringUtils.tokenize(postDescDto.getSalaryCategoryStr(), CommonConstant.COMMA);
			if(!StringUtils.isEmpty(scs)){
				for(String sc : scs){
					scDtos.add(codeTableHelper.getSalaryCategoryByCode(request, sc));
				}
			}
			
			List<SocietyWelfareDTO> swDtos = postDescDto.getScoitetyWelfareDtos();
			String[] sws = StringUtils.tokenize(postDescDto.getSocietyWelfareStr(), CommonConstant.COMMA);
			if(!StringUtils.isEmpty(sws)){
				for(String sw : sws){
					swDtos.add(codeTableHelper.getSocietyWelfareByCode(request, sw));
				}
			}
			
			List<ResidentialWelfareDTO> rwDtos = postDescDto.getResidentialWelfareDtos();
			String[] rws = StringUtils.tokenize(postDescDto.getResidentialWelfareStr(), CommonConstant.COMMA);
			if(!StringUtils.isEmpty(rws)){
				for(String rw : rws){
					rwDtos.add(codeTableHelper.getResidentialWelfareByCode(request, rw));
				}
			}
			
			List<AnnualLeaveWelfareDTO> awDtos = postDescDto.getAnnualLeaveWelfareDtos();
			String[] aws = StringUtils.tokenize(postDescDto.getAnnualLeaveWelfareStr(), CommonConstant.COMMA);
			if(!StringUtils.isEmpty(aws)){
				for(String aw : aws){
					awDtos.add(codeTableHelper.getAnnualLeaveWelfareByCode(request, aw));
				}
			}
		}
		
		if(projectDto.getPostRequireDto() != null){
			PositionRequirementDTO postRequireDto = projectDto.getPostRequireDto();
			List<IndustryDTO> industryDtos = new ArrayList<IndustryDTO>();
			if(!StringUtils.isEmpty(postRequireDto.getExpectIndustries())){
				String[] industries = StringUtils.tokenize(postRequireDto.getExpectIndustries(), CommonConstant.COMMA);
				if(industries != null && industries.length != 0){
					for(String industryCode : industries){
						industryDtos.add(codeTableHelper.getIndustryByCode(industryCode));
					}
				}
			}
			postRequireDto.setExpectIndustryDtos(industryDtos);
		}
		
		String module = request.getParameter(ModuleIndicator.MODULE);
		
		if(StringUtils.isEmpty(module)){
			module = (String)request.getSession(false).getAttribute(ModuleIndicator.MODULE);
		}else {
			request.getSession(false).setAttribute(ModuleIndicator.MODULE, module);
		}
		
		if(ModuleIndicator.CUSTOMER_ENQUIRY.equals(module)){
			mv.addObject(SessionAttributeConstant.BACK_URL, URLUtils.getHDIVUrl(request, request.getContextPath() + "/customer/viewCustomerDetail.do?_id=" + projectDto.getCustomerDto().getSystemCustRefNum()));
		}else if(ModuleIndicator.PROJECT_REGISTRATION.equals(module)){
			mv.addObject(SessionAttributeConstant.BACK_URL, URLUtils.getHDIVUrl(request, request.getContextPath() + "/project/completeProjectRegistration.do"));
		}else if(ModuleIndicator.PROJECT_ENQUIRY.equals(module)){
			mv.addObject(SessionAttributeConstant.BACK_URL, URLUtils.getHDIVUrl(request, request.getContextPath() + "/project/projectsSearch.do"));
		}
		
		transactionLogService.logTransaction(ModuleIndicator.PROJECT, getMessage("tx.log.project.view" , new String[]{projectDto.getSystemProjectRefNum()}));
		
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		return mv;
	}
	
	@RequestMapping("/project/loadUnassignedProjects.do")
	public void loadUnassignedProjects(HttpServletRequest request , HttpServletResponse response , @ModelAttribute(SessionAttributeConstant.PROJECT_ENQUIRE_DTO)ProjectEnquireDTO prjEnquireDto){
		PrintWriter out = null;
		XMLWriter writer = null;
		try {
			response.setContentType("text/xml;charset=UTF-8");
			out = response.getWriter();
			List<ProjectDTO> dtos = projectCommonService.getUnassignedProjectsByCriteria(ProjectConvertor.toPagedCriteria(prjEnquireDto));
			
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("xml");
			
			if(!CollectionUtils.isEmpty(dtos)){
				for(ProjectDTO dto : dtos){
					dto.setStatusDto(codeTableHelper.getProjectStatusByCode(request, dto.getStatus()));
					Element project = root.addElement("project");
					project.addElement("systemProjectRefNum").setText(dto.getSystemProjectRefNum());
					project.addElement("projectName").setText(dto.getProjectName());
					project.addElement("customerName").setText(dto.getCustomerDto().getFullName());
					project.addElement("projectStatus").setText(dto.getStatusDto().getDisplayName());
				}
			}
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			writer = new XMLWriter(out, format);
			writer.write(doc);
			
			if(null != writer){
				writer.close();
			}
		} catch (Exception e) {
			logger.error(">>>>> Exception Catched (loadUnassignedProjects) : " + e.getMessage());
		} finally {
			if(null != out){
				out.close();
			}
		}
	}
	
	@RequestMapping(value="/project/pop/viewProjectDetail.do")
	public ModelAndView viewPopUpProjectDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("common/projectDetail_pop");
		
		String id = request.getParameter(ParameterConstant.P_ID);
		
		ProjectDTO projectDto = projectCommonService.getProjectByID(id);
		
		projectDto.setStatusDto(codeTableHelper.getProjectStatusByCode(request, projectDto.getStatus()));
		
		if(projectDto.getPostDescDto() != null){
			PositionDescriptionDTO postDescDto = projectDto.getPostDescDto();
			
			postDescDto.setPositionDto(codeTableHelper.getPositionByCode(projectDto.getPostDescDto().getPosition()));
			postDescDto.setPositionCategory(projectDto.getPostDescDto().getPositionDto().getTopType());
			postDescDto.setPositionCategoryDto(codeTableHelper.getPositionCategoryByCode(request, projectDto.getPostDescDto().getPositionCategory()));
			
			List<CityDTO> cityDtos = new ArrayList<CityDTO>();
			if(!StringUtils.isEmpty(postDescDto.getCities())){
				String[] cities = StringUtils.tokenize(postDescDto.getCities(), CommonConstant.COMMA);
				if(cities != null && cities.length != 0){
					for(String cityCode : cities){
						cityDtos.add(codeTableHelper.getCityByCode(cityCode));
					}
				}
			}
			postDescDto.setCityDtos(cityDtos);
			
			List<SalaryCategoryDTO> scDtos = postDescDto.getSalaryCategoryDtos();
			String[] scs = StringUtils.tokenize(postDescDto.getSalaryCategoryStr(), CommonConstant.COMMA);
			if(!StringUtils.isEmpty(scs)){
				for(String sc : scs){
					scDtos.add(codeTableHelper.getSalaryCategoryByCode(request, sc));
				}
			}
			
			List<SocietyWelfareDTO> swDtos = postDescDto.getScoitetyWelfareDtos();
			String[] sws = StringUtils.tokenize(postDescDto.getSocietyWelfareStr(), CommonConstant.COMMA);
			if(!StringUtils.isEmpty(sws)){
				for(String sw : sws){
					swDtos.add(codeTableHelper.getSocietyWelfareByCode(request, sw));
				}
			}
			
			List<ResidentialWelfareDTO> rwDtos = postDescDto.getResidentialWelfareDtos();
			String[] rws = StringUtils.tokenize(postDescDto.getResidentialWelfareStr(), CommonConstant.COMMA);
			if(!StringUtils.isEmpty(rws)){
				for(String rw : rws){
					rwDtos.add(codeTableHelper.getResidentialWelfareByCode(request, rw));
				}
			}
			
			List<AnnualLeaveWelfareDTO> awDtos = postDescDto.getAnnualLeaveWelfareDtos();
			String[] aws = StringUtils.tokenize(postDescDto.getAnnualLeaveWelfareStr(), CommonConstant.COMMA);
			if(!StringUtils.isEmpty(aws)){
				for(String aw : aws){
					awDtos.add(codeTableHelper.getAnnualLeaveWelfareByCode(request, aw));
				}
			}
		}
		
		if(projectDto.getPostRequireDto() != null){
			PositionRequirementDTO postRequireDto = projectDto.getPostRequireDto();
			List<IndustryDTO> industryDtos = new ArrayList<IndustryDTO>();
			if(!StringUtils.isEmpty(postRequireDto.getExpectIndustries())){
				String[] industries = StringUtils.tokenize(postRequireDto.getExpectIndustries(), CommonConstant.COMMA);
				if(industries != null && industries.length != 0){
					for(String industryCode : industries){
						industryDtos.add(codeTableHelper.getIndustryByCode(industryCode));
					}
				}
			}
			postRequireDto.setExpectIndustryDtos(industryDtos);
		}
		
		String module = request.getParameter(ModuleIndicator.MODULE);
		
		if(StringUtils.isEmpty(module)){
			module = (String)request.getSession(false).getAttribute(ModuleIndicator.MODULE);
		}else {
			request.getSession(false).setAttribute(ModuleIndicator.MODULE, module);
		}
		
		if(ModuleIndicator.CUSTOMER_ENQUIRY.equals(module)){
			mv.addObject(SessionAttributeConstant.BACK_URL, URLUtils.getHDIVUrl(request, request.getContextPath() + "/customer/viewCustomerDetail.do?_id=" + projectDto.getCustomerDto().getSystemCustRefNum()));
		}else if(ModuleIndicator.PROJECT_REGISTRATION.equals(module)){
			mv.addObject(SessionAttributeConstant.BACK_URL, URLUtils.getHDIVUrl(request, request.getContextPath() + "/project/completeProjectRegistration.do"));
		}else if(ModuleIndicator.PROJECT_ENQUIRY.equals(module)){
			mv.addObject(SessionAttributeConstant.BACK_URL, URLUtils.getHDIVUrl(request, request.getContextPath() + "/project/projectsSearch.do"));
		}
		
		transactionLogService.logTransaction(ModuleIndicator.PROJECT, getMessage("tx.log.project.view" , new String[]{projectDto.getSystemProjectRefNum()}));
		
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		return mv;
	}
}
