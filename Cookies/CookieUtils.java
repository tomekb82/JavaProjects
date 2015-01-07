public class CookieUtils {
	
	
	public static void addCookie(String cookieName, String cookieValue, int maxAge){
		
		FacesContext context = FacesContext.getCurrentInstance();
		StringBuilder cookie = new StringBuilder(cookieName+"="+cookieValue+"; ");
		DateFormat df = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss 'GMT'", Locale.US);
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.SECOND, maxAge);
	    cookie.append("Expires=" + df.format(cal.getTime()) + "; ");
	    cookie.append("Max-Age=" + maxAge + "; ");
	    cookie.append("Path=" + "/" + "; ");
	    ((HttpServletResponse) context.getExternalContext()
			       .getResponse()).setHeader("Set-Cookie", cookie.toString());
	}
	
	public static String getCookieValue(String cookieName){
		
		String value = null;
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Cookie cookies[] = ((HttpServletRequest) externalContext.getRequest()).getCookies();
	
		if ( cookies != null )
		for (int i = 0; i < cookies.length; i++) {
    		if (cookies[i].getName().equals(cookieName)) {
                value = cookies[i].getValue();
                break;
            }
        }

	return value;
	}

}
		
