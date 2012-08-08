package com.pccw.ehunter.dao;

import org.hibernate.LockMode;

public interface IDGeneratorDAO {
	public Long getNextValue(String key);
	public Long getNextValueNoWait(String key);
	public Long getNextValue(String key , LockMode lockmod);
	public Long getCurrValue(String key);
	public Long getCurrValueNoWait(String key);
	public Long getCurrValue(String key , LockMode lockmod);
}
