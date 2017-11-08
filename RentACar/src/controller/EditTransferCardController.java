package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import binder.CarPropertyEditor;
import binder.EmployeePropertyEditor;
import binder.OfficePropertyEditor;
import binder.TransferCardPropertyEditor;
import domain.Car;
import domain.CarHistory;
import domain.Employee;
import domain.Office;
import domain.TransferCard;
import service.ServiceInterface;
import validator.TransferCardValidator;

@Controller
@SessionAttributes(value = { "officeList"})
public class EditTransferCardController {
	
	@Autowired
	private ServiceInterface service;

	int carKm;
	@RequestMapping(value = "/editTransferCard.html")
	public String closeTransferCardForm(@RequestParam Integer id, Model model) {
		TransferCard transferCard = service.fetchTransferCardById(id);		
		List<Office>officeList=service.fetchAllOffices();
		carKm=transferCard.getCar().getKm();
		model.addAttribute("carKm",carKm);
		model.addAttribute("officeList",officeList);
		model.addAttribute("transferCard", transferCard);
		return "editTransferCard";
	}

	@RequestMapping(value = "editTransferCard.html", method = RequestMethod.POST)
	public String closeTransferCard(Model model, @ModelAttribute("transferCard") TransferCard transferCard,
			BindingResult resultTransfer) throws MalformedURLException, IOException {
		Validator validator = new TransferCardValidator();
		validator.validate(transferCard, resultTransfer);
		if (resultTransfer.hasErrors()) {
			model.addAttribute("carKm",carKm);
			return "editTransferCard";

		}
		if(transferCard.getStatus().equals("Zatvorena")){
			transferCard.getCar().setOffice(transferCard.getOfficeIn());
			service.updateTransferCard(transferCard);			
			CarHistory carHistory = service.createCarHistoryForTransferCard(transferCard);			
			service.saveCarHistory(carHistory);
			service.createPdfForClosedTransferCard(transferCard.getId());
			if (Desktop.isDesktopSupported()) {
				try {
					File myFile = new File("C:/TransferCards/ClosedTransferCard" + transferCard.getId() + ".pdf");
					Desktop.getDesktop().open(myFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
				
		}
			
		else{service.updateTransferCard(transferCard);}		
		
		return "redirect: openedTransferCardList.html";
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Car.class, new CarPropertyEditor(service));
		binder.registerCustomEditor(Employee.class, new EmployeePropertyEditor(service));
		binder.registerCustomEditor(Office.class, new OfficePropertyEditor(service));
		binder.registerCustomEditor(TransferCard.class, new TransferCardPropertyEditor(service));
	}
}
