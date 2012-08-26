package com.pccw.ehunter.service;

import com.pccw.ehunter.dto.TalentDTO;

public interface TalentRegistrationService {
	public int getCountByPhoneNumber(String phone);
	public int getCountByEmail(String email);
	public void completeTalentRegistration(TalentDTO dto);
}
