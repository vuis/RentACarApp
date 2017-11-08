package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.Contract;
import domain.Employee;
import service.ServiceInterface;
@Controller
public class ContractListController {
	@Autowired
	private ServiceInterface service;
	
	@RequestMapping(value = "/openedContractList.html", method = RequestMethod.GET)
	public String showOpenedContract(Model model) {				
		List<Contract> contractList;		
			contractList = service.fetchAllOpenedContracts();
			model.addAttribute("contractList", contractList);
		return "contractList";
	}
	
	@RequestMapping(value = "/closedContractList.html", method = RequestMethod.GET)
	public String showClosedContract(Model model) {		
		User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=user.getUsername();
		Employee emp=service.fetchEmployeeByUserName(username);
		List<Contract> contractList;
		if(username.equals("admin")||username.equals("admin1") ){
			contractList = service.fetchAllClosedContracts();}
		else
			contractList = service.fetchClosedContractsForOffice(emp.getOffice().getId());
		model.addAttribute("contractList", contractList);
		return "contractList";
	}
}
