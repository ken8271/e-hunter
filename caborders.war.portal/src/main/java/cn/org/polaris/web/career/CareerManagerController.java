package cn.org.polaris.web.career;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.org.polaris.constant.CabordersConstant;
import cn.org.polaris.convertor.PagedCriteriaConvertor;
import cn.org.polaris.dto.PageInfoDTO;
import cn.org.polaris.dto.PagedCriteria;
import cn.org.polaris.dto.RowSelect;
import cn.org.polaris.dto.biz.ReleasedPositionDTO;
import cn.org.polaris.service.CareerManagerService;
import cn.org.polaris.web.BaseController;

@Controller
public class CareerManagerController extends BaseController{
	
	@Autowired
	private CareerManagerService careerManagerService;

	@RequestMapping("/career/index.do")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("career/index");
		
		handlePagedSearch(request, mv);
		
		return mv;
	}
	
	@RequestMapping("/career/positions.do")
	public ModelAndView positions(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("career/positions");
		
		handlePagedSearch(request, mv);
		
		return mv;
	}
	
	private void handlePagedSearch(HttpServletRequest request , ModelAndView mv){
		PagedCriteria pagedCriteria = new PagedCriteria();
		
		RowSelect rowSelect = getRowSelect(request, mv , pagedCriteria);
		
		PagedCriteriaConvertor.convert2PagedCriteria(pagedCriteria, rowSelect);
		List<ReleasedPositionDTO> positionDtos = careerManagerService.getPositionsByCriteria(pagedCriteria);
		
		mv.addObject(CabordersConstant.LIST_OF_POSITION , positionDtos);
	}
	
	private RowSelect getRowSelect(HttpServletRequest request , ModelAndView mv , PagedCriteria pagedCriteria){
		String pageStr = request.getParameter("page");
		
		int page = 1;
		if(pageStr != null && pageStr.length() != 0){
			page = Integer.parseInt(pageStr);
		}
		
		int totalRows = careerManagerService.getPositionsCountByCriteria(pagedCriteria);
		
		PageInfoDTO pageInfoDto = new PageInfoDTO();
		pageInfoDto.setCurrPage(page);
		pageInfoDto.setTotalCount(totalRows);

		mv.addObject(CabordersConstant.POSITION_PAGE_INFO, pageInfoDto);
		
		return new RowSelect(page, CabordersConstant.POSITION_MAX_ROWS, totalRows);
	}
	
	@RequestMapping("/career/view.do")
	public ModelAndView view(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("career/view");
		
		String id = request.getParameter("id");
		
		ReleasedPositionDTO positionDto = null;
		if(id != null && id.length() != 0){
			positionDto =  careerManagerService.getPositionByID(id);
		}else {
			positionDto = new ReleasedPositionDTO();
		}
		
		mv.addObject(CabordersConstant.POSITION_DTO , positionDto);
		return mv;
	}
	
	@RequestMapping("/career/cvDelegate.do")
	public ModelAndView cvDelegate(HttpServletRequest request){
		return new ModelAndView("career/cvDelegate");
	}
	
	@RequestMapping("/career/personalityAssessment.do")
	public ModelAndView personalityAssessment(HttpServletRequest request){
		return new ModelAndView("career/personalityAssessment");
	}
	
	@RequestMapping("/career/occupationalPlanning.do")
	public ModelAndView occupationalPlanning(HttpServletRequest request){
		return new ModelAndView("career/occupationalPlanning");
	}
	
	@RequestMapping("/career/cvGuidance.do")
	public ModelAndView cvGuidance(HttpServletRequest request){
		return new ModelAndView("career/cvGuidance");
	}
}
