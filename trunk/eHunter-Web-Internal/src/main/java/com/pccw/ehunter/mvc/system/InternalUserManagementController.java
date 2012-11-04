package com.pccw.ehunter.mvc.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.DateFormatConstant;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.convertor.InternalUserConvertor;
import com.pccw.ehunter.dto.InternalRoleDTO;
import com.pccw.ehunter.dto.InternalUserDTO;
import com.pccw.ehunter.dto.InternalUserEnquireDTO;
import com.pccw.ehunter.dto.InternalUserPagedCriteria;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.InternalUserManagementService;
import com.pccw.ehunter.utility.DateUtils;
import com.pccw.ehunter.utility.StringUtils;

@Controller
@SessionAttributes({
	SessionAttributeConstant.INTERNAL_USER_DTO,
	SessionAttributeConstant.INTERNAL_USER_ENQUIRE_DTO
})
public class InternalUserManagementController extends BaseController{
	
	@Autowired
	private InternalUserManagementService internalUserService;
	
	@RequestMapping("/usrMgmt/initUsersSearch.do")
	public ModelAndView initUsersSearch(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("userManagement/accountManagement");
		
		mv.addObject(SessionAttributeConstant.INTERNAL_USER_ENQUIRE_DTO , new InternalUserEnquireDTO());
		mv.addObject(WebConstant.LIST_OF_ACTIVE_DIRECTORY_ROLE, getActiveDirectoryRoles(request));
		
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	private List<InternalRoleDTO> getActiveDirectoryRoles(HttpServletRequest request){
		List<InternalRoleDTO> roles = (List<InternalRoleDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_ACTIVE_DIRECTORY_ROLE);
		
		if(CollectionUtils.isEmpty(roles)){
			roles = internalUserService.getActiveDirectoryRoles();
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_ACTIVE_DIRECTORY_ROLE, roles);
		} else {
			logger.debug("retrieved from cache.");
		}
		
		return roles;
	}
	
	private InternalRoleDTO getRoleByCode(HttpServletRequest request , String code){
		List<InternalRoleDTO> roleDtos = getActiveDirectoryRoles(request);
		
		if(!CollectionUtils.isEmpty(roleDtos)){
			for(InternalRoleDTO dto : roleDtos){
				if(code.equals(dto.getSysRefRole())){
					return dto;
				}
			}
		}
		
		InternalRoleDTO dto = new InternalRoleDTO();
		dto.setSysRefRole(code);
		return dto;
	}
	
	@RequestMapping("/usrMgmt/searchInternalUsers.do")
	public ModelAndView searchInternalUsers(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.INTERNAL_USER_ENQUIRE_DTO)InternalUserEnquireDTO userEnquireDto){
		ModelAndView mv = new ModelAndView("userManagement/accountManagement");
		
		handlePagedSearch(request , userEnquireDto , mv);
		
		mv.addObject(SessionAttributeConstant.INTERNAL_USER_ENQUIRE_DTO, userEnquireDto);
		return mv;
	}
	
	private void handlePagedSearch(HttpServletRequest request,final InternalUserEnquireDTO enquireDto, ModelAndView mv) {
		TableModel model = new TableModel("_jmesa_int_usr", request);
		model.autoFilterAndSort(false);
		model.setStateAttr("restore");
		
		model.setItems(new PageItems() {
			
			@Override
			public int getTotalRows(Limit limit) {
				return internalUserService.getInternalUsersCountByCriteria(InternalUserConvertor.toPagedCriteria(enquireDto));
			}
			
			@Override
			public Collection<?> getItems(Limit limit) {
				InternalUserPagedCriteria pagedCriteria = InternalUserConvertor.toPagedCriteria(enquireDto);
				
				int rowStart = limit.getRowSelect().getRowStart();
				int rowEnd = limit.getRowSelect().getRowEnd();
				
				pagedCriteria.getPageFilter().setRowStart(rowStart);
				pagedCriteria.getPageFilter().setRowEnd(rowEnd);
				
				return internalUserService.getInternalUsersByCriteria(pagedCriteria);
			}
		});
		
		model.setTable(getHtml(request));
		
		mv.addObject(SessionAttributeConstant.LIST_OF_INTERNAL_USER, model.render());
	}
	
	private Table getHtml(final HttpServletRequest request) {
		Table table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.setSortable(false);
		table.setRow(row);
		
		HtmlColumn id = new HtmlColumn("userRecId");
		id.setWidth("15%");
		id.setTitle("用户编号");
		row.addColumn(id);
		
		HtmlColumn loginId = new HtmlColumn("loginId");
		loginId.setWidth("15%");
		loginId.setTitle("登录名");
		row.addColumn(loginId);
		
		HtmlColumn name = new HtmlColumn("name");
		name.setWidth("20%");
		name.setTitle("中文名/英文名");
		name.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				InternalUserDTO dto = (InternalUserDTO)item;
				
				StringBuffer buffer = new StringBuffer();
				buffer.append(dto.getCnName());
				
				if(!StringUtils.isEmpty(dto.getEnName())){
					buffer.append("(");
					buffer.append(dto.getEnName());
					buffer.append(")");
				}
				return buffer.toString();
			}
		});
		row.addColumn(name);
		
		HtmlColumn staffId = new HtmlColumn("staffId");
		staffId.setWidth("15%");
		staffId.setTitle("员工号");
		row.addColumn(staffId);
		
		HtmlColumn accountStatus = new HtmlColumn("accountStatus");
		accountStatus.setWidth("10%");
		accountStatus.setTitle("账户状态");
		accountStatus.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				InternalUserDTO dto = (InternalUserDTO)item;
				
				if(CommonConstant.USER_ACCOUNT_ACTIVE.equals(dto.getAccountStatus())){
					return "正常/活跃";
				}else if(CommonConstant.USER_ACCOUNT_INACTIVE.equals(dto.getAccountStatus())){
					return "禁用/锁定";
				}
				
				return StringUtils.EMPTY_STRING;
			}
		});
		row.addColumn(accountStatus);
		
		HtmlColumn createDate = new HtmlColumn("createDate");
		createDate.setWidth("10%");
		createDate.setTitle("创建时间");
		createDate.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				InternalUserDTO dto = (InternalUserDTO)item;
				
				if(dto.getCreateDateTime() != null){
					return DateUtils.formatDateTime(DateFormatConstant.DATE_YYYY_MM_DD, dto.getCreateDateTime());
				}
				return StringUtils.EMPTY_STRING;
			}
		});
		row.addColumn(createDate);
		
		HtmlColumn operation = new HtmlColumn("operation");
		operation.setWidth("10%");
		operation.setHeaderEditor(new HeaderEditor() {
			
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
		operation.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				HtmlBuilder imgs = new HtmlBuilder();
				imgs.img().src(request.getContextPath() + "/images/icon/preview.gif").title("查看").style("vertical-align: middle;cursor: pointer;").end().nbsp()
				    .img().src(request.getContextPath() + "/images/icon/edit.gif").title("编辑").style("vertical-align: middle;cursor: pointer;").end().nbsp()
				    .img().src(request.getContextPath() + "/images/icon/delete.gif").title("删除").style("vertical-align: middle;cursor: pointer;").end();
				
				
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
		row.addColumn(operation);
		
		return table;
	}

	@RequestMapping("/usrMgmt/preCreateInternalUser.do")
	public ModelAndView preCreateInternalUser(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("userManagement/accountCreate");
		
		mv.addObject(SessionAttributeConstant.INTERNAL_USER_DTO, new InternalUserDTO());
		mv.addObject(WebConstant.LIST_OF_ACTIVE_DIRECTORY_ROLE, getActiveDirectoryRoles(request));
		
		return mv;
	}
	
	@RequestMapping("/usrMgmt/saveUserAccount.do")
	public ModelAndView saveUserAccount(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.INTERNAL_USER_DTO)InternalUserDTO internalUserDto){
		ModelAndView mv = new ModelAndView("userManagement/accountManagement");
		
		List<InternalRoleDTO> roleDtos = new ArrayList<InternalRoleDTO>();
		if(!StringUtils.isEmpty(internalUserDto.getRoleStr())){
			String[] roles = StringUtils.tokenize(internalUserDto.getRoleStr(), CommonConstant.COMMA);
			if(roles != null && roles.length != 0){
				for(String roleCode : roles){
					roleDtos.add(getRoleByCode(request, roleCode));
				}
			}
		}
		internalUserDto.setActiveRoles(roleDtos);
		
		internalUserService.saveInternalUser(internalUserDto);
		return mv;
	}
	
	@RequestMapping("/usrMgmt/completeRegistration.do")
	public ModelAndView completeInternalUserRegistration(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.INTERNAL_USER_DTO)InternalUserDTO internalUserDto){
		ModelAndView mv = new ModelAndView("userManagement/accountManagement");
		
		return mv;
	}
}
