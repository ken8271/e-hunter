package com.pccw.ehunter.domain.internal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EmploymentHistoryPK implements Serializable {
	private static final long serialVersionUID = 5164089254646875890L;

	private Talent talent;
	private int itemNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYS_REF_TLNT", referencedColumnName = "SYS_REF_TLNT", nullable = false)
	public Talent getTalent() {
		return talent;
	}

	public void setTalent(Talent talent) {
		this.talent = talent;
	}

	@Column(name="SEQ_NBR")
	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

}