package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
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
import org.springframework.web.bind.annotation.SessionAttributes;



import binder.CarPropertyEditor;
import binder.EmployeePropertyEditor;
import binder.OfficePropertyEditor;
import domain.Car;
import domain.CarHistory;
import domain.Employee;
import domain.Office;
import domain.TransferCard;
import service.ServiceInterface;
import validator.TransferCardValidator;

@Controller
@SessionAttributes(value = { "officeList", "employeeList", "carListForOffice" })
public class CreateTransferCardController {
	@Autowired
	private ServiceInterface service;	

	@RequestMapping(value = "/createTransferCard.html", method = RequestMethod.GET)
	public String createTransferCard(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Office> officeList = service.fetchAllOffices();
		List<Employee> employeeList = service.fetchAllEmployees();
		String username = user.getUsername();
		Employee zaposlenik = service.fetchEmployeeByUserName(username);
		List<Car> carListForOffice = null;
		if (username.equals("admin") || username.equals("admin1")) {
			carListForOffice = service.fetchIdleCars();
		} else {
			int officeId = zaposlenik.getOffice().getId();
			carListForOffice = service.fetchIdleCarsForOffice(officeId);
		}
		model.addAttribute("officeList", officeList);
		model.addAttribute("employeeList", employeeList);
		if (carListForOffice.size() > 0) {
			model.addAttribute("carListForOffice", carListForOffice);
		}
		model.addAttribute("transferCard", new TransferCard());
		return "createTransferCard";
	}

	@RequestMapping(value = "createTransferCard.html", method = RequestMethod.POST)
	public String saveTransferCard(Model model, @ModelAttribute("transferCard") TransferCard transferCard,
			BindingResult result) throws MalformedURLException, IOException {
		Validator validator = new TransferCardValidator();
		validator.validate(transferCard, result);
		if (result.hasErrors()) {
			return "createTransferCard";
		}
		LocalTime localTime = LocalTime.now();
		Time time = Time.valueOf(localTime);
		transferCard.setDateOut(new Date());
		transferCard.setTimeOut(time);
		service.saveTransferCard(transferCard);		
		CarHistory carHistory = service.createCarHistoryForTransferCard(transferCard);		
		service.saveCarHistory(carHistory);
		service.createPdfForTransferCard(transferCard.getId());
		if (Desktop.isDesktopSupported()) {
			try {
				File myFile = new File("C:/TransferCards/TransferCard" + transferCard.getId() + ".pdf");
				Desktop.getDesktop().open(myFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect: openedTransferCardList.html";

	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Car.class, new CarPropertyEditor(service));
		binder.registerCustomEditor(Employee.class, new EmployeePropertyEditor(service));
		binder.registerCustomEditor(Office.class, new OfficePropertyEditor(service));
	}
}
