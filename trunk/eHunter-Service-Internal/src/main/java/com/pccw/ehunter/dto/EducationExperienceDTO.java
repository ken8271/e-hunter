package com.pccw.ehunter.dto;

public class EducationExperienceDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private SimpleDateDTO fromDateDto;
	private SimpleDateDTO toDateDto;
	private String school;
	private String majorType;
	private String major;
	private String degree;

	private SubjectDTO majorDto;
	private DegreeDTO degreeDto;

	public SimpleDateDTO getFromDateDto() {
		return fromDateDto;
	}

	public void setFromDateDto(SimpleDateDTO fromDateDto) {
		this.fromDateDto = fromDateDto;
	}

	public SimpleDateDTO getToDateDto() {
		return toDateDto;
	}

	public void setToDateDto(SimpleDateDTO toDateDto) {
		this.toDateDto = toDateDto;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public SubjectDTO getMajorDto() {
		return majorDto;
	}

	public void setMajorDto(SubjectDTO majorDto) {
		this.majorDto = majorDto;
	}

	public DegreeDTO getDegreeDto() {
		return degreeDto;
	}

	public void setDegreeDto(DegreeDTO degreeDto) {
		this.degreeDto = degreeDto;
	}

	public String getMajorType() {
		return majorType;
	}

	public void setMajorType(String majorType) {
		this.majorType = majorType;
	}

}
