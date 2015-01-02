<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:if test="${not empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION'] }">
	<div style="baroder:red; background:#ffaaaa">
		Logowanie nie powiodło się, powód:<br />
		${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
	</div>
</c:if>



<form action="<c:url value="j_spring_security_check" />" method="post">
	<div class="login-form">
		<div class="form-group">
			<input type="text" class="form-control login-field" value="" placeholder="Enter your name" id="login-name" name="j_username">
			<label class="login-field-icon fui-user" for="login-name"></label>
		</div>
	
		<div class="form-group">
			<input type="password" class="form-control login-field" value="" placeholder="Password" id="login-pass" name="j_password">
			<label class="login-field-icon fui-lock" for="login-pass"></label>
		</div>
	
		<input name="submit" type="submit" class="btn btn-block btn-lg btn-primary" value="Log in" />
	</div>
</form>