package cn.org.polaris.error;

public class ErrorBean {

	private String code;
	private String[] args;
	private String defaultMsg;

	public ErrorBean() {
		super();
	}

	public ErrorBean(String code, String[] args, String defaultMsg) {
		super();
		this.code = code;
		this.args = args;
		this.defaultMsg = defaultMsg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	public String getDefaultMsg() {
		return defaultMsg;
	}

	public void setDefaultMsg(String defaultMsg) {
		this.defaultMsg = defaultMsg;
	}

}
