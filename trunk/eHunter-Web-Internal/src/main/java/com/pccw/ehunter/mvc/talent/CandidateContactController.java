package com.pccw.ehunter.mvc.talent;

import java.io.PrintWriter;
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
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.convertor.TalentConvertor;
import com.pccw.ehunter.dto.CandidateContactHistoryDTO;
import com.pccw.ehunter.dto.InternalUserDTO;
import com.pccw.ehunter.dto.TalentEnquireDTO;
import com.pccw.ehunter.dto.TalentPagedCriteria;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.service.CandidateContactHistoryService;
import com.pccw.ehunter.service.TalentCommonService;
import com.pccw.ehunter.service.TalentRegistrationService;
import com.pccw.ehunter.utility.DateUtils;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.SecurityUtils;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_DTO,
	SessionAttributeConstant.CANDIDATE_CONTACT_HISTORY,
	SessionAttributeConstant.TALENT_ENQUIRE_DTO
})
public class CandidateContactController extends BaseCandidateController{
	
	@Autowired
	private TalentCommonService talentCommonService;
	
	@Autowired
	private TalentRegistrationService talentRegtService;
	
	@Autowired
	private CandidateContactHistoryService candidateContactHistoryService;
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@RequestMapping("/talent/initCandidateContact.do")
	public ModelAndView initCandidateContact(HttpServletRequest request){
		return new ModelAndView(new RedirectViewExt("/talent/initCandidatesSearch.do", true));
	}
	
	@RequestMapping("/talent/initCandidatesSearch.do")
	public ModelAndView initCandidatesSearch(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/candidateEnquiry");
		
		mv.addObject(WebConstant.LIST_OF_TALENT_SOURCE, codeTableHelper.getTalentSources(request));
		mv.addObject(SessionAttributeConstant.TALENT_ENQUIRE_DTO, new TalentEnquireDTO());
		return mv;
	}
	
	@RequestMapping("/talent/candidatesSearch.do")
	public ModelAndView candidatesSearch(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_ENQUIRE_DTO)TalentEnquireDTO enquireDto){
		ModelAndView mv = new ModelAndView("talent/candidateEnquiry");
		
		handlePagedSearch(request , mv , enquireDto);
		mv.addObject(SessionAttributeConstant.TALENT_ENQUIRE_DTO, enquireDto);
		return mv;
	}
	
	private void handlePagedSearch(final HttpServletRequest request, ModelAndView mv,final TalentEnquireDTO enquireDto) {
		final String tableId = "_jmesa_cddts";
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
				
				return talentCommonService.getTalentsByCriteria(pagedCriteria);
			}
		});
		
		model.setTable(getHtmlTable(request , enquireDto.getJmesaDto(), tableId , false , MODULE_CANDIDATE_CONTACT_MGMT));
		
		mv.addObject(SessionAttributeConstant.LIST_OF_TALENT, model.render());
	}
	
	@RequestMapping("/talent/viewCandidateContactHistory.do")
	public ModelAndView viewCandidateContactHistory(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/candidateContactHistoryView");
		
		String talentID = request.getParameter("_tid");
		String projectID = request.getParameter("_pid");
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentRegtService.getTalentByID(talentID , false));
		mv.addObject(SessionAttributeConstant.LIST_OF_PARTICIPATED_PROJECT, talentCommonService.getParticipatedProjectByTalentID(talentID));
		mv.addObject("projectID", projectID);
		
		mv.addObject(SessionAttributeConstant.CANDIDATE_CONTACT_HISTORY, new CandidateContactHistoryDTO());
		mv.addObject(WebConstant.LIST_OF_CANDIDATE_STATUS, codeTableHelper.getCandidateStatuses(request));
				
		mv.addObject(ModuleIndicator.MODULE, request.getParameter(ModuleIndicator.MODULE));
		
		return mv;
	}
	
	@RequestMapping("/talent/viewContactHistoryDetail.do")
	public void viewCandidateContactHistoryDetail(HttpServletRequest request , HttpServletResponse response){
		PrintWriter out = null;
		XMLWriter writer = null;
		
		try {
			response.setContentType("text/xml;charset=UTF-8");
			
			String id = request.getParameter("_id");
			
			CandidateContactHistoryDTO dto = candidateContactHistoryService.getContactHistoryByID(id);
			
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("xml");
			
			if(dto != null){
				dto.setContactCategoryDto(codeTableHelper.getCandidateStatusByCode(request, dto.getContactCategory()));
				Element hst = root.addElement("history");
				hst.addElement("systemContactRefNum").setText(dto.getSystemContactRefNum());
				hst.addElement("systemTalentRefNum").setText(dto.getTalentDto().getTalentID());
				hst.addElement("candidateName").setText(dto.getTalentDto().getCnName() + "/" + dto.getTalentDto().getEnName());
				hst.addElement("systemProjectRefNum").setText(dto.getProjectDto().getSystemProjectRefNum());
				hst.addElement("projectName").setText(dto.getProjectDto().getProjectName());
				hst.addElement("contactCategory").setText(dto.getContactCategoryDto().getDisplayName());
				hst.addElement("record").setText(dto.getRecord());
				hst.addElement("remark").setText(dto.getRemark());
				hst.addElement("adviser").setText(dto.getAdviserDto().getCnName());
				hst.addElement("contactDate").setText(DateUtils.formatDateTime(DateFormatConstant.DATE_YYYY_MM_DD, dto.getCreateDateTime()));
			}
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			
			out = response.getWriter();
			writer = new XMLWriter(out, format);
			
			writer.write(doc);
			
			if(writer != null){
				writer.close();
			}
		} catch (Exception e) {
			logger.error(">>>>> Exception Catched(viewCandidateContactHistoryDetail) : " + e.getMessage());
		} finally {
			if(null != out){
				out.close();
			}
		}
	}
	
	@RequestMapping("/talent/submitContactHistory.do")
	public ModelAndView submitContactHistory(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.CANDIDATE_CONTACT_HISTORY)CandidateContactHistoryDTO candidateContactHistoryDto){
		ModelAndView mv = new ModelAndView("talent/candidateContactHistoryView");
		
		candidateContactHistoryDto.setAdviserDto((InternalUserDTO)SecurityUtils.getUser());
		
		candidateContactHistoryService.saveContactHistory(candidateContactHistoryDto);
		
		transactionLogService.logTransaction(ModuleIndicator.TALENT, getMessage("tx.log.talent.contact.create" , new String[]{candidateContactHistoryDto.getTalentDto().getTalentID()}));
		
		return mv;
	}
	
	@RequestMapping("/talent/loadContactHistories.do")
	public void loadContactHistories(HttpServletRequest request ,HttpServletResponse response){
		PrintWriter out = null;
		XMLWriter writer = null;
		
		try {
			response.setContentType("text/xml;charset=UTF-8");
			
			String talentID = request.getParameter("_tid");
			String projectID = request.getParameter("_pid");
			List<CandidateContactHistoryDTO> histories = candidateContactHistoryService.getContactHistories(talentID, projectID);
			
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("xml");
			
			if(!CollectionUtils.isEmpty(histories)){
				for(CandidateContactHistoryDTO dto : histories){
					dto.setContactCategoryDto(codeTableHelper.getCandidateStatusByCode(request, dto.getContactCategory()));
					
					Element hst = root.addElement("history");
					hst.addElement("systemContactRefNum").setText(dto.getSystemContactRefNum());
					hst.addElement("contactCategory").setText(dto.getContactCategoryDto().getDisplayName());
					hst.addElement("adviser").setText(dto.getAdviserDto().getCnName());
					hst.addElement("contactDate").setText(DateUtils.formatDateTime(DateFormatConstant.DATE_YYYY_MM_DD, dto.getCreateDateTime()));
				}
			}
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			
			out = response.getWriter();
			writer = new XMLWriter(out, format);
			
			writer.write(doc);
			
			if(writer != null){
				writer.close();
			}
		} catch (Exception e) {
			logger.error(">>>>> Exception Catched(loadContactHistories) : " + e.getMessage());
		} finally {
			if(null != out){
				out.close();
			}
		}
	}
}
