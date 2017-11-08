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
<title>Izmjena ugovora</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css" />
<link rel="stylesheet" href="/resources/demos/style.css" />
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
	src="https://cdn.jsdelivr.net/bootstrap.timepicker/0.2.6/js/bootstrap-timepicker.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$(".change").hide();
		$(".closeContract").hide();
		if ($("#changeCar").attr("checked")) {
			$(".change").show();
			$(".closeContract").show();
		}
		if ($("#checkbox").attr("checked")) {
			$(".showRow").show();
		} else if ($("[name='accessories.gps']").attr("checked")) {
			$("#checkbox").attr("checked", true);
			$(".showRow").show();
			$(".customer2").hide();
		} else if ($("[name='accessories.wifi']").attr("checked")) {
			$("#checkbox").attr("checked", true);
			$(".showRow").show();
			$(".customer2").hide();
		} else if ($("[name='accessories.babySeat']").attr("checked")) {
			$("#checkbox").attr("checked", true);
			$(".showRow").show();
			$(".customer2").hide();
		} else if ($("[name='accessories.oneWayFee']").attr("checked")) {
			$("#checkbox").attr("checked", true);
			$(".showRow").show();
			$(".customer2").hide();
		} else if ($("[name='accessories.additionalDriver']").attr("checked")) {
			$("#checkbox").attr("checked", true);
			$(".showRow").show();
			$('.customer2').show();
		} else {
			$(".showRow").hide();
			$(".customer2").hide();
		}
		$("#checkbox").change(function() {
			$(".showRow").toggle();
		});
		$("[name='accessories.additionalDriver']").change(function() {
			$(".customer2").toggle();
		});
		$("#changeCar").change(function() {
			$(".change").toggle();
			$(".closeContract").toggle();

		});

	});
	$(document).ready(function() {
		$("#status").change(function() {
			$(this).find("option:selected").each(function() {
				if ($(this).attr("value") == "Zatvoren") {
					$(".closeContract").show();
				} else {
					$(".closeContract").hide();
				}
			});
		}).change();
	});
	function spremiUgovor() {
		document.getElementById('form').action = 'editContract.html?carId='
				+ document.getElementById('changedCar').value;
	}

	function izracunajKm() {
		var trenutniKm = document.getElementById('km').value;
		var izlazniKm = document.getElementById('kmOut').value;
		var km = trenutniKm - izlazniKm;
		if (km < 0) {			
			document.getElementById('totalKm').value = km;

		} else {
			
			document.getElementById('totalKm').value = km;
		}
	}
</script>

<script src="script/moment.js"></script>
<link rel="stylesheet" href="css/style.css" />
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
		<sf:form id="form" method="POST" commandName="contract">
			<table style="width: 100%">
				<tr>
					<td><input type="hidden" name="id" value="${contract.id}" /></td>
					<td><input type="hidden" name="accessories.id"
						value="${contract.accessories.id}" /></td>
					<td><input type="hidden" name="insurance.id"
						value="${contract.insurance.id}" /></td>
					<td><input type="hidden" name="priceAdditions.id"
						value="${contract.priceAdditions.id}" /></td>
					<td><input type="hidden" name="customer.id"
						value="${contract.customer.id}" /></td>
					<c:if test="${contract.accessories.additionalDriver eq true}">
						<td><input type="hidden" name="customer2.id"
							value="${contract.customer2.id}" /></td>
					</c:if>
					<c:if test="${contract.carChange eq true}">
						<td><input type="hidden" name="car.km"
							value="${car1Km}" /></td>
						<td><input type="hidden" name="car.fuel"
							value="${contract.car.fuel}" /></td>
						<td><input type="hidden" name="car.status"
							value="${contract.car.status}" /></td>
						<td><input type="hidden" name="car2.id"
							value="${contract.car2.id}" /></td>
						<td><input type="hidden" name="car2.office"
							value="${contract.pickUpOffice.id}" /></td>
						<td><input type="hidden" name="car2.name"
							value="${contract.car2.name}" /></td>
						<td><input type="hidden" name="car2.registrationNr"
							value="${contract.car2.registrationNr}" /></td>
						<fmt:formatDate pattern="dd.MM.yyyy."
							value="${contract.car2.registrationExp}" var="istekRegistracije2" />
						<td><input type="hidden" name="car2.registrationExp"
							value="${istekRegistracije2}" /></td>
						<td><input type="hidden" name="car2.owner"
							value="${contract.car2.owner}" /></td>
						<td><input type="hidden" name="car2.picture"
							value="${contract.car2.picture}" /></td>
						<td><input type="hidden" name="reservationNr"
							value="${contract.reservationNr}" /></td>
					</c:if>
					<td><input type="hidden" name="car.id"
						value="${contract.car.id}" /></td>
					<td><input type="hidden" name="car.office"
						value="${contract.pickUpOffice.id}" /></td>
					<td><input type="hidden" name="car.name"
						value="${contract.car.name}" /></td>
					<td><input type="hidden" name="car.registrationNr"
						value="${contract.car.registrationNr}" /></td>
					<fmt:formatDate pattern="dd.MM.yyyy."
						value="${contract.car.registrationExp}" var="istekRegistracije" />
					<td><input type="hidden" name="car.registrationExp"
						value="${istekRegistracije}" /></td>
					<td><input type="hidden" name="car.owner"
						value="${contract.car.owner}" /></td>
					<td><input type="hidden" name="car.picture"
						value="${contract.car.picture}" /></td>

				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="status" style="color: red" /></td>
				</tr>
				<tr>
					<td>Status ugovora:</td>
					<td><select name="status" id="status">
							<option value="Otvoren"
								<c:if test="${contract.status eq 'Otvoren'}"> <c:out value= "selected=selected"/></c:if>>Otvoren</option>
							<option value="Zatvoren"
								<c:if test="${contract.status eq 'Zatvoren'}"> <c:out value= "selected=selected"/></c:if>>Zatvoren</option>
					</select></td>
					<td>
				</tr>

				<tr>
					<td><h4>Podaci o stranici:</h4></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="customer.firstname" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="customer.lastname" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="customer.dob" style="color: red" /></td>
				</tr>

				<tr>
					<td>Ime stranke:</td>
					<td><sf:input path="customer.firstname"
							value="${contract.customer.firstname}" /></td>
					<td>Prezime stranke:</td>
					<td><sf:input path="customer.lastname"
							value="${contract.customer.lastname}" /></td>
					<td>Datum rođenja:</td>
					<fmt:formatDate pattern="dd.MM.yyyy."
						value="${contract.customer.dob}" var="dob" />
					<td><sf:input path="customer.dob" id="datepicker2"
							value="${dob}" /> <script>
								$(function() {
									$("#datepicker2").datepicker(
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
					<td><sf:errors path="customer.address" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="customer.postalCode" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="customer.city" style="color: red" /></td>
				</tr>
				<tr>
					<td>Adresa stranke:</td>
					<td><sf:input path="customer.address"
							value="${contract.customer.address}" /></td>
					<td>Poštanski broj:</td>
					<td><sf:input path="customer.postalCode"
							value="${contract.customer.postalCode}" /></td>
					<td>Grad:</td>
					<td><sf:input path="customer.city"
							value="${contract.customer.city}" /></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="customer.country" style="color: red" /></td>
				</tr>
				<tr>
					<td>Država:</td>
					<td><sf:input path="customer.country"
							value="${contract.customer.country}" /></td>
					<td>Email:</td>
					<td><sf:input path="customer.email"
							value="${contract.customer.email}" /></td>
					<td>Broj telefona:</td>
					<td><sf:input path="customer.phoneNr"
							value="${contract.customer.phoneNr}" /></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="customer.driversLicenseNr"
							style="color: red" /></td>
					<td></td>
					<td><sf:errors path="customer.passportNr" style="color: red" /></td>

				</tr>
				<tr>
					<td>Broj vozačke dozvole:</td>
					<td><sf:input path="customer.driversLicenseNr"
							value="${contract.customer.driversLicenseNr}" /></td>
					<td>Broj putovnice:</td>
					<td><sf:input path="customer.passportNr"
							value="${contract.customer.passportNr}" /></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="customer.cardNr" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="customer.cardExp" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="customer.cardCvv" style="color: red" /></td>
				</tr>
				<tr>
					<td>Broj kartice:</td>
					<td><sf:input path="customer.cardNr"
							value="${contract.customer.cardNr}" /></td>
					<td>Istek kartice:</td>
					<td><sf:input path="customer.cardExp"
							value="${contract.customer.cardExp}" /></td>
					<td>CVV broj kartice:</td>
					<td><sf:input path="customer.cardCvv"
							value="${contract.customer.cardCvv}" /></td>
				</tr>
				<tr class="customer2">
					<td><h4>Podaci o dodatnom vozaču:</h4></td>
				</tr>
				<tr class="customer2">
					<td></td>
					<td><sf:errors path="customer2.firstname" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="customer2.lastname" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="customer2.dob" style="color: red" /></td>
				</tr>

				<tr class="customer2">
					<td>Ime stranke:</td>
					<td><sf:input path="customer2.firstname"
							value="${contract.customer2.firstname}" /></td>
					<td>Prezime stranke:</td>
					<td><sf:input path="customer2.lastname"
							value="${contract.customer2.lastname}" /></td>
					<td>Datum rođenja:</td>
					<fmt:formatDate pattern="dd.MM.yyyy."
						value="${contract.customer2.dob}" var="dob2" />
					<td><sf:input path="customer2.dob" id="datepicker3"
							value="${dob2}" /> <script>
								$(function() {
									$("#datepicker3").datepicker(
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
				<tr class="customer2">
					<td></td>
					<td><sf:errors path="customer2.address" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="customer2.postalCode" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="customer2.city" style="color: red" /></td>
				</tr>
				<tr class="customer2">
					<td>Adresa stranke:</td>
					<td><sf:input path="customer2.address"
							value="${contract.customer2.address}" /></td>
					<td>Poštanski broj:</td>
					<td><sf:input path="customer2.postalCode"
							value="${contract.customer2.postalCode}" /></td>
					<td>Grad:</td>
					<td><sf:input path="customer2.city"
							value="${contract.customer2.city}" /></td>
				</tr>
				<tr class="customer2">
					<td></td>
					<td><sf:errors path="customer2.country" style="color: red" /></td>
				</tr>
				<tr class="customer2">
					<td>Država:</td>
					<td><sf:input path="customer2.country"
							value="${contract.customer2.country}" /></td>
					<td>Email:</td>
					<td><sf:input path="customer2.email"
							value="${contract.customer2.email}" /></td>
					<td>Broj telefona:</td>
					<td><sf:input path="customer2.phoneNr"
							value="${contract.customer2.phoneNr}" /></td>
				</tr>
				<tr class="customer2">
					<td></td>
					<td><sf:errors path="customer2.driversLicenseNr"
							style="color: red" /></td>
					<td></td>
					<td><sf:errors path="customer2.passportNr" style="color: red" /></td>

				</tr>
				<tr class="customer2">
					<td>Broj vozačke dozvole:</td>
					<td><sf:input path="customer2.driversLicenseNr"
							value="${contract.customer2.driversLicenseNr}" /></td>
					<td>Broj putovnice:</td>
					<td><sf:input path="customer2.passportNr"
							value="${contract.customer2.passportNr}" /></td>

				</tr>

				<tr>
					<td><h3>Podaci o najmu:</h3></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="pickUpOffice" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="pickUpDate" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="pickUpTime" style="color: red" /></td>
				</tr>
				<tr>
					<td>Poslovnica preuzimanja:</td>
					<td><select name="pickUpOffice">
							<c:forEach items='${officeList}' var='office'>
								<c:choose>
									<c:when test="${office.name eq contract.pickUpOffice.name}">
										<option value="${office.id}" selected="true">${office.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${office.id}">${office.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
					<td>Datum preuzimanja:</td>
					<fmt:formatDate pattern="dd.MM.yyyy."
						value="${contract.pickUpDate}" var="datumPreuzimanja" />
					<td><sf:input path="pickUpDate" id="datepicker"
							value="${datumPreuzimanja}" /> <script>
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
					<td>Vrijeme preuzimanja:</td>
					<td><sf:input path="pickUpTime" id="timepicker"
							value="${contract.pickUpTime}" /> <script type="text/javascript">
								$('#timepicker').timepicker({
									showMeridian : false,
									defaultTime : '12:00'
								});
							</script></td>

				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="dropOffOffice" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="dropOffDate" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="dropOffTime" style="color: red" /></td>
				</tr>
				<tr>
					<td>Poslovnica vraćanja:</td>
					<td><select name="dropOffOffice">
							<c:forEach items='${officeList}' var='office'>
								<c:choose>
									<c:when test="${office.name eq contract.dropOffOffice.name}">
										<option value="${office.id}" selected="true">${office.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${office.id}">${office.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
					<td>Datum vraćanja:</td>
					<fmt:formatDate pattern="dd.MM.yyyy."
						value="${contract.dropOffDate}" var="datumVracanja" />
					<td><sf:input path="dropOffDate" id="datepicker1"
							value="${datumVracanja}" /> <script>
								$(function() {
									$("#datepicker1").datepicker(
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
					<td>Vrijeme vraćanja:</td>
					<td><sf:input path="dropOffTime" id="timepicker1"
							value="${contract.dropOffTime}" /> <script
							type="text/javascript">
								$('#timepicker1').timepicker({
									showMeridian : false,
									defaultTime : '12:00'
								});
							</script></td>

				</tr>
				<tr>
					<td><input type="checkbox" name="carChange"
						<c:if test="${contract.carChange eq true}">checked=checked</c:if>
						style="margin-bottom: 3px; visibility: hidden;" /></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><sf:errors path="car2" style="color: red" /></td>
				</tr>
				<c:if test="${contract.carChange eq false}">
					<tr>
						<td>Vozilo:</td>
						<td><select name="car">
								<option value="${contract.car.id}">${contract.car.data}</option>
						</select></td>
						<td>Zamjena vozila</td>
						<td><input type="checkBox" id="changeCar" /></td>
						<td class="change">Zamjensko vozilo</td>
						<td><select class="change" id="changedCar" name="car2">
								<option value="0">Odaberite auto</option>
								<c:forEach items='${carListForOffice}' var='car'>
									<option value="${car.id}">${car.data}</option>
								</c:forEach>
						</select></td>
					</tr>
						<tr><td></td>
						<td></td>
						<td></td>
					<td><sf:errors path="totalKm" style="color:red" /></td></tr>
					<tr class="closeContract">
						<td>Izlazni km:</td>
						<td><input type="text" id="kmOut" value="${car1Km}" /></td>
						<td>Trenutni km:</td>
						<td><input type="text" id="km" name="car.km"
							value="${contract.car.km}" /></td>
					</tr>
				
					<tr class="closeContract">
						<td>Napravljeni km:</td>
						<td><input type="text" name="totalKm" id="totalKm"
							value="${contract.totalKm}" /></td>
						<td><button type="button" onclick="izracunajKm()">Izračunaj
								km</button></td>
					</tr>

					<tr class="closeContract">
						<td>Gorivo:</td>
						<td><input type="text" name="car.fuel"
							value="${contract.car.fuel}" /></td>
						<td>Status:</td>
						<td><select name="car.status">
								<option value="Za najam"
									<c:if test="${contract.car.status eq 'Za najam'}"> <c:out value= "selected=selected"/></c:if>>Za
									najam</option>
								<option value="U najmu"
									<c:if test="${contract.car.status eq 'U najmu'}"> <c:out value= "selected=selected"/></c:if>>U
									najmu</option>
								<option value="U servisu"
									<c:if test="${contract.car.status eq 'U servisu'}"> <c:out value= "selected=selected"/></c:if>>U
									servisu</option>
								<option value="U transferu"
									<c:if test="${contract.car.status eq 'U transferu'}"> <c:out value= "selected=selected"/></c:if>>U
									transferu</option>
						</select></td>
					<tr class="closeContract">
						<td>Oštećenje:</td>
						<td><input type="checkbox" name="damage"
							<c:if test="${contract.damage eq true}">checked=checked</c:if>
							style="margin-bottom: 3px;" /></td>
						<td>Za naplatu:</td>
						<td><input type="text" name="damagePrice"
							value="${contract.damagePrice}"></td>
					</tr>
				</c:if>
				<c:if test="${contract.carChange eq true}">
					<tr>
						<td>Zamjensko vozilo:</td>
						<td><select name="car2">
								<option value="${contract.car2.id}">${contract.car2.data}</option>
						</select></td>
						<td>Prvo vozilo:</td>
						<td><select><option value="${contract.car.id}">${contract.car.data}</option>
						</select></td>

					</tr>
					<tr><td></td>
					<td></td>
					<td></td>
					<td><sf:errors path="totalKm" style="color:red" /></td></tr>
					<tr class="closeContract">
						<td>Izlazni km:</td>
						<td><input type="text" id="kmOut" value="${car2Km}" /></td>
						<td>Trenutni km:</td>
						<td><input type="text" id="km" name="car2.km"
							value="${contract.car2.km}" /></td>
					</tr>
					<tr class="closeContract">
						<td>Napravljeni km:</td>
						<td><input type="text" name="totalKm" id="totalKm"
							value="${contract.totalKm}" /></td>
						<td><button type="button" onclick="izracunajKm()">Izračunaj
								km</button></td>
					</tr>
					<tr class="closeContract">
						<td>Gorivo:</td>
						<td><input type="text" name="car2.fuel"
							value="${contract.car2.fuel}" /></td>
						<td>Status:</td>
						<td><select name="car2.status">
								<option value="Za najam"
									<c:if test="${contract.car2.status eq 'Za najam'}"> <c:out value= "selected=selected"/></c:if>>Za
									najam</option>
								<option value="U najmu"
									<c:if test="${contract.car2.status eq 'U najmu'}"> <c:out value= "selected=selected"/></c:if>>U
									najmu</option>
								<option value="U servisu"
									<c:if test="${contract.car2.status eq 'U servisu'}"> <c:out value= "selected=selected"/></c:if>>U
									servisu</option>
								<option value="U transferu"
									<c:if test="${contract.car2.status eq 'U transferu'}"> <c:out value= "selected=selected"/></c:if>>U
									transferu</option>
						</select></td>
						<tr class="closeContract">
						<td>Oštećenje:</td>
						<td><input type="checkbox" name="damage"
							<c:if test="${contract.damage eq true}">checked=checked</c:if>
							style="margin-bottom: 3px;" />
				</td>
						<td>Za naplatu:</td>
						<td><input type="text" name="damagePrice"
							value="${contract.damagePrice}"></td>
					</tr></c:if>
				<tr>
					<td><h4>Osiguranja:</h4></td>
				</tr>
				<tr>
					<td>Osiguranje u slučaju oštećenja:</td>
					<td><sf:select path="insurance.cdw">
							<c:forEach items='${acceptanceList}' var='acceptance'>
								<c:choose>
									<c:when test="${acceptance eq contract.insurance.cdw}">
										<option value="${acceptance}" selected="true">${acceptance}</option>
									</c:when>
									<c:otherwise>
										<option value="${acceptance}">${acceptance}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</sf:select></td>
					<td>Osiguranje u slučaju krađe:</td>
					<td><sf:select path="insurance.tp">
							<c:forEach items='${acceptanceList}' var='acceptance'>
								<c:choose>
									<c:when test="${acceptance eq contract.insurance.tp}">
										<option value="${acceptance}" selected="true">${acceptance}</option>
									</c:when>
									<c:otherwise>
										<option value="${acceptance}">${acceptance}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</sf:select></td>
					<td>Osiguranje putnika:</td>
					<td><sf:select path="insurance.pai">
							<c:forEach items='${acceptanceList}' var='acceptance'>
								<c:choose>
									<c:when test="${acceptance eq contract.insurance.pai}">
										<option value="${acceptance}" selected="true">${acceptance}</option>
									</c:when>
									<c:otherwise>
										<option value="${acceptance}">${acceptance}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</sf:select></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="insurance.cdwPrice" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="insurance.tpPrice" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="insurance.paiPrice" style="color: red" /></td>
				</tr>
				<tr>
					<td>Cijena:</td>
					<td><sf:input path="insurance.cdwPrice"
							value="${contract.insurance.cdwPrice}" /></td>
					<td>Cijena:</td>
					<td><sf:input path="insurance.tpPrice"
							value="${contract.insurance.tpPrice}" /></td>
					<td>Cijena:</td>
					<td><sf:input path="insurance.paiPrice"
							value="${contract.insurance.paiPrice}" /></td>
				</tr>



				<tr>
					<td><h4>Dodaci:</h4></td>
					<td><input type="checkbox" id="checkbox"
						style="margin-bottom: 8px;" /></td>
				</tr>
				<tr class="showRow">
					<td>Gps:</td>
					<td><input type="checkbox" name="accessories.gps"
						<c:if test="${contract.accessories.gps eq true}">checked=checked</c:if>
						style="margin-bottom: 3px;" /></td>
					<td>Wifi:</td>
					<td><input type="checkbox" name="accessories.wifi"
						<c:if test="${contract.accessories.wifi eq true}">checked=checked</c:if>
						style="margin-bottom: 3px;" /></td>
					<td>Sjedalica za dijete:</td>
					<td><input type="checkbox" name="accessories.babySeat"
						<c:if test="${contract.accessories.babySeat eq true}">checked=checked</c:if>
						style="margin-bottom: 3px;" /></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="accessories.gpsPrice" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="accessories.wifiPrice" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="accessories.babySeatPrice"
							style="color: red" /></td>
				</tr>
				<tr class="showRow">
					<td>Cijena:</td>
					<td><sf:input path="accessories.gpsPrice"
							value="${contract.accessories.gpsPrice}" /></td>
					<td>Cijena:</td>
					<td><sf:input path="accessories.wifiPrice"
							value="${contract.accessories.wifiPrice}" /></td>
					<td>Cijena:</td>
					<td><sf:input path="accessories.babySeatPrice"
							value="${contract.accessories.babySeatPrice}" /></td>
				</tr>

				<tr class="showRow">
					<td>Jednosmjerna naknada:</td>
					<td><input type="checkbox" name="accessories.oneWayFee"
						<c:if test="${contract.accessories.oneWayFee eq true}">checked=checked</c:if>
						style="margin-bottom: 3px;" /></td>
					<td>Dodatni vozač:</td>
					<td><input type="checkbox" name="accessories.additionalDriver"
						<c:if test="${contract.accessories.additionalDriver eq true}">checked=checked</c:if>
						style="margin-bottom: 3px;" /></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="accessories.oneWayFeePrice"
							style="color: red" /></td>
					<td></td>
					<td><sf:errors path="accessories.additionalDriverPrice"
							style="color: red" /></td>
				</tr>
				<tr class="showRow">
					<td>Cijena:</td>
					<td><sf:input path="accessories.oneWayFeePrice"
							value="${contract.accessories.oneWayFeePrice}" /></td>
					<td>Cijena:</td>
					<td><sf:input path="accessories.additionalDriverPrice"
							value="${contract.accessories.additionalDriverPrice}" /></td>
				</tr>
				<tr>
					<td><h4>Dodaci na cijenu:</h4></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="priceAdditions.aptFee" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="priceAdditions.roadFee"
							style="color: red" /></td>
					<td></td>
					<td><sf:errors path="priceAdditions.vehicleLicenseFee"
							style="color: red" /></td>
				</tr>
				<tr>
					<td>Aerodromska taksa (%):</td>
					<td><sf:input path="priceAdditions.aptFee"
							value="${contract.priceAdditions.aptFee}" /></td>
					<td>Cestarina:</td>
					<td><sf:input path="priceAdditions.roadFee"
							value="${contract.priceAdditions.roadFee}" /></td>
					<td>Registracija vozila:</td>
					<td><sf:input path="priceAdditions.vehicleLicenseFee"
							value="${contract.priceAdditions.vehicleLicenseFee}" /></td>
				</tr>
				<tr>
					<td><h4>Autorizacija:</h4></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="authorizationAmount" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="authorizationNr" style="color: red" /></td>
				</tr>

				<tr>
					<td>Iznos autorizacije:</td>
					<td><sf:input path="authorizationAmount"
							value="${contract.authorizationAmount}" /></td>
					<td>Broj autorizacije:</td>
					<td><sf:input path="authorizationNr"
							value="${contract.authorizationNr}" /></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="baseRate" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="price" style="color: red" /></td>
				</tr>

				<tr>
					<td>Base rate:</td>
					<td><sf:input path="baseRate" value="${contract.baseRate}" /></td>
					<td>Ukupna cijena:</td>
					<td><sf:input path="price" value="${contract.price}" /></td>
					<td><button type="button" onclick="izracunajCijenu()">Izračunaj
							cijenu</button></td>
				</tr>
				<tr>
					<td>Napomena:</td>
					<td><sf:textarea path="note" rows="3" cols="3"
							value="${contract.note}" /></td>
				</tr>
				<tr>
					<td><input type="hidden" name="outAgentNr"
						value="${contract.outAgentNr}" /></td>
					<td><input type="hidden" name="inAgentNr"
						value="${contract.inAgentNr}" /></td>
				</tr>
			</table>


			<center>
				<button type="submit" style="margin-top: 20px"
					onclick="spremiUgovor()">Spremi ugovor</button>
			</center>

		</sf:form>
	</div>
	<script type="text/javascript">
		function izracunajCijenu() {
			var date1 = document.getElementById('datepicker').value;
			var date2 = document.getElementById('datepicker1').value;
			var vrijemePreuzimanja = document.getElementById('timepicker').value;
			var vrijemeVracanja = document.getElementById('timepicker1').value;
			if (date1 == null || date2 == null) {
				alert("Niste odabrali datum")
			} else {
				var a = moment(date1, 'DD.MM.YYYY.');
				var b = moment(date2, 'DD.MM.YYYY.');
				var c = moment(vrijemePreuzimanja, 'hh:mm');
				var d = moment(vrijemeVracanja, 'hh:mm');
				var diffDays = b.diff(a, 'days');
				var diffHours = d.diff(c, 'hours');
				var diffMinutes = d.diff(c, 'minutes');
				if (diffDays < 0) {
					alert("Izabrali ste datum vraćanja koji je prije datuma preuzimanja")
				} else {
					if (diffHours > 2 && diffMinutes > 15 || diffDays == 0) {
						diffDays = diffDays + 1;
					}
					var baseRate = document.getElementById('baseRate').value;
					var cijenaOsiguranja = parseFloat(document
							.getElementById('insurance.cdwPrice').value)
							+ parseFloat(document
									.getElementById('insurance.tpPrice').value)
							+ parseFloat(document
									.getElementById('insurance.paiPrice').value);
					var cijenaDodataka = parseFloat(document
							.getElementById('accessories.gpsPrice').value)
							+ parseFloat(document
									.getElementById('accessories.wifiPrice').value)
							+ parseFloat(document
									.getElementById('accessories.babySeatPrice').value)
							+ parseFloat(document
									.getElementById('accessories.oneWayFeePrice').value)
							+ parseFloat(document
									.getElementById('accessories.additionalDriverPrice').value);

					var aerodromskaTaksa = parseFloat(document
							.getElementById('priceAdditions.aptFee').value) / 100;
					var cestarina = document
							.getElementById('priceAdditions.roadFee').value;
					var registracija = document
							.getElementById('priceAdditions.vehicleLicenseFee').value;
					var cijena = (diffDays * ((parseFloat(baseRate)
							+ parseFloat(cijenaOsiguranja) + parseFloat(cijenaDodataka))))
							+ (baseRate * parseFloat(aerodromskaTaksa))
							+ parseFloat(cestarina) + parseFloat(registracija);
					document.getElementById('price').value = toFixed(cijena, 2);
				}
			}
		}
		function toFixed(value, precision) {
			var precision = precision || 0, power = Math.pow(10, precision), absValue = Math
					.abs(Math.round(value * power)), result = (value < 0 ? '-'
					: '')
					+ String(Math.floor(absValue / power));

			if (precision > 0) {
				var fraction = String(absValue % power), padding = new Array(
						Math.max(precision - fraction.length, 0) + 1).join('0');
				result += '.' + padding + fraction;
			}
			return result;
		}
	</script>
</body>
</html>