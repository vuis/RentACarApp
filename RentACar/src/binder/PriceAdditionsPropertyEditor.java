package binder;

import java.beans.PropertyEditorSupport;


import domain.PriceAdditions;
import service.ServiceInterface;

public class PriceAdditionsPropertyEditor extends PropertyEditorSupport {
	private ServiceInterface service;

	public PriceAdditionsPropertyEditor(ServiceInterface service) {
		this.service = service;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text.isEmpty()) {
			return;
		}
		PriceAdditions priceAdditions = null;
		try {
			Integer id = Integer.parseInt(text);
			priceAdditions = service.fetchPriceAdditionsById(id);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		setValue(priceAdditions);
	}
}



