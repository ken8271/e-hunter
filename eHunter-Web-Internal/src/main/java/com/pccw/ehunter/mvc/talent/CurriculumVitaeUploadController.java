package com.pccw.ehunter.mvc.talent;

import java.io.File;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.UploadedCurriculumVitaeDTO;
import com.pccw.ehunter.helper.BatchUploadFileConvertor;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.CurriculumVitaeUploadService;
import com.pccw.ehunter.utility.FileUtils;
import com.pccw.ehunter.utility.StringEncryptUtils;

@Controller
@SessionAttributes({
	SessionAttributeConstant.ATTACHMENT_CV_DTO
})
public class CurriculumVitaeUploadController extends BaseController{
	
	private String uploadDirectory;
	
	private String swfDirectory;
	
	@Autowired
	private BatchUploadFileConvertor fileConvertor;
	
	@Autowired
	private CurriculumVitaeUploadService cvUploadService;
	
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
		UploadedCurriculumVitaeDTO cvDto = new UploadedCurriculumVitaeDTO();
		cvDto.setTalentID(talentID);
		cvDto.setBaseUploadDir(uploadDirectory);
		cvDto.setBaseSwfDir(swfDirectory);
		
		mv.addObject(SessionAttributeConstant.ATTACHMENT_CV_DTO , cvDto);
		return mv;
	}
	
	@RequestMapping("/talent/submitAttachementUpload.do")
	public ModelAndView submitAttachementUpload(HttpServletRequest request,@RequestParam("uploadFile") MultipartFile uploadFile , @ModelAttribute(SessionAttributeConstant.ATTACHMENT_CV_DTO)UploadedCurriculumVitaeDTO cvDto , BindingResult errors){
		ModelAndView mv = new ModelAndView("talent/uploadSuccess");
		
		try {
			//validation
			
			if(errors.hasErrors()){
				mv = new ModelAndView("talent/resumeBatchUpload");
				mv.addObject(SessionAttributeConstant.ATTACHMENT_CV_DTO, cvDto);
				return mv;
			}
			
			//set properties
			cvDto.setRelativeUploadPath(File.separator + StringEncryptUtils.encrypt(cvDto.getTalentID(), StringEncryptUtils.KEY_MD5) + File.separator + FileUtils.replaceFileNameWithUUID(FileUtils.getExtension(uploadFile.getOriginalFilename())));
			cvDto.setRelativeSwfPath(File.separator + StringEncryptUtils.encrypt(cvDto.getTalentID(), StringEncryptUtils.KEY_MD5) + File.separator + FileUtils.replaceFileNameWithUUID(FileUtils.SWF_FILE_EXT));
			cvDto.setSize(Long.toString(uploadFile.getSize()));
			cvDto.setEncrypted(CommonConstant.NO);
			cvDto.setConverted(CommonConstant.NO);
			
			//prepare disk files
			cvDto.prepareDiskDirs();
			
			//write the original file to disk
			FileUtils.write2Disk(new File(cvDto.getUploadPath()), uploadFile.getInputStream(),false);
			
			//convert upload file to swf
			if(FileUtils.isPdfFile(uploadFile.getOriginalFilename())){
				fileConvertor.convertPdf2Swf(cvDto.getUploadPath(),cvDto.getSwfPath());
				cvDto.setConverted(CommonConstant.YES);
			}else if(FileUtils.isWordFile(uploadFile.getOriginalFilename())){
				
			}
			
			cvUploadService.saveUploadedCurriculumVitae(cvDto);
			
		} catch (Exception e) {
			logger.error(">>>>>Exception Catched(submitAttachementUpload) :" + e.getMessage());
		}
		return mv;
	}
	
	@RequestMapping("/talent/ViewCurriculumVitaeOnline.do")
	public ModelAndView ViewCurriculumVitaeOnline(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("common/viewCurriculumVitaeOnline");
		
		try {
			String id = request.getParameter("_id");
			
			UploadedCurriculumVitaeDTO cvDto = cvUploadService.getUploadedCurriculumVitaeByID(id);
			
			String tempPath = request.getSession(false).getServletContext().getRealPath("temp");
			
			File swf = new File(swfDirectory + cvDto.getRelativeSwfPath());
			
			if(!swf.exists()){
				throw new Exception("The swf file doesn't exist , path : " + swfDirectory + cvDto.getRelativeSwfPath());
			}
			
			File temp = new File(tempPath);
			if(!temp.exists()){
				temp.mkdirs();
			}
			
			FileUtils.copyFile(swf, new File(tempPath + File.separator + cvDto.getSwfFileName()));
			
			mv.addObject("swfPath", cvDto.getSwfFileName());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(">>>>>Exception Catched(ViewCurriculumVitaeOnline) : " + e.getMessage());
		}

		return mv;
	}
}
