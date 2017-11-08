package binder;

import java.beans.PropertyEditorSupport;

import domain.Car;
import service.ServiceInterface;

public class CarPropertyEditor extends PropertyEditorSupport {
	private ServiceInterface service;
	
	public CarPropertyEditor(ServiceInterface service) {
		this.service = service;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text.isEmpty()) {
			return;
		}
		Car car = null;
		try {
			Integer id = Integer.parseInt(text);
			car = service.fetchCarById(id);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		setValue(car);
	}

}
