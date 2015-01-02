<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<c:if test="${not empty notifications}">
	<c:forEach items="${notifications}" var="notification">
		<div class="alert alert-${notification.type}">
			<c:choose>
				<c:when test="${notification.coded}"><s:message code="${notification.content}" arguments="${notification.attributes}"/></c:when>
				<c:otherwise>${notification.content}</c:otherwise>
			</c:choose>
		</div>
	</c:forEach>
</c:if>
