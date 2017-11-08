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
<title>Izmjena auta</title>

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
		<sf:form action="editCar.html" method="POST" commandName="car">
			<table style="width: 100%">
				<tr>
					<td><input type="hidden" name="id" value="${car.id}" /></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="office" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="owner" style="color:red" /></td>
					<td></td>
					<td><sf:errors path="picture" style="color:red" /></td>
				</tr>
				<tr>
					<td>Poslovnica:</td>
					<td><select name="office">
							<c:forEach items='${officeList}' var='office'>
								<c:choose>
									<c:when test="${office.name eq car.office.name}">
										<option value="${office.id}" selected="true">${office.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${office.id}">${office.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
					<td>Vlasnik:</td>
					<td><sf:input path="owner" value="${car.owner}" /></td>
					<td>Slika:</td>
					<td><sf:input path="picture" value="${car.picture}" /></td>
					<td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="name" style="color:red" /></td>
					<td></td>
					<td><sf:errors path="registrationNr" style="color:red" /></td>
					<td></td>
					<td><sf:errors path="registrationExp" style="color:red" /></td>
				</tr>
				<tr>
					<td>Naziv:</td>
					<td><sf:input path="name" value="${car.name}" /></td>
					<td>Registracija:</td>
					<td><sf:input path="registrationNr"
							value="${car.registrationNr}" /></td>
					<td>Istek registracije:</td>
					<fmt:formatDate pattern="dd.MM.yyyy."
						value="${car.registrationExp}" var="istekRegistracije" />
					<td><sf:input path="registrationExp"
							value="${istekRegistracije}" id="datepicker" /> <script>
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
				<tr>
					<td></td>
					<td><sf:errors path="km" style="color:red" /></td>
					<td></td>
					<td><sf:errors path="fuel" style="color:red" /></td>
					<td></td>
					<td><sf:errors path="status" style="color: red" /></td>
				</tr>
				<tr>
					<td>Km:</td>
					<td><sf:input path="km" value="${car.km}" /></td>
					<td>Gorivo:</td>
					<td><sf:input path="fuel" value="${car.fuel}" /></td>
					<td>Status</td>
					<td><select name="status">
							<option value="Za najam"
								<c:if test="${car.status eq 'Za najam'}"> <c:out value= "selected=selected"/></c:if>>Za
								najam</option>
							<option value="U najmu"
								<c:if test="${car.status eq 'U najmu'}"> <c:out value= "selected=selected"/></c:if>>U
								najmu</option>
							<option value="U servisu"
								<c:if test="${car.status eq 'U servisu'}"> <c:out value= "selected=selected"/></c:if>>U
								servisu</option>
							<option value="U transferu"
								<c:if test="${car.status eq 'U transferu'}"> <c:out value= "selected=selected"/></c:if>>U
								transferu</option>
					</select></td>
					<td>
				</tr>
			</table>
			<center>
				<button type="submit" style="margin-top: 20px">Spremi auto</button>
			</center>
		</sf:form>
	</div>
</body>
</html>