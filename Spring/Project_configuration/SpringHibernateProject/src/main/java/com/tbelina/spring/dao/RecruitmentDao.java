package com.tbelina.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbelina.spring.model.Recruitment;

@Repository
public class RecruitmentDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	public void setEnitityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public Recruitment findById(Integer id) {
		return this.entityManager.find(Recruitment.class, id);
	}

	public List<Recruitment> getList() {
		return this.entityManager.createQuery("from Recruitment r WHERE r.active=true ORDER BY r.start DESC",Recruitment.class).getResultList();
	}

	@Transactional
	public void save(Recruitment recruitment) {
		entityManager.persist(recruitment);
	}

	@Transactional
	public Recruitment update(Recruitment recruitment) {
		return entityManager.merge(recruitment);
	}

	@Transactional
	public void delete(Recruitment recruitment) {
		entityManager.remove(recruitment);
	}

	@Transactional
	public void deleteById(Integer id) {
		Recruitment recruitment = this.findById(id);
		delete(recruitment);
	}

}