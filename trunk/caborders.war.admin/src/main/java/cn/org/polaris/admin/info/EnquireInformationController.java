package cn.org.polaris.admin.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.org.polaris.dto.GridStoreDTO;
import cn.org.polaris.dto.biz.InformationDTO;
import cn.org.polaris.dto.biz.InformationPagedCriteria;
import cn.org.polaris.service.InfoCenterService;
import cn.org.polaris.utility.StringUtils;

@Controller
public class EnquireInformationController {
	
	@Autowired
	private InfoCenterService infoCenterService;
	
	@RequestMapping("/information/list.do")
	public @ResponseBody GridStoreDTO list(HttpServletRequest request){
		System.out.println("+++++list++++++");
		InformationPagedCriteria pagedCriteria = new InformationPagedCriteria();
		return handlePagedSearch(request , pagedCriteria);
	}
	
	@RequestMapping("/information/search.do")
	public @ResponseBody GridStoreDTO search(HttpServletRequest request , @RequestBody InformationPagedCriteria pagedCriteria){
		System.out.println("---------" + pagedCriteria.getTitle() + "++++" + pagedCriteria.getFromDate());
		return handlePagedSearch(request , pagedCriteria);
	}
	
	private GridStoreDTO handlePagedSearch(HttpServletRequest request , InformationPagedCriteria pagedCriteria){
		int start = StringUtils.isEmpty(request.getParameter("start")) ? 0
				: Integer.parseInt(request.getParameter("start"));
		int limit = StringUtils.isEmpty(request.getParameter("limit")) ? 0
				: Integer.parseInt(request.getParameter("limit"));
		
		pagedCriteria.setRowStart(start);
		pagedCriteria.setRowEnd(start + limit);
		
		int totalCount = infoCenterService.getInfosCountByCriteria(pagedCriteria);
		List<InformationDTO> list = infoCenterService.getInfosByCriteria(pagedCriteria);
		
		if(!CollectionUtils.isEmpty(list)){
			return new GridStoreDTO(totalCount, list);
		} else {
			return null;
		}
	}
}
