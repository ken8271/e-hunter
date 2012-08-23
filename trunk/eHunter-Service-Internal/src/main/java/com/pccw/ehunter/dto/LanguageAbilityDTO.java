package com.pccw.ehunter.dto;

public class LanguageAbilityDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private int itemNumber;
	private String languageCategory;
	private String ablitityOfRW;
	private String ablitityOfLS;

	private LanguageCategoryDTO languageCategoryDto;
	private SkillLevelDTO ablitityOfRWDto;
	private SkillLevelDTO ablitityOfLSDto;

	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getLanguageCategory() {
		return languageCategory;
	}

	public void setLanguageCategory(String languageCategory) {
		this.languageCategory = languageCategory;
	}

	public String getAblitityOfRW() {
		return ablitityOfRW;
	}

	public void setAblitityOfRW(String ablitityOfRW) {
		this.ablitityOfRW = ablitityOfRW;
	}

	public String getAblitityOfLS() {
		return ablitityOfLS;
	}

	public void setAblitityOfLS(String ablitityOfLS) {
		this.ablitityOfLS = ablitityOfLS;
	}

	public SkillLevelDTO getAblitityOfRWDto() {
		return ablitityOfRWDto;
	}

	public void setAblitityOfRWDto(SkillLevelDTO ablitityOfRWDto) {
		this.ablitityOfRWDto = ablitityOfRWDto;
	}

	public SkillLevelDTO getAblitityOfLSDto() {
		return ablitityOfLSDto;
	}

	public void setAblitityOfLSDto(SkillLevelDTO ablitityOfLSDto) {
		this.ablitityOfLSDto = ablitityOfLSDto;
	}

	public LanguageCategoryDTO getLanguageCategoryDto() {
		return languageCategoryDto;
	}

	public void setLanguageCategoryDto(LanguageCategoryDTO languageCategoryDto) {
		this.languageCategoryDto = languageCategoryDto;
	}
	
}
