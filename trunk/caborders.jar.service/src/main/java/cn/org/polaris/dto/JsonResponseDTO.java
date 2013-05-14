package cn.org.polaris.dto;

public class JsonResponseDTO {

	private boolean success;

	public JsonResponseDTO() {
		super();
	}

	public JsonResponseDTO(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
