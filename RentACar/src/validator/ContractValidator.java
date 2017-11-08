package validator;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import domain.Contract;

public class ContractValidator implements Validator {
	@Override
	public boolean supports(Class<?> arg0) {
		return Contract.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		Contract contract = (Contract) arg0;

		if (contract.getStatus().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "status", "status");
		}

		if (contract.getCustomer().getFirstname().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer.firstname", "customerName");
		}
		if (contract.getCustomer().getLastname().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer.lastname", "customerLastName");
		}

		if (contract.getCustomer().getDob() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "customer.dob", "customerDob");
		}

		if (contract.getCustomer().getAddress().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer.address", "customerAddress");
		}

		if (contract.getCustomer().getCity().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer.city", "customerCity");
		}

		if (contract.getCustomer().getCountry().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer.country", "customerCountry");
		}

		if (contract.getCustomer().getDriversLicenseNr().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer.driversLicenseNr", "licenseNr");
		}

		if (contract.getCustomer().getPassportNr().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer.passportNr", "passportNr");
		}

		if (contract.getCustomer().getCardNr().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer.cardNr", "cardNr");
		}

		if (contract.getCustomer().getCardExp().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer.cardExp", "cardExp");
		}

		if (contract.getCustomer().getCardCvv().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer.cardCvv", "cardCvv");
		}

		if (contract.getAccessories().isAdditionalDriver() == true
				&& contract.getCustomer2().getFirstname().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer2.firstname", "customerName");
		}
		if (contract.getAccessories().isAdditionalDriver() == true && contract.getCustomer2().getLastname().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer2.lastname", "customerLastName");
		}

		if (contract.getAccessories().isAdditionalDriver() == true && contract.getCustomer2().getDob() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "customer2.dob", "customerDob");
		}

		if (contract.getAccessories().isAdditionalDriver() == true && contract.getCustomer2().getAddress().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer2.address", "customerAddress");
		}

		if (contract.getAccessories().isAdditionalDriver() == true && contract.getCustomer2().getCity().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer2.city", "customerCity");
		}

		if (contract.getAccessories().isAdditionalDriver() == true && contract.getCustomer2().getCountry().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer2.country", "customerCountry");
		}

		if (contract.getAccessories().isAdditionalDriver() == true
				&& contract.getCustomer2().getDriversLicenseNr().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "customer2.driversLicenseNr", "licenseNr");
		}

		if (contract.getCar() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "car", "car");
		}

		if (contract.getPickUpOffice() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "pickUpOffice", "pickUpLocation");
		}

		if (contract.getDropOffOffice() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "dropOffOffice", "dropOffLocation");
		}

		if (contract.getPickUpDate() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "pickUpDate", "pickUpDate");
		}

		if (contract.getDropOffDate() != null && contract.getPickUpDate() != null && ChronoUnit.DAYS
				.between(contract.getPickUpDate().toInstant(), contract.getDropOffDate().toInstant()) < 0) {
			arg1.rejectValue("dropOffDate", "dateBeforePickup");
		}

		if ((contract.getDropOffDate()) == null) {
			ValidationUtils.rejectIfEmpty(arg1, "dropOffDate", "dropOffDate");
		}

		if (contract.getBaseRate() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "baseRate", "noBaseRate");
		}

		if (contract.getPrice() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "price", "price");
		}
				
		if (contract.getPriceAdditions().getAptFee().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "priceAdditions.aptFee", "aptFee");
		}

		if (contract.getPriceAdditions().getRoadFee() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "priceAdditions.roadFee", "roadFee");
		}

		if (contract.getPriceAdditions().getVehicleLicenseFee() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "priceAdditions.vehicleLicenseFee", "vehicleLicenseFee");
		}

		if (contract.getAuthorizationAmount() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "authorizationAmount", "authorization");
		}
		if (contract.getAuthorizationNr().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "authorizationNr", "authorizationNr");
		}
		if (contract.getInsurance().getCdw().equals("Stranka je prihvatila")
				&& contract.getInsurance().getCdwPrice().compareTo(new BigDecimal(0.00))==0) {
			arg1.rejectValue("insurance.cdwPrice", "insurancePrice");

		}
		if (contract.getInsurance().getTp().equals("Stranka je prihvatila")
				&& contract.getInsurance().getTpPrice().compareTo(new BigDecimal(0.00))==0) {
			arg1.rejectValue("insurance.tpPrice", "insurancePrice");
		}
		if (contract.getInsurance().getPai().equals("Stranka je prihvatila")
				&& contract.getInsurance().getPaiPrice().compareTo(new BigDecimal(0.00))==0) {
			arg1.rejectValue("insurance.paiPrice", "insurancePrice");
		}
		if (contract.getAccessories().isGps() == true
				&& contract.getAccessories().getGpsPrice() == null) {
			arg1.rejectValue("accessories.gpsPrice", "accessoriesPrice");
		}
		if (contract.getAccessories().isWifi() == true
				&& contract.getAccessories().getWifiPrice() == null) {
			arg1.rejectValue("accessories.wifiPrice", "accessoriesPrice");
		}
		if (contract.getAccessories().isBabySeat() == true
				&& contract.getAccessories().getBabySeatPrice() == null) {
			arg1.rejectValue("accessories.babySeatPrice", "accessoriesPrice");
		}
		if (contract.getAccessories().isOneWayFee() == true
				&& contract.getAccessories().getOneWayFeePrice() == null) {
			arg1.rejectValue("accessories.oneWayFeePrice", "accessoriesPrice");
		}

		if (contract.getAccessories().isAdditionalDriver() == true
				&& contract.getAccessories().getAdditionalDriverPrice() == null) {
			arg1.rejectValue("accessories.additionalDriverPrice", "accessoriesPrice");
		}
		
				
		if (contract.getTotalKm() < 0) {
			arg1.rejectValue("totalKm", "wrongKilometers");
		}
	}

}
