package cn.org.polaris.dto;

public class JsonDataResponseDTO extends JsonResponseDTO {

	private CabordersDTO data;

	public JsonDataResponseDTO() {
		super();
	}
	
	public JsonDataResponseDTO(boolean success , CabordersDTO data) {
		super(success);
		this.data = data;
	}

	public CabordersDTO getData() {
		return data;
	}

	public void setData(CabordersDTO data) {
		this.data = data;
	}

}
