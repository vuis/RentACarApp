package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Employee;
import domain.Reservation;
import service.ServiceInterface;

@Controller
public class ReservationListController {
	@Autowired
	private ServiceInterface service;

	@RequestMapping(value = "/openedReservationList.html", method = RequestMethod.GET)
	public String showOpenedReservation(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		Employee emp = service.fetchEmployeeByUserName(username);
		List<Reservation> reservationList;
		if (username.equals("admin") || username.equals("admin1")|| username.equals("rezervacije")) {
			reservationList = service.fetchAllOpenedReservations();
		} else
			reservationList = service.fetchOpenedReservationsForOffice(emp.getOffice().getId());
		model.addAttribute("reservationList", reservationList);
		return "reservationList";
	}

	@RequestMapping(value = "/closedReservationList.html", method = RequestMethod.GET)
	public String showClosedReservation(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		Employee emp = service.fetchEmployeeByUserName(username);
		List<Reservation> reservationList;
		if (username.equals("admin") || username.equals("admin1")| username.equals("rezervacije")) {
			reservationList = service.fetchAllClosedReservations();
		} else
			reservationList = service.fetchClosedReservationsForOffice(emp.getOffice().getId());
		model.addAttribute("reservationList", reservationList);
		return "reservationList";
	}

	@RequestMapping(value = "/createPdfForReservation.html", method = RequestMethod.GET)
	public String createPdfForOpenedReservation(@RequestParam Integer id, Model model) {
		service.createPdfForReservation(id);
		if (Desktop.isDesktopSupported()) {
			try {
				File myFile = new File("C:/Reservations/Reservation" + id + ".pdf");
				Desktop.getDesktop().open(myFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:openedReservationList.html";

	}

}
