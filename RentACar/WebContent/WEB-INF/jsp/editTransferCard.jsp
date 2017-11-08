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
<script type="text/javascript">
	$(document).ready(function() {
		$(".closeTransfer").hide();
	});
	$(document).ready(function() {
		$("#status").change(function() {
			$(this).find("option:selected").each(function() {
				if ($(this).attr("value") == "Zatvorena") {
					$(".closeTransfer").show();
				} else {
					$(".closeTransfer").hide();
				}
			});
		}).change();
	});
</script>
<title>Izmjena transfer karte</title>
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
		<sf:form action="editTransferCard.html" method="POST"
			commandName="transferCard">
			<table style="width: 90%">
				<tr>
					<fmt:formatDate pattern="dd.MM.yyyy."
						value="${transferCard.dateOut}" var="datumIz" />
					<td><input type="hidden" name="id" value="${transferCard.id}"
						readonly /></td>
					<td><input type="hidden" name="employee"
						value="${transferCard.employee.id}" readonly /></td>
					<td><input type="hidden" name="officeOut"
						value="${transferCard.officeOut.id}" readonly /></td>


				</tr>
				<tr>
					<td><h4>Transfer karta broj ${transferCard.id}</h4></td>
				</tr>
				</br>
				<tr>
					<td>Status</td>
					<td><select name="status" id="status">
							<option value="Otvorena"
								<c:if test="${transferCard.status eq 'Otvorena'}"> <c:out value= "selected=selected"/></c:if>>Otvorena</option>
							<option value="Zatvorena"
								<c:if test="${transferCard.status eq 'Zatvorena'}"> <c:out value= "selected=selected"/></c:if>>Zatvorena</option>
					</select></td>
				</tr>
				<tr>
					<td>Ime i prezime zaposlenika</td>
					<td><input type="text"
						value="${transferCard.employee.fullName}" /></td>
				</tr>
				<tr>
					<td>Poslovnica izlaska:</td>
					<td><input type="text" value=" ${transferCard.officeOut.name}" />
				</tr>
				<tr>
					<td>Poslovnica ulaska:</td>
					<td><select name="officeIn">
							<c:forEach items='${officeList}' var='office'>
								<c:choose>
									<c:when test="${office.name eq transferCard.officeIn.name}">
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
					<td>Datum izlaska:</td>
					<td><input type="text" name="dateOut" value="${datumIz}"></td>
					<td>Vrijeme izlaska:</td>
					<td><input type="text" name="timeOut"
						value="${transferCard.timeOut}" /></td>
					<td class="closeTransfer">Datum ulaska:</td>
					<fmt:formatDate pattern="dd.MM.yyyy."
						value="<%=new java.util.Date()%>" var="datumUl" />
					<td class="closeTransfer"><input type="text" name="dateIn"
						value="${datumUl}"></td>
				</tr>

				<tr>
					<td><input type="hidden" name="car.office"
						value="${transferCard.car.office.id}" /></td>
					<td><input type="hidden" name="car.id"
						value="${transferCard.car.id}" /></td>
					<td><input type="hidden" name="car.name"
						value="${transferCard.car.name}" /></td>
					<td><input type="hidden" name="car.registrationNr"
						value="${transferCard.car.registrationNr}" /></td>
					<fmt:formatDate pattern="dd.MM.yyyy."
						value="${transferCard.car.registrationExp}"
						var="istekRegistracije" />
					<td><input type="hidden" name="car.registrationExp"
						value="${istekRegistracije}" /></td>
					<td><input type="hidden" name="car.owner"
						value="${transferCard.car.owner}" /></td>
					<td><input type="hidden" name="car.picture"
						value="${transferCard.car.picture}" /></td>
					
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><sf:errors path="totalKm" style="color:red" /></td>
				</tr>
				<tr>
					<td>Vozilo:</td>
					<td><input type="text" value="${transferCard.car.data}" /></td>
					<td class="closeTransfer">Izlazni km:</td>
					<td class="closeTransfer"><input type="text" id="kmOut"
						value="${carKm}" /></td>
					<td class="closeTransfer">Trenutni km:</td>
					<td class="closeTransfer"><input type="text" id="km"
						name="car.km" value="${transferCard.car.km}" /></td>
				</tr>
				<tr>
					<td>Status:</td>
					<td><sf:select path="car.status">
							<option value="U transferu"
								<c:if test="${transferCard.car.status eq 'U transferu'}"> <c:out value= "selected=selected"/></c:if>>U
								transferu</option>
							<option value="U servisu"
								<c:if test="${transferCard.car.status eq 'U servisu'}"> <c:out value= "selected=selected"/></c:if>>U
								servisu</option>
							<option value="Za najam"
								<c:if test="${transferCard.car.status eq 'Za najam'}"> <c:out value= "selected=selected"/></c:if>>Za
								najam</option>
						</sf:select></td>

					<td class="closeTransfer">Napravljeni km:</td>
					<td class="closeTransfer"><input type="text"
						id="napravljeniKm" name="totalKm" value="${transferCard.totalKm}"/></td>
					<td class="closeTransfer">
						<button type="button" onclick="izracunajKm()">Izračunaj
							km</button>
					</td>
				</tr>
				<tr class="closeTransfer">
					<td></td>
					<td><sf:errors path="car.fuel" style="color:red" /></td>
				</tr>
				<tr class="closeTransfer">
					<td>Gorivo:</td>
					<td><input type="text" name="car.fuel" id="gorivo"
						value="${transferCard.car.fuel}" /></td>
				</tr>

				<tr class="closeTransfer">
					<td>Broj računa</td>
					<td><input type="text" name="invoiceNr"
						value="${transferCard.invoiceNr}" /></td>
					<td>Iznos računa</td>
					<td><input type="text" name="invoiceAmount"
						value="${transferCard.invoiceAmount}" /></td>
				</tr>
				<tr>
					<td>Napomena:</td>
					<td><sf:textarea path="note" rows="3" cols="3"
							value="${transferCard.note}" /></td>
				</tr>
			</table>
			<center>
				<button>Spremi transfer kartu</button>
			</center>
		</sf:form>

		<script>
			function izracunajKm() {
				var trenutniKm = document.getElementById('km').value;
				var izlazniKm = document.getElementById('kmOut').value;
				var km = trenutniKm - izlazniKm;
				if (km < 0) {					
					document.getElementById('napravljeniKm').value = km;

				} else {					
					document.getElementById('napravljeniKm').value = km;
				}
			}
		</script>
	</div>
</body>
</html>