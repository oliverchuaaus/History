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
 * @struts.action name="UpdateUnitForm" path="/UpdateUnit" scope="session" validate="true" input="/UpdateUnitInput.do"
 * @struts.action-forward name="SUCCESS" path="/pages/employee/unit/UpdateUnitSuccess.jsp"
 * @struts.action-forward name="ERROR" path="/UpdateUnitInput.do"
 */
public class UpdateUnitAction extends Action {
	private static final Logger log = Logger.getLogger(UpdateUnitAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AddUnitForm uf = (AddUnitForm) form;
		List unitList = UnitFacade.getUnitListWithNone();
		request.setAttribute("unitList", unitList);
		try {
			log.debug("unitCode: "+uf.getUnitCode());
			log.debug("bean: "+uf.getBean());
			log.debug("unitCode: "+uf.getBean().getUnitCode());
			log.debug("unitName: "+uf.getBean().getUnitName());
			log.debug("superUnitName: "+uf.getBean().getSuperUnitCode());
			UnitFacade.updateUnit(uf.getBean());
			request.setAttribute("unit", uf.getBean());
			return mapping.findForward("SUCCESS");
		} catch (InvalidInputException e) {
			StrutsUtil.extractErrors(request, e);
			return mapping.findForward("ERROR");
		}

	}
}
