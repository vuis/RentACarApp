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
import binder.OfficePropertyEditor;
import domain.Car;
import domain.CarHistory;
import domain.Contract;
import domain.Customer;
import domain.Employee;
import domain.Office;
import domain.Reservation;
import service.ServiceInterface;
import validator.ContractValidator;

@Controller
@SessionAttributes(value = { "officeList", "acceptanceList", "carListForOffice", "agentNr", "vehicleGroupList" })
public class CreateContractController {

	@Autowired
	private ServiceInterface service;
	Reservation reservation = null;

	@RequestMapping(value = "/createContract.html", method = RequestMethod.GET)
	public String createContract(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Office> officeList = service.fetchAllOffices();
		List<String> acceptanceList = service.acceptanceList();
		String username = user.getUsername();
		Employee employee = service.fetchEmployeeByUserName(username);
		List<Car> carListForOffice = null;
		if (username.equals("admin") || username.equals("admin1")) {
			carListForOffice = service.fetchIdleCars();
		} else {
			Office office = employee.getOffice();
			int officeId = office.getId();
			carListForOffice = service.fetchIdleCarsForOffice(officeId);
			model.addAttribute("office", office);

		}
		if (carListForOffice.size() > 0) {
			model.addAttribute("carListForOffice", carListForOffice);
		}
		model.addAttribute("agentNr", employee.getAgentNr());
		model.addAttribute("officeList", officeList);
		model.addAttribute("acceptanceList", acceptanceList);
		model.addAttribute("username", username);
		model.addAttribute("contract", new Contract());
		return "createContract";
	}

	@RequestMapping(value = "createContract.html", method = RequestMethod.POST)
	public String saveContract(Model model, @ModelAttribute("contract") Contract contract, BindingResult result)
			throws ParseException {
		Validator validator = new ContractValidator();
		validator.validate(contract, result);

		if (result.hasErrors()) {
			return "createContract";
		}
		if (contract.getAccessories().isAdditionalDriver() == false) {
			contract.setCustomer2(null);
		}
		if (contract.getCustomer2() != null) {
			for (Customer cust : service.fetchAllCustomers()) {
				if (cust.getFullName().equals(contract.getCustomer2().getFullName())
						&& cust.getAddress().equals(contract.getCustomer2().getAddress())
						&& cust.getCity().equals(contract.getCustomer2().getCity())
						&& cust.getCountry().equals(contract.getCustomer2().getCountry())) {
					contract.getCustomer2().setId(cust.getId());
				}

			}
		}
		if (reservation != null) {
			reservation.setStatus("Zatvorena");
			service.updateReservation(reservation);
		}
		for (Customer cust : service.fetchAllCustomers()) {
			if (cust.getFullName().equals(contract.getCustomer().getFullName())
					&& cust.getAddress().equals(contract.getCustomer().getAddress())
					&& cust.getCity().equals(contract.getCustomer().getCity())
					&& cust.getCountry().equals(contract.getCustomer().getCountry())) {
				contract.getCustomer().setId(cust.getId());
			}

		}
		contract.getCar().setStatus("U najmu");
		service.saveContract(contract);
		CarHistory carHistory = service.createCarHistoryForContract(contract);		
		service.saveCarHistory(carHistory);
		service.createPdfForContract(contract.getId());
		if (Desktop.isDesktopSupported()) {
			try {
				File myFile = new File("C:/Contracts/Contract" + contract.getId() + ".pdf");
				Desktop.getDesktop().open(myFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:openedContractList.html";
	}

	@RequestMapping(value = "/createContractFromReservation.html", method = RequestMethod.GET)
	public String createContractFromReservation(@RequestParam Integer id, Model model) throws ParseException {
		List<String> vehicleGroupList = service.fetchVehicleGroup();
		reservation = service.fetchReservationById(id);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Office> officeList = service.fetchAllOffices();
		List<String> acceptanceList = service.acceptanceList();
		String username = user.getUsername();
		Employee employee = service.fetchEmployeeByUserName(username);
		List<Car> carListForOffice = null;
		if (username.equals("admin") || username.equals("admin1")) {
			carListForOffice = service.fetchIdleCars();
		} else {
			Office office = employee.getOffice();
			int officeId = office.getId();
			carListForOffice = service.fetchIdleCarsForOffice(officeId);
			model.addAttribute("office", office);

		}
		if (carListForOffice.size() > 0) {
			model.addAttribute("carListForOffice", carListForOffice);
		}
		model.addAttribute("vehicleGroupList", vehicleGroupList);
		model.addAttribute("reservation", reservation);
		model.addAttribute("agentNr", employee.getAgentNr());
		model.addAttribute("officeList", officeList);
		model.addAttribute("acceptanceList", acceptanceList);
		model.addAttribute("username", username);
		model.addAttribute("contract", new Contract());
		return "createContractFromReservation";
	}

	@RequestMapping(value = "/createContractForCustomer.html", method = RequestMethod.GET)
	public String createContractForCustomer(@RequestParam Integer customerId, Model model) throws ParseException {
		Customer customer = service.fetchCustomerById(customerId);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Office> officeList = service.fetchAllOffices();
		List<String> acceptanceList = service.acceptanceList();
		String username = user.getUsername();
		Employee employee = service.fetchEmployeeByUserName(username);
		List<Car> carListForOffice = null;
		if (username.equals("admin") || username.equals("admin1")) {
			carListForOffice = service.fetchIdleCars();
		} else {
			Office office = employee.getOffice();
			int officeId = office.getId();
			carListForOffice = service.fetchIdleCarsForOffice(officeId);
			model.addAttribute("office", office);

		}
		if (carListForOffice.size() > 0) {
			model.addAttribute("carListForOffice", carListForOffice);
		}

		model.addAttribute("customer", customer);
		model.addAttribute("agentNr", employee.getAgentNr());
		model.addAttribute("officeList", officeList);
		model.addAttribute("acceptanceList", acceptanceList);
		model.addAttribute("username", username);
		model.addAttribute("contract", new Contract());
		return "createContractForCustomer";
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Car.class, new CarPropertyEditor(service));
		binder.registerCustomEditor(Office.class, new OfficePropertyEditor(service));

	}
}
