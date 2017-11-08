package binder;

import java.beans.PropertyEditorSupport;


import domain.Insurance;
import service.ServiceInterface;

public class InsurancePropertyEditor extends PropertyEditorSupport {
	private ServiceInterface service;

	public InsurancePropertyEditor(ServiceInterface service) {
		this.service = service;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text.isEmpty()) {
			return;
		}
		Insurance insurance = null;
		try {
			Integer id = Integer.parseInt(text);
			insurance = service.fetchInsuranceById(id);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		setValue(insurance);
	}
}

