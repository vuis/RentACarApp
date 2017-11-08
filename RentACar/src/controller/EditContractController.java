package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
import binder.ContractPropertyEditor;
import binder.CustomerPropertyEditor;
import binder.OfficePropertyEditor;
import domain.Car;
import domain.CarHistory;
import domain.Contract;
import domain.Customer;
import domain.Employee;
import domain.Office;
import service.ServiceInterface;
import validator.ContractValidator;

@Controller
@SessionAttributes(value = { "officeList", "acceptanceList", "carListForOffice" })
public class EditContractController {
	@Autowired
	private ServiceInterface service;

	int car1Km;
	int car2Km;

	@RequestMapping(value = "/editContract.html")
	public String editContract(@RequestParam Integer id, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Contract contract = service.fetchContractById(id);
		List<Office> officeList = service.fetchAllOffices();
		List<String> acceptanceList = service.acceptanceList();
		String username = user.getUsername();
		Employee employee = service.fetchEmployeeByUserName(username);
		List<Car> carListForOffice = null;
		if (username.equals("admin") || username.equals("admin1")) {
			carListForOffice = service.fetchIdleCars();
		} else {
			int officeId = employee.getOffice().getId();
			carListForOffice = service.fetchIdleCarsForOffice(officeId);
		}
		if (carListForOffice.size() > 0) {
			model.addAttribute("carListForOffice", carListForOffice);
		}
		car1Km = contract.getCar().getKm();

		model.addAttribute("car1Km", car1Km);
		if (contract.isCarChange() == true) {
			car2Km = contract.getCar2().getKm();
			model.addAttribute("car2Km", car2Km);
		}
		model.addAttribute("officeList", officeList);
		model.addAttribute("acceptanceList", acceptanceList);
		model.addAttribute("contract", contract);
		return "editContract";
	}

	@RequestMapping(value = "editContract.html", method = RequestMethod.POST)
	public String updateContract(@RequestParam(required = false, defaultValue = "0") Integer carId, Model model,
			@ModelAttribute("contract") Contract contract, BindingResult result) throws ParseException {
		Validator validator = new ContractValidator();
		validator.validate(contract, result);
		int id = contract.getId();
		if (result.hasErrors()) {
			model.addAttribute("car1Km", car1Km);
			if (contract.isCarChange() == true) {
				model.addAttribute("car2Km", car2Km);
			}
			return "editContract";
		}
		if (contract.getAccessories().isAdditionalDriver() == false) {
			contract.setCustomer2(null);
		}

		if (contract.isCarChange() == false) {
			contract.setCar2(null);
		}

		if (carId > 0) {
			CarHistory carHistory = service.createCarHistoryForContract(contract);
			service.saveCarHistory(carHistory);
			System.out.println(carHistory.getCar().getRegistrationNr());
			Car car = service.fetchCarById(carId);
			contract.setCar2(car);
			contract.getCar2().setStatus("U najmu");
			service.updateContract(contract);
			carHistory = service.createCarHistoryForContract(contract);
			System.out.println(carHistory.getCar().getRegistrationNr());
			service.saveCarHistory(carHistory);
			contract.setCarChange(true);
			service.createPdfForCarChange(id);
			if (Desktop.isDesktopSupported()) {
				try {
					File myFile = new File("C:/Contracts/Contract" + id + "-carChange.pdf");
					Desktop.getDesktop().open(myFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		if (contract.getStatus().equals("Zatvoren")) {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = user.getUsername();
			Employee employee = service.fetchEmployeeByUserName(username);
			CarHistory carHistory = service.createCarHistoryForContract(contract);
			service.saveCarHistory(carHistory);
			contract.setInAgentNr(employee.getAgentNr());
			if (contract.isCarChange() == false) {
				contract.getCar().setOffice(contract.getDropOffOffice());
			} else {
				contract.getCar2().setOffice(contract.getDropOffOffice());
			}
			service.updateContract(contract);
			service.createPdfForClosedContract(id);
			if (Desktop.isDesktopSupported()) {
				try {
					File myFile = new File("C:/Contracts/ClosedContract" + id + ".pdf");
					Desktop.getDesktop().open(myFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		else {
			service.updateContract(contract);
		}
		return "redirect:openedContractList.html";
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Contract.class, new ContractPropertyEditor(service));
		binder.registerCustomEditor(Office.class, new OfficePropertyEditor(service));
		binder.registerCustomEditor(Customer.class, new CustomerPropertyEditor(service));
		binder.registerCustomEditor(Car.class, new CarPropertyEditor(service));
	}

}
