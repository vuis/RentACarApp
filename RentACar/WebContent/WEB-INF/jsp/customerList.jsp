<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Pregled stranaka</title>
<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.1/semantic.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.12/css/dataTables.semanticui.min.css" />
<script type="text/javascript" src="//code.jquery.com/jquery-1.12.3.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.12/js/dataTables.semanticui.min.js"></script>
<script type="text/javascript"
	src="//cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.1/semantic.min.js"></script>
<link rel="stylesheet" href="css/style.css">
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
		<table id="myTable" class="ui celled table">
			<thead>
				<tr>
					<th style="font-weight: bold">Akcije</th>
					<th style="font-weight: bold">Ime i prezime stranke:</th>
					<th style="font-weight: bold">Datum rođenja:</th>
					<th style="font-weight: bold">Adresa:</th>
					<th style="font-weight: bold">Grad:</th>
					<th style="font-weight: bold">Država:</th>
					<th style="font-weight: bold">Email:</th>
					<th style="font-weight: bold">Telefon:</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${customerList}" var="customer">
					<tr>
						<td><center>
								<a href="createReservationForCustomer.html?customerId=${customer.id}"> <img
									border="0" alt="Rezervacija" src="images/reservation.png">
								</a>
								<sec:authorize access="hasAnyRole('Administrator','Zaposlenik')">
								<a href="createContractForCustomer.html?customerId=${customer.id}"> <img
									border="0" alt="Ugovor" src="images/contract.png">
								</a></sec:authorize>
							</center></td>
						<td><c:out value="${customer.fullName}" /></td>
						<td><fmt:formatDate pattern="dd.MM.yyyy."
								value="${customer.dob}" /></td>
						<td><c:out value="${customer.address}" /></td>
						<td><c:out value="${customer.city}" /></td>
						<td><c:out value="${customer.country}" /></td>
						<td><c:out value="${customer.email}" /></td>
						<td><c:out value="${customer.phoneNr}" /></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
		<center>
			<form method="POST" action="j_spring_security_logout">
				<button type="submit" style="margin-top: 20px">Odjava</button>
			</form>
		</center>

		<script type="text/javascript">
			$(document)
					.ready(
							function() {
								$('#myTable')
										.dataTable(
												{
													"language" : {
														"url" : "//cdn.datatables.net/plug-ins/1.10.12/i18n/Croatian.json"
													}
												});
							});
		</script>
	</div>

</body>
</html>