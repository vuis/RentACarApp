package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import domain.Car;

public class CarValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Car.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		Car car = (Car) arg0;
		if (car.getOffice() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "office", "office");
		}

		if (car.getName().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "name", "carName");
		}

		if (car.getRegistrationNr().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "registrationNr", "registrationNr");
		}

		if (car.getRegistrationExp() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "registrationExp", "registrationExp");
		}

		if (car.getKm() != (int) car.getKm()) {
			ValidationUtils.rejectIfEmpty(arg1, "km", "kilometers");
		}

		if (car.getKm() < 0) {
			arg1.rejectValue("km", "wrongKilometers");
		}

		if (car.getFuel() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "fuel", "fuel");
		}

		if (car.getStatus().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "status", "status");
		}
		if (car.getOwner().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "owner", "owner");
		}
		if (car.getPicture().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "picture", "picture");
		}

	}

}
