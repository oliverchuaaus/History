package com.tougher.app.v1.repo.util;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;

import com.tougher.app.v1.dto.criteria.EmployeeSearchCriteriaDTO;
import com.tougher.app.v1.model.Employee;

public class EmployeeRepositoryImpl implements EmployeeRepositoryInf {
	@Autowired
	private EntityManager em;

	@Override
	public Page<Employee> findByCriteria(EmployeeSearchCriteriaDTO dto, Pageable pageable) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
		Root<Employee> employee = query.from(Employee.class);

		/*
		 * Path<String> hobbyPath = employee.get("hobbies[].id"); List<Predicate>
		 * predicates = new ArrayList<>(); for (String hobby : dto.getHobbyList()) {
		 * predicates.add(cb.like(hobbyPath, hobby)); }
		 */
		query = query.select(employee);

		TypedQuery<Employee> q = em.createQuery(query);

		if (pageable.getSort() != null) {
			query.orderBy(QueryUtils.toOrders(pageable.getSort(), employee, cb));
		}
		q.setMaxResults(pageable.getPageSize());
		q.setFirstResult((int) pageable.getOffset());

		int total = q.getResultList().size();
		return new PageImpl<Employee>(q.getResultList(), pageable, total);
	}

}
