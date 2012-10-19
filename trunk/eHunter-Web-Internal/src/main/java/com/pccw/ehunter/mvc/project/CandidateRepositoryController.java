package com.pccw.ehunter.mvc.project;

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

import com.pccw.ehunter.constant.ActionFlag;
import com.pccw.ehunter.constant.ModuleIndicator;
import com.pccw.ehunter.constant.ParameterConstant;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.StatusCode;
import com.pccw.ehunter.convertor.TalentConvertor;
import com.pccw.ehunter.dto.JmesaCheckBoxDTO;
import com.pccw.ehunter.dto.CandidateDTO;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.dto.Selection;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.TalentEnquireDTO;
import com.pccw.ehunter.dto.TalentPagedCriteria;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.CandidateRepositoryService;
import com.pccw.ehunter.service.ProjectCommonService;
import com.pccw.ehunter.service.ProjectRegistrationService;
import com.pccw.ehunter.service.TalentCommonService;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.utility.URLUtils;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_ENQUIRE_DTO,
	SessionAttributeConstant.PROJECT_DTO,
	SessionAttributeConstant.TALENT_DTO,
})
public class CandidateRepositoryController extends BaseController{
	
	@Autowired
	private TalentCommonService talentCommonService;
	
	@Autowired
	private CandidateRepositoryService cddtRepoService;
	
	@Autowired
	private ProjectRegistrationService projectRegtService;
	
	@Autowired
	private ProjectCommonService projectCommonService;
	
	@RequestMapping("/project/initProjectCandidateRepository.do")
	public ModelAndView initProjectCandidateRepository(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto){
		ModelAndView mv = new ModelAndView("project/candidateRepositoryCreate");
		
		String systemProjectRefNum = request.getParameter(ParameterConstant.P_ID);
		String type = request.getParameter("type");
		
		if(StringUtils.isEmpty(type)){
			type = (String)request.getSession(false).getAttribute("type");
		}else {
			request.getSession(false).setAttribute("type", type);
		}
		
		mv.addObject("systemProjectRefNum", systemProjectRefNum);
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		
		TalentEnquireDTO enquireDto = new TalentEnquireDTO();
		
		initializeCandidateEnqireDto(enquireDto , projectDto);
		
		mv.addObject(SessionAttributeConstant.TALENT_ENQUIRE_DTO, enquireDto);
		
		request.getSession(false).removeAttribute(SessionAttributeConstant.LIST_OF_MATCHED_TALENT);
		return mv;
	}
	
	private void initializeCandidateEnqireDto(TalentEnquireDTO enquireDto , ProjectDTO projectDto) {
		enquireDto.setSystemProjectRefNum(projectDto.getSystemProjectRefNum());
		enquireDto.setAgeFrom(projectDto.getPostRequireDto().getAgeFromStr());
		enquireDto.setAgeTo(projectDto.getPostRequireDto().getAgeToStr());
		enquireDto.setGender(projectDto.getPostRequireDto().getGender());
		enquireDto.setMajorCategory(projectDto.getPostRequireDto().getMajorCategory());
		enquireDto.setWorkExperience(projectDto.getPostRequireDto().getWorkExperienceStr());
		enquireDto.setDegree(projectDto.getPostRequireDto().getDegree());
		//enquireDto.setFtEduIndicator(projectDto.getPostRequireDto().getFtEduIndicator());
		//enquireDto.setExpectIndustries(projectDto.getPostRequireDto().getExpectIndustries());
	}

	@RequestMapping("/project/appendCandidateRepositoryActions.do")
	public ModelAndView appendCandidateRepositoryActions(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_ENQUIRE_DTO)TalentEnquireDTO enquireDto){
		ModelAndView mv = new ModelAndView("project/candidateRepositoryCreate");
		
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		if(StringUtils.isEmpty(actionFlag)){
			actionFlag = (String)request.getSession(false).getAttribute(ActionFlag.ACTION_FLAG);
		}else {
			request.getSession(false).setAttribute(ActionFlag.ACTION_FLAG, actionFlag);
		}
		
		if(ActionFlag.PAGING.equals(actionFlag)){
			enquireDto.getJmesaDto().handleSelected();
		}else if(ActionFlag.SELECT_ALL.equals(actionFlag)){
			enquireDto.getJmesaDto().handleSelectAll();
		}else if(ActionFlag.SEARCH.equals(actionFlag)){
			enquireDto.getJmesaDto().resetJmesa();
			iniAllSelectOption(request , enquireDto);
		}else if(ActionFlag.SUBMIT.equals(actionFlag)){
			enquireDto.getJmesaDto().handleSelected();
			mv = new ModelAndView(new RedirectViewExt("/project/verifyCandidateRepository.do", true));
			return mv;
		}else {
			mv =  new ModelAndView("project/candidateRepositoryCreate");
			mv.addObject(SessionAttributeConstant.TALENT_ENQUIRE_DTO, enquireDto);
			return mv;
		}
		
		handlePagedSearch(request , mv , enquireDto);
		mv.addObject(SessionAttributeConstant.TALENT_ENQUIRE_DTO, enquireDto);
		return mv;
	}
	
	@RequestMapping("/project/candidateSearch.do")
	public ModelAndView candidateSearch(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_ENQUIRE_DTO)TalentEnquireDTO enquireDto){
		ModelAndView mv = new ModelAndView("project/candidateRepositoryCreate");
		
		String actionFlag = request.getParameter(ActionFlag.ACTION_FLAG);
		
		if(StringUtils.isEmpty(actionFlag)){
			actionFlag = (String)request.getSession(false).getAttribute(ActionFlag.ACTION_FLAG);
		}else {
			request.getSession(false).setAttribute(ActionFlag.ACTION_FLAG, actionFlag);
		}
		
		if(ActionFlag.PAGING.equals(actionFlag)){
			enquireDto.getJmesaDto().handleSelected();
		}else if(ActionFlag.SELECT_ALL.equals(actionFlag)){
			enquireDto.getJmesaDto().handleSelectAll();
		}else {
			enquireDto.getJmesaDto().resetJmesa();
			iniAllSelectOption(request , enquireDto);
		}
		
		handlePagedSearch(request , mv , enquireDto);
		mv.addObject(SessionAttributeConstant.TALENT_ENQUIRE_DTO, enquireDto);
		
		return mv;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/project/verifyCandidateRepository.do")
	public ModelAndView verifyCandidateRepository(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto,
			@ModelAttribute(SessionAttributeConstant.TALENT_ENQUIRE_DTO)TalentEnquireDTO enquireDTO){
		ModelAndView mv = new ModelAndView("project/candidateRepositoryVerify");
		
		List<TalentDTO> talentDtos = (List<TalentDTO>)request.getSession(false).getAttribute(SessionAttributeConstant.LIST_OF_MATCHED_TALENT);
		
		List<CandidateDTO> repoDtos = new ArrayList<CandidateDTO>();
		if(!CollectionUtils.isEmpty(talentDtos)){			
			repoDtos = handleSelected(enquireDTO ,projectDto , talentDtos);
		}
		
		projectDto.setCddtRepoDtos(repoDtos);
		
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		return mv;
	}
	
	@RequestMapping("/project/singleCandidateAsgnVerify.do")
	public ModelAndView singleCandidateAsgnVerify(HttpServletRequest request,@ModelAttribute(SessionAttributeConstant.TALENT_DTO)TalentDTO talentDto){
		ModelAndView mv = new ModelAndView("project/candidateRepositoryVerify");
		String id = request.getParameter("_id");
		ProjectDTO projectDto = projectCommonService.getProjectByID(id);
		
		List<CandidateDTO> cddtDtos = new ArrayList<CandidateDTO>();
		CandidateDTO cddt = new CandidateDTO();
		cddt.setProjectDto(projectDto);
		cddt.setTalentDto(talentDto);
		cddtDtos.add(cddt);
		
		projectDto.setCddtRepoDtos(cddtDtos);
		
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		return mv;
	}
	
	private List<CandidateDTO> handleSelected(TalentEnquireDTO enquireDTO ,ProjectDTO projectDto , List<TalentDTO> talentDtos) {
		List<CandidateDTO> repoDtos = new ArrayList<CandidateDTO>();
		
		CandidateDTO repo = null;
		if(!CollectionUtils.isEmpty(enquireDTO.getJmesaDto().getAllSelections())){
			for(Selection s : enquireDTO.getJmesaDto().getAllSelections()){
				if(s.isChecked()){					
					for(TalentDTO dto : talentDtos){
						if(s.getKey().equals(dto.getTalentID())){
							repo = new CandidateDTO();
							repo.setProjectDto(projectDto);
							repo.setTalentDto(dto);
							repoDtos.add(repo);
							break;
						}
					}
				}
			}
		}

		return repoDtos;
	}

	@RequestMapping("/project/assignCandidates2Project.do")
	public ModelAndView assignCandidate2Project(HttpServletRequest request ,@ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/project/viewCandidateRepository.do", true));
		
		cddtRepoService.saveCandidateRepository(projectDto.getCddtRepoDtos());
		
		
		if(StatusCode.INITIALIZED.equals(projectDto.getStatus())){
			projectDto.setStatus(StatusCode.PROCESSING);
			projectRegtService.updateProjectStatus(projectDto);
		}
		
		return mv;
	}
	
	private void iniAllSelectOption(HttpServletRequest request ,TalentEnquireDTO enquireDto){
		TalentPagedCriteria pagedCriteria = null ;
		
		pagedCriteria = TalentConvertor.toPagedCriteria(enquireDto);
		
		pagedCriteria.getPageFilter().setRowStart(0);
		pagedCriteria.getPageFilter().setRowEnd(0);
		
		List<TalentDTO> dtos = talentCommonService.getTalentsByCriteria(request, pagedCriteria);
		
		request.getSession(false).setAttribute(SessionAttributeConstant.LIST_OF_MATCHED_TALENT, dtos);
		
		String[] talentIDs = new String[dtos.size()];
		if(!CollectionUtils.isEmpty(dtos)){
			for(int i = 0 ; i < dtos.size() ; i++){
				talentIDs[i] = dtos.get(i).getTalentID();
			}
		}
		enquireDto.getJmesaDto().initAllSelectOption(talentIDs);
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
					checkbox = "<input type=\"checkbox\" style=\"align:left\" onclick=\"javascript:selectAllByTableId('_jmesa_tlnts')\" checked=\"checked\"/>";
				}else {
					checkbox = "<input type=\"checkbox\" style=\"align:left\" onclick=\"javascript:selectAllByTableId('_jmesa_tlnts')\" />";
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
		talentId.setWidth("20%");
		talentId.setTitle("人才编号");
		talentId.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				TalentDTO dto = (TalentDTO)item;
				StringBuffer url = new StringBuffer();
				url.append(request.getContextPath());
				url.append("/talent/viewTalentDetail.do?_id=");
				url.append(dto.getTalentID());
				url.append("&type=1");
				
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
		name.setWidth("20%");
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
		degree.setWidth("20%");
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
		place.setWidth("32%");
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
	
	@RequestMapping("/project/viewCandidateRepository.do")
	public ModelAndView viewCandidateRepository(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto){
		ModelAndView mv = new ModelAndView("project/candidateRepositoryView");
		
		TalentEnquireDTO enquireDto = new TalentEnquireDTO();
		enquireDto.setSystemProjectRefNum(projectDto.getSystemProjectRefNum());
		
		handleCandidateRepositorySeach(request , mv , enquireDto , projectDto);
		
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		mv.addObject(SessionAttributeConstant.TALENT_ENQUIRE_DTO, enquireDto);
		return mv;
	}
	
	@RequestMapping("/project/handleCandidatePagedSearch.do")
	public ModelAndView handleCandidatePagedSearch(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.TALENT_ENQUIRE_DTO)TalentEnquireDTO enquireDto,
			@ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto){
		ModelAndView mv = new ModelAndView("project/candidateRepositoryView");
		
		handleCandidateRepositorySeach(request , mv , enquireDto , projectDto);
		
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		mv.addObject(SessionAttributeConstant.TALENT_ENQUIRE_DTO, enquireDto);
		return mv;
	}

	private void handleCandidateRepositorySeach(final HttpServletRequest request,ModelAndView mv,final TalentEnquireDTO enquireDto , ProjectDTO projectDto) {
		TableModel model = new TableModel("_jmesa_cddts", request);
		model.autoFilterAndSort(false);
		model.setStateAttr("restore");
		
		model.setItems(new PageItems() {
			
			@Override
			public int getTotalRows(Limit limit) {
				return cddtRepoService.getCandidateRepositoryCountByProjectID(TalentConvertor.toPagedCriteria(enquireDto));
			}
			
			@Override
			public Collection<CandidateDTO> getItems(Limit limit) {
				TalentPagedCriteria pagedCriteria = TalentConvertor.toPagedCriteria(enquireDto);
				
				int rowStart = limit.getRowSelect().getRowStart();
				int rowEnd = limit.getRowSelect().getRowEnd();
				
				pagedCriteria.getPageFilter().setRowStart(rowStart);
				pagedCriteria.getPageFilter().setRowEnd(rowEnd);
				
				return cddtRepoService.getCandidateRepositoryByProjectID(request , pagedCriteria);
			}
		});
		model.setTable(getCandidateRepositoryHtml(request ,enquireDto.getJmesaDto() , projectDto));
		
		mv.addObject(SessionAttributeConstant.LIST_OF_CANDIDATE_REPOSITORY, model.render());
	}

	private Table getCandidateRepositoryHtml(final HttpServletRequest request,final JmesaCheckBoxDTO jmesaDto , final ProjectDTO projectDto) {
		Table table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.setSortable(false);
		table.setRow(row);
		

		HtmlColumn select = new HtmlColumn("select");
		select.setWidth("5%");
		select.setStyle("width:5%;align:center");
		select.setHeaderEditor(new HeaderEditor() {
			
			@Override
			public Object getValue() {
				HtmlBuilder builder = new HtmlBuilder();
				
				String checkbox = null;
				if(jmesaDto.isSelectAll()){
					checkbox = "<input type=\"checkbox\" style=\"align:left\" onclick=\"javascript:selectAllByTableId('_jmesa_cddts')\" checked=\"checked\"/>";
				}else {
					checkbox = "<input type=\"checkbox\" style=\"align:left\" onclick=\"javascript:selectAllByTableId('_jmesa_cddts')\" />";
				}
				
				return builder.append(checkbox).toString();
			}
		});
		select.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				CandidateDTO dto = (CandidateDTO)item;
				
				StringBuffer checkbox = new StringBuffer();
				if(jmesaDto.isSelectAll() || jmesaDto.isSelected(dto.getTalentDto().getTalentID())){	
					checkbox.append("<input name=\"jmesaDto.select\" type=\"checkbox\" checked=\"checked\" value=\"");
				}else {
					checkbox.append("<input name=\"jmesaDto.select\" type=\"checkbox\" value=\"");
				}
				checkbox.append(dto.getTalentDto().getTalentID());
				checkbox.append("\" />");
				checkbox.append("<input name=\"jmesaDto.currSelect\" type=\"checkbox\" value=\"");
				checkbox.append(dto.getTalentDto().getTalentID());
				checkbox.append("\" checked=\"checked\" style=\"display:none\" />&nbsp;&nbsp;");
				
				return checkbox.toString();
			}
		});
		row.addColumn(select);
		
		HtmlColumn talentId = new HtmlColumn("talentID");
		talentId.setWidth("15%");
		talentId.setTitle("人才编号");
		talentId.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				CandidateDTO dto = (CandidateDTO)item;
//				StringBuffer url = new StringBuffer();
//				url.append(request.getContextPath());
//				url.append("/talent/viewCandidateContactHistory.do?_tid=");
//				url.append(dto.getTalentDto().getTalentID());
//				url.append("&_pid=");
//				url.append(projectDto.getSystemProjectRefNum());
//				url.append("&module=" + ModuleIndicator.PROJECT_CANDIDATE_REPOSITORY);
//				
//				StringBuffer buffer = new StringBuffer();
//				
//				buffer.append("<a href=\"");
//				buffer.append(URLUtils.getHDIVUrl(request, url.toString()));
//				buffer.append("\">");
//				buffer.append(dto.getTalentDto().getTalentID());
//				buffer.append("</a>");
								
				StringBuffer viewUrl = new StringBuffer();
				viewUrl.append(request.getContextPath());
				viewUrl.append("/talent/pop/viewTalentDetail.do?_id=");
				viewUrl.append(dto.getTalentDto().getTalentID());
				
				StringBuffer html = new StringBuffer();
				html.append(dto.getTalentDto().getTalentID() + "&nbsp;");
				html.append("<img src=\"" + request.getContextPath() + "/images/icon/tips.gif\" ");
				html.append("title=\"查看候选人资料\" ");
				html.append("style=\"vertical-align: middle; cursor: pointer;\" ");
				html.append("onclick=\"var customerInfoWindow = window.open('" + URLUtils.getHDIVUrl(request, viewUrl.toString())+ "','customerInfoWindow', 'directories=no,height=550,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680,top=100,left=200');\" ");
				return html.toString();
			}
		});
		row.addColumn(talentId);
		
		HtmlColumn name = new HtmlColumn("name");
		name.setWidth("15%");
		name.setTitle("中文名/英文名");
		name.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				CandidateDTO dto = (CandidateDTO)item;
				StringBuffer buffer = new StringBuffer();
				buffer.append(dto.getTalentDto().getCnName());
				
				if(!StringUtils.isEmpty(dto.getTalentDto().getEnName())){
					buffer.append("&nbsp;(");
					buffer.append(dto.getTalentDto().getEnName());
					buffer.append(")");
				}
				return  buffer.toString();
			}
		});
		row.addColumn(name);
		
		HtmlColumn degree = new HtmlColumn("degree");
		degree.setWidth("15%");
		degree.setTitle("学历/学位");
		degree.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				CandidateDTO dto = (CandidateDTO)item;
				
				if(dto.getTalentDto().getDegreeDto() != null){
					return dto.getTalentDto().getDegreeDto().getDisplayName();
				}else {
					return StringUtils.EMPTY_STRING;
				}
			}
		});
		row.addColumn(degree);
		
		HtmlColumn place = new HtmlColumn("place");
		place.setWidth("30%");
		place.setTitle("现居地");
		place.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				CandidateDTO dto = (CandidateDTO)item;
				
				if(!StringUtils.isEmpty(dto.getTalentDto().getNowLivePlace())){
					return dto.getTalentDto().getNowLivePlace();
				}else {
					return StringUtils.EMPTY_STRING;
				}
			}
		});
		row.addColumn(place);
		
		HtmlColumn status = new HtmlColumn("candidateStatus");
		status.setWidth("15%");
		status.setTitle("状态");
		status.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				CandidateDTO dto = (CandidateDTO)item;
				
				if(!StringUtils.isEmpty(dto.getCandidateStatus())){
					return dto.getCandidateStatusDto().getDisplayName();
				}else {
					return StringUtils.EMPTY_STRING;
				}
			}
		});
		row.addColumn(status);
		
		HtmlColumn view = new HtmlColumn("view");
		view.setWidth("5%");
		view.setStyle("width:5%;align:center");
		view.setTitle("联系记录");
		view.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				CandidateDTO dto = (CandidateDTO)item;
				
				StringBuffer viewUrl = new StringBuffer();
				viewUrl.append(request.getContextPath());
				viewUrl.append("/talent/pop/viewTalentDetail.do?_id=");
				viewUrl.append(dto.getTalentDto().getTalentID());
				
				StringBuffer html = new StringBuffer();
				html.append("<img src=\"" + request.getContextPath() + "/images/icon/tips.gif\" ");
				html.append("title=\"查看候选人资料\" ");
				html.append("style=\"vertical-align: middle; cursor: pointer;\" ");
				html.append("onclick=\"var customerInfoWindow = window.open('" + URLUtils.getHDIVUrl(request, viewUrl.toString())+ "','customerInfoWindow', 'directories=no,height=550,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680,top=100,left=200');\" ");
				
				return html.toString();
			}
		});
		row.addColumn(view);
		
		return table;
	}
}
