package cn.org.polaris.admin.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.org.polaris.admin.BaseController;
import cn.org.polaris.dto.JsonResponseDTO;
import cn.org.polaris.dto.GridStoreDTO;
import cn.org.polaris.dto.biz.InformationDTO;
import cn.org.polaris.dto.biz.InformationPagedCriteria;
import cn.org.polaris.error.Errors;
import cn.org.polaris.service.InfoCenterService;
import cn.org.polaris.utility.StringUtils;
import cn.org.polaris.validator.InformationValidator;

@Controller
public class InfoCenterManagementController extends BaseController{

	@Autowired
	private InfoCenterService infoCenterService;

	@Autowired
	private InformationValidator infoValidator;

	@RequestMapping(value = "/information/release.do")
	public @ResponseBody
	JsonResponseDTO release(@RequestBody InformationDTO dto) {
		try {
			Errors errors = infoValidator.validate(dto);
						
			if (errors.hasErrors()) {
				return new JsonResponseDTO(false , messageHelper.convert2Messages(errors));
			}

			infoCenterService.releaseInformation(dto);

			return new JsonResponseDTO(true);
		} catch (Exception e) {
			return new JsonResponseDTO(false);
		}
	}

	@RequestMapping("/information/list.do")
	public @ResponseBody
	GridStoreDTO list(HttpServletRequest request) {
		InformationPagedCriteria pagedCriteria = getPagedCriteria(request);

		int totalCount = infoCenterService
				.getInfosCountByCriteria(pagedCriteria);
		List<InformationDTO> list = infoCenterService
				.getInfosByCriteria(pagedCriteria);

		if (!CollectionUtils.isEmpty(list)) {
			return new GridStoreDTO(totalCount, list);
		} else {
			return null;
		}
	}

	private InformationPagedCriteria getPagedCriteria(HttpServletRequest request) {
		InformationPagedCriteria pagedCriteria = new InformationPagedCriteria();

		pagedCriteria.setId(request.getParameter("id"));
		pagedCriteria.setTitle(request.getParameter("title"));
		pagedCriteria.setCategory(request.getParameter("category"));

		int start = StringUtils.isEmpty(request.getParameter("start")) ? 0
				: Integer.parseInt(request.getParameter("start"));
		int limit = StringUtils.isEmpty(request.getParameter("limit")) ? 0
				: Integer.parseInt(request.getParameter("limit"));

		pagedCriteria.setRowStart(start);
		pagedCriteria.setRowEnd(start + limit);

		return pagedCriteria;
	}
}
