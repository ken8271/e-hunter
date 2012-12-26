package cn.org.polaris.web.infocenter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.org.polaris.dto.biz.InformationDTO;
import cn.org.polaris.dto.biz.InformationPagedCriteria;
import cn.org.polaris.service.InfoCenterService;

@Controller
public class InfoCenterController {
	
	@Autowired
	private InfoCenterService infoCenterService;

	@RequestMapping("/infocenter/index.do")
	public ModelAndView index(){
		return new ModelAndView("infocenter/index");
	}
	
	@RequestMapping("/infocenter/listInfos.do")
	public ModelAndView listInfos(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("infocenter/index");
		
		String category = request.getParameter("category");
		
		InformationPagedCriteria pagedCriteria = new InformationPagedCriteria();
		pagedCriteria.setCategory(category);
		
		List<InformationDTO> infoDtos = infoCenterService.getInfosByCriteria(pagedCriteria);
		
		return mv;
	}
}
