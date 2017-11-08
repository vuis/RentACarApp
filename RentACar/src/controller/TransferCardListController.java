package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.Employee;
import domain.TransferCard;
import service.ServiceInterface;
@Controller
public class TransferCardListController {

	@Autowired
	private ServiceInterface service;	

	@RequestMapping(value = "/openedTransferCardList.html", method = RequestMethod.GET)
	public String showOpenedTransferCard(Model model) {
		User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=user.getUsername();
		Employee emp=service.fetchEmployeeByUserName(username);
		List<TransferCard> transferCardList;
		if(username.equals("admin")||username.equals("admin1") ){
		transferCardList = service.fetchAllOpenedTransferCards();}
		else
			transferCardList = service.fetchOpenedTransferCardsForOffice(emp.getOffice().getId());
		model.addAttribute("transferCardList", transferCardList);
		return "transferCardList";
	}
	
	@RequestMapping(value = "/closedTransferCardList.html", method = RequestMethod.GET)
	public String showClosedTransferCard(Model model) {
		User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=user.getUsername();
		Employee emp=service.fetchEmployeeByUserName(username);
		List<TransferCard> transferCardList;
		if(username.equals("admin")||username.equals("admin1") ){
		transferCardList = service.fetchAllClosedTransferCards();}
		else
			transferCardList = service.fetchClosedTransferCardsForOffice(emp.getOffice().getId());
		model.addAttribute("transferCardList", transferCardList);
		return "transferCardList";
	}
	
}
