package com.tougher.intranet.unit.model.dao;

import java.util.List;

import com.tougher.intranet.unit.model.domain.Unit;
import com.tougher.intranet.unit.model.service.UnitSearchDto;
import com.tougher.util.GenericDAO;

public interface UnitDao extends GenericDAO<Unit, Long> {

	public List<Unit> findAllOrdered();

	public List<Unit> findByCriteria(UnitSearchDto searchUnitDto);

	public Long findByCriteriaCount(UnitSearchDto searchUnitDto);

	public List<Unit> findSuperUnitList();

	public boolean checkExisting(Unit unit);

	public boolean checkNoSubunits(Unit unit);

}
