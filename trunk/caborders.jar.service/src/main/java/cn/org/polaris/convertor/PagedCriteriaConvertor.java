package cn.org.polaris.convertor;

import cn.org.polaris.dto.PagedCriteria;
import cn.org.polaris.dto.RowSelect;

public class PagedCriteriaConvertor {
	
	public static void convert2PagedCriteria(PagedCriteria pagedCriteria , RowSelect select){
		if(pagedCriteria != null && select != null){
			pagedCriteria.setRowStart(select.getRowStart());
			pagedCriteria.setRowEnd(select.getRowEnd());
		}
	}
}
