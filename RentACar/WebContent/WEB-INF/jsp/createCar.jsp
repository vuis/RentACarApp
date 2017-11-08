<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Unos auta</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<link
	href="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/bootstrap.timepicker/0.2.6/css/bootstrap-timepicker.min.css"
	rel="stylesheet" />
<script
	src="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="nav">
		<ul>
			<sec:authorize access="hasAnyRole('Administrator,Zaposlenik')">
				<li><a href="index.html">Početna</a></li>
			</sec:authorize>
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
		<sf:form action="createCar.html" metdod="POST" commandName="car">
			<table style="width: 100%">
				<tr>
					<td></td>
					<td><sf:errors path="office" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="owner" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="picture" style="color: red" /></td>
				</tr>
				<tr>
					<td>Poslovnica:</td>
					<td><sf:select path="office">
							<sf:option value="">
								Odaberite poslovnicu
							</sf:option>
							<sf:options itemLabel="name" items="${officeList}" itemValue="id" />
						</sf:select></td>
					<td>Vlasnik:</td>
					<td><sf:input path="owner" /></td>
					<td>Slika:</td>
					<td><sf:input path="picture" /></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="name" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="registrationNr" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="registrationExp" style="color: red" /></td>
				</tr>
				<tr>
					<td>Naziv:</td>
					<td><sf:input path="name" /></td>
					<td>Registracija</td>
					<td><sf:input path="registrationNr" /></td>
					<td>Istek registracije</td>
					<td><sf:input path="registrationExp" id="datepicker1" /> <script>
						$(function() {
							$("#datepicker1").datepicker(
									{
										dateFormat : 'dd.mm.yy.',
										monthNames : [ "Siječanj", "Veljača",
												"Ožujak", "Travanj", "Svibanj",
												"Lipanj", "Srpanj", "Kolovoz",
												"Rujan", "Listopad", "Studeni",
												"Prosinac" ]
									});
						});
					</script></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="km" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="fuel" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="status" style="color: red" /></td>
				</tr>
				<tr>
					<td>KM:</td>
					<td><sf:input path="km" /></td>
					<td>Gorivo:</td>
					<td><sf:input path="fuel" /></td>
					<td>Status:</td>
					<td><sf:select path="status">
							<sf:option value="">
								Odaberite status
							</sf:option>
							<sf:option value="U najmu">
								U najmu
							</sf:option>
							<sf:option value="Za najam">
								Za najam
							</sf:option>
							<sf:option value="U servisu">
								U servisu
							</sf:option>
							<sf:option value="U transferu">
								U transferu
							</sf:option>
						</sf:select></td>
				</tr>
			</table>
			<center>
				<button type="submit" style="margin-top: 20px">Spremi auto</button>
			</center>
		</sf:form>
	</div>
</body>
</html>