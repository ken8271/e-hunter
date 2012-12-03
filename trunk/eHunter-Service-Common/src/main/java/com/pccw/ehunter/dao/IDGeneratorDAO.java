package com.pccw.ehunter.dao;

import org.hibernate.LockMode;

public interface IDGeneratorDAO {
	public Long getNextValue(String key , boolean needCycle);
	public Long getNextValueNoWait(String key ,  boolean needCycle);
	public Long getNextValue(String key , LockMode lockmod ,  boolean needCycle);
	public Long getCurrValue(String key);
	public Long getCurrValueNoWait(String key);
	public Long getCurrValue(String key , LockMode lockmod);
}
