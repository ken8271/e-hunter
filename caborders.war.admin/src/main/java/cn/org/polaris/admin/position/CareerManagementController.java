package cn.org.polaris.admin.position;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.org.polaris.dto.JsonResponseDTO;
import cn.org.polaris.dto.GridStoreDTO;
import cn.org.polaris.dto.biz.PositionPagedCriteria;
import cn.org.polaris.dto.biz.ReleasedPositionDTO;
import cn.org.polaris.service.CareerManagerService;
import cn.org.polaris.utility.StringUtils;

@Controller
public class CareerManagementController {
	
	@Autowired
	private CareerManagerService careerManagerService;
	
	@RequestMapping("/career/release.do")
	public @ResponseBody JsonResponseDTO release(@RequestBody ReleasedPositionDTO rpDto){
		try {
			careerManagerService.releasePosition(rpDto);
			return new JsonResponseDTO(true);
		} catch (Exception e) {
			return new JsonResponseDTO(false);
		}
	}
	
	@RequestMapping("/career/list.do")
	public @ResponseBody GridStoreDTO list(HttpServletRequest request){
		PositionPagedCriteria pagedCriteria = getPagedCriteria(request);
		
		int totalCount = careerManagerService.getPositionsCountByCriteria(pagedCriteria);
		List<ReleasedPositionDTO> list = careerManagerService.getPositionsByCriteria(pagedCriteria);
		
		if(!CollectionUtils.isEmpty(list)){
			return new GridStoreDTO(totalCount, list);
		} else {
			return null;
		}
	}

	private PositionPagedCriteria getPagedCriteria(HttpServletRequest request){
		PositionPagedCriteria pagedCriteria = new PositionPagedCriteria();
		
		pagedCriteria.setId(request.getParameter("id"));
		pagedCriteria.setTitle(request.getParameter("title"));
		pagedCriteria.setWorkCity(request.getParameter("workCity"));
		
		int start = StringUtils.isEmpty(request.getParameter("start")) ? 0
				: Integer.parseInt(request.getParameter("start"));
		int limit = StringUtils.isEmpty(request.getParameter("limit")) ? 0
				: Integer.parseInt(request.getParameter("limit"));
		
		pagedCriteria.setRowStart(start);
		pagedCriteria.setRowEnd(start + limit);
		
		return pagedCriteria;
	}
}
