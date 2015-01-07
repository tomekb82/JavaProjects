

private Dog getTabletMachine(){
		cookieValueIdentValue = CookieUtils.getCookieValue("tabletIdent");

		if ( cookieValueIdentValue!= null && !cookieValueIdentValue.isEmpty() ){
			value = valueService.getTabletByIdent(cookieValueIdentValue);

			if ( value != null ){
				dog = dogService.getDogByID(value.getDogId());

				if ( dog != null ){
					return dog;
				}
			}
		}
		return null;
	}
