package cn.org.polaris.admin.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.org.polaris.dto.AjaxResponseDTO;
import cn.org.polaris.dto.biz.InformationDTO;
import cn.org.polaris.service.InfoCenterService;

@Controller
public class InformationReleaseController {
	
	@Autowired
	private InfoCenterService infoCenterService;

	@RequestMapping(value="/information/release.do")
	public @ResponseBody AjaxResponseDTO release(@RequestBody InformationDTO dto){
		try {			
			infoCenterService.releaseInformation(dto);
			return new AjaxResponseDTO(true);
		} catch(Exception e){
			return new AjaxResponseDTO(false);
		}
	}
}
