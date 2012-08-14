package com.pccw.ehunter.dao;

import java.util.List;

public interface TalentCommonDAO {
	public List<Object> getTalentSource();
	public List<Object> getSubjectsByType(String typeCode);
}
