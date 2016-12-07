package com.tougher.intranet.employee.unit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tougher.intranet.employee.unit.SearchUnit;
import com.tougher.intranet.employee.unit.Unit;
import com.tougher.util.ProgramAttributes;

/**
 * @spring.bean id="unitDAO"
 * @spring.property name="sessionFactory" ref="sessionFactory"
 */
public class UnitDAOHibernate extends HibernateDaoSupport {
	private static final Logger log = Logger.getLogger(UnitDAOHibernate.class);

	public Unit getUnit(final String unitCode) {
		HibernateCallback hc = new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				Unit unit = (Unit)s.get(Unit.class,new Long(unitCode));
				if (unit.getSuperUnit() != null) {
					unit.getSuperUnit().getUnitName();
				}
				return unit;
			}
		};
		Unit unit = (Unit) getHibernateTemplate().execute(hc);
		if (unit.getSuperUnit() != null) {
			log.debug(unit.getSuperUnit().getUnitName());
		}
		return unit;
	}
	public List getUnitList() {
		return getHibernateTemplate().findByNamedQuery("getUnitList");
	}

	public List getUnitListSubset(final SearchUnit searchUnit) {
		final int maxPageItems = Integer.parseInt(ProgramAttributes
				.getProperty("maxPageItems"));
		final int itemOffset = searchUnit.getItemNumberAsInt();
		log.debug("itemOffset: " + itemOffset);

		HibernateCallback hc = new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				String queryName = "getUnitListBySubstring";
				if (SearchUnit.SEARCH_BY_SUPER_UNIT.equals(searchUnit
						.getSearchType())) {
					if (!Unit.NONE.equals(searchUnit.getSuperUnitCode())) {
						queryName = "getUnitListBySuperUnit";
					} else {
						queryName = "getUnitListBySuperUnitNull";
					}
				}
				Query q = s.getNamedQuery(queryName);
				q.setFirstResult(itemOffset);
				q.setMaxResults(maxPageItems);
				if (SearchUnit.SEARCH_BY_SUPER_UNIT.equals(searchUnit
						.getSearchType())) {
					String superUnitCode = searchUnit.getSuperUnitCode();
					if (!Unit.NONE.equals(superUnitCode)) {
						q.setLong(0, Long.parseLong(superUnitCode));
					}
				} else {
					q.setString(0, "%" + searchUnit.getUnitSubstring() + "%");
				}
				return q.list();
			}
		};

		List l = (List) getHibernateTemplate().execute(hc);
		return l;
	}

	public Integer getUnitListSubsetCount(final SearchUnit searchUnit) {
		HibernateCallback hc = new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				String queryName = "getUnitListBySubstringCount";
				if (SearchUnit.SEARCH_BY_SUPER_UNIT.equals(searchUnit
						.getSearchType())) {
					if (!Unit.NONE.equals(searchUnit.getSuperUnitCode())) {
						queryName = "getUnitListBySuperUnitCount";
					} else {
						queryName = "getUnitListBySuperUnitNullCount";
					}
				}

				Query q = s.getNamedQuery(queryName);
				if (SearchUnit.SEARCH_BY_SUPER_UNIT.equals(searchUnit
						.getSearchType())) {
					String superUnitCode = searchUnit.getSuperUnitCode();
					if (!Unit.NONE.equals(superUnitCode)) {
						q.setLong(0, Long.parseLong(superUnitCode));
					}
				} else {
					q.setString(0, "%" + searchUnit.getUnitSubstring() + "%");
				}
				Integer count = (Integer) q.list().get(0);
				log.debug(count);
				return count;
			}
		};

		Integer count = (Integer) getHibernateTemplate().execute(hc);
		return count;
	}

	public void addUnit(Unit unit) {
		getHibernateTemplate().save(unit);
	}

	public void updateUnit(Unit unit) {
		getHibernateTemplate().update(unit);
	}

	public void deleteUnit(Unit unit) {
		getHibernateTemplate().delete(unit);
	}
}
