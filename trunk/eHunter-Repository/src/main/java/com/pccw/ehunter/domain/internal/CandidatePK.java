package com.pccw.ehunter.domain.internal;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CandidatePK implements Serializable{
	private static final long serialVersionUID = 4763464926958786281L;
	
	private Project project;
	private Talent talent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYS_REF_PRJ", referencedColumnName = "SYS_REF_PRJ", nullable = false)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYS_REF_TLNT", referencedColumnName = "SYS_REF_TLNT", nullable = false)
	public Talent getTalent() {
		return talent;
	}

	public void setTalent(Talent talent) {
		this.talent = talent;
	}

}
