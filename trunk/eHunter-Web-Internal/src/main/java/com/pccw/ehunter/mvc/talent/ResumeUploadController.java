package com.pccw.ehunter.mvc.talent;

import java.io.File;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.AttachmentCurriculumVitaeDTO;
import com.pccw.ehunter.helper.BatchUploadFileConvertor;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.utility.FileUtils;
import com.pccw.ehunter.utility.StringEncryptUtils;

@Controller
@SessionAttributes({
	SessionAttributeConstant.ATTACHMENT_CV_DTO
})
public class ResumeUploadController extends BaseController{
	
	private String uploadDirectory;
	
	private String swfDirectory;
	
	@Autowired
	private BatchUploadFileConvertor fileConvertor;
	
	@Autowired
	@Qualifier("xmlProcessorConfig")
	public void setProperties(Properties xmlProcessorConfig){
		uploadDirectory = xmlProcessorConfig.getProperty("resume.path.upload");
		swfDirectory = xmlProcessorConfig.getProperty("resume.path.swf");
	}

	@RequestMapping("/talent/initAttachementUpload.do")
	public ModelAndView initTalentBatchUpload(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/resumeBatchUpload");
		
		String talentID = request.getParameter("_id");
		AttachmentCurriculumVitaeDTO cvDto = new AttachmentCurriculumVitaeDTO();
		cvDto.setTalentID(talentID);
		
		mv.addObject(SessionAttributeConstant.ATTACHMENT_CV_DTO , cvDto);
		return mv;
	}
	
	@RequestMapping("/talent/submitAttachementUpload.do")
	public ModelAndView submitAttachementUpload(HttpServletRequest request,@RequestParam("uploadFile") MultipartFile uploadFile , @ModelAttribute(SessionAttributeConstant.ATTACHMENT_CV_DTO)AttachmentCurriculumVitaeDTO cvDto){
		ModelAndView mv = new ModelAndView("talent/uploadSuccess");
		
		
		try {
			String uploadPath = getUploadDirectory(cvDto.getTalentID());
			String fileName = FileUtils.genUUIDFileName(FileUtils.getExtension(uploadFile.getOriginalFilename()));
			FileUtils.write2Disk(uploadPath,fileName , uploadFile.getInputStream());
			if(FileUtils.isPdfFile(uploadFile.getOriginalFilename())){
				fileConvertor.convertPdf2Swf(uploadPath + File.separator + fileName , getSwfDirectory(cvDto.getTalentID()) + File.separator +  FileUtils.genUUIDFileName(FileUtils.SWF_FILE_EXT));
			}else if(FileUtils.isWordFile(uploadFile.getOriginalFilename())){
				
			}
		} catch (Exception e) {
			logger.error(">>>>>Exception Catched(submitAttachementUpload) :" + e.getMessage());
		}
		return mv;
	}
	
	private void setPath(AttachmentCurriculumVitaeDTO cvDto){
		
	}
	
	private String getUploadDirectory(String talentID) throws Exception{
		return uploadDirectory + File.separator + StringEncryptUtils.encrypt(talentID, StringEncryptUtils.KEY_MD5);
	}
	
	private String getSwfDirectory(String talentID) throws Exception{
		return swfDirectory + File.separator + StringEncryptUtils.encrypt(talentID, StringEncryptUtils.KEY_MD5);
	}
	
	@RequestMapping("/talent/ViewAttachmentOnline.do")
	public ModelAndView ViewAttachmentOnline(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("common/viewAttachmentOnline");
		//String attachmentID = request.getParameter("_id");
		//read from DB
		return mv;
	}
}
