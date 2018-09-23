package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.entities.Simple;
import springboot.repositories.SimpleRepository;

@Service
public class SimpleService {
	@Autowired
	private SimpleRepository simpleRepository;

	public void save(Simple simple) {
		simpleRepository.save(simple);
	}
	
	public void delete(Simple simple) {
		simpleRepository.delete(simple);
	}
}
