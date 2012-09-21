package com.pccw.ehunter.dto;

public class CandidateContactHistoryDTO extends BaseDTO {
	private static final long serialVersionUID = 574220951185132835L;

	private String systemContactRefNum;
	private String contactCategory;
	private String record;
	private String remark;

	private TalentDTO talentDto;
	private ProjectDTO projectDto;
	private InternalUserDTO adviserDto;
	private CandidateStatusDTO contactCategoryDto;

	public String getSystemContactRefNum() {
		return systemContactRefNum;
	}

	public void setSystemContactRefNum(String systemContactRefNum) {
		this.systemContactRefNum = systemContactRefNum;
	}

	public String getContactCategory() {
		return contactCategory;
	}

	public void setContactCategory(String contactCategory) {
		this.contactCategory = contactCategory;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public TalentDTO getTalentDto() {
		return talentDto;
	}

	public void setTalentDto(TalentDTO talentDto) {
		this.talentDto = talentDto;
	}

	public ProjectDTO getProjectDto() {
		return projectDto;
	}

	public void setProjectDto(ProjectDTO projectDto) {
		this.projectDto = projectDto;
	}

	public InternalUserDTO getAdviserDto() {
		return adviserDto;
	}

	public void setAdviserDto(InternalUserDTO adviserDto) {
		this.adviserDto = adviserDto;
	}

	public CandidateStatusDTO getContactCategoryDto() {
		return contactCategoryDto;
	}

	public void setContactCategoryDto(CandidateStatusDTO contactCategoryDto) {
		this.contactCategoryDto = contactCategoryDto;
	}

}
