package com.pccw.ehunter.domain.internal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_TLNT_PRO_SKL")
public class ProfessionalSkill extends BaseEntity {
	private static final long serialVersionUID = -4654099347588124926L;
	
	private ProfessionalSkillPK pk;
	private String categoryCode;
	private String skillName;
	private String duration;
	private String levelCode;

	@EmbeddedId
	public ProfessionalSkillPK getPk() {
		return pk;
	}

	public void setPk(ProfessionalSkillPK pk) {
		this.pk = pk;
	}

	@Column(name="SKL_TY")
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Column(name="SKL_NM")
	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	@Column(name="USE_DURTN")
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Column(name="CNTL_LEVL")
	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

}
