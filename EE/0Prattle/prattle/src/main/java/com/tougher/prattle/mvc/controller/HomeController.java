package com.tougher.prattle.mvc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tougher.prattle.service.PrattleService;

@Controller
public class HomeController {

	@Autowired
	private PrattleService prattleService;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String showHomePage(Map<String, Object> model) {
		model.put("prattles", prattleService.getRecentPrattles(1));
		return "home";
	}

}
