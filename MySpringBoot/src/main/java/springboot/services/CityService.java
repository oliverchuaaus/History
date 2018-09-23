package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import springboot.entities.City;
import springboot.repositories.CityRepository;

@Service
public class CityService {
	@Autowired
	private CityRepository cityRepository;

	public void addCity(City city) {
		cityRepository.save(city);
	}

	public void updateCity(City city, String name) {
		city.setName(name);
		cityRepository.save(city);
	}

	public void deleteCity(City city) {
		cityRepository.delete(city);
	}

	public City getCityByName(String name) {
		return cityRepository.findByName(name).get(0);
	}

	public City getCityByName2(String name) {
		return cityRepository.findByName(name, PageRequest.of(1, 1, Sort.by("id name"))).get(0);
	}
}
