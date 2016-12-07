package com.tougher.intranet.struts.employee.unit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.tougher.intranet.employee.unit.UnitFacade;

/** 
 * @struts.action path="/AddUnitInput" name="AddUnitForm" scope="request" validate="false"
 * @struts.action-forward name="SUCCESS" path="/pages/employee/unit/AddUnit.jsp"
 */
public class AddUnitInputAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List unitList = UnitFacade.getUnitListWithNone();
		request.setAttribute("unitList", unitList);
		return mapping.findForward("SUCCESS");
	}
}
