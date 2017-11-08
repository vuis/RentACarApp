package controller;

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

import binder.OfficePropertyEditor;
import domain.Customer;
import domain.Employee;
import domain.Office;
import domain.Reservation;
import service.ServiceInterface;
import validator.ReservationValidator;

@Controller
@SessionAttributes(value = { "officeList", "vehicleGroupList", "reservationTypes", "acceptanceList" })
public class CreateReservationController {

	@Autowired
	private ServiceInterface service;
	
	@RequestMapping(value = "/createReservation.html", method = RequestMethod.GET)
	public String createReservation(Model model) {
		List<Office> officeList = service.fetchAllOffices();
		List<String> vehicleGroupList = service.fetchVehicleGroup();
		List<String> reservationTypes = service.reservationTypes();
		List<String> acceptanceList = service.acceptanceList();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		Employee employee = service.fetchEmployeeByUserName(username);
		Office office = employee.getOffice();
		model.addAttribute("office", office);
		model.addAttribute("officeList", officeList);
		model.addAttribute("vehicleGroupList", vehicleGroupList);
		model.addAttribute("reservationTypes", reservationTypes);
		model.addAttribute("acceptanceList", acceptanceList);
		model.addAttribute("reservation", new Reservation());
		return "createReservation";

	}

	@RequestMapping(value = "createReservation.html", method = RequestMethod.POST)
	public String saveReservation(Model model, @ModelAttribute("reservation") Reservation reservation,
			BindingResult result) throws ParseException {
		Validator validator = new ReservationValidator();
		validator.validate(reservation, result);
		if (result.hasErrors()) {		
			return "createReservation";
		}	
		for (Customer cust : service.fetchAllCustomers()) {
			if (cust.getFullName().equals(reservation.getCustomer().getFullName())					
					&& cust.getAddress().equals(reservation.getCustomer().getAddress())
					&& cust.getCity().equals(reservation.getCustomer().getCity())
					&& cust.getCountry().equals(reservation.getCustomer().getCountry())) {
				reservation.getCustomer().setId(cust.getId());				
			}
			
		}	
		service.saveReservation(reservation);
		return "redirect:openedReservationList.html";
	}
	
	@RequestMapping(value = "/createReservationForCustomer.html", method = RequestMethod.GET)
	public String createReservationForCustomer(@RequestParam Integer customerId,Model model) {
		Customer customer=service.fetchCustomerById(customerId);
		List<Office> officeList = service.fetchAllOffices();
		List<String> vehicleGroupList = service.fetchVehicleGroup();
		List<String> reservationTypes = service.reservationTypes();
		List<String> acceptanceList = service.acceptanceList();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		Employee employee = service.fetchEmployeeByUserName(username);
		Office office = employee.getOffice();
		model.addAttribute("office", office);
		model.addAttribute("officeList", officeList);
		model.addAttribute("vehicleGroupList", vehicleGroupList);
		model.addAttribute("reservationTypes", reservationTypes);
		model.addAttribute("acceptanceList", acceptanceList);
		model.addAttribute("customer",customer);
		model.addAttribute("reservation", new Reservation());
		return "createReservationForCustomer";

	}
	

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {	
		binder.registerCustomEditor(Office.class, new OfficePropertyEditor(service));		
		
	}
	

}
