package cn.org.polaris.dto;

public class AjaxResponseDTO {
	private boolean success;

	public AjaxResponseDTO() {

	}
	
	public AjaxResponseDTO(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
