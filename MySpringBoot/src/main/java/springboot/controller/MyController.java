package springboot.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.entities.City;
import springboot.entities.CityService;
import springboot.entities.Simple;
import springboot.entities.SimpleService;

@RestController
public class MyController {
	@Autowired
	CityService cityService;
	@Autowired
	SimpleService simpleService;

	@RequestMapping("/")
	String home() {
		City city = new City();
		city.setName("Manila");
		cityService.addCity(city);
		cityService.updateCity(city, "London");
		Long id = cityService.getCityByName("London").getId();
		cityService.deleteCity(city);
		
		return "Hello World! " + id;
	}
	
	@RequestMapping("/simple")
	String simple() {
		Simple simple = new Simple();
		simple.setBirthDate(LocalDate.now());
		simple.setBirthTime(LocalTime.now());
		
		simpleService.findDefaultMethods(simple);
		
		Long id = simple.getId();

		return id.toString();
	}

}