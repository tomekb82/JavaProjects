/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the 
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.tbelina.jboss.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import eu.tbelina.jboss.model.Seat;
import eu.tbelina.jboss.model.SeatType;

import java.util.List;


@ApplicationScoped
public class DataManager {

	@Inject
	private EntityManager em;

	public Seat findSeatById(Long id) {
		return em.find(Seat.class, id);
	}

	public List<SeatType> findAllSeatTypes() {

		return em.createQuery("from SeatType seat").getResultList();
	}
	public List<Seat> findAllSeats() {

		return em.createQuery("from Seat seat").getResultList();
	}
	public void deleteAllData() {
		em.createQuery("delete from Seat").executeUpdate();
		em.createQuery("delete from SeatType").executeUpdate();

	}
}
