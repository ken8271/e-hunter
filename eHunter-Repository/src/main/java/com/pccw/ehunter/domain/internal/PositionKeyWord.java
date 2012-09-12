package com.pccw.ehunter.domain.internal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_PRJ_POST_KEY_WD")
public class PositionKeyWord extends BaseEntity {
	private static final long serialVersionUID = -525255547360035395L;

	private PositionKeyWordPK pk;
	private String content;

	@EmbeddedId
	public PositionKeyWordPK getPk() {
		return pk;
	}

	public void setPk(PositionKeyWordPK pk) {
		this.pk = pk;
	}

	@Column(name="KEY_WD_CNT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
