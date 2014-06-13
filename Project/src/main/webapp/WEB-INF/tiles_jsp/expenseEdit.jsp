<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Add new expense
</h1>

<sf:form method="POST" modelAttribute="expense">

	<table>
		
		<tr> 
			<th><label for="name">Name:</label></th>
			<td><sf:input path="name" size="15" id="name"/>
				<small id="name">No spaces, please</small> <br/>
				<sf:errors path="name" cssClass="error"/>
			</td>
			
		</tr>
		<tr> 
			<th><label for="value">Value:</label></th>
			<td><sf:input path="value" id="value"/><br/>
				<sf:errors path="value" cssClass="error"/>
			</td>
		</tr>
		<tr> 
			<th><label for="quantity">Quantity:</label></th>
			<td><sf:input path="quantity" id="quantity"/><br/>
				<sf:errors path="quantity" cssClass="error"/>
			</td>
		</tr>
		
	
		<tr>
			<th></th>
			<td><input name="commit" type="submit" value="Accept"/></td>
		</tr>
	</table>

</sf:form>

</body>
</html>
