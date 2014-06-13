<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>


<h2>	User: ${expense.name}  </h2>

Expense:<br/>
- id: ${expense.id}  <br/>
- value: ${expense.value} <br/>
- quantity: ${expense.quantity}<br/> 
- date: ${expense.date} 


</body>
</html>
