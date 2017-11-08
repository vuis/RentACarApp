package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Car;
import domain.Employee;
import service.ServiceInterface;

@Controller
public class AjaxRequests {
	@Autowired
	private ServiceInterface service;

	@RequestMapping(value = "/employeeTable.html", method = RequestMethod.GET)
	public String showTable(Model model,@RequestParam int employeeId) {
		Employee employee=service.fetchEmployeeById(employeeId);
		model.addAttribute("employee", employee);
		return "employeeTable";
		
	}
	
	@RequestMapping(value = "/officeOut.html", method = RequestMethod.GET)
	public String showInput(Model model,@RequestParam int carId) {
		Car car=service.fetchCarById(carId);
		model.addAttribute("officeOut", car.getOffice());	
		return "officeOut";
		
	}

}
