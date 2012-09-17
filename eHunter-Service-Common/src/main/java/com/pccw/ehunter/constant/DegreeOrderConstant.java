package com.pccw.ehunter.constant;

import java.util.ArrayList;
import java.util.List;

public class DegreeOrderConstant {
	public static final String JUNIOR_HIGH_SCHOOL = "JHS";
	public static final String TECHNICAL_SCHOOL = "THC";
	public static final String SENIOR_HIGH_SCHOOL = "SHS";
	public static final String POLYTECHNIC_SCHOOL = "PLT";
	public static final String ASSOCIATE_DEGREE = "ASC";
	public static final String BACHELOR_DEGREE = "BCL";
	public static final String MASTER_DEGREE = "MST";
	public static final String MASTER_OF_BUISNESS_ADMINISTRATION = "MBA";
	public static final String DOCTOR_DEGREE = "DOC";
	
	public static final String OTHER = "OTH";
	
	public static final List<String> DEGREE_ORDER_LIST = new ArrayList<String>();
	
	static {
		DEGREE_ORDER_LIST.add(JUNIOR_HIGH_SCHOOL);
		DEGREE_ORDER_LIST.add(TECHNICAL_SCHOOL);
		DEGREE_ORDER_LIST.add(SENIOR_HIGH_SCHOOL);
		DEGREE_ORDER_LIST.add(POLYTECHNIC_SCHOOL);
		DEGREE_ORDER_LIST.add(ASSOCIATE_DEGREE);
		DEGREE_ORDER_LIST.add(BACHELOR_DEGREE);
		DEGREE_ORDER_LIST.add(MASTER_DEGREE);
		DEGREE_ORDER_LIST.add(MASTER_OF_BUISNESS_ADMINISTRATION);
		DEGREE_ORDER_LIST.add(DOCTOR_DEGREE);
	}
}
