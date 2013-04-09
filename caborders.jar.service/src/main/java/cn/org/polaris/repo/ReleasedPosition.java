package cn.org.polaris.repo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_RLESD_POSTN")
public class ReleasedPosition extends BaseEntity {
	private static final long serialVersionUID = -4617306523140295525L;

	private String systemRefPosition;
	private String title;
	private String content;
	private String workCity;

	@Id
	@Column(name = "SYS_REF_POSTN")
	public String getSystemRefPosition() {
		return systemRefPosition;
	}

	public void setSystemRefPosition(String systemRefPosition) {
		this.systemRefPosition = systemRefPosition;
	}

	@Column(name = "POSTN_TITL")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "POSTN_CTNT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "WK_CTY")
	public String getWorkCity() {
		return workCity;
	}

	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}
}
