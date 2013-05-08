package cn.org.polaris.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.org.polaris.constant.CabordersConstant;
import cn.org.polaris.dto.biz.InformationDTO;
import cn.org.polaris.dto.biz.InformationPagedCriteria;
import cn.org.polaris.dto.biz.PositionPagedCriteria;
import cn.org.polaris.dto.biz.ReleasedPositionDTO;
import cn.org.polaris.service.CareerManagerService;
import cn.org.polaris.service.InfoCenterService;

@Controller
public class IndexController extends BaseController{
	
	@Autowired
	private CareerManagerService careerManagerService;
	
	@Autowired
	private InfoCenterService infoCenterService;
	
	@RequestMapping("/index.do")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("index");
		
		handleInformationPagedSearch(request, mv);
		handlePositionPagedSearch(request,mv);
		
		return mv;
	}
	
	private void handleInformationPagedSearch(HttpServletRequest request , ModelAndView mv){
		InformationPagedCriteria pagedCriteria = new InformationPagedCriteria();
		
		pagedCriteria.setRowStart(0);
		pagedCriteria.setRowEnd(7);
		
		List<InformationDTO> infoDtos = infoCenterService.getInfosByCriteria(pagedCriteria);
		
		mv.addObject(CabordersConstant.LIST_OF_INFO	, infoDtos);
	}
	
	private void handlePositionPagedSearch(HttpServletRequest request , ModelAndView mv){
		PositionPagedCriteria pagedCriteria = new PositionPagedCriteria();
		
		pagedCriteria.setRowStart(0);
		pagedCriteria.setRowEnd(4);
		
		List<ReleasedPositionDTO> positionDtos = careerManagerService.getPositionsByCriteria(pagedCriteria);
		
		mv.addObject(CabordersConstant.LIST_OF_POSITION , positionDtos);
	}
	
}
