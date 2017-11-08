package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.Employee;
import service.ServiceInterface;

@Controller
public class HomePageController {
	@Autowired
	private ServiceInterface service;

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String showHomePage(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		Employee emp = service.fetchEmployeeByUserName(username);
		Long idleCars = null;
		Long carsInTransfer = null;
		Long carsInService = null;
		Long carsInRent = null;
		Long reservations = null;
		Long returns = null;
		String office = null;
		if (username.equals("admin") || username.equals("admin1")) {
			idleCars = service.countIdleCars();
			carsInTransfer = service.countCarsInTransfer();
			carsInService = service.countCarsInService();
			carsInRent = service.countCarsInRent();
			reservations = service.countReservations();
			returns = service.countReturns();
		} else if (username.equals("rezervacije")) {
			return "redirect:openedReservationList.html";
		} else {
			idleCars = service.countIdleCarsForOffice(emp.getOffice().getId());
			carsInTransfer = service.countCarsInTransferForOffice(emp.getOffice().getId());
			carsInService = service.countCarsInServiceForOffice(emp.getOffice().getId());
			carsInRent = service.countCarsInRentForOffice(emp.getOffice().getId());
			reservations = service.countReservationsForOffice(emp.getOffice().getId());
			returns = service.countReturnsForOffice(emp.getOffice().getId());
			office = emp.getOffice().getName();
		}
		model.addAttribute("fullName", emp.getFullName());
		model.addAttribute("office", office);
		model.addAttribute("idleCars", idleCars);
		model.addAttribute("carsInTransfer", carsInTransfer);
		model.addAttribute("carsInService", carsInService);
		model.addAttribute("carsInRent", carsInRent);
		model.addAttribute("reservations", reservations);
		model.addAttribute("returns", returns);

		return "index";
	}
}
