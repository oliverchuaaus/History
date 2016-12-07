package com.tougher.prattle.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.tougher.prattle.domain.Prattle;
import com.tougher.prattle.domain.Prattler;
import com.tougher.prattle.service.PrattleService;
import com.tougher.prattle.service.PrattlerService;

@Controller
@RequestMapping("/prattlers")
public class PrattlerController {

	@Autowired
	private PrattlerService prattlerService;
	@Autowired
	private PrattleService prattleService;

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		model.addAttribute(prattlerService.getPrattler(username));
		return "prattlers/view";
	}

	@RequestMapping(method = RequestMethod.GET, params = "new")
	public String createSpitterProfile(Model model) {
		model.addAttribute(new Prattler());
		return "prattlers/edit";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addPrattlerFromForm(@Valid Prattler prattler,
			BindingResult bindingResult,
			@RequestParam(value = "image", required = false) MultipartFile image) {

		if (bindingResult.hasErrors()) {
			return "prattlers/edit";
		}
		try {
			if (!image.isEmpty()) {
				validateImage(image);
			}
		} catch (Exception e) {
			bindingResult.reject(e.getMessage());
			return "spitters/edit";
		}
		prattlerService.savePrattler(prattler);
		try {
			saveImage(prattler.getId(), image);
		} catch (IOException e) {
			bindingResult.reject(e.getMessage());
			return "spitters/edit";
		}

		return "redirect:/prattlers/" + prattler.getUsername();
	}

	private void validateImage(MultipartFile image) throws Exception {
		if (!image.getContentType().equals("image/jpeg")) {
			throw new Exception("Only JPG images accepted");
		}
	}

	private void saveImage(Long id, MultipartFile image) throws IOException {
		File file = new File("/avatars/avatar_" + id + ".jpg");
		if (!file.exists()) {
			FileUtils.writeByteArrayToFile(file, image.getBytes());
		}
	}

	@RequestMapping(value = "/{username}/prattles", method = RequestMethod.GET)
	public String listSpittlesForSpitter(@PathVariable String username,
			Model model) {
		model.addAttribute(prattlerService.getPrattler(username));
		List<Prattle> spittlesForSpitter = prattleService
				.getPrattlesForPrattler(username);
		model.addAttribute(spittlesForSpitter);
		return "prattles/list";
	}

	// CREATE
	@RequestMapping(value = "/rest/{username}", method = RequestMethod.POST, headers = "Content-Type=application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody
	Prattler createPrattler(@RequestBody Prattler prattler) {
		prattlerService.savePrattler(prattler);
		return prattler;
	}

	// RETRIEVE
	@RequestMapping(value = "/rest/{username}", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" }, produces = "application/json")
	public @ResponseBody
	Prattler getPrattler(@PathVariable String username) {
		return prattlerService.getPrattler(username);
	}

	// UPDATE
	@RequestMapping(value = "/rest/{username}", method = RequestMethod.PUT, headers = "Content-Type=application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePrattler(@PathVariable String username,
			@RequestBody Prattler prattler) {
		prattlerService.savePrattler(prattler);
	}

	// DELETE
	@RequestMapping(value = "/rest/{username}", method = RequestMethod.DELETE)
	public String deletePrattler(@PathVariable String username) {
		return "redirect:/home";
	}

	// RETRIEVE
	@RequestMapping(value = "/rest/prattlers", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" }, produces = "application/json")
	public @ResponseBody
	List<Prattler> getPrattlers() {
		return prattlerService.getAllPrattlers();
	}

}
