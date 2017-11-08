package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import domain.TransferCard;

public class TransferCardValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return TransferCard.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		TransferCard transferCard = (TransferCard) arg0;
		if (transferCard.getCar()== null ||transferCard.getCar().getId()==0 ) {
			ValidationUtils.rejectIfEmpty(arg1, "car", "car");
		}

		if (transferCard.getEmployee()==null) {
			ValidationUtils.rejectIfEmpty(arg1, "employee", "employee");
		}
		
		if (transferCard.getOfficeIn()==null) {
			ValidationUtils.rejectIfEmpty(arg1, "officeIn", "dropOffOffice");
		}	
		
		if (transferCard.getTotalKm() < 0) {
			arg1.rejectValue("totalKm", "wrongKilometers");
		}
	}
}
