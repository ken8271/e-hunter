package cn.org.polaris.dto.biz;

import cn.org.polaris.dto.BaseDTO;

public class ReleasedPositionDTO extends BaseDTO {
	private static final long serialVersionUID = -5296014964714967969L;

	private String systemRefPosition;
	private String title;
	private String content;
	private String workCity;

	public String getSystemRefPosition() {
		return systemRefPosition;
	}

	public void setSystemRefPosition(String systemRefPosition) {
		this.systemRefPosition = systemRefPosition;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWorkCity() {
		return workCity;
	}

	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}

}
