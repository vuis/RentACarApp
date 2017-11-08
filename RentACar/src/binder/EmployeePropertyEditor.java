package binder;

import java.beans.PropertyEditorSupport;

import domain.Employee;
import service.ServiceInterface;

public class EmployeePropertyEditor extends PropertyEditorSupport {
	private ServiceInterface service;
	
	public EmployeePropertyEditor(ServiceInterface service) {
		this.service = service;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text.isEmpty()) {
			return;
		}
		Employee employee = null;
		try {
			Integer id = Integer.parseInt(text);
			employee = service.fetchEmployeeById(id);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		setValue(employee);
	}

}
