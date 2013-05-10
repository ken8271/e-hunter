package cn.org.polaris.error;

import java.util.List;

public interface Errors {
	
	public boolean hasErrors();
	
	public void rejectValue(String code , String[] args , String defaultMsg);
	
	public List<ErrorBean> getErrors();
}
