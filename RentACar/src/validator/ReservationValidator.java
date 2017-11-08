package validator;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import domain.Reservation;

public class ReservationValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Reservation.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		Reservation reservation = (Reservation) arg0;
		if (reservation.getStatus().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "status", "status");
		}
		if (reservation.getReservationNr().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "reservationNr", "reservationNr");
		}
		if (reservation.getReservationType().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "reservationType", "reservationType");
		}

		if (reservation.getReservationType().equals("Booking agencija") && reservation.getBookerNr().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "bookerNr", "bookerNr");
		}

		if (reservation.getCustomer().getFirstname().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer.firstname", "customerName");
		}
		if (reservation.getCustomer().getLastname().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer.lastname", "customerLastName");
		}
		if (reservation.getVehicleGroup().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "vehicleGroup", "vehicleGroup");
		}

		if (reservation.getPickUpOffice() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "pickUpOffice", "pickUpLocation");
		}

		if (reservation.getPickUpDate() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "pickUpDate", "pickUpDate");
		}

		if (reservation.getDropOffOffice() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "dropOffOffice", "dropOffLocation");
		}

		if (reservation.getDropOffDate() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "dropOffDate", "dropOffDate");
		}

		if (reservation.getDropOffDate() != null && reservation.getPickUpDate() != null && ChronoUnit.DAYS
				.between(reservation.getPickUpDate().toInstant(), reservation.getDropOffDate().toInstant()) < 0) {
			arg1.rejectValue("dropOffDate", "dateBeforePickup");
		}

		if (reservation.getBaseRate() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "baseRate", "noBaseRate");
		}

		if (reservation.getPriceAdditions().getAptFee().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "priceAdditions.aptFee", "aptFee");
		}

		if (reservation.getPriceAdditions().getRoadFee() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "priceAdditions.roadFee", "roadFee");
		}

		if (reservation.getPriceAdditions().getVehicleLicenseFee() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "priceAdditions.vehicleLicenseFee", "vehicleLicenseFee");
		}

		if (reservation.getInsurance().getCdw().equals("Stranka je prihvatila")
				&& reservation.getInsurance().getCdwPrice().compareTo(new BigDecimal(0.00))==0) {
			arg1.rejectValue("insurance.cdwPrice", "insurancePrice");

		}
		if (reservation.getInsurance().getTp().equals("Stranka je prihvatila")
				&& reservation.getInsurance().getTpPrice().compareTo(new BigDecimal(0.00))==0) {
			arg1.rejectValue("insurance.tpPrice", "insurancePrice");
		}
		if (reservation.getInsurance().getPai().equals("Stranka je prihvatila")
				&& reservation.getInsurance().getPaiPrice().compareTo(new BigDecimal(0.00))==0) {
			arg1.rejectValue("insurance.paiPrice", "insurancePrice");
		}

		if (reservation.getAccessories().isGps() == true && reservation.getAccessories().getGpsPrice() == null) {
			arg1.rejectValue("accessories.gpsPrice", "accessoriesPrice");
		}
		if (reservation.getAccessories().isWifi() == true && reservation.getAccessories().getWifiPrice() == null) {
			arg1.rejectValue("accessories.wifiPrice", "accessoriesPrice");
		}
		if (reservation.getAccessories().isBabySeat() == true
				&& reservation.getAccessories().getBabySeatPrice() == null) {
			arg1.rejectValue("accessories.babySeatPrice", "accessoriesPrice");
		}
		if (reservation.getAccessories().isOneWayFee() == true
				&& reservation.getAccessories().getOneWayFeePrice() == null) {
			arg1.rejectValue("accessories.oneWayFeePrice", "accessoriesPrice");
		}

		if (reservation.getAccessories().isAdditionalDriver() == true
				&& reservation.getAccessories().getAdditionalDriverPrice() == null) {
			arg1.rejectValue("accessories.additionalDriverPrice", "accessoriesPrice");
		}

	}
}
