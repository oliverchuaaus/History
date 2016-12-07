package com.tougher.intranet.struts.employee.unit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.tougher.intranet.employee.unit.UnitFacade;
import com.tougher.util.InvalidInputException;
import com.tougher.util.StrutsUtil;

/** 
 * @struts.action name="AddUnitForm" path="/AddUnit" scope="request" validate="true" input="/AddUnitInput.do"
 * @struts.action-forward name="SUCCESS" path="/pages/employee/unit/AddUnitSuccess.jsp"
 * @struts.action-forward name="ERROR" path="/AddUnitInput.do"
 */
public class AddUnitAction extends Action {
	private static final Logger log = Logger.getLogger(AddUnitAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AddUnitForm uf = (AddUnitForm) form;
		List unitList = UnitFacade.getUnitListWithNone();
		request.setAttribute("unitList", unitList);
		try {
			log.debug("bean: "+uf.getBean());
			UnitFacade.addUnit(uf.getBean());
			request.setAttribute("unit", uf.getBean());
			return mapping.findForward("SUCCESS");
		} catch (InvalidInputException e) {
			StrutsUtil.extractErrors(request, e);
			return mapping.findForward("ERROR");
		}

	}
}
