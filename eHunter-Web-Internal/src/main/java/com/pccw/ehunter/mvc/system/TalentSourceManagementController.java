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
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.SessionAttributeConstant;
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
		return new ModelAndView("system/codetable/talentSource/talentSourceManagement");
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
					src.addElement("officialSite").setText(dto.getOffcialSite());
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
	
	@RequestMapping("/system/codetable/submitNewTalentSource.do")
	public ModelAndView submitNewTalentSource(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TALENT_SOURCE_DTO)TalentSourceDTO talentSourceDto){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/system/codetable/talentSourceManagement.do", true));
		
		talentSourceService.saveTalentSource(talentSourceDto);
		
		return mv;
	}

}
