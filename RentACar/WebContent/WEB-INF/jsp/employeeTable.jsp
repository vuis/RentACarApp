<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="domain.Employee"%>
<%@ page import="java.text.SimpleDateFormat"%>
 
<html>
<head>
<link rel="stylesheet" href="css/style.css">
</head>
<body >
	<%			
		Employee emp= (Employee)request.getAttribute("employee");		
		SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy.");
		out.print("<tr><td>Poslovnica:</td><td><input type='text' value='"+emp.getOffice().getName()+"'/></td></tr>");		
		out.print("<tr><td>Broj vozačke:</td><td><input type='text' value='"+emp.getDriversLicenseNr()+"'/></td></tr>");
		out.print("<tr><td>Datum rođenja:</td><td><input type='text' value='"+dt.format(emp.getDob())+"'/></td></tr>");			
	%>
</body>
</html>