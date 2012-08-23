package com.pccw.ehunter.domain.internal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name="T_TLNT_LAN_ABLT")
public class LanguageAbility extends BaseEntity{
	private LanguageAbilityPK pk;
	private String languageCategory;
	private String ablitityOfRW;
	private String ablitityOfLS;

	@EmbeddedId
	public LanguageAbilityPK getPk() {
		return pk;
	}

	public void setPk(LanguageAbilityPK pk) {
		this.pk = pk;
	}

	@Column(name="LAN_TY")
	public String getLanguageCategory() {
		return languageCategory;
	}

	public void setLanguageCategory(String languageCategory) {
		this.languageCategory = languageCategory;
	}

	@Column(name="RW_ABLT")
	public String getAblitityOfRW() {
		return ablitityOfRW;
	}

	public void setAblitityOfRW(String ablitityOfRW) {
		this.ablitityOfRW = ablitityOfRW;
	}

	@Column(name="LS_ABLT")
	public String getAblitityOfLS() {
		return ablitityOfLS;
	}

	public void setAblitityOfLS(String ablitityOfLS) {
		this.ablitityOfLS = ablitityOfLS;
	}
}
