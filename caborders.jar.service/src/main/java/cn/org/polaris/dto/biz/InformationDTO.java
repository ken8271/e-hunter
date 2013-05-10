package cn.org.polaris.dto.biz;

import cn.org.polaris.dto.BaseDTO;
import cn.org.polaris.utility.StringUtils;

public class InformationDTO extends BaseDTO {
	private static final long serialVersionUID = 6693328889207962545L;

	private String systemRefInfo;
	private String title;
	private String content;
	private String category;

	private String categoryDesc;
	private String createDateStr;

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
		if (!StringUtils.isEmpty(content)) {
			if ("<br>".equals(content)) {
				this.content = "";
			} else {
				byte[] bs = new byte[] { -30, -128, -117 };
				String regex = new String(bs);
				// special handle for unknow character
				this.content = content.replaceAll(regex, "");
			}
		} else {
			this.content = "";
		}
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

}
