package controller;

import java.text.ParseException;
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

import binder.CustomerPropertyEditor;
import binder.OfficePropertyEditor;
import binder.ReservationPropertyEditor;
import domain.Customer;
import domain.Office;
import domain.Reservation;
import service.ServiceInterface;
import validator.ReservationValidator;

@Controller
@SessionAttributes(value = { "officeList", "vehicleGroupList", "reservationTypes", "acceptanceList" })
public class EditReservationController {
	@Autowired
	private ServiceInterface service;

	@RequestMapping(value = "/editReservation.html")
	public String editReservation(@RequestParam Integer id, Model model) {
		Reservation reservation = service.fetchReservationById(id);
		List<Office> officeList = service.fetchAllOffices();
		List<String> vehicleGroupList = service.fetchVehicleGroup();
		List<String> reservationTypes = service.reservationTypes();
		List<String> acceptanceList = service.acceptanceList();
		model.addAttribute("officeList", officeList);
		model.addAttribute("vehicleGroupList", vehicleGroupList);
		model.addAttribute("reservationTypes", reservationTypes);
		model.addAttribute("acceptanceList", acceptanceList);
		model.addAttribute("reservation", reservation);
		return "editReservation";
	}

	@RequestMapping(value = "editReservation.html", method = RequestMethod.POST)
	public String updateReservation(Model model, @ModelAttribute("reservation") Reservation reservation,
			BindingResult result) throws ParseException {
		Validator validator = new ReservationValidator();
		validator.validate(reservation, result);

		if (result.hasErrors()) {
			
			return "editReservation";
		}	
		service.updateReservation(reservation);
		return "redirect:openedReservationList.html";
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Reservation.class, new ReservationPropertyEditor(service));
		binder.registerCustomEditor(Office.class, new OfficePropertyEditor(service));
		binder.registerCustomEditor(Customer.class, new CustomerPropertyEditor(service));
	}

}
