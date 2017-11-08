<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<link
	href="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css"
	rel="stylesheet" />
<script
	src="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/style.css">
<title>Izmjena zaposlenika</title>
</head>
<body>
	<div class="nav">
		<ul>
		<sec:authorize access="hasAnyRole('Administrator,Zaposlenik')">
			<li><a href="index.html">Početna</a></li></sec:authorize>
			<li><a href="openedReservationList.html">Rezervacije</a>
				<ul>
					<li><a href="openedReservationList.html">Otvorene
							rezervacije</a></li>
					<li><a href="closedReservationList.html">Zatvorene
							rezervacije</a></li>
					<li><a href="createReservation.html">Nova rezervacija</a></li>
				</ul></li>
			<sec:authorize access="hasAnyRole('Administrator,Zaposlenik')">
				<sec:authorize access="hasRole('Administrator')">
					<li><a href="employeeList.html">Zaposlenici</a></li>
				</sec:authorize>
				<li><a href="carList.html">Flota</a>
					<ul>
						<li><a href="carHistoryList.html">Kretanje vozila</a></li>
						<li><a href="carList.html">Pregled flote</a></li>
						<sec:authorize access="hasRole('Zaposlenik')">
							<li><a href="carListForOffice.html">Pregled flote u
									poslovnici</a></li>
						</sec:authorize>
						<sec:authorize access="hasRole('Administrator')">
							<li><a href="createCar.html">Novi auto</a></li>
						</sec:authorize>
					</ul></li>

				<li><a href="openedTransferCardList.html">Transfer karte</a>
					<ul>
						<li><a href="openedTransferCardList.html">Otvorene
								transfer karte</a></li>
						<li><a href="closedTransferCardList.html">Zatvorene
								transfer karte</a></li>
						<li><a href="createTransferCard.html">Nova transfer karta</a></li>
					</ul></li>
				<li><a href="openedContractList.html">Ugovori</a>
					<ul>
						<li><a href="openedContractList.html">Otvoreni ugovori</a></li>
						<li><a href="closedContractList.html">Zatvoreni ugovori</a></li>
						<li><a href="createContract.html">Novi ugovor</a></li>
					</ul></li>
			</sec:authorize>
			<li><a href="customerList.html">Stranke</a></li>
		</ul>
	</div>
	<div id="table">
		<sf:form action="editEmployee.html" method="POST"
			commandName="employee">
			<table style="width: 100%">
				<tr>
					<td><input type="hidden" name="id" value="${employee.id}" /></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="office" style="color: red" /></td>
				</tr>
				<tr>
					<td>Poslovnica:</td>
					<td><select name="office">
							<c:forEach items='${officeList}' var='office'>
								<c:choose>
									<c:when test="${office.name eq employee.office.name}">
										<option value="${office.id}" selected="true">${office.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${office.id}">${office.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="username" style="color:red" /></td>
					<td></td>
					<td><sf:errors path="password" style="color:red" /></td>
				</tr>
				<tr>
					<td>Korisničko ime:</td>
					<td><sf:input path="username" value="${employee.username}" /></td>
					<td>Lozinka:</td>
					<td><sf:input path="password" value="${employee.password}" /></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="firstname" style="color:red" /></td>
					<td></td>
					<td><sf:errors path="lastname" style="color:red" /></td>
				</tr>
				<tr>
					<td>Ime zaposlenika:</td>
					<td><sf:input path="firstname" value="${employee.firstname}" /></td>
					<td>Prezime zaposlenika:</td>
					<td><sf:input path="lastname" value="${employee.lastname}" /></td>
					<td>Broj zaposlenika</td>
					<td><sf:input path="agentNr" value="${employee.agentNr}" /></td>
				</tr>
				<tr>
					<td>Broj vozačke:</td>
					<td><sf:input path="driversLicenseNr"
							value="${employee.driversLicenseNr}" /></td>
					<td>Datum rođenja:</td>
					<fmt:formatDate pattern="dd.MM.yyyy." value="${employee.dob}"
						var="datumRodjenja" />
					<td><sf:input path="dob" id="datepicker"
							value="${datumRodjenja}" /> <script>
								$(function() {
									$("#datepicker").datepicker(
											{

												dateFormat : 'dd.mm.yy.',
												monthNames : [ "Siječanj",
														"Veljača", "Ožujak",
														"Travanj", "Svibanj",
														"Lipanj", "Srpanj",
														"Kolovoz", "Rujan",
														"Listopad", "Studeni",
														"Prosinac" ]
											});

								});
							</script></td>
				</tr>
			</table>
			<center>
				<button type="submit" style="margin-top: 20px">Spremi
					zaposlenika</button>
			</center>
		</sf:form>
	</div>

</body>
</html>