<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<table>

	<c:forEach items="${expenses}" var="expense">
		<tr>
			<td> <c:out value="${expense.name}"/> <br/>
			<td> <c:out value="${expense.quantity}"/> <br/>		
			</td>
		</tr>
	</c:forEach>

</table>

ddddddddddd

</body>
</html>
