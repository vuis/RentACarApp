package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import domain.Employee;

public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Employee.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		Employee employee = (Employee) arg0;
		if (employee.getOffice() == null) {
			ValidationUtils.rejectIfEmpty(arg1, "office", "office");
		}

		if (employee.getUsername().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "username", "username");
		}
		
		if (employee.getPassword().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "password", "password");
		}
		
		if (employee.getFirstname().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "firstName", "name");
		}
		
		if (employee.getLastname().isEmpty()) {
			ValidationUtils.rejectIfEmpty(arg1, "lastName", "lastName");
		}

	}
}
