package cn.org.polaris.dto;

import java.util.List;

public class JsonResponseDTO {

	private boolean success;

	private List<MessageDTO> messages;

	public JsonResponseDTO() {

	}

	public JsonResponseDTO(boolean success) {
		this.success = success;
	}
	
	public JsonResponseDTO(boolean success , List<MessageDTO> messages){
		this.success = success;
		this.messages = messages;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<MessageDTO> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}

}
