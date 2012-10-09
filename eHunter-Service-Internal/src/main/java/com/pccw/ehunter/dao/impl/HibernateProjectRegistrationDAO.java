package com.pccw.ehunter.dao.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.pccw.ehunter.dao.ProjectRegistrationDAO;
import com.pccw.ehunter.domain.internal.PositionDescription;
import com.pccw.ehunter.domain.internal.Project;

@Component("projectRegtDao")
public class HibernateProjectRegistrationDAO implements ProjectRegistrationDAO{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void saveProject(Project project) {
		hibernateTemplate.save(project);
	}

	@Override
	public void updateProject(Project project) {
		hibernateTemplate.update(project);
	}

	@Override
	public void updateProjectStatus(final Project project) {
		hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" UPDATE T_PRJ ");
				buffer.append(" SET PRJ_ST = :status , LST_UPD_BY = :updateBy , LST_UPD_DTTM = :updateDate , LST_TX_ACTN = :action ");
				buffer.append(" WHERE SYS_REF_PRJ = :projectID ");
				
				Query query = session.createSQLQuery(buffer.toString());
				
				query.setString("status", project.getStatus());
				query.setString("updateBy", project.getLastUpdateBy());
				query.setDate("updateDate", project.getLastUpdateDateTime());
				query.setString("action", project.getLastTransactionIndicator());
				query.setString("projectID", project.getSystemProjectRefNum());
				
				query.executeUpdate();
				
				return null;
			}
		});
	}

	@Override
	public void updatePositionDescription(PositionDescription pd) {
		hibernateTemplate.update(pd);
	}

}
