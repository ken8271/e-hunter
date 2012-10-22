package com.pccw.ehunter.mvc.talent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.BatchUploadConstant;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.convertor.TalentConvertor;
import com.pccw.ehunter.dto.BatchUploadDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.helper.ExcelHelper;
import com.pccw.ehunter.mvc.BaseController;

@Controller
@SessionAttributes({
	SessionAttributeConstant.BATCH_UPLOAD_DTO
})
public class TalentDraftBatchUploadController extends BaseController{
	
	@Autowired
	private ExcelHelper excel2003Helper;

	@RequestMapping("/talent/initDraftBatchUpload.do")
	public ModelAndView initTalentBatchUpload(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/draftBatchUpload");
		
		mv.addObject(SessionAttributeConstant.BATCH_UPLOAD_DTO, new BatchUploadDTO());
		return mv;
	}
	
	@RequestMapping(value = "/talent/downloadBatchUploadTemplate.do")
	public void downloadBatchUploadTemplate(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;fileName="+ BatchUploadConstant.TALENT_BATCH_UPLOAD_FILE_NAME + ".xls");
			
			excel2003Helper.createExcel(response.getOutputStream(), BatchUploadConstant.TEMPLATE_TITLE);
		} catch (Exception e) {
			logger.error(">>>>> exception catched (downloadBatchUploadTemplate) : " + e.getMessage());
		}
	}
	
	@RequestMapping("/talent/submitBatchUploadDraft.do")
	public ModelAndView submitBatchUploadDraft(HttpServletRequest request,@RequestParam("uploadFile") MultipartFile uploadFile){
		ModelAndView mv = new ModelAndView("talent/batchUploadVerifyInfo");
		
		List<TalentDTO> dtos = new ArrayList<TalentDTO>();
		try {
			dtos = TalentConvertor.toDtos(excel2003Helper.parseExcel(uploadFile.getInputStream()));
		} catch (IOException e) {
			logger.error(">>>>> exception catched (submitBatchUploadDraft) : " + e.getMessage());
		}
		
		mv.addObject(SessionAttributeConstant.LIST_OF_TALENT, dtos);
		return mv;
	}
}
