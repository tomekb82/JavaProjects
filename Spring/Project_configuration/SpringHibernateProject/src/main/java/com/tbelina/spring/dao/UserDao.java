package com.tbelina.spring.dao;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbelina.spring.model.User;


@Repository
public class UserDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	public void setEnitityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public User findById(Integer id) {
		return this.entityManager.find(User.class, id);
	}
	
	public User findByLogin(String login) {
		try {
			Query query = this.entityManager.createQuery("from User u WHERE u.login = :login AND u.active=true",User.class);
			query.setParameter("login", login);
			return (User) query.getSingleResult();
		} catch (Exception e) {
			//ignore
		}
		return null;
	}

	public List<User> getList() {
		return this.entityManager.createQuery("from User u WHERE u.active=true",User.class).getResultList();
	}


	@Transactional
	public void save(User user) {
		entityManager.persist(user);
	}

	@Transactional
	public void update(User user) {
		entityManager.merge(user);
	}

	@Transactional
	public void delete(User user) {
		entityManager.remove(user);
	}

	@Transactional
	public void deleteById(Integer id) {
		User user = this.findById(id);
		delete(user);
	}

}