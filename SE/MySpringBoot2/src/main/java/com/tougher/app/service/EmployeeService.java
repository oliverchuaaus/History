package com.tougher.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tougher.app.dto.EmployeeDTO;
import com.tougher.app.dto.criteria.EmployeeSearchCriteriaDTO;
import com.tougher.app.dto.criteria.EmployeeSearchResultDTO;
import com.tougher.app.dto.criteria.PageableDTO;
import com.tougher.app.model.Employee;
import com.tougher.app.repo.EmployeeRepository;
import com.tougher.app.repo.HobbyRepository;
import com.tougher.app.repo.OccupationRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private OccupationRepository occupationRepo;
	@Autowired
	private HobbyRepository hobbyRepo;
	@Autowired
	private ModelMapper modelMapper;

	public EmployeeDTO save(EmployeeDTO empDTO) {
		Employee emp = convertToEntity(empDTO);
		emp = employeeRepo.save(emp);
		return convertToDto(emp);
	}

	public EmployeeDTO findById(Long id) {
		Employee employee = employeeRepo.findById(id).get();
		return convertToDto(employee);
	}

	public List<EmployeeDTO> findAll() {
		List<Employee> list = employeeRepo.findAll();
		return list.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public EmployeeSearchResultDTO findByCriteria(EmployeeSearchCriteriaDTO criteria) {
		Sort sort = Sort.unsorted();
		if (criteria.getPageable() != null && criteria.getPageable().getSort() != null) {
			sort = convertToEntity(criteria.getPageable().getSort());
		}
		PageRequest pageable = PageRequest.of(0, 20);
		if (criteria.getPageable() != null && criteria.getPageable().getOffset() != null
				&& criteria.getPageable().getPageSize() != null) {
			pageable = PageRequest.of(criteria.getPageable().getOffset(), criteria.getPageable().getPageSize(), sort);
		}
		Page<Employee> page = employeeRepo.findByCriteria(criteria, pageable);

		EmployeeSearchResultDTO result = new EmployeeSearchResultDTO();
		result.setEmployeeList(page.getContent().stream().map(this::convertToDto).collect(Collectors.toList()));
		return result;
	}

	private EmployeeDTO convertToDto(Employee employee) {
		return modelMapper.map(employee, EmployeeDTO.class);
	}

	private Sort convertToEntity(PageableDTO.SortDTO dto) {
		return modelMapper.map(dto, Sort.class);
	}

	private Employee convertToEntity(EmployeeDTO employeeDTO) {
		Employee emp = modelMapper.map(employeeDTO, Employee.class);
		if (emp.getOccupation() != null && emp.getOccupation().getId() != null) {
			emp.setOccupation(occupationRepo.getOne(emp.getOccupation().getId()));
		}
		if (emp.getHobbies() != null) {
			for (int i = 0; i < emp.getHobbies().size(); i++) {
				emp.getHobbies().set(i, hobbyRepo.getOne(emp.getHobbies().get(i).getId()));
			}
		}
		if (emp.getPersonalDetail() != null && emp.getPersonalDetail().getId() == null) {
			emp.getPersonalDetail().setEmployee(emp);
		}
		if (emp.getProfessionalDetail() != null && emp.getProfessionalDetail().getId() == null) {
			emp.getProfessionalDetail().setEmployee(emp);
		}
		if (emp.getAddresses() != null) {
			emp.getAddresses().stream().forEach(address -> address.setEmployee(emp));
		}
		return emp;
	}

}
