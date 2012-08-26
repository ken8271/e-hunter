package com.pccw.ehunter.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name="T_SEQ_NBR_LST")
public class Sequence extends BaseEntity {
	private static final long serialVersionUID = 8552716580295341657L;
	
	private String key;
	private Long value;
	private String description;

	@Id
	@Column(name="SEQ_NM")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name="SEQ_VAL")
	public Long getValue() {
		return value;
	}
	
	public void setValue(Long value) {
		this.value = value;
	}

	@Column(name="SEQ_DESC")
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

}
