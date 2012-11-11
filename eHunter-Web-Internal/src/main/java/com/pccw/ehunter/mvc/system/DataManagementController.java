package com.pccw.ehunter.mvc.system;

import java.io.File;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jmesa.limit.Limit;
import org.jmesa.model.PageItems;
import org.jmesa.model.TableModel;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.DateFormatConstant;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.DataBackupHistoryDTO;
import com.pccw.ehunter.dto.PagedCriteria;
import com.pccw.ehunter.helper.DataBackupHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.DataBackupHistoryService;
import com.pccw.ehunter.utility.DateUtils;
import com.pccw.ehunter.utility.FileUtils;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.SecurityUtils;
import com.pccw.ehunter.utility.StringUtils;

@Controller
@SessionAttributes({
	SessionAttributeConstant.BACKUP_HISTORY_DTO
})
public class DataManagementController extends BaseController{
	
	@Autowired
	private DataBackupHistoryService dataBackupHistoryService;
	
	@Autowired
	private DataBackupHelper dataBackupHelper;
	
	private String backupPath;
	
	@Autowired
	@Qualifier("xmlProcessorConfig")
	public void setProperties(Properties xmlProcessorConfig){
		backupPath = xmlProcessorConfig.getProperty("data.backup.path");	
	}

	@RequestMapping("/system/initDataManagement.do")
	public ModelAndView initDataManagement(HttpServletRequest request){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/system/listOfDataBackupHistory.do", true));
		return mv;
	}
	
	@RequestMapping("/system/listOfDataBackupHistory.do")
	public ModelAndView listOfDataBackupHistory(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("system/dataBackupManagement");
		
		handlePagedSeach(request , mv);
		
		return mv;
	}

	private void handlePagedSeach(HttpServletRequest request, ModelAndView mv) {
		TableModel model = new TableModel("_jmesa_bcup_hst", request);
		model.autoFilterAndSort(false);
		model.setStateAttr("restore");
		
		model.setItems(new PageItems() {
			
			@Override
			public int getTotalRows(Limit limit) {
				return dataBackupHistoryService.getBackupHistoryCountByCriteria(new PagedCriteria());
			}
			
			@Override
			public Collection<DataBackupHistoryDTO> getItems(Limit limit) {
				PagedCriteria pagedCriteria = new PagedCriteria();
				
				int rowStart = limit.getRowSelect().getRowStart();
				int rowEnd = limit.getRowSelect().getRowEnd();
				
				pagedCriteria.getPageFilter().setRowStart(rowStart);
				pagedCriteria.getPageFilter().setRowEnd(rowEnd);
				
				return dataBackupHistoryService.getBackupHistoriesByCriteria(pagedCriteria);
			}
		});
		
		model.setTable(getHtmlTable(request));
		
		mv.addObject(SessionAttributeConstant.LIST_OF_BACKUP_HISTORY, model.render());
	}

	private Table getHtmlTable(HttpServletRequest request) {
		Table table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.setSortable(false);
		table.setRow(row);
		
		HtmlColumn backupDttm = new HtmlColumn("backupDttm");
		backupDttm.setWidth("20%");
		backupDttm.setTitle("备份时间");
		backupDttm.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				DataBackupHistoryDTO dto = (DataBackupHistoryDTO)item;
				
				if(dto.getBackupDttm() != null){
					return DateUtils.formatDateTime(DateFormatConstant.DATE_TIME,dto.getBackupDttm());
				}
				
				return StringUtils.EMPTY_STRING;
			}
		});
		row.addColumn(backupDttm);
		
		HtmlColumn channel = new HtmlColumn("backupChannel");
		channel.setWidth("15%");
		channel.setTitle("备份渠道");
		channel.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				return CommonConstant.getBackupChannel(((DataBackupHistoryDTO)item).getBackupChannel());
			}
		});
		row.addColumn(channel);
		
		HtmlColumn fileName = new HtmlColumn("fileName");
		fileName.setWidth("50%");
		fileName.setTitle("备份文件名");
		row.addColumn(fileName);
		
		HtmlColumn backupBy = new HtmlColumn("backupBy");
		backupBy.setWidth("15%");
		backupBy.setTitle("备份人");
		backupBy.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				DataBackupHistoryDTO dto = (DataBackupHistoryDTO)item;
				
				if(!StringUtils.isEmpty(dto.getBackupBy())){
					return dto.getBackupBy();
				}
				return CommonConstant.ANOYMOUS_INTERNAL_USER;
			}
		});
		row.addColumn(backupBy);
		
		return table;
	}
	
	@RequestMapping("/system/backupDataImmediately.do")
	public ModelAndView backupDataImmediately(HttpServletRequest request){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/system/dataBackupSuccess.do", true));
		
		DataBackupHistoryDTO dto = null;
		try {
			String backupFileName = FileUtils.replaceFileNameWithUUID(DateUtils.formatDateTime(DateFormatConstant.DATE_YYYYMMDD, new Date()), FileUtils.SQL_FILE_EXT);
			if(dataBackupHelper.handleDataBackup(backupFileName) != -1){
				logger.info(">>>>> data backup successfully ... ... ");
				dto = new DataBackupHistoryDTO();
				dto.setFileName(backupFileName);
				dto.setBackupDttm(new Date());
				dto.setBackupChannel(CommonConstant.BACKUP_CHANNEL_OF_WEB);
				dto.setBackupBy(SecurityUtils.getUserRecId());
				dataBackupHistoryService.saveBackupHistory(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(">>>>> Exception catched (handleDataBackup) : " + e.getMessage());
			return new ModelAndView(new RedirectViewExt("/system/listOfDataBackupHistory.do", true));
		}
		
		mv.addObject(SessionAttributeConstant.BACKUP_HISTORY_DTO, dto);
		return mv;
	}
	
	@RequestMapping("/system/dataBackupSuccess.do")
	public ModelAndView dataBackupSuccess(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.BACKUP_HISTORY_DTO)DataBackupHistoryDTO backupHistoryDto){
		ModelAndView mv = new ModelAndView("system/dataBackupSuccess");
		mv.addObject(SessionAttributeConstant.BACKUP_HISTORY_DTO, backupHistoryDto);
		return mv;
	}
	
	@RequestMapping("/system/downloadBackupFile.do")
	public void downloadBackupFile(HttpServletRequest request , HttpServletResponse response){
		OutputStream out = null;
		DataBackupHistoryDTO dto = null;
		try {
			
			String id = request.getParameter("_id");
			
			dto = dataBackupHistoryService.getBackupHistoryByID(id);
			
			if(dto == null){
				dto = new DataBackupHistoryDTO();
				dto.setHistoryID(id);
			}
			
			File file = new File(backupPath + File.separator + dto.getFileName());
			if(file.exists()){
				//REMINDER : It should set Header first
				String fileName = dto.getFileName();
				
				if(!StringUtils.isEmpty(fileName)) {
					fileName = fileName.substring(0, fileName.lastIndexOf("."));
				}else {
					fileName = UUID.randomUUID().toString();
				}
				
				response.setHeader("Content-Disposition", "attachment;fileName="+ StringUtils.toUtf8String(fileName) + FileUtils.SQL_FILE_EXT);
				out = response.getOutputStream();
				FileUtils.readfile(file, out);
				
				out.flush();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeIOStream(out);
		}
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
