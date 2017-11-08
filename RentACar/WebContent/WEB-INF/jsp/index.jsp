<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Početna</title>
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
		<c:set var="now" value="<%=new java.util.Date()%>" />

		<div>
			<p>Prijavljeni ste kao ${fullName}</p>
			<b><p>
					Stanje na dan:
					<fmt:formatDate pattern="dd.MM.yyyy." value="${now}" />
				</p></b>
			<p>Poslovnica: ${office}</p>
			<p>Rezervacija: ${reservations}</p>
			<p>Vraćanja: ${returns}</p>
			<p>Vozila za najam: ${idleCars}</p>
			<p>Vozila u najmu: ${carsInRent}</p>
			<p>Vozila u transferu: ${carsInTransfer}</p>
			<p>Vozila u servisu: ${carsInService}</p>
			<form method="POST" action="j_spring_security_logout">
			<button type="submit" style="margin-top: 20px">Odjava</button>
		</form>
		</div>
		
	</div>
</body>
</html>