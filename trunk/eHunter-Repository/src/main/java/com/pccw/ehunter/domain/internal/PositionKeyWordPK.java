package com.pccw.ehunter.domain.internal;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PositionKeyWordPK implements Serializable {
	private static final long serialVersionUID = -6636878518304530732L;

	private PositionDescription postDesc;
	private int itemNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="SYS_REF_POST" , referencedColumnName="SYS_REF_POST" , nullable=false)
	public PositionDescription getPostDesc() {
		return postDesc;
	}

	public void setPostDesc(PositionDescription postDesc) {
		this.postDesc = postDesc;
	}

	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

}
