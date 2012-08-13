package com.pccw.ehunter.helper;

import java.util.List;

import com.pccw.ehunter.dto.BaseLabelValueDTO;

public interface StaticCodeOperator {
	public List<BaseLabelValueDTO> getStaticCodes(String tableKey , String codeKey);
}
