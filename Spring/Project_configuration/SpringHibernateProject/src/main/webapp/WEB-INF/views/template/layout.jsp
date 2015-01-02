<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
	<head>
		<meta charset="utf-8">
		<base href="<c:url value="/" />" />
		<title>Spring - Hibernate Project</title>
		
		<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;"> 
	    <link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet">
	    <link href="resources/css/flat-ui.css" rel="stylesheet">
	    <link href="resources/css/custom.css" rel="stylesheet">
	    <!--[if lt IE 9]>
	      <script src="resources/js/html5shiv.js"></script>
	      <script src="resources/js/respond.min.js"></script>
	    <![endif]-->
	
	    <link rel="shortcut icon" href="resources/images/favicon.ico">
		<script src="resources/js/jquery-1.8.3.min.js"></script>
	    <script src="resources/js/jquery-ui-1.10.3.custom.min.js"></script>
	    <script src="resources/js/jquery.ui.touch-punch.min.js"></script>
	    <script src="resources/js/bootstrap.min.js"></script>
	    <script src="resources/js/bootstrap-select.js"></script>
	    <script src="resources/js/bootstrap-switch.js"></script>
	    <script src="resources/js/flatui-checkbox.js"></script>
	    <script src="resources/js/flatui-radio.js"></script>
	    <script src="resources/js/jquery.tagsinput.js"></script>
	    <script src="resources/js/jquery.placeholder.js"></script>
	    <script src="resources/js/jquery.floatThead.min.js"></script>
	    
	    <link rel="stylesheet" href="resources/fancybox/jquery.fancybox.css?v=2.1.5" type="text/css" media="screen" />
		<script type="text/javascript" src="resources/fancybox/jquery.fancybox.pack.js?v=2.1.5"></script>

	</head>
	<body>
	<c:set var="displaytop"><tiles:insertAttribute name="displaytop" /></c:set>
		<div id="wrap" ${(displaytop=='false') ? 'style="padding-top:0px;"' : ''}>
			<c:if test="${displaytop=='true'}">
				<div class="navbar navbar-default navbar-fixed-top">
			      <div class="container">
			        <div class="navbar-collapse collapse">
			          <ul class="nav navbar-nav">
			            <li class="active"><a href="<c:url value="/" />">Home</a></li>
			            <sec:authorize access="hasRole('admin')">
				            <li><a href="<c:url value="/manage/users" />">Users</a></li>
			            </sec:authorize>
			          </ul>
			          <ul class="nav navbar-nav navbar-right">
			            <sec:authorize access="isAuthenticated()">
							<li><a href="<c:url value="/logout" />">${logged_user_details.nameFull} (Logout)</a></li>
						</sec:authorize>
			            <sec:authorize access="!isAuthenticated()">
							<li><a class="active" href="<c:url value="/login" />">Login in</a></li>
						</sec:authorize>
			          </ul>
			        </div>
			      </div>
			    </div>
			</c:if>
			<div class="container">
				<tiles:insertTemplate template="/WEB-INF/views/template/notifications.jsp" />
				<tiles:insertAttribute name="body" />
			</div>
			<div id="push"></div>
		</div>
		<div id="footer">
	      <div class="container">
	        <p class="credit">Tomasz Belina</p>
	      </div>
	    </div>
	    
	</body>
</html>