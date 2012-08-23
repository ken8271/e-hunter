package com.pccw.ehunter.domain.internal;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_TLNT_RSUM")
public class Resume extends BaseEntity {
	private String resumeID;
	private int resumeIndex;
	private String language;
	
	private Talent talent;
	
	private SelfEvaluation selfEvaluation;
	private JobIntention intention;
	private List<EducationExperience> eduExps;
	private List<JobExperience> jobExps;
	private List<ProjectExperience> prjExps;
	private List<TrainingExperience> trnExps;
	private List<ProfessionalSkill> skills;
	private List<LanguageAbility> languages;
	private List<Cert> certs;

	public String getResumeID() {
		return resumeID;
	}

	public void setResumeID(String resumeID) {
		this.resumeID = resumeID;
	}

	@Column(name="RSUM_SEQ_NBR")
	public int getResumeIndex() {
		return resumeIndex;
	}

	public void setResumeIndex(int resumeIndex) {
		this.resumeIndex = resumeIndex;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "SYS_REF_TLNT")
	public Talent getTalent() {
		return talent;
	}

	public void setTalent(Talent talent) {
		this.talent = talent;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "resume", cascade = { CascadeType.ALL })
	public SelfEvaluation getSelfEvaluation() {
		return selfEvaluation;
	}

	public void setSelfEvaluation(SelfEvaluation selfEvaluation) {
		this.selfEvaluation = selfEvaluation;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "resume", cascade = { CascadeType.ALL })
	public JobIntention getIntention() {
		return intention;
	}

	public void setIntention(JobIntention intention) {
		this.intention = intention;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.resume", cascade = { CascadeType.ALL })
	@OrderBy(value = "pk.itemNumber asc")
	public List<EducationExperience> getEduExps() {
		return eduExps;
	}

	public void setEduExps(List<EducationExperience> eduExps) {
		this.eduExps = eduExps;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.resume", cascade = { CascadeType.ALL })
	@OrderBy(value = "pk.itemNumber asc")
	public List<JobExperience> getJobExps() {
		return jobExps;
	}

	public void setJobExps(List<JobExperience> jobExps) {
		this.jobExps = jobExps;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.resume", cascade = { CascadeType.ALL })
	@OrderBy(value = "pk.itemNumber asc")
	public List<ProjectExperience> getPrjExps() {
		return prjExps;
	}

	public void setPrjExps(List<ProjectExperience> prjExps) {
		this.prjExps = prjExps;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.resume", cascade = { CascadeType.ALL })
	@OrderBy(value = "pk.itemNumber asc")
	public List<TrainingExperience> getTrnExps() {
		return trnExps;
	}

	public void setTrnExps(List<TrainingExperience> trnExps) {
		this.trnExps = trnExps;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.resume", cascade = { CascadeType.ALL })
	@OrderBy(value = "pk.itemNumber asc")
	public List<ProfessionalSkill> getSkills() {
		return skills;
	}

	public void setSkills(List<ProfessionalSkill> skills) {
		this.skills = skills;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.resume", cascade = { CascadeType.ALL })
	@OrderBy(value = "pk.itemNumber asc")
	public List<LanguageAbility> getLanguages() {
		return languages;
	}

	public void setLanguages(List<LanguageAbility> languages) {
		this.languages = languages;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.resume", cascade = { CascadeType.ALL })
	@OrderBy(value = "pk.itemNumber asc")
	public List<Cert> getCerts() {
		return certs;
	}

	public void setCerts(List<Cert> certs) {
		this.certs = certs;
	}

}
