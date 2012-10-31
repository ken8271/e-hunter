package com.pccw.ehunter.dto;

import java.util.ArrayList;
import java.util.List;

public class TalentDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String talentID;
	private String cnName;
	private String enName;
	private String gender;
	private String maritalStatus;
	private SimpleDateDTO birthDateDto;
	private String nativePlace;
	private String nowLivePlace;
	private String onceLivePlace;
	private String highestDegree;

	private TelephoneDTO homeNumberDto;
	private TelephoneDTO companyNumberDto;
	private MobilePhoneDTO mobilePhoneDto1;
	private MobilePhoneDTO mobilePhoneDto2;
	private String QQ;
	private String msn;
	private String email;
	private String homeAddress;
	
	private String talentSrc;
	private TalentSourceDTO talentSrcDto;
	
	private DegreeDTO degreeDto;
	private ResumeDTO resumeDto;
	private List<ResumeDTO> resumeDtos = new ArrayList<ResumeDTO>(0);
	private List<UploadedCurriculumVitaeDTO> cvDtos = new ArrayList<UploadedCurriculumVitaeDTO>();
	
	private EmploymentHistoryDTO employmentHistoryDto;
	private List<EmploymentHistoryDTO> employmentHistoryDtos = new ArrayList<EmploymentHistoryDTO>();

	public String getTalentID() {
		return talentID;
	}

	public void setTalentID(String talentID) {
		this.talentID = talentID;
	}

	public String getTalentSrc() {
		return talentSrc;
	}

	public void setTalentSrc(String talentSrc) {
		this.talentSrc = talentSrc;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public SimpleDateDTO getBirthDateDto() {
		return birthDateDto;
	}

	public void setBirthDateDto(SimpleDateDTO birthDateDto) {
		this.birthDateDto = birthDateDto;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getNowLivePlace() {
		return nowLivePlace;
	}

	public void setNowLivePlace(String nowLivePlace) {
		this.nowLivePlace = nowLivePlace;
	}

	public String getOnceLivePlace() {
		return onceLivePlace;
	}

	public void setOnceLivePlace(String onceLivePlace) {
		this.onceLivePlace = onceLivePlace;
	}

	public String getHighestDegree() {
		return highestDegree;
	}

	public void setHighestDegree(String highestDegree) {
		this.highestDegree = highestDegree;
	}

	public TelephoneDTO getHomeNumberDto() {
		return homeNumberDto;
	}

	public void setHomeNumberDto(TelephoneDTO homeNumberDto) {
		this.homeNumberDto = homeNumberDto;
	}

	public TelephoneDTO getCompanyNumberDto() {
		return companyNumberDto;
	}

	public void setCompanyNumberDto(TelephoneDTO companyNumberDto) {
		this.companyNumberDto = companyNumberDto;
	}

	public MobilePhoneDTO getMobilePhoneDto1() {
		return mobilePhoneDto1;
	}

	public void setMobilePhoneDto1(MobilePhoneDTO mobilePhoneDto1) {
		this.mobilePhoneDto1 = mobilePhoneDto1;
	}

	public MobilePhoneDTO getMobilePhoneDto2() {
		return mobilePhoneDto2;
	}

	public void setMobilePhoneDto2(MobilePhoneDTO mobilePhoneDto2) {
		this.mobilePhoneDto2 = mobilePhoneDto2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public List<ResumeDTO> getResumeDtos() {
		return resumeDtos;
	}

	public void setResumeDtos(List<ResumeDTO> resumeDtos) {
		this.resumeDtos = resumeDtos;
	}

	public ResumeDTO getResumeDto() {
		return resumeDto;
	}

	public void setResumeDto(ResumeDTO resumeDto) {
		this.resumeDto = resumeDto;
	}

	public DegreeDTO getDegreeDto() {
		return degreeDto;
	}

	public void setDegreeDto(DegreeDTO degreeDto) {
		this.degreeDto = degreeDto;
	}

	public TalentSourceDTO getTalentSrcDto() {
		return talentSrcDto;
	}

	public void setTalentSrcDto(TalentSourceDTO talentSrcDto) {
		this.talentSrcDto = talentSrcDto;
	}

	public List<EmploymentHistoryDTO> getEmploymentHistoryDtos() {
		return employmentHistoryDtos;
	}

	public void setEmploymentHistoryDtos(
			List<EmploymentHistoryDTO> employmentHistoryDtos) {
		this.employmentHistoryDtos = employmentHistoryDtos;
	}

	public EmploymentHistoryDTO getEmploymentHistoryDto() {
		return employmentHistoryDto;
	}

	public void setEmploymentHistoryDto(EmploymentHistoryDTO employmentHistoryDto) {
		this.employmentHistoryDto = employmentHistoryDto;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public List<UploadedCurriculumVitaeDTO> getCvDtos() {
		return cvDtos;
	}

	public void setCvDtos(List<UploadedCurriculumVitaeDTO> cvDtos) {
		this.cvDtos = cvDtos;
	}
}
