package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.Car;
import domain.Employee;
import service.ServiceInterface;

@Controller
public class CarListController {
	@Autowired
	private ServiceInterface service;

	@RequestMapping(value = "/carList.html", method = RequestMethod.GET)
	public String showCarList(Model model) {
		List<Car> carList = service.fetchAllCars();
		model.addAttribute("carList", carList);
		return "carList";
	}
	
	

	@RequestMapping(value = "/carListForOffice.html", method = RequestMethod.GET)
	public String showCarListForOffice(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		if (username.equals("admin") || username.equals("admin1")) {
			List<Car> carList = service.fetchAllCars();
			model.addAttribute("carList ", carList);
		} else {
			Employee employee = service.fetchEmployeeByUserName(username);
			int officeId = employee.getOffice().getId();
			List<Car> carList = service.fetchCarsForOffice(officeId);
			model.addAttribute("carList", carList);
		}
		return "carList";
	}

}
