package cn.org.polaris.error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class ValidationErrors implements Errors{
	
	private List<ErrorBean> errorBeans = new ArrayList<ErrorBean>();

	public List<ErrorBean> getErrorBeans() {
		if(CollectionUtils.isEmpty(errorBeans)){
			errorBeans = new ArrayList<ErrorBean>();
		}
		return errorBeans;
	}

	public void setErrorBeans(List<ErrorBean> errorBeans) {
		this.errorBeans = errorBeans;
	}

	@Override
	public boolean hasErrors() {
		return !CollectionUtils.isEmpty(getErrorBeans());
	}

	@Override
	public void rejectValue(String code, String[] args, String defaultMsg) {
		getErrorBeans().add(new ErrorBean(code , args, defaultMsg));
	}

	@Override
	public List<ErrorBean> getErrors() {
		return getErrorBeans();
	}

}
