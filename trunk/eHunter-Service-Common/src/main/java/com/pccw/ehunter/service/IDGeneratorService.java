package com.pccw.ehunter.service;

public interface IDGeneratorService {
	public Long getNextValue(String key , boolean nowait);
	public Long getCurrValue(String key , boolean nowait);
}
