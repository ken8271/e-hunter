package com.pccw.ehunter.mvc.talent;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.BatchUploadDTO;
import com.pccw.ehunter.helper.BatchUploadHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.utility.FileUtils;

@Controller
@SessionAttributes({
	SessionAttributeConstant.BATCH_UPLOAD_DTO
})
public class ResumeUploadController extends BaseController{
	
	private String uploadPath;
	
	@Autowired
	private BatchUploadHelper batchUploadHelper;
	
	@Autowired
	@Qualifier("xmlProcessorConfig")
	public void setProperties(Properties xmlProcessorConfig){
		uploadPath = xmlProcessorConfig.getProperty("resume.path.upload");
	}

	@RequestMapping("/talent/initAttachementUpload.do")
	public ModelAndView initTalentBatchUpload(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/draftBatchUpload");
		
		mv.addObject(SessionAttributeConstant.BATCH_UPLOAD_DTO, new BatchUploadDTO());
		return mv;
	}
	
	@RequestMapping("/talent/submitAttachementUpload.do")
	public ModelAndView submitBatchUploadDraft(HttpServletRequest request,@RequestParam("uploadFile") MultipartFile uploadFile){
		ModelAndView mv = new ModelAndView("talent/uploadSuccess");
		
		try {
			FileUtils.write2Disk(uploadPath, uploadFile.getOriginalFilename(), uploadFile.getInputStream());
			batchUploadHelper.convertPdf2Swf(uploadFile.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
}
