package binder;

import java.beans.PropertyEditorSupport;

import domain.Accessories;

import service.ServiceInterface;

public class AccessoriesPropertyEditor extends PropertyEditorSupport {
	private ServiceInterface service;

	public AccessoriesPropertyEditor(ServiceInterface service) {
		this.service = service;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text.isEmpty()) {
			return;
		}
		Accessories accessories = null;
		try {
			Integer id = Integer.parseInt(text);
			accessories = service.fetchAccessoriesById(id);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		setValue(accessories);
	}
}
