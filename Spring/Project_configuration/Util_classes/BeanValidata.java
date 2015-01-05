
...

public void validate(ComponentSystemEvent event) {
		 
		FacesContext fc = FacesContext.getCurrentInstance();
	 
		UIComponent components = event.getComponent();	
		
		UIPanel panelGroup = (UIPanel) components.findComponent("id1");
		String panelGroupId = panelGroup.getClientId();

		UIInput uiInputNFC = (UIInput) components.findComponent("id2");
		String nfcNr = uiInputNFC.getLocalValue() == null ? ""
			: uiInputNFC.getLocalValue().toString();

		UIInput uiInputWatch = (UIInput) components.findComponent("id3");
		String watchNr = uiInputWatch.getLocalValue() == null ? ""
			: uiInputWatch.getLocalValue().toString();
		
		UIInput uiInputEmergency = (UIInput) components.findComponent("id4");
		String emergencyNr = uiInputEmergency.getLocalValue() == null ? ""
			: uiInputEmergency.getLocalValue().toString();
		
		if( (nfcNr.equals(emergencyNr) || nfcNr.equals(watchNr) || watchNr.equals(emergencyNr)) 
				&& !(watchNr.equals("")|| nfcNr.equals(""))) { 
			FacesMessage msg = new FacesMessage("Blebleble.");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			fc.addMessage(panelGroupId, msg);
			fc.renderResponse();
		}
	}

...
