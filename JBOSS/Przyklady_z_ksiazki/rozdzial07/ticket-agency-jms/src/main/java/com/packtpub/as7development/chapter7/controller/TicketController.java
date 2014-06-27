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
package com.packtpub.as7development.chapter7.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;



import com.packtpub.as7development.chapter7.model.SeatType;
import com.packtpub.as7development.chapter7.service.TicketService;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class TicketController {

	@Inject
	private FacesContext facesContext;

	@Inject
	private TicketService ticketService;

	@Inject
	private List <SeatType> seatTypes;

	@Inject
	private Conversation conversation;

    @Produces
    @Named
    private SeatType newSeatType;

	@PostConstruct
	public void initNewSeatType() {
		newSeatType = new SeatType();
	}
	public String finish() {

		ticketService.createTheatre(seatTypes);
		conversation.begin();
		return "book";
	}

	public String restart() {

		ticketService.doCleanUp();
		conversation.end();
		return "/index";
	}
	public void create() throws Exception {
		try {
			ticketService.createSeatType(newSeatType);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Wykonano!", "Miejsca dodane");
			facesContext.addMessage(null, m);
			initNewSeatType();

		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Błąd w trakcie zapisu danych");
			facesContext.addMessage(null, m);
		}
	}

	private String getRootErrorMessage(Exception e) {
		// Domyślnie stosuj ogólny błąd dotyczący błędu rejestracji.
		String errorMessage = "Błąd rejestracji. Dodatkowe informacje w dzienniku serwera";
		if (e == null) {
			// To nie powinno się zdarzyć, ale zwróć komunikat domyślny.
			return errorMessage;
		}

		// Zacznij od wyjątku i posuwaj się wgłąb, by znaleźć faktyczną przyczynę.
		Throwable t = e;
		while (t != null) {
			// Pobierz komunikat z instancji klasy Throwable.
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// To najbardziej szczegółowy komunikat o błędzie.
		return errorMessage;
	}

}
