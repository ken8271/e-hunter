package com.pccw.ehunter.service;

import com.pccw.ehunter.dto.TalentDTO;

public interface TalentRegistrationService {
	public int getCountByPhoneNumber(String phone);
	public int getCountByEmail(String email);
	public void completeTalentRegistration(TalentDTO dto);
	public TalentDTO getTalentByID(String id , boolean byHibernate);
	public void udpateTalent(TalentDTO talentDto);
}
