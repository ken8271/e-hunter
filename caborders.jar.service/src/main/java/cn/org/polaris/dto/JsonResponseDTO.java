package cn.org.polaris.dto;

import java.util.List;

public class JsonResponseDTO {

	private boolean success;

	private List<String> messages;

	public JsonResponseDTO() {

	}

	public JsonResponseDTO(boolean success) {
		this.success = success;
	}
	
	public JsonResponseDTO(boolean success , List<String> messages){
		this.success = success;
		this.messages = messages;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}
