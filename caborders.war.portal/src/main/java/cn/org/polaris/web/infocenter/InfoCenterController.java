package cn.org.polaris.web.infocenter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.org.polaris.constant.CabordersConstant;
import cn.org.polaris.convertor.PagedCriteriaConvertor;
import cn.org.polaris.dto.PageInfoDTO;
import cn.org.polaris.dto.RowSelect;
import cn.org.polaris.dto.biz.InformationDTO;
import cn.org.polaris.dto.biz.InformationPagedCriteria;
import cn.org.polaris.service.InfoCenterService;

@Controller
public class InfoCenterController {
	
	@Autowired
	private InfoCenterService infoCenterService;

	@RequestMapping("/infocenter/index.do")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("infocenter/index");
		
		String category = request.getParameter("category");
		
		if(category == null){
			category = "";
		}
		
		handlePagedSearch(request, mv, category);
		
		return mv;
	}
	
	@RequestMapping("/infocenter/industory.do")
	public ModelAndView industory(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("infocenter/industory");
		
		handlePagedSearch(request, mv, CabordersConstant.INFO_INDUSTRY);
		
		return mv;
	}
	
	@RequestMapping("/infocenter/digest.do")
	public ModelAndView digest(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("infocenter/digest");
		
		handlePagedSearch(request, mv, CabordersConstant.INFO_DIGEST);
		
		return mv;
	}
	
	@RequestMapping("/infocenter/others.do")
	public ModelAndView others(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("infocenter/others");
		
		handlePagedSearch(request, mv, CabordersConstant.INFO_OTHER);
		
		return mv;
	}
	
	private void handlePagedSearch(HttpServletRequest request , ModelAndView mv , String category){
		InformationPagedCriteria pagedCriteria = new InformationPagedCriteria();
		pagedCriteria.setCategory(category);
		
		RowSelect rowSelect = getRowSelect(request, mv , pagedCriteria);
		
		PagedCriteriaConvertor.convert2PagedCriteria(pagedCriteria, rowSelect);
		List<InformationDTO> infoDtos = infoCenterService.getInfosByCriteria(pagedCriteria);
		
		mv.addObject(CabordersConstant.LIST_OF_INFO	, infoDtos);
	}
	
	private int getTotalRows(InformationPagedCriteria pagedCriteria){
		return infoCenterService.getInfosCountByCriteria(pagedCriteria);
	}
	
	private RowSelect getRowSelect(HttpServletRequest request , ModelAndView mv , InformationPagedCriteria pagedCriteria){
		String pageStr = request.getParameter("page");
		
		int page = 1;
		if(pageStr != null && pageStr.length() != 0){
			page = Integer.parseInt(pageStr);
		}
		
		int totalRows = getTotalRows(pagedCriteria);
		
		PageInfoDTO pageInfoDto = new PageInfoDTO();
		pageInfoDto.setCurrPage(page);
		pageInfoDto.setTotalCount(totalRows);
		
		mv.addObject(CabordersConstant.INFO_CENTER_PAGE_INFO, pageInfoDto);

		return new RowSelect(page, CabordersConstant.DEFAULT_MAX_ROWS, totalRows);
	}
	
	@RequestMapping("/infocenter/view.do")
	public ModelAndView view(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("infocenter/view");
		
		String id = request.getParameter("id");
		
		InformationDTO infoDto = null;
		if(id != null && id.length() != 0){
			infoDto =  infoCenterService.getInformationByID(id);
		}else {
			infoDto = new InformationDTO();
		}
		
		mv.addObject(CabordersConstant.INFO_DETAIL, infoDto);
		return mv;
	}
}
