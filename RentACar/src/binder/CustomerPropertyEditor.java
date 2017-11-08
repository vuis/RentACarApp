package binder;

import java.beans.PropertyEditorSupport;

import domain.Customer;
import service.ServiceInterface;

public class CustomerPropertyEditor extends PropertyEditorSupport {
	private ServiceInterface service;

	public CustomerPropertyEditor(ServiceInterface service) {
			this.service = service;
		}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text.isEmpty()) {
			return;
		}
		Customer customer = null;
		try {
			Integer id = Integer.parseInt(text);
			customer = service.fetchCustomerById(id);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		setValue(customer);
	}

}
