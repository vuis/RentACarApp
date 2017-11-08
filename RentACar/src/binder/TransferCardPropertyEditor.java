package binder;

import java.beans.PropertyEditorSupport;

import domain.TransferCard;
import service.ServiceInterface;

public class TransferCardPropertyEditor extends PropertyEditorSupport {
	private ServiceInterface service;

	public TransferCardPropertyEditor(ServiceInterface service) {
		this.service = service;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text.isEmpty()) {
			return;
		}
		TransferCard transferCard = null;
		try {
			Integer id = Integer.parseInt(text);
			transferCard = service.fetchTransferCardById(id);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		setValue(transferCard);
	}

}
