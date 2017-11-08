<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Unos transfera</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<link rel="stylesheet" href="css/style.css">
<script>
	var request;
	var requestOffice;
	function sendInfo() {
		var v = document.getElementById('employee').value;
		var url = "/RentACar/employeeTable.html?employeeId=" + v;

		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			request = new ActiveXObject("Microsoft.XMLHTTP");
		}

		try {
			request.onreadystatechange = getInfo;
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}

	function getInfo() {
		if (request.readyState == 4) {
			var val = request.responseText;
			document.getElementById('employeeInfo').innerHTML = val;
		}
	}

	function sendOfficeOut() {
		var v = document.getElementById('car').value;
		var url = "/RentACar/officeOut.html?carId=" + v;
		if (window.XMLHttpRequest) {
			requestOffice = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requestOffice = new ActiveXObject("Microsoft.XMLHTTP");
		}

		try {
			requestOffice.onreadystatechange = getOfficeOut;
			requestOffice.open("GET", url, true);
			requestOffice.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}

	function getOfficeOut() {
		if (requestOffice.readyState == 4) {
			var val = requestOffice.responseText;
			document.getElementById('officeOutResponse').innerHTML = val;
		}
	}
</script>

<style>
table tr td {
	width: 192px;
}

#employeeInfo tr td {
	width: 192px;
}
</style>
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
		<sf:form action="createTransferCard.html" method="POST"
			commandName="transferCard" class="form">
			<table>
				<tr>
					<td>Status:</td>
					<td><sf:select path="status">
							<sf:option value="Otvorena">
								Otvorena
							</sf:option>
							<sf:option value="Zatvorena">
								Zatvorena
							</sf:option>
						</sf:select></td>
				</tr>
				<tr>
					<td><input type="hidden" name="invoiceAmount" value="0.00"/></td>
					<td><sf:errors path="employee" style="color:red" /></td>
				</tr>
				<tr>
					<td>Ime i prezime zaposlenika:</td>
					<td><sf:select path="employee" onchange="sendInfo()">
							<sf:option value="">
								Odaberite zaposlenika
							</sf:option>
							<sf:options itemLabel="fullName" items="${employeeList}"
								itemValue="id" />
						</sf:select></td>
				</tr>
			</table>
			<table id="employeeInfo"></table>
			<table>
				<tr>
					<td></td>
					<td><sf:errors path="car" style="color:red" /></td>
				</tr>
				<tr>
					<td>Vozilo:</td>
					<td><sf:select path="car" onChange="sendOfficeOut()">
							<sf:option value="">
								Odaberite auto
							</sf:option>
							<sf:options itemLabel="data" items="${carListForOffice}"
								itemValue="id" />
						</sf:select></td>
				</tr>
			</table>
			<table id="officeOutResponse"></table>
			<table>
				<tr>
					<td></td>
					<td><sf:errors path="officeIn" style="color:red" /></td>
				</tr>

				<tr>
					<td>Poslovnica dolaska:</td>
					<td><sf:select path="officeIn">
							<sf:option value="">
								Odaberite poslovnicu
							</sf:option>
							<sf:options itemLabel="name" items="${officeList}" itemValue="id" />
						</sf:select></td>
				</tr>
				<tr>
				<tr>

					<td>Status:</td>
					<td><sf:select path="car.status">
							<sf:option value="U transferu">U transferu</sf:option>
							<sf:option value="U servisu">U servisu</sf:option>
							<sf:option value="Za najam">Za najam</sf:option>
						</sf:select></td>
				</tr>
				<tr>
					<td>Napomena:</td>
					<td><textarea name="note" rows="3" cols="3"></textarea></td>
				</tr>
			</table>
			<center>
			<button>Spremi</button></center>
		</sf:form>



	</div>
</body>
</html>