package com.pccw.ehunter.mvc.talent;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.BatchUploadDTO;
import com.pccw.ehunter.mvc.BaseController;

@Controller
@SessionAttributes({
	SessionAttributeConstant.BATCH_UPLOAD_DTO
})
public class TalentDraftBatchUploadController extends BaseController{

	@RequestMapping("/talent/initDraftBatchUpload.do")
	public ModelAndView initTalentBatchUpload(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/draftBatchUpload");
		
		mv.addObject(SessionAttributeConstant.BATCH_UPLOAD_DTO, new BatchUploadDTO());
		return mv;
	}
}
