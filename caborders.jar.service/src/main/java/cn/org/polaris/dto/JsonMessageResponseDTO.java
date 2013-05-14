package cn.org.polaris.dto;

import java.util.List;

public class JsonMessageResponseDTO extends JsonResponseDTO {

	private List<MessageDTO> messages;

	public JsonMessageResponseDTO() {
		super();
	}

	public JsonMessageResponseDTO(boolean success, List<MessageDTO> messages) {
		super(success);
		this.messages = messages;
	}

	public List<MessageDTO> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}
}
