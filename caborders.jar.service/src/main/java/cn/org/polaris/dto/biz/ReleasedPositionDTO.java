package cn.org.polaris.dto.biz;

import cn.org.polaris.dto.BaseDTO;
import cn.org.polaris.dto.CabordersDTO;
import cn.org.polaris.utility.StringUtils;

public class ReleasedPositionDTO extends BaseDTO implements CabordersDTO{
	private static final long serialVersionUID = -5296014964714967969L;

	private String systemRefPosition;
	private String title;
	private String content;
	private String workCity;
	
	private String createDateStr;

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

	public String getWorkCity() {
		return workCity;
	}

	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

}
