package com.tougher.prattle.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tougher.prattle.service.PrattleService;
import com.tougher.prattle.service.PrattlerService;

@Controller
@RequestMapping("/prattles")
public class PrattleController {

	@Autowired
	private PrattlerService prattlerService;
	@Autowired
	private PrattleService prattleService;

	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteSpittle(@PathVariable("id") long id) {
		String username = prattleService.getPrattleById(id).getPrattler()
				.getUsername();
		prattleService.deletePrattle(id);
		return "redirect:/prattlers/" + username + "/prattles";
	}
}
