package com.pccw.ehunter.mvc.system;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.dto.TalentSourceDTO;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.TalentSourceService;
import com.pccw.ehunter.utility.RedirectViewExt;

@Controller
public class TalentSourceManagementController extends BaseController{
	
	@Autowired
	private TalentSourceService talentSourceService;

	@RequestMapping("/system/codetable/talentSourceManagement.do")
	public ModelAndView talentSourceManagement(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("system/codetable/talentSource/talentSourceManagement");
		mv.addObject(SessionAttributeConstant.TALENT_SOURCE_DTO, new TalentSourceDTO());
		return mv;
	}
	
	@RequestMapping("/system/codetable/loadSourcesOfTalent.do")
	public void loadSourcesOfTalent(HttpServletRequest request , HttpServletResponse response){
		PrintWriter out = null;
		XMLWriter writer = null;
		
		try {
			response.setContentType("text/xml;charset=UTF-8");
			
			List<TalentSourceDTO> dtos = talentSourceService.getAllTalentSources();
			
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("xml");
			
			if(!CollectionUtils.isEmpty(dtos)){
				for(TalentSourceDTO dto : dtos){
					Element src = root.addElement("source");
					src.addElement("sourceID").setText(dto.getSourceId());
					src.addElement("displayName").setText(dto.getDisplayName());
					src.addElement("officialSite").setText(dto.getOfficialSite());
					src.addElement("activeIndicator").setText(dto.getActiveIndicator());
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
			logger.error(">>>>> Exception Catched(loadSourcesOfTalent) : " + e.getMessage());
		} finally {
			if(null != out){
				out.close();
			}
		}
	}
	
	@RequestMapping("/system/codetable/checkExistsStatus.do")
	public void checkExists(HttpServletRequest request , HttpServletResponse response , @RequestParam("officialSite")String officialSite){
		PrintWriter out = null;
		XMLWriter writer = null;
		
		try {
			response.setContentType("text/xml;charset=UTF-8");
			
			boolean isExists = talentSourceService.isTalentSourceExists(officialSite);
			
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("xml");
			
			root.addElement("isExists").setText(isExists ? CommonConstant.YES : CommonConstant.NO);
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			
			out = response.getWriter();
			writer = new XMLWriter(out, format);
			
			writer.write(doc);
			
			if(writer != null){
				writer.close();
			}
		} catch (Exception e) {
			logger.error(">>>>> Exception Catched(checkExists) : " + e.getMessage());
		} finally {
			if(null != out){
				out.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/system/codetable/submitNewTalentSource.do")
	public ModelAndView submitNewTalentSource(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_SOURCE_DTO)TalentSourceDTO talentSourceDto){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/system/codetable/talentSourceManagement.do", true));
		
		talentSourceService.saveTalentSource(talentSourceDto);
		
		//refresh the codes in session
		List<TalentSourceDTO> srcs = (List<TalentSourceDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_TALENT_SOURCE);
		if(!CollectionUtils.isEmpty(srcs)){
			srcs = talentSourceService.getAllTalentSources();
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_TALENT_SOURCE, srcs);
		}
		
		return mv;
	}
	
	@RequestMapping("/system/codetable/handleTalentSourceDelete.do")
	public ModelAndView deleteTalentSource(HttpServletRequest request , @RequestParam("_id")String id){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/system/codetable/talentSourceManagement.do", true));
		
		talentSourceService.deleteTalentSource(id);
		
		return mv;
	}
}
