package com.tougher.intranet.unit.model.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.tougher.intranet.unit.model.domain.Unit;
import com.tougher.intranet.unit.model.service.UnitSearchDto;
import com.tougher.intranet.unit.model.service.UnitSearchType;
import com.tougher.util.AbstractDAO;
import com.tougher.util.JPAUtil;

@Component
public class UnitDaoImpl extends AbstractDAO<Unit, Long> implements UnitDao {
	{
		persistentClass = Unit.class;
	}

	public List<Unit> findAllOrdered() {
		em.isOpen();
		TypedQuery<Unit> query = em.createQuery(
				" SELECT u FROM Unit u ORDER BY UPPER(u.unitName)", Unit.class);
		return query.getResultList();
	}

	public List<Unit> findByCriteria(UnitSearchDto searchUnitDto) {
		String qlString = "";
		if (searchUnitDto.getUnitSearchType() == null
				|| UnitSearchType.SEARCH_BY_UNIT_SUBSTRING.equals(searchUnitDto
						.getUnitSearchType())) {
			qlString += JPAUtil.addWhereOrAnd(qlString);
			qlString += "u.unitName LIKE :unitSubstring";
		} else {
			qlString += JPAUtil.addWhereOrAnd(qlString);
			qlString += "u.superUnit.unitCode = :superUnitCode";
		}
		qlString = "SELECT u FROM Unit u " + qlString + " ORDER BY u.unitCode";

		TypedQuery<Unit> q = em.createQuery(qlString, Unit.class);

		if (searchUnitDto.getUnitSearchType() == null
				|| UnitSearchType.SEARCH_BY_UNIT_SUBSTRING.equals(searchUnitDto
						.getUnitSearchType())) {
			String substring = searchUnitDto.getUnitSubstring();
			q.setParameter("unitSubstring", substring == null ? "%" : "%"
					+ substring + "%");
		} else {
			q.setParameter("superUnitCode", searchUnitDto.getSuperUnit()
					.getUnitCode());
		}
		q.setFirstResult(searchUnitDto.getFirstResult());
		q.setMaxResults(searchUnitDto.getMaxResults());

		return q.getResultList();
	}

	public Long findByCriteriaCount(UnitSearchDto searchUnitDto) {
		String qlString = "";
		if (searchUnitDto.getUnitSearchType() == null
				|| UnitSearchType.SEARCH_BY_UNIT_SUBSTRING.equals(searchUnitDto
						.getUnitSearchType())) {
			qlString += JPAUtil.addWhereOrAnd(qlString);
			qlString += "u.unitName LIKE :unitSubstring";
		} else {
			qlString += JPAUtil.addWhereOrAnd(qlString);
			qlString += "u.superUnit.unitCode = :superUnitCode";
		}
		qlString = "SELECT count(u) FROM Unit u " + qlString;

		TypedQuery<Long> q = em.createQuery(qlString, Long.class);

		if (searchUnitDto.getUnitSearchType() == null
				|| UnitSearchType.SEARCH_BY_UNIT_SUBSTRING.equals(searchUnitDto
						.getUnitSearchType())) {
			String substring = searchUnitDto.getUnitSubstring();
			q.setParameter("unitSubstring", substring == null ? "%" : "%"
					+ substring + "%");
		} else {
			q.setParameter("superUnitCode", searchUnitDto.getSuperUnit()
					.getUnitCode());
		}

		return q.getSingleResult();
	}

	public List<Unit> findSuperUnitList() {
		String qlString;
		qlString = "SELECT u FROM Unit u WHERE u.unitCode IN (SELECT u2.superUnit.u.superUnit.unitCode FROM Unit u2)";
		TypedQuery<Unit> q = em.createQuery(qlString, Unit.class);
		return q.getResultList();
	}

	public boolean checkExisting(Unit unit) {
		String qlString;
		qlString = "SELECT COUNT(u) FROM Unit u WHERE UPPER(u.unitName) = UPPER(:unitName)";
		if (unit.getUnitCode() != null) {
			qlString += " AND u.unitCode <> :unitCode";
		}
		TypedQuery<Long> q = em.createQuery(qlString, Long.class);
		q.setParameter("unitName", unit.getUnitName());
		if (unit.getUnitCode() != null) {
			q.setParameter("unitCode", unit.getUnitCode());
		}
		Long count = q.getSingleResult();
		return count > 0;
	}

	public boolean checkNoSubunits(Unit unit) {
		String qlString;
		qlString = "SELECT COUNT(u) FROM Unit u WHERE u.unitCode = :unitCode AND u.subUnits IS NOT EMPTY";
		TypedQuery<Long> q = em.createQuery(qlString, Long.class);
		q.setParameter("unitCode", unit.getUnitCode());
		Long count = q.getSingleResult();
		return count > 0;
	}

}
