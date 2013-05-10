package cn.org.polaris.validator;

import cn.org.polaris.error.Errors;

public interface CabordersValidator {
	public Errors validate(Object target);
	public Errors initErrors() ;
	public Errors doInternalValidate(Object target , Errors errors); 
}
