package com.tougher.intranet.unit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tougher.intranet.unit.model.dao.UnitDao;
import com.tougher.intranet.unit.model.domain.Unit;

@Component
public class UnitServiceImpl implements UnitService {
	@Autowired
	private UnitDao dao;

	public Unit getUnit(Long unitCode) {
		return dao.findById(unitCode);
	}

	public List<Unit> getUnitList() {
		return dao.findAllOrdered();
	}

	public void addUnit(Unit unit) {
		dao.persist(unit);
	}

	public void updateUnit(Unit unit) {
		dao.merge(unit);
	}

	public void deleteUnit(Unit unit) {
		dao.remove(unit);
	}

	public List<Unit> getUnitListSubset(UnitSearchDto searchUnitDto) {
		return dao.findByCriteria(searchUnitDto);
	}

	public Long getUnitListSubsetCount(UnitSearchDto searchUnitDto) {
		return dao.findByCriteriaCount(searchUnitDto);
	}
	
	public boolean checkExisting(Unit unit) {
		return dao.checkExisting(unit);
	}
	
	public boolean checkNoSubunits(Unit unit) {
		return dao.checkNoSubunits(unit);
	}
}
