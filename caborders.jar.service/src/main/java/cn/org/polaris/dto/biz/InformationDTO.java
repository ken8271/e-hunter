package cn.org.polaris.dto.biz;

import cn.org.polaris.dto.BaseDTO;

public class InformationDTO extends BaseDTO {
	private static final long serialVersionUID = 6693328889207962545L;

	private String systemRefInfo;
	private String title;
	private String content;
	private String category;

	public String getSystemRefInfo() {
		return systemRefInfo;
	}

	public void setSystemRefInfo(String systemRefInfo) {
		this.systemRefInfo = systemRefInfo;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
