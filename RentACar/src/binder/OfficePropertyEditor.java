package binder;

import java.beans.PropertyEditorSupport;

import domain.Office;
import service.ServiceInterface;

public class OfficePropertyEditor extends PropertyEditorSupport {
	private ServiceInterface service;

	public OfficePropertyEditor(ServiceInterface service) {
		this.service = service;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text.isEmpty()) {
			return;
		}
		Office office = null;
		try {
			Integer id = Integer.parseInt(text);
			office = service.fetchOfficeById(id);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		setValue(office);
	}

}
