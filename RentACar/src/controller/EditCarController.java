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

import binder.CarPropertyEditor;
import binder.OfficePropertyEditor;
import domain.Car;
import domain.Office;
import service.ServiceInterface;
import validator.CarValidator;

@Controller
@SessionAttributes(value = { "officeList" })
public class EditCarController {
	@Autowired
	private ServiceInterface service;

	@RequestMapping(value = "/editCar.html")
	public String editCar(@RequestParam Integer id, Model model) {
		Car car = service.fetchCarById(id);
		List<Office> officeList = service.fetchAllOffices();
		model.addAttribute("officeList", officeList);
		model.addAttribute("car", car);
		return "editCar";
	}

	@RequestMapping(value = "editCar.html", method = RequestMethod.POST)
	public String updateCar(Model model, @ModelAttribute("car") Car car, BindingResult result) {
		Validator validator = new CarValidator();
		validator.validate(car, result);
		if (result.hasErrors()) {			
			return "editCar";
		}
		service.updateCar(car);
		return "redirect:carList.html";
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Car.class, new CarPropertyEditor(service));
		binder.registerCustomEditor(Office.class, new OfficePropertyEditor(service));
	}

}