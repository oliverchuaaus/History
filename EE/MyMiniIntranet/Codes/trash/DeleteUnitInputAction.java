package com.tougher.intranet.struts.employee.unit;

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
 * @struts.action name="DeleteUnitForm" path="/DeleteUnitInput" scope="session" validate="false" 
 * @struts.action-forward name="SUCCESS" path="/pages/employee/unit/DeleteUnit.jsp"
 */
public class DeleteUnitInputAction extends Action {
	private static final Logger log = Logger
			.getLogger(DeleteUnitInputAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeleteUnitForm uf = (DeleteUnitForm) form;
		log.debug("unitCode: "+uf.getUnitCode());
		Unit unit = UnitFacade.getUnit(uf.getUnitCode().toString());
		uf.setBean(unit);
		log.debug("unitCode: "+unit.getUnitName());
		log.debug("unitName: "+uf.getBean().getUnitName());
		log.debug("unitName: "+unit.getSuperUnit().getUnitName());
		request.setAttribute("unit",uf);
		return mapping.findForward("SUCCESS");
	}
}
