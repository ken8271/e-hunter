package com.pccw.ehunter.dto;

public class ProfessionalSkillDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private int itemNumber;
	private String categoryCode;
	private String skillName;
	private String duration;
	private String levelCode;

	private SkillCategoryDTO categoryDto;
	private SkillLevelDTO levelDto;

	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public SkillCategoryDTO getCategoryDto() {
		return categoryDto;
	}

	public void setCategoryDto(SkillCategoryDTO categoryDto) {
		this.categoryDto = categoryDto;
	}

	public SkillLevelDTO getLevelDto() {
		return levelDto;
	}

	public void setLevelDto(SkillLevelDTO levelDto) {
		this.levelDto = levelDto;
	}

}
