package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.Employee;
import service.ServiceInterface;

@Controller
public class EmployeeListController {
	@Autowired
	private ServiceInterface service;

	@RequestMapping(value = "/employeeList.html", method = RequestMethod.GET)
	public String showEmployeeList(Model model) {
		List<Employee> employeeList = service.fetchAllEmployees();
		model.addAttribute("employeeList", employeeList);
		return "employeeList";
	}

	

}
