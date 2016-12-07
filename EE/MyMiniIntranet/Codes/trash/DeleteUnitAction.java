package com.tougher.intranet.struts.employee.unit;

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
 * @struts.action name="DeleteUnitForm" path="/DeleteUnit" scope="session" validate="false" input="/DeleteUnitInput.do"
 * @struts.action-forward name="SUCCESS" path="/pages/employee/unit/DeleteUnitSuccess.jsp"
 * @struts.action-forward name="CANCEL" path="/SearchUnit.do"
 * @struts.action-forward name="ERROR" path="/DeleteUnitInput.do"
 */
public class DeleteUnitAction extends Action {
	private static final Logger log = Logger.getLogger(DeleteUnitAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (isCancelled(request)){
			log.info("was cancelled");
			return (mapping.findForward("CANCEL"));
		}		
		DeleteUnitForm uf = (DeleteUnitForm) form;
		try {
			log.debug("bean: "+uf.getBean());
			UnitFacade.deleteUnit(uf.getBean());
			return mapping.findForward("SUCCESS");
		} catch (InvalidInputException e) {
			log.debug("iie: "+e);
			StrutsUtil.extractErrors(request, e);
			return mapping.findForward("ERROR");
		}

	}
}
