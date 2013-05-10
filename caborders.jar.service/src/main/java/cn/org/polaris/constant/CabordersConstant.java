package cn.org.polaris.constant;

public class CabordersConstant {

	public static final String INFO_INDUSTRY = "I";
	public static final String INFO_DIGEST = "D";
	public static final String INFO_OTHER = "O";
	
	public static final String INFO_INDUSTRY_DESC = "行业动态";
	public static final String INFO_DIGEST_DESC = "管理文摘";
	public static final String INFO_OTHER_DESC = "其他资讯";
	
	public static final int DEFAULT_MAX_ROWS = 3;
	public static final int POSITION_MAX_ROWS = 2;
	
	public static final String LIST_OF_INFO = "listOfInfo";
	public static final String INFO_DETAIL = "infoDto";
	
	public static final String LIST_OF_POSITION = "listOfPosition";
	public static final String POSITION_DTO = "positionDto";
	
	public static final String INFO_CENTER_PAGE_INFO = "infoCenterPageInfoDto";
	public static final String POSITION_PAGE_INFO = "positionPageInfoDto";
	
	public static final String LOGICAL_TRUE = "true";
	public static final String LOGICAL_FALSE = "false";
	
	public static String getCategoryDesc(String category){
		if(INFO_INDUSTRY.equals(category)){
			return INFO_INDUSTRY_DESC;
		} else if(INFO_DIGEST.equals(category)){
			return INFO_DIGEST_DESC;
		} else if(INFO_OTHER.equals(category)){
			return INFO_OTHER_DESC;
		} else {
			return "";
		}
	}
}
