package controller;

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

import binder.EmployeePropertyEditor;
import binder.OfficePropertyEditor;
import domain.Employee;
import domain.Office;
import service.ServiceInterface;
import validator.EmployeeValidator;

@Controller
@SessionAttributes(value = { "officeList" })
public class EditEmployeeController {
	@Autowired
	private ServiceInterface service;

	@RequestMapping(value = "/editEmployee.html")
	public String editEmployee(@RequestParam Integer id, Model model) {

		Employee employee = service.fetchEmployeeById(id);
		List<Office> officeList = service.fetchAllOffices();
		model.addAttribute("officeList", officeList);
		model.addAttribute("employee", employee);
		return "editEmployee";

	}

	@RequestMapping(value = "editEmployee.html", method = RequestMethod.POST)
	public String updateEmployee(Model model, @ModelAttribute("employee") Employee employee, BindingResult result) {
		Validator validator = new EmployeeValidator();
		validator.validate(employee, result);

		if (result.hasErrors()) {
			
			return "editEmployee";
		}

		service.updateEmployee(employee);
		
		return "redirect:employeeList.html";
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Employee.class, new EmployeePropertyEditor(service));
		binder.registerCustomEditor(Office.class, new OfficePropertyEditor(service));
	}

}