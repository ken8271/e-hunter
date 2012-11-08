package com.pccw.ehunter.mvc.system;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.limit.Limit;
import org.jmesa.model.PageItems;
import org.jmesa.model.TableModel;
import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.DateFormatConstant;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.convertor.TransactionLogConvertor;
import com.pccw.ehunter.dto.TransactionLogDTO;
import com.pccw.ehunter.dto.TransactionLogEnquireDTO;
import com.pccw.ehunter.dto.TransactionLogPagedCriteria;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.utility.DateUtils;
import com.pccw.ehunter.utility.StringUtils;

@Controller
@SessionAttributes({
	SessionAttributeConstant.TRANSACTION_LOG_ENQUIRE_DTO
})
public class TransactionLogController extends BaseController{

	@RequestMapping("/system/initTransactionlogSearch.do")
	public ModelAndView initTransactionlogSearch(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("system/listOfTransactionlog");
		
		mv.addObject(SessionAttributeConstant.TRANSACTION_LOG_ENQUIRE_DTO, new TransactionLogEnquireDTO());
		return mv;
	}
	
	@RequestMapping("/system/searchTransactionlog.do")
	public ModelAndView searchTransactionlog(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.TRANSACTION_LOG_ENQUIRE_DTO)TransactionLogEnquireDTO txlogEnquireDto){
		ModelAndView mv = new ModelAndView("system/listOfTransactionlog");
		
		handlePagedSearch(request , mv , txlogEnquireDto);
		
		mv.addObject(SessionAttributeConstant.TRANSACTION_LOG_ENQUIRE_DTO, txlogEnquireDto);
		return mv;
	}

	private void handlePagedSearch(HttpServletRequest request, ModelAndView mv, final TransactionLogEnquireDTO txlogEnquireDto) {
		TableModel model = new TableModel("_jmesa_tx_log", request);
		model.autoFilterAndSort(false);
		model.setStateAttr("restore");
		
		model.setItems(new PageItems() {
			
			@Override
			public int getTotalRows(Limit limit) {
				return transactionLogService.getTransactionlogCountByCriteria(TransactionLogConvertor.toPagedCriteria(txlogEnquireDto));
			}
			
			@Override
			public Collection<TransactionLogDTO> getItems(Limit limit) {
				TransactionLogPagedCriteria pagedCriteria = TransactionLogConvertor.toPagedCriteria(txlogEnquireDto);
				
				int rowStart = limit.getRowSelect().getRowStart();
				int rowEnd = limit.getRowSelect().getRowEnd();
				
				pagedCriteria.getPageFilter().setRowStart(rowStart);
				pagedCriteria.getPageFilter().setRowEnd(rowEnd);
				
				return transactionLogService.getTransactionlogByCriteria(pagedCriteria);
			}
		});
		
		model.setTable(getHtmlTable(request));
		
		mv.addObject(SessionAttributeConstant.LIST_OF_TRANSACTION_LOG, model.render());
	}

	private Table getHtmlTable(final HttpServletRequest request) {
		Table table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.setSortable(false);
		table.setRow(row);
		
		HtmlColumn txDttm = new HtmlColumn("transactionDatetime");
		txDttm.setWidth("25%");
		txDttm.setTitle("操作时间");
		txDttm.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				TransactionLogDTO dto = (TransactionLogDTO)item;
				
				if(dto.getTransactionDatetime() != null){
					return DateUtils.formatDateTime(DateFormatConstant.DATE_TIME, dto.getTransactionDatetime());
				}
				return StringUtils.EMPTY_STRING;
			}
		});
		row.addColumn(txDttm);
		
		HtmlColumn module = new HtmlColumn("functionIndicator");
		module.setWidth("10%");
		module.setTitle("操作模块");
		row.addColumn(module);
		
		HtmlColumn txUser = new HtmlColumn("userName");
		txUser.setWidth("15%");
		txUser.setTitle("操作用户");
		row.addColumn(txUser);
		
		HtmlColumn txMsg = new HtmlColumn("transactionMsg");
		txMsg.setWidth("50%");
		txMsg.setTitle("操作日志");
		row.addColumn(txMsg);
		
		return table;
	}
}
