package com.pccw.ehunter.mvc.talent;

import java.io.File;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.pccw.ehunter.constant.ModuleIndicator;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.UploadedCurriculumVitaeDTO;
import com.pccw.ehunter.helper.BatchUploadFileConvertor;
import com.pccw.ehunter.helper.ContentSearchEngine;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.CurriculumVitaeUploadService;
import com.pccw.ehunter.utility.FileUtils;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.StringEncryptUtils;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.validator.UploadedCurriculumVitaeValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.UPLOADED_CV_DTO
})
public class CurriculumVitaeUploadController extends BaseController{
	
	private String uploadDirectory;
	
	private String swfDirectory;
	
	@Autowired
	private BatchUploadFileConvertor fileConvertor;
	
	@Autowired
	private CurriculumVitaeUploadService cvUploadService;
	
	@Autowired
	private UploadedCurriculumVitaeValidator cvUploadValidator;
	
	@Autowired
	private ContentSearchEngine contentSearchEngine;
	
	@Autowired
	@Qualifier("xmlProcessorConfig")
	public void setProperties(Properties xmlProcessorConfig){
		uploadDirectory = xmlProcessorConfig.getProperty("resume.path.upload");
		swfDirectory = xmlProcessorConfig.getProperty("resume.path.swf");
	}

	@RequestMapping("/talent/initCurriculumVitaeUpload.do")
	public ModelAndView initCurriculumVitaeUpload(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/curriculumVitaeUpload");
		
		String talentID = request.getParameter("_id");
		UploadedCurriculumVitaeDTO cvDto = new UploadedCurriculumVitaeDTO();
		cvDto.setTalentID(talentID);
		cvDto.setBaseUploadDir(uploadDirectory);
		cvDto.setBaseSwfDir(swfDirectory);
		
		mv.addObject(SessionAttributeConstant.UPLOADED_CV_DTO , cvDto);
		return mv;
	}
	
	@RequestMapping("/talent/submitCurriculumVitaeUpload.do")
	public ModelAndView submitCurriculumVitaeUpload(HttpServletRequest request,@RequestParam("uploadFile") MultipartFile uploadFile , @ModelAttribute(SessionAttributeConstant.UPLOADED_CV_DTO)UploadedCurriculumVitaeDTO cvDto , BindingResult errors){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/talent/viewTalentDetail.do", true));
		
		try {
			//validation
			if(uploadFile == null || StringUtils.isEmpty(uploadFile.getOriginalFilename())){
				errors.rejectValue("size", "EHT-E-0010", null , "Please select the upload file [EHT-E-0012]");
			}
			
			if(errors.hasErrors()){
				mv = new ModelAndView("talent/curriculumVitaeUpload");
				mv.addObject(SessionAttributeConstant.UPLOADED_CV_DTO, cvDto);
				return mv;
			}
			
			String type = FileUtils.getExtension(uploadFile.getOriginalFilename());
			cvDto.setType(type);
			cvDto.setSize(Long.toString(uploadFile.getSize()));
			
			cvUploadValidator.validate(cvDto, errors);
			
			if(errors.hasErrors()){
				mv = new ModelAndView("talent/curriculumVitaeUpload");
				mv.addObject(SessionAttributeConstant.UPLOADED_CV_DTO, cvDto);
				return mv;
			}
			
			//set properties
			cvDto.setRelativeUploadPath(File.separator + StringEncryptUtils.encrypt(cvDto.getTalentID(), StringEncryptUtils.KEY_MD5) + File.separator + FileUtils.replaceFileNameWithUUID(type));
			cvDto.setRelativeSwfPath(File.separator + StringEncryptUtils.encrypt(cvDto.getTalentID(), StringEncryptUtils.KEY_MD5) + File.separator + FileUtils.replaceFileNameWithUUID(FileUtils.SWF_FILE_EXT));
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
				fileConvertor.convertOffice2Swf(cvDto.getUploadPath(), cvDto.getSwfPath());
				cvDto.setConverted(CommonConstant.YES);
			}
			
			//create index
			contentSearchEngine.createIndex(cvDto.getTalentID(), cvDto.getUploadPath());
			
			cvUploadService.saveUploadedCurriculumVitae(cvDto);
			
			transactionLogService.logTransaction(ModuleIndicator.TALENT, getMessage("tx.log.talent.cv.upload" , new String[]{cvDto.getTalentID() , cvDto.getCvID()}));
			
		} catch (Exception e) {
			logger.error(">>>>>Exception Catched(submitAttachementUpload) :" + e.getMessage());
		}
		
		mv.addObject("_id", cvDto.getTalentID());
		return mv;
	}
	
	@RequestMapping("/talent/pop/viewCurriculumVitaeOnline.do")
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
			mv.addObject(SessionAttributeConstant.UPLOADED_CV_DTO, cvDto);
			
			transactionLogService.logTransaction(ModuleIndicator.TALENT, getMessage("tx.log.talent.cv.view" , new String[]{cvDto.getTalentID() , cvDto.getCvID()}));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(">>>>>Exception Catched(ViewCurriculumVitaeOnline) : " + e.getMessage());
		}

		return mv;
	}
	
	@RequestMapping("/talent/downloadCurriculumVitae.do")
	public void downloadCurriculumVitae(HttpServletRequest request , HttpServletResponse response){
		OutputStream out = null;
		UploadedCurriculumVitaeDTO cvDto = null;
		try {
			
			String id = request.getParameter("_id");
			
			cvDto = cvUploadService.getUploadedCurriculumVitaeByID(id);
			
			File file = new File(uploadDirectory + cvDto.getRelativeUploadPath());
			if(cvDto != null && file.exists()){
				//REMINDER : It should set Header first
				response.setHeader("Content-Disposition", "attachment;fileName="+ StringUtils.toUtf8String(cvDto.getCvName()) + FileUtils.getExtension(cvDto.getRelativeUploadPath()));
				out = response.getOutputStream();
				FileUtils.readfile(file, out);
			}
			
			out.flush();
			
			transactionLogService.logTransaction(ModuleIndicator.TALENT, getMessage("tx.log.talent.cv.download" , new String[]{cvDto.getTalentID() , cvDto.getCvID()}));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeIOStream(out);
		}
	}
	
	@RequestMapping("/talent/deleteCurriculumVitae.do")
	public ModelAndView deleteCurriculumVitae(HttpServletRequest request){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/talent/viewTalentDetail.do", true));
		String id = request.getParameter("_id");
		
		UploadedCurriculumVitaeDTO cvDto = cvUploadService.getUploadedCurriculumVitaeByID(id);
		
		String talentID = cvDto.getTalentID();
		
		if(cvDto != null){
			FileUtils.handleDelete(new File(uploadDirectory + cvDto.getRelativeUploadPath()));
			FileUtils.handleDelete(new File(swfDirectory + cvDto.getRelativeSwfPath()));
			cvUploadService.deleteCurriculumVitae(cvDto);
			transactionLogService.logTransaction(ModuleIndicator.TALENT, getMessage("tx.log.talent.cv.delete" , new String[]{talentID , id}));
		}
		
		mv.addObject("_id", cvDto.getTalentID());
		
		return mv;
	}
	
	private void closeIOStream(OutputStream out){
		try {
			if(out != null){
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
