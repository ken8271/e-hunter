package com.pccw.ehunter.dao;

import com.pccw.ehunter.domain.internal.Talent;

public interface TalentRegistrationDAO {
	public int getTalentCountByPhoneNumber(String phone);
	public int getTalentCountByEmail(String email);
	public void saveTalent(Talent talent);
	public void updateTalent(Talent talent);
}
