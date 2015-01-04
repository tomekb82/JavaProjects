<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
	.clickableRow {
		cursor: pointer;
	}
</style>
<table>
	<c:forEach items="${recruitments}" var="recruitment" varStatus="status">
		<button class="btn btn-block btn-lg btn-${(status.count%2 == 0) ? 'primary' : 'info'} clickableRow">
           	<div class="row">
		        <div class="col-md-4"><a style="color:white;" href="<c:url value="/grading/tasks/${recruitment.id}" />">${recruitment.name}</a></div>
		        <div class="col-md-4"><fmt:formatDate pattern="yyyy-MM-dd" value="${recruitment.start}" /></div>
		    	<div class="col-md-2">${recruitment.place}</div>
		    	<div class="col-md-2">${(recruitment.active) ? 'aktywna' : 'nieaktywna'}</div>
		    </div>
	    </button>
	</c:forEach>
</table>
<script>
	$(function() {
		$('.clickableRow').click(function() {
			window.location.href=$(this).find('a').attr('href');
		});
	});
</script>