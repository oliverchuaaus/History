package com.tougher.intranet.unit.model.service;

import java.util.List;

import com.tougher.intranet.unit.model.domain.Unit;

public interface UnitService {

	public Unit getUnit(Long unitCode);

	public List<Unit> getUnitList();

	public void addUnit(Unit unit);

	public void updateUnit(Unit unit);

	public void deleteUnit(Unit unit);

	public List<Unit> getUnitListSubset(UnitSearchDto searchUnitDto);

	public Long getUnitListSubsetCount(UnitSearchDto searchUnitDto);

	public boolean checkExisting(Unit unit);

	public boolean checkNoSubunits(Unit unit);

}