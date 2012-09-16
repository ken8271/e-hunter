package com.pccw.ehunter.domain.internal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name="T_PRJ_TLNT_LIB")
public class ProjectCandidateRepository extends BaseEntity {
	private static final long serialVersionUID = -3104559413553551205L;

	private ProjectCandidateRepositoryPK pk;
	private String candidateStatus;

	@EmbeddedId
	public ProjectCandidateRepositoryPK getPk() {
		return pk;
	}

	public void setPk(ProjectCandidateRepositoryPK pk) {
		this.pk = pk;
	}

	@Column(name="TLNT_ST")
	public String getCandidateStatus() {
		return candidateStatus;
	}

	public void setCandidateStatus(String candidateStatus) {
		this.candidateStatus = candidateStatus;
	}

}
