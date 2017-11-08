package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.CarHistory;
import service.ServiceInterface;

@Controller
public class CarHistoryListController {
	@Autowired
	private ServiceInterface service;

	@RequestMapping(value = "/carHistoryList.html", method = RequestMethod.GET)
	public String showCarHistoryList(Model model) {
		List<CarHistory> carHistoryList = service.fetchAllCarHistory();
		model.addAttribute("carHistoryList", carHistoryList);
		return "carHistoryList";
	}

}
