<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<title>Unos rezervacije</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<script src="script/moment.js"></script>
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
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript">
$(document).ready(function() {
	$(".change").hide();
	if ($("#changeCar").attr("checked")) {
		$(".change").show();
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
});
</script>
</head>
<body>
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
		<sf:form action="createReservation.html" method="POST"
			commandName="reservation">
			<table style="width: 100%">
			<tr>			
				<tr>
					<td></td>
					<td><sf:errors path="status" style="color: red" /></td>
				</tr>
				<tr>
					<td>Status rezervacije:</td>
					<td><sf:select path="status">
							<sf:option value="Otvorena">
								Otvorena
							</sf:option>
							<sf:option value="Zatvorena">
								Zatvorena
							</sf:option>
						</sf:select></td>
					<td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="reservationNr" style="color:red" /></td>
					<td></td>
					<td><sf:errors path="reservationType" style="color: red" /></td>
					<td></td>
					<td><sf:errors path="bookerNr" style="color: red" /></td>
				</tr>
				<tr>
					<td>Broj rezervacije:</td>
					<td><sf:input path="reservationNr" /></td>
					<td>Vrsta rezervacije:</td>
					<td><sf:select path="reservationType">
							<sf:option value="">
								Odaberite vrstu rezervacije
							</sf:option>
							<sf:options items="${reservationTypes}" />
						</sf:select></td>
					<td>Broj bookera:</td>
					<td><sf:input path="bookerNr" /></td>
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
					<td><input type="hidden" name="customer.id"
						value="${customer.id}" /></td>
				</tr>
				<tr>

					<td>Ime stranke:</td>
					<td><sf:input path="customer.firstname"
							value="${customer.firstname}" /></td>
					<td>Prezime stranke:</td>
					<td><sf:input path="customer.lastname"
							value="${customer.lastname}" /></td>
					<td>Datum rođenja:</td>
					<fmt:formatDate pattern="dd.MM.yyyy."
						value="${customer.dob}" var="dob" />
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
							value="${customer.address}" /></td>
					<td>Poštanski broj:</td>
					<td><sf:input path="customer.postalCode"
							value="${customer.postalCode}" /></td>
					<td>Grad:</td>
					<td><sf:input path="customer.city"
							value="${customer.city}" /></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="customer.country" style="color: red" /></td>
				</tr>
				<tr>
					<td>Država:</td>
					<td><sf:input path="customer.country"
							value="${customer.country}" /></td>
					<td>Email:</td>
					<td><sf:input path="customer.email"
							value="${customer.email}" /></td>
					<td>Broj telefona:</td>
					<td><sf:input path="customer.phoneNr"
							value="${customer.phoneNr}" /></td>
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
							value="${customer.driversLicenseNr}" /></td>
					<td>Broj putovnice:</td>
					<td><sf:input path="customer.passportNr"
							value="${customer.passportNr}" /></td>
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
							value="${customer.cardNr}" /></td>
					<td>Istek kartice:</td>
					<td><sf:input path="customer.cardExp"
							value="${customer.cardExp}" /></td>
					<td>CVV broj kartice:</td>
					<td><sf:input path="customer.cardCvv"
							value="${customer.cardCvv}" /></td>
				</tr>
				<tr>
					<td><h3>Podaci o najmu:</h3></td>
				<tr>
				<tr>
					<td></td>
					<td><sf:errors path="vehicleGroup" style="color: red" /></td>
				</tr>
				<tr>
					<td>Grupa vozila:</td>
					<td><sf:select path="vehicleGroup">
							<sf:option value="">
								Odaberite grupu vozila
							</sf:option>
							<sf:options items="${vehicleGroupList}" />
						</sf:select></td>
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
					<td><sf:select path="pickUpOffice">
							<c:forEach items='${officeList}' var='pickupOffice'>
								<c:choose>
									<c:when test="${pickupOffice.name eq office}">
										<option value="${pickupOffice.id}" selected="true">${pickupOffice.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${pickupOffice.id}">${pickupOffice.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</sf:select></td>
					<td>Datum preuzimanja:</td>
					<td><sf:input path="pickUpDate" id="datepicker" /> <script>
						$(function() {
							$("#datepicker").datepicker(
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
					<td>Vrijeme preuzimanja:</td>
					<td><sf:input path="pickUpTime" id="timepicker" /> <script
							type="text/javascript">
						$('#timepicker').timepicker({
							showMeridian : false,
							defaultTime : '07:00'
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
					<td><sf:select path="dropOffOffice">
							<sf:option value="">
								Odaberite poslovnicu
							</sf:option>
							<sf:options itemLabel="name" items="${officeList}" itemValue="id" />
						</sf:select></td>
					<td>Datum vraćanja:</td>
					<td><sf:input path="dropOffDate" id="datepicker1" /> <script>
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
					<td>Vrijeme vraćanja:</td>
					<td><sf:input path="dropOffTime" id="timepicker1" /> <script
							type="text/javascript">
						$('#timepicker1').timepicker({
							showMeridian : false,
							defaultTime : '07:00'
						});
					</script></td>
				</tr>
				<tr>
					<td><h4>Osiguranja:</h4></td>
				</tr>
				<tr>
					<td>Osiguranje u slučaju oštećenja:</td>
					<td><sf:select path="insurance.cdw">
							<sf:options items="${acceptanceList}" />
						</sf:select></td>
					<td>Osiguranje u slučaju krađe:</td>
					<td><sf:select path="insurance.tp">
							<sf:options items="${acceptanceList}" />
						</sf:select></td>
					<td>Osiguranje putnika:</td>
					<td><sf:select path="insurance.pai">
							<sf:options items="${acceptanceList}" />
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
					<td><sf:input path="insurance.cdwPrice" value="0.00"/></td>
					<td>Cijena:</td>
					<td><sf:input path="insurance.tpPrice" value="0.00"/></td>
					<td>Cijena:</td>
					<td><sf:input path="insurance.paiPrice" value="0.00"/></td>
				</tr>



				<tr>
					<td><h4>Dodaci:</h4></td>
					<td><input type="checkbox" id="checkbox"
						style="margin-top: 0px; margin-bottom: 8px;" /></td>
				</tr>
				<tr class="showRow">
					<td>Gps:</td>
					<td><form:checkbox path="accessories.gps" style="margin-bottom: 3px;"/></td>
					<td>Wifi:</td>
					<td><form:checkbox path="accessories.wifi" style="margin-bottom: 3px;"/></td>
					<td>Sjedalica za dijete:</td>
					<td><form:checkbox path="accessories.babySeat" style="margin-bottom: 3px;"/></td>
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
					<td><sf:input path="accessories.gpsPrice" value="0.00"/></td>
					<td>Cijena:</td>
					<td><sf:input path="accessories.wifiPrice" value="0.00"/></td>
					<td>Cijena:</td>
					<td><sf:input path="accessories.babySeatPrice" value="0.00"/></td>
				</tr>

				<tr class="showRow">
					<td>Jednosmjerna naknada:</td>
					<td><form:checkbox path="accessories.oneWayFee" style="margin-bottom: 3px;"/></td>
					<td>Dodatni vozač:</td>
					<td><form:checkbox path="accessories.additionalDriver" style="margin-bottom: 3px;"/></td>
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
					<td><sf:input path="accessories.oneWayFeePrice" value="0.00"/></td>
					<td>Cijena:</td>
					<td><sf:input path="accessories.additionalDriverPrice" value="0.00"/></td>
				<tr>
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
					<td><sf:input path="priceAdditions.aptFee" /></td>
					<td>Cestarina:</td>
					<td><sf:input path="priceAdditions.roadFee" /></td>
					<td>Registracija vozila:</td>
					<td><sf:input path="priceAdditions.vehicleLicenseFee" /></td>
				</tr>
				<tr>
					<td></td>
					<td><sf:errors path="baseRate" style="color: red" /></td>
				</tr>
				<tr>
					<td>Base rate:</td>
					<td><sf:input path="baseRate"/></td>
					<td>Ukupna cijena:</td>
					<td><sf:input path="price" /></td>
					<td><button type="button" onclick="izracunajCijenu()">Izračunaj
							cijenu</button></td>
				</tr>
				<tr>
					<td>Napomena:</td>
					<td><textarea name="note" rows="3" cols="3"></textarea></td>
				</tr>
			</table>



			<center>
				<button type="submit" style="margin-top: 20px">Spremi
					rezervaciju</button>
			</center>
		</sf:form>
	</div>
		<script type="text/javascript">
		function izracunajCijenu() {
			var date1 = document.getElementById('datepicker').value;
			var date2 = document.getElementById('datepicker1').value;
			var vrijemePreuzimanja = document.getElementById('timepicker').value;
			var vrijemeVracanja = document.getElementById('timepicker1').value;
			if (date1 == "" || date2 == "") {
				alert("Niste odabrali datum")
			} else if (document.getElementById('priceAdditions.aptFee').value == ""
					|| document.getElementById('priceAdditions.roadFee').value == ""
					|| document
							.getElementById('priceAdditions.vehicleLicenseFee').value == "") {
				alert("Niste unijeli dodatke na cijenu")
			} else if (document.getElementById('baseRate').value == "") {
				alert("Niste unijeli base rate")
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