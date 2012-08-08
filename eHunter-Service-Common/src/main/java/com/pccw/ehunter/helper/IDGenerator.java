package com.pccw.ehunter.helper;

public interface IDGenerator {
	public String generateID(String key);
	public String generateID(String key , String prefix , int length);
}
