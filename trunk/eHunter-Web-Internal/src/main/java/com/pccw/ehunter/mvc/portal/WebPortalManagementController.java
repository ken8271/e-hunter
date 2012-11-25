package com.pccw.ehunter.mvc.portal;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.DateFormatConstant;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.CityDTO;
import com.pccw.ehunter.dto.PagedCriteria;
import com.pccw.ehunter.dto.ProvinceDTO;
import com.pccw.ehunter.dto.portal.PortalInformationCenterDTO;
import com.pccw.ehunter.dto.portal.PortalReleasedPositionDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.PortalInformationCenterService;
import com.pccw.ehunter.service.PortalPositionReleaseService;
import com.pccw.ehunter.utility.DateUtils;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.utility.URLUtils;
import com.pccw.ehunter.validator.PortalInformationCenterValidator;
import com.pccw.ehunter.validator.PortalReleasedPositionValidator;

@Controller
public class WebPortalManagementController extends BaseController{
	
	@Autowired
	private PortalInformationCenterService infoCenterService;
	
	@Autowired
	private PortalPositionReleaseService positionReleaseService;
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@Autowired
	private PortalInformationCenterValidator infoCenterValidator;
	
	@Autowired
	private PortalReleasedPositionValidator releasedPositionValidator;

	@RequestMapping("/portal/initInformationMgmt.do")
	public ModelAndView initInformationMgmt(HttpServletRequest request){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/portal/listOfInformationCenter.do", true));
		return mv;
	}
	
	@RequestMapping("/portal/listOfInformationCenter.do")
	public ModelAndView listOfInformationCenter(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("portal/listOfInformation");
		
		handleInformationPagedSeach(request , mv);
		
		return mv;
	}
	
	private void handleInformationPagedSeach(HttpServletRequest request, ModelAndView mv) {
		TableModel model = new TableModel("_jmesa_info_centr", request);
		model.autoFilterAndSort(false);
		model.setStateAttr("restore");
		
		model.setItems(new PageItems() {
			
			@Override
			public int getTotalRows(Limit limit) {
				return infoCenterService.getPortalInformationCountByCriteria(new PagedCriteria());
			}
			
			@Override
			public Collection<PortalInformationCenterDTO> getItems(Limit limit) {
				PagedCriteria pagedCriteria = new PagedCriteria();
				
				int rowStart = limit.getRowSelect().getRowStart();
				int rowEnd = limit.getRowSelect().getRowEnd();
				
				pagedCriteria.getPageFilter().setRowStart(rowStart);
				pagedCriteria.getPageFilter().setRowEnd(rowEnd);
				
				return infoCenterService.getPortalInformationByCriteria(pagedCriteria);
			}
		});
		
		model.setTable(getInformationHtmlTable(request));
		
		mv.addObject(SessionAttributeConstant.LIST_OF_PORTAL_INFORMATION, model.render());
	}

	private Table getInformationHtmlTable(final HttpServletRequest request) {
		Table table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.setSortable(false);
		table.setRow(row);
		
		HtmlColumn title = new HtmlColumn("title");
		title.setWidth("75%");
		title.setTitle("标题");
		title.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				PortalInformationCenterDTO dto = (PortalInformationCenterDTO)item;
				
				StringBuffer viewUrl = new StringBuffer();
				viewUrl.append(request.getContextPath());
				viewUrl.append("/portal/viewInformationDetail.do");
				viewUrl.append("?_id="+ dto.getSystemRefInfo());
				
				HtmlBuilder builder = new HtmlBuilder();
				builder.ahref(URLUtils.getHDIVUrl(request, viewUrl.toString()), dto.getTitle());
				return builder.toString();
			}
		});
		row.addColumn(title);
		
		HtmlColumn createDttm = new HtmlColumn("createDate");
		createDttm.setWidth("15%");
		createDttm.setTitle("发布时间");
		createDttm.setCellEditor(new CellEditor() {
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				PortalInformationCenterDTO dto = (PortalInformationCenterDTO)item;
				
				if(dto.getCreateDateTime() != null){
					return DateUtils.formatDateTime(DateFormatConstant.DATE_YYYY_MM_DD, dto.getCreateDateTime());
				}
				
				return StringUtils.EMPTY_STRING;
			}
		});
		row.addColumn(createDttm);
		
		return table;
	}
	
	@RequestMapping("/portal/viewInformationDetail.do")
	public ModelAndView viewInformationDetail(HttpServletRequest request ,@RequestParam("_id")String id){
		ModelAndView mv = new ModelAndView("portal/viewInformation");
		
		PortalInformationCenterDTO dto =  infoCenterService.getInformationByID(id);
		
		if(dto == null){
			dto = new PortalInformationCenterDTO();
		}
		
		mv.addObject(SessionAttributeConstant.PORTAL_INFORMATION_DTO, dto);
		
		return mv;
	}
	
	@RequestMapping("/portal/initReleasedPositionMgmt.do")
	public ModelAndView initReleasedPositionMgmt(HttpServletRequest request){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/portal/listOfReleasedPosition.do", true));
		return mv;
	}
	
	@RequestMapping("/portal/listOfReleasedPosition.do")
	public ModelAndView listOfReleasedPosition(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("portal/listOfReleasedPosition");
		
		handleRelesedPositionPagedSeach(request , mv);
		
		return mv;
	}
	
	private void handleRelesedPositionPagedSeach(HttpServletRequest request, ModelAndView mv) {
		TableModel model = new TableModel("_jmesa_rlesd_postn", request);
		model.autoFilterAndSort(false);
		model.setStateAttr("restore");
		
		model.setItems(new PageItems() {
			
			@Override
			public int getTotalRows(Limit limit) {
				return positionReleaseService.getReleasedPositionsCountByPagedCriteria(new PagedCriteria());
			}
			
			@Override
			public Collection<PortalReleasedPositionDTO> getItems(Limit limit) {
				PagedCriteria pagedCriteria = new PagedCriteria();
				
				int rowStart = limit.getRowSelect().getRowStart();
				int rowEnd = limit.getRowSelect().getRowEnd();
				
				pagedCriteria.getPageFilter().setRowStart(rowStart);
				pagedCriteria.getPageFilter().setRowEnd(rowEnd);
				
				return positionReleaseService.getReleasedPositionsByCriteria(pagedCriteria);
			}
		});
		
		model.setTable(getReleasedPositionHtmlTable(request));
		
		mv.addObject(SessionAttributeConstant.LIST_OF_RELEASED_POSITION, model.render());
	}

	private Table getReleasedPositionHtmlTable(final HttpServletRequest request) {
		Table table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.setSortable(false);
		table.setRow(row);
		
		HtmlColumn title = new HtmlColumn("title");
		title.setWidth("55%");
		title.setTitle("标题");
		row.addColumn(title);
		
		HtmlColumn workCity = new HtmlColumn("workCity");
		workCity.setWidth("20%");
		workCity.setTitle("工作地点");
		workCity.setCellEditor(new CellEditor() {
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				PortalReleasedPositionDTO dto = (PortalReleasedPositionDTO)item;
				
				ProvinceDTO province = codeTableHelper.getProvinceByCode(request, dto.getWorkProvince());
				CityDTO city = codeTableHelper.getCityByCode(dto.getWorkCity());
				
				StringBuffer buffer = new StringBuffer();
				if(province != null){
					buffer.append(province.getDisplayName());
				}
				
				if(city != null){
					buffer.append(">>" + city.getDisplayName());
				}
				return buffer.toString();
			}
		});
		row.addColumn(workCity);
		
		HtmlColumn createDttm = new HtmlColumn("createDate");
		createDttm.setWidth("15%");
		createDttm.setTitle("发布时间");
		createDttm.setCellEditor(new CellEditor() {
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				PortalReleasedPositionDTO dto = (PortalReleasedPositionDTO)item;
				
				if(dto.getCreateDateTime() != null){
					return DateUtils.formatDateTime(DateFormatConstant.DATE_YYYY_MM_DD, dto.getCreateDateTime());
				}
				
				return StringUtils.EMPTY_STRING;
			}
		});
		row.addColumn(createDttm);
		
		return table;
	}
	
	@RequestMapping("/portal/preCreateInformation.do")
	public ModelAndView preCreateInformation(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("portal/informationCreate");
		
		mv.addObject(SessionAttributeConstant.PORTAL_INFORMATION_DTO, new PortalInformationCenterDTO());
		
		return mv;
	}
	
	@RequestMapping("/portal/releaseInformation.do")
	public ModelAndView releaseInformation(HttpServletRequest request ,@RequestParam("content")String content ,  @ModelAttribute(SessionAttributeConstant.PORTAL_INFORMATION_DTO)PortalInformationCenterDTO informationDto , BindingResult errors){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/portal/listOfInformationCenter.do", true));

		informationDto.setContent(content);
		
		infoCenterValidator.validate(informationDto, errors);

		if(errors.hasErrors()){
			mv = new ModelAndView("portal/informationCreate");
			mv.addObject(SessionAttributeConstant.PORTAL_INFORMATION_DTO, new PortalInformationCenterDTO());
			mv.addObject("content", StringEscapeUtils.escapeHtml(content));
			return mv;
		}

		infoCenterService.saveInformation(informationDto);

		return mv;
	}
	
	@RequestMapping("/portal/preCreatePosition.do")
	public ModelAndView preCreatePosition(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("portal/positionCreate");
		
		mv.addObject(SessionAttributeConstant.PORTAL_POSITION_DTO, new PortalReleasedPositionDTO());
		
		return mv;
	}
	
	@RequestMapping("/portal/releasePosition.do")
	public ModelAndView releasePosition(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.PORTAL_POSITION_DTO)PortalReleasedPositionDTO portalPositionDto , BindingResult errors){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/portal/listOfReleasedPosition.do", true));
		
		releasedPositionValidator.validate(portalPositionDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("portal/positionCreate");
			mv.addObject(SessionAttributeConstant.PORTAL_POSITION_DTO, new PortalReleasedPositionDTO());
			return mv;
		}
		
		positionReleaseService.saveReleasedPosition(portalPositionDto);
		
		return mv;
	}
	
}
