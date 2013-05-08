package cn.org.polaris.error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class Errors {
	
	private List<BuisnessError> errors = new ArrayList<BuisnessError>();

	public List<BuisnessError> getErrors() {
		return errors;
	}

	public void setErrors(List<BuisnessError> errors) {
		this.errors = errors;
	}
	
	public void rejectValue(String code , String[] args , String defaultMsg){
		BuisnessError error = new BuisnessError();
		
		error.setCode(code);
		error.setArgs(args);
		error.setDefaultMsg(defaultMsg);
		
		this.errors.add(error);
	}
	
	public boolean hasErrors(){
		return !CollectionUtils.isEmpty(errors);
	}
}
