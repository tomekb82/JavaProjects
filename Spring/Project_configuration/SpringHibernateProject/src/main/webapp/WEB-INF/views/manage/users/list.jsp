<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<div class="table-responsive">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Imię i Nazwisko</th>
				<th>Login</th>
				<th>Hasło</th>
				<th>Rola</th>
				<th>Opcje</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.nameFull}</td>
					<td>${user.login}</td>
					<td>${(user.role != 'admin') ? user.cleartextPassword : ' -- '}</td>
					<td>${user.role}</td>
					<td>
						<c:if test="${user.role!='admin'}">
							<a href="<c:url value="/manage/users/delete/${user.id}" />"  onclick="return confirm('Czy na pewno chcesz usunąć tego użytkownika?')">Usuń</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="well">
	<form method="post" action="<c:url value="/manage/users/add" />">
		<div class="row">
			<div class="col-md-2"><label>Imię</label></div>
			<div class="col-md-4 form-group">
				<input type="text" name="nameFirst" class="form-control" />
			</div>
			<div class="col-md-2"><label>Nazwisko</label></div>
			<div class="col-md-4 form-group">
				<input type="text" name="nameLast" class="form-control" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-2"><label>Login</label></div>
			<div class="col-md-4 form-group">
				<input type="text" name="login" class="form-control" />
			</div>
			<div class="col-md-2"><label>Hasło</label></div>
			<div class="col-md-4 form-group">
				<input type="text" name="password" class="form-control" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<label class="radio">
					<input type="radio" name="role" value="assesor" data-toggle="radio" checked="checked" />
					Assesor
				</label>
			</div>
			<div class="col-md-4">
				<label class="radio">
					<input type="radio" name="role" value="superassesor" data-toggle="radio">
					Superasesor
				</label>
			</div>
			<div class="col-md-4">
				<label class="radio">
					<input type="radio" name="role" value="admin" data-toggle="radio">
					Administrator
				</label>
			</div>
		</div>
		<input class="btn btn-xl btn-block btn-primary" type="submit" value="Dodaj" />
	</form>
</div>

