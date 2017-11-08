<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<%@ page import="domain.Office"%>	
 <html>
<head>
<link rel="stylesheet" href="css/style.css">
</head>
<body >
	<%			
		Office officeOut=(Office)request.getAttribute("officeOut");		
		out.print("<tr><td>Poslovnica izlaska:</td><td><input type='text'  value='"+officeOut.getName()+"'/></td> ");	
		out.print("<td><input type='hidden' value='"+officeOut.getId()+"' name='officeOut' /></td></tr>");
	%>
</body>
</html>