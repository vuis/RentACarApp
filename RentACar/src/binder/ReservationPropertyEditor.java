package binder;

import java.beans.PropertyEditorSupport;

import domain.Reservation;
import service.ServiceInterface;

public class ReservationPropertyEditor extends PropertyEditorSupport {
	private ServiceInterface service;

	public ReservationPropertyEditor(ServiceInterface service) {
		this.service = service;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text.isEmpty()) {
			return;
		}
		Reservation reservation = null;
		try {
			Integer id = Integer.parseInt(text);
			reservation = service.fetchReservationById(id);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		setValue(reservation);
	}
}
