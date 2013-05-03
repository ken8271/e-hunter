package cn.org.polaris.admin.info;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.org.polaris.dto.biz.InformationDTO;

@Controller
public class InformationReleaseController {

	@RequestMapping("/test.do")
	@ResponseBody
	public InformationDTO test(){
		System.out.println("---testing----");
		
		InformationDTO dto = new InformationDTO();
		dto.setSystemRefInfo("123");
		dto.setTitle("helloworld");
		dto.setContent("testing with json now....");
		
		return dto;
	}
}
