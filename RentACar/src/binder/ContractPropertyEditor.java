package binder;

import java.beans.PropertyEditorSupport;

import domain.Contract;
import service.ServiceInterface;

public class ContractPropertyEditor extends PropertyEditorSupport {
	private ServiceInterface service;

	public ContractPropertyEditor(ServiceInterface service) {
		this.service = service;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text.isEmpty()) {
			return;
		}
		Contract contract = null;
		try {
			Integer id = Integer.parseInt(text);
			contract = service.fetchContractById(id);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		setValue(contract);
	}

}
