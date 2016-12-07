package com.tougher.intranet.struts.employee.unit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tougher.intranet.employee.unit.Unit;
import com.tougher.intranet.employee.unit.UnitFacade;

/** 
 * @struts.action path="/UpdateUnitInput" name="UpdateUnitForm" scope="session" validate="false"
 * @struts.action-forward name="SUCCESS" path="/pages/employee/unit/UpdateUnit.jsp"
 */
public class UpdateUnitInputAction extends Action {
	private static final Logger log = Logger.getLogger(AddUnitAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List unitList = UnitFacade.getUnitListWithNone();
		request.setAttribute("unitList", unitList);
		String unitCode = request.getParameter("unitCode");
		Unit unit = UnitFacade.getUnit(unitCode);
		UpdateUnitForm uf = (UpdateUnitForm) form;
		log.debug("unitForm: " + uf);
		log.debug("unitCode: " + uf.getUnitCode());
		log.debug("superUnit: " + uf.getSuperUnitCode());
		log.debug("superUnit: " + uf.getSuperUnit());
		//log.debug("superUnit: " + uf.getSuperUnit().getUnitName());
		uf.setBean(unit);
		return mapping.findForward("SUCCESS");
	}
}
