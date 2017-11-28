package rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import domain.Employee;
import domain.Reservation;
import service.ServiceInterface;

@RestController
public class ReservationListRest {
	@Autowired
	private ServiceInterface service;

	@RequestMapping(value="/rest/openedReservationList", produces = "application/json;charset=UTF-8")
	public String showOpenedReservation() throws JsonProcessingException {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		Employee emp = service.fetchEmployeeByUserName(username);
		List<Reservation> reservationList;
		if (username.equals("admin") || username.equals("admin1") || username.equals("rezervacije")) {
			reservationList = service.fetchAllOpenedReservations();
		} else
			reservationList = service.fetchOpenedReservationsForOffice(emp.getOffice().getId());

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.addMixIn(Object.class, IgnoreHibernatePropertiesInJackson.class);
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");
		mapper.setDateFormat(dateFormat);
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(reservationList);
	}

}
