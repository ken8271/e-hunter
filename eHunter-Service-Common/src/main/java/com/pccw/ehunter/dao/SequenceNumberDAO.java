package com.pccw.ehunter.dao;

import org.hibernate.LockMode;

public interface SequenceNumberDAO {
	public long getNextValue(String key , LockMode lockmod);
	public long getCurrValue(String key , LockMode lockmod);
}
