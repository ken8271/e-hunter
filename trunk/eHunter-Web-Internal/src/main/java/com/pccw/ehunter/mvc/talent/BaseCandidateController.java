package com.pccw.ehunter.mvc.talent;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.view.component.Table;
import org.jmesa.view.editor.CellEditor;
import org.jmesa.view.editor.HeaderEditor;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.component.HtmlColumn;
import org.jmesa.view.html.component.HtmlRow;
import org.jmesa.view.html.component.HtmlTable;

import com.pccw.ehunter.constant.ModuleIndicator;
import com.pccw.ehunter.dto.JmesaCheckBoxDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.utility.URLUtils;

public class BaseCandidateController extends BaseController{
	
	public static final String MODULE_TALENT_MGMT = "talentMgmt";
	
	public static final String MODULE_CANDIDATE_CONTACT_MGMT = "candidateContactMgmt";
	
	protected Table getHtmlTable(final HttpServletRequest request ,final JmesaCheckBoxDTO jmesaDto , final String tableId , boolean needCheckbox , final String module) {
		Table table = new HtmlTable().width("100%");
		
		HtmlRow row = new HtmlRow();
		row.setFilterable(false);
		row.setSortable(false);
		table.setRow(row);
		

		if(needCheckbox){
			HtmlColumn select = new HtmlColumn("select");
			select.setWidth("5%");
			select.setStyle("align:center");
			select.setHeaderEditor(new HeaderEditor() {
				
				@Override
				public Object getValue() {
					HtmlBuilder builder = new HtmlBuilder();
					
					String checkbox = null;
					if(jmesaDto.isSelectAll()){
						checkbox = "<input type=\"checkbox\" style=\"align:left\" onclick=\"javascript:selectAllByTableId('messageBox')\" checked=\"checked\"/>";
					}else {
						checkbox = "<input type=\"checkbox\" style=\"align:left\" onclick=\"javascript:selectAllByTableId('messageBox')\" />";
					}
					
					return builder.append(checkbox).toString();
				}
			});
			select.setCellEditor(new CellEditor() {
				
				@Override
				public Object getValue(Object item, String property, int rowcount) {
					TalentDTO dto = (TalentDTO)item;
					
					StringBuffer checkbox = new StringBuffer();
					if(jmesaDto.isSelectAll() || jmesaDto.isSelected(dto.getTalentID())){	
						checkbox.append("<input name=\"jmesaDto.select\" type=\"checkbox\" checked=\"checked\" value=\"");
					}else {
						checkbox.append("<input name=\"jmesaDto.select\" type=\"checkbox\" value=\"");
					}
					checkbox.append(dto.getTalentID());
					checkbox.append("\" />");
					checkbox.append("<input name=\"jmesaDto.currSelect\" type=\"checkbox\" value=\"");
					checkbox.append(dto.getTalentID());
					checkbox.append("\" checked=\"checked\" style=\"display:none\" />&nbsp;&nbsp;");
					
					return checkbox.toString();
				}
			});
			row.addColumn(select);
		}
		
		HtmlColumn talentId = new HtmlColumn("talentID");
		talentId.setWidth("width:20% nowrap");
		talentId.setTitle("人才编号");
		talentId.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				TalentDTO dto = (TalentDTO)item;
				StringBuffer url = new StringBuffer();
				
				if(MODULE_CANDIDATE_CONTACT_MGMT.equals(module)){
					url.append(request.getContextPath());
					url.append("/talent/viewCandidateContactHistory.do?_tid=");
					url.append(dto.getTalentID());
					url.append("&module=" + ModuleIndicator.TALENT_CONTACT_HISTORY);
				}else {
					url.append(request.getContextPath());
					url.append("/talent/viewTalentDetail.do?_id=");
					url.append(dto.getTalentID());
					url.append("&module="+ModuleIndicator.TALENT_ENQUIRY);		
				}
				
				StringBuffer buffer = new StringBuffer();
				
				buffer.append("<a href=\"");
				buffer.append(URLUtils.getHDIVUrl(request, url.toString()));
				buffer.append("\">");
				buffer.append(dto.getTalentID());
				buffer.append("</a>");
				return buffer.toString();
			}
		});
		row.addColumn(talentId);
		
		HtmlColumn name = new HtmlColumn("name");
		name.setWidth("width:20% nowrap");
		name.setTitle("姓名");
		name.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				TalentDTO dto = (TalentDTO)item;
				StringBuffer buffer = new StringBuffer();
				buffer.append(dto.getCnName());
				
				if(!StringUtils.isEmpty(dto.getEnName())){
					buffer.append("&nbsp;(");
					buffer.append(dto.getEnName());
					buffer.append(")");
				}
				return  buffer.toString();
			}
		});
		row.addColumn(name);
		
		HtmlColumn degree = new HtmlColumn("degree");
		degree.setWidth("width:20% nowrap");
		degree.setTitle("最高学历");
		degree.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				TalentDTO dto = (TalentDTO)item;
				
				if(dto.getDegreeDto() != null){
					return dto.getDegreeDto().getDisplayName();
				}else {
					return "";
				}
			}
		});
		row.addColumn(degree);
		
		HtmlColumn place = new HtmlColumn("place");
		place.setWidth("width:32% nowrap");
		place.setTitle("现居地");
		place.setCellEditor(new CellEditor() {
			
			@Override
			public Object getValue(Object item, String property, int rowcount) {
				return ((TalentDTO)item).getNowLivePlace();
			}
		});
		row.addColumn(place);
		
		return table;
	}

}
