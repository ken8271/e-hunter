package com.pccw.ehunter.domain.internal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ProfessionalSkillPK implements Serializable{
	private static final long serialVersionUID = -7802207535061380663L;
	
	private Resume resume;
	private int itemNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="SYS_REF_RSUM" , referencedColumnName="SYS_REF_RSUM" , nullable = false)
	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	@Column(name="SKL_SEQ_NBR")
	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

}
