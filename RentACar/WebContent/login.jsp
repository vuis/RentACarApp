<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Prijava</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="form-main">
		<div id="form-div">
			<center>
				<h1>Prijava</h1>
			</center>
			<c:if test="${param.error==true }">
				<p
					style="color: red; font-size: 18px; font-family: 'Montserrat', Arial, Helvetica, sans-serif;">Pogreška
					prilikom prijave!</p>
			</c:if>
			<c:if test="${not empty param.logoff }">
				<p
					style="color: black; font-size: 18px; font-family: 'Montserrat', Arial, Helvetica, sans-serif;">Uspješna
					odjava</p>
			</c:if>
			<c:if test="${param.error=='forbidden'}">
				<p
					style="color: red; font-size: 18px; font-family: 'Montserrat', Arial, Helvetica, sans-serif;">Nedovoljna
					prava za pristup stranici!</p>
			</c:if>
			<form class="form" id="form1"
				action="<c:url value='j_spring_security_check'/>" method="POST">
				<input type="text" name="j_username" maxlength="22"
					class="feedback-input" placeholder="Korisničko ime" /> <input
					type="password" name="j_password" maxlength="22"
					class="feedback-input" placeholder="Lozinka" /> <br /> <br /> <br />
				<div class="submit">
					<input type="submit" id="button-blue" value="Prijava" />
					<div class="ease"></div>
				</div>
			</form>
		</div>
	</div>



</body>
</html>