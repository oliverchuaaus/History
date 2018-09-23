package springboot.entities;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {
	@Autowired
	private SimpleRepository simpleRepository;

	public void findDefaultMethods(Simple simple) {
		simpleRepository.findById(simple.getId());
		simpleRepository.findAll();
		simpleRepository.findAllById(Arrays.asList(1l));
		
		simpleRepository.count();
		simpleRepository.existsById(1l);
	}
}
