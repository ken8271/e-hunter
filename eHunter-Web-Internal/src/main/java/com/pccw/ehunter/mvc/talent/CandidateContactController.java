package com.pccw.ehunter.mvc.talent;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.DateFormatConstant;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.CandidateContactHistoryDTO;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.CandidateContactHistoryService;
import com.pccw.ehunter.service.TalentCommonService;
import com.pccw.ehunter.service.TalentRegistrationService;
import com.pccw.ehunter.utility.DateUtils;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TALENT_DTO
})
public class CandidateContactController extends BaseController{
	
	@Autowired
	private TalentCommonService talentCommonService;
	
	@Autowired
	private TalentRegistrationService talentRegtService;
	
	@Autowired
	private CandidateContactHistoryService candidateContactHistoryService;
	
	@RequestMapping("/talent/viewCandidateContactHistory.do")
	public ModelAndView viewCandidateContactHistory(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/candidateContactHistoryView");
		
		String talentID = request.getParameter("_tid");
		String projectID = request.getParameter("_pid");
		
		mv.addObject(SessionAttributeConstant.TALENT_DTO, talentRegtService.getTalentByID(talentID));
		mv.addObject(SessionAttributeConstant.LIST_OF_PARTICIPATED_PROJECT, talentCommonService.getParticipatedProjectByTalentID(talentID));
		mv.addObject("projectID", projectID);
		return mv;
	}
	
	@RequestMapping("/talent/viewCandidateContactHistoryDetail.do")
	public ModelAndView viewCandidateContactHistoryDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/candidateContactHistoryDetail");
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
					Element hst = root.addElement("history");
					hst.addElement("systemContactRefNum").setText(dto.getSystemContactRefNum());
					hst.addElement("contactCategory").setText(dto.getContactCategory());
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
