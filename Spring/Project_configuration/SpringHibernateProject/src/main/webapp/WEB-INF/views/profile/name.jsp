<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>



<form action="<c:url value="/profile" />" method="post">
	<div class="login-form">
		<p>Przed przejściem dalej proszę uzupełnić dane profilu - swoje imię oraz nazwisko</p>
		<div class="form-group">
			<input type="text" class="form-control login-field" value="${nameFirst}" placeholder="Imię" id="login-name" name="name_first">
			<label class="login-field-icon fui-user" for="login-name"></label>
		</div>
		
		<div class="form-group">
			<input type="text" class="form-control login-field" value="${nameLast}" placeholder="Nazwisko" id="login-name" name="name_last">
			<label class="login-field-icon fui-user" for="login-name"></label>
		</div>
	
		<input name="submit" type="submit" class="btn btn-block btn-lg btn-primary" value="Zapisz dane" />
	</div>
</form>