package pl.qualent.allianz.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl {

	@PersistenceContext(unitName="AllianzPersistenceUnit")
	private EntityManager em;
	
	public String getITextName(long taskId){
		Query query = em.createNativeQuery("select shorttext from i18ntext where task_names_id = :taskId");
		query.setParameter("taskId", taskId);
		return (String) query.getSingleResult();
	}
}
