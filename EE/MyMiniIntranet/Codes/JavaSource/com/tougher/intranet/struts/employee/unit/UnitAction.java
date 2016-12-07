package com.tougher.intranet.struts.employee.unit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.tougher.intranet.employee.unit.Unit;
import com.tougher.intranet.employee.unit.UnitFacade;
import com.tougher.util.InvalidInputException;
import com.tougher.util.ProgramAttributes;
import com.tougher.util.StrutsUtil;

/** 
 * @struts.action name="SearchUnitForm" path="/SearchUnit" scope="request" validate="false" parameter="command"
 * @struts.action-forward name="SEARCH" path="/pages/employee/unit/SearchUnit.jsp"
 * 
 * @struts.action path="/AddUnitInput" name="AddUnitForm" scope="session" validate="false" parameter="command"
 * @struts.action-forward name="ADD" path="/pages/employee/unit/AddUnit.jsp"
 * @struts.action path="/AddUnit" name="AddUnitForm" scope="session" validate="true" input="/AddUnitInput.do" parameter="command"
 * @struts.action-forward name="ADD_SUCCESS" path="/pages/employee/unit/AddUnitSuccess.jsp" redirect="true"
 * @struts.action-forward name="ADD_ERROR" path="/AddUnitInput.do"
 * 
 * @struts.action path="/UpdateUnitInput" name="UpdateUnitForm" scope="session" validate="false" parameter="command"
 * @struts.action-forward name="UPDATE" path="/pages/employee/unit/UpdateUnit.jsp"
 * @struts.action path="/UpdateUnit" name="UpdateUnitForm" scope="session" validate="true" input="/UpdateUnitInput.do" parameter="command" 
 * @struts.action-forward name="UPDATE_SUCCESS" path="/pages/employee/unit/UpdateUnitSuccess.jsp" redirect="true"
 * @struts.action-forward name="UPDATE_ERROR" path="/UpdateUnitInput.do"
 * 
 * @struts.action path="/DeleteUnitInput" name="DeleteUnitForm" scope="session" validate="false" parameter="command"
 * @struts.action-forward name="DELETE" path="/pages/employee/unit/DeleteUnit.jsp"
 * @struts.action path="/DeleteUnit" name="DeleteUnitForm" scope="session" validate="false" parameter="command" 
 * @struts.action-forward name="CANCEL" path="/SearchUnit.do" redirect="true"
 * @struts.action-forward name="DELETE_SUCCESS" path="/pages/employee/unit/DeleteUnitSuccess.jsp" redirect="true"
 * @struts.action-forward name="DELETE_ERROR" path="/DeleteUnitInput.do"
 */
public class UnitAction extends DispatchAction {
	private static Logger log=Logger.getLogger(UnitAction.class);
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
	
	
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchUnitForm sf = (SearchUnitForm) form;
		String itemOffsetString = request.getParameter("pager.offset");
		log.debug("itemOffsetString: " + itemOffsetString);
		if (itemOffsetString != null) {
			sf.setItemNumber(itemOffsetString);
		}

		log.debug("unitSubstring: " + sf.getUnitSubstring());
		log.debug("itemNumber: " + sf.getItemNumber());
		log.debug("searchType: " + sf.getSearchType());
		log.debug("superUnitCode: " + sf.getSuperUnitCode());

		List unitList = UnitFacade.getUnitListWithNone();
		request.setAttribute("unitList", unitList);

		boolean newPage = "".equals(sf.getUnitSubstring())
				&& null == sf.getSuperUnitCode() && null == itemOffsetString;
		log.debug("newPage: " + newPage);
		request.setAttribute("newPage", new Boolean(newPage));
		if (!newPage) {
			List unitListResults = UnitFacade.getUnitListSubset(sf.getBean());
			request.setAttribute("unitListResults", unitListResults);
			Integer resultCount = UnitFacade.getUnitListSubsetCount(sf
					.getBean());
			if (resultCount == null)
				resultCount = new Integer(0);
			request.setAttribute("resultCount", resultCount);
			log.debug("resultCount: " + resultCount);
			String maxIndexPages = ProgramAttributes
					.getProperty("maxIndexPages");
			request.setAttribute("maxIndexPages", maxIndexPages);
			String maxPageItems = ProgramAttributes.getProperty("maxPageItems");
			request.setAttribute("maxPageItems", maxPageItems);
		}
		return mapping.findForward("SEARCH");
	}

	public ActionForward addInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List unitList = UnitFacade.getUnitListWithNone();
		request.setAttribute("unitList", unitList);
		return mapping.findForward("ADD");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UnitForm uf = (UnitForm) form;
		List unitList = UnitFacade.getUnitListWithNone();
		request.setAttribute("unitList", unitList);
		System.out.println("nasa add!");
		log.debug("in add");
		try {
			log.debug("bean: " + uf.getBean());
			UnitFacade.addUnit(uf.getBean());
			request.getSession().setAttribute("unit", uf.getBean());
			return mapping.findForward("ADD_SUCCESS");
		} catch (InvalidInputException e) {
			StrutsUtil.extractErrors(request, e);
			return mapping.findForward("ADD_ERROR");
		}
	}
	
	public ActionForward updateInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List unitList = UnitFacade.getUnitListWithNone();
		request.setAttribute("unitList", unitList);
		String unitCode = request.getParameter("unitCode");
		Unit unit = UnitFacade.getUnit(unitCode);
		UnitForm uf = (UnitForm) form;
		log.debug("unitForm: " + uf);
		log.debug("unitCode: " + uf.getUnitCode());
		log.debug("superUnit: " + uf.getSuperUnitCode());
		log.debug("superUnit: " + uf.getSuperUnit());
		uf.setBean(unit);
		return mapping.findForward("UPDATE");
	}	
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UnitForm uf = (UnitForm) form;
		List unitList = UnitFacade.getUnitListWithNone();
		request.setAttribute("unitList", unitList);
		try {
			log.debug("unitCode: "+uf.getUnitCode());
			log.debug("bean: "+uf.getBean());
			log.debug("unitCode: "+uf.getBean().getUnitCode());
			log.debug("unitName: "+uf.getBean().getUnitName());
			log.debug("superUnitName: "+uf.getBean().getSuperUnitCode());
			UnitFacade.updateUnit(uf.getBean());
			request.getSession().setAttribute("unit", uf.getBean());
			return mapping.findForward("UPDATE_SUCCESS");
		} catch (InvalidInputException e) {
			StrutsUtil.extractErrors(request, e);
			return mapping.findForward("UPDATE_ERROR");
		}
	}
	
	public ActionForward deleteInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UnitForm uf = (UnitForm) form;
		log.debug("unitCode: "+uf.getUnitCode());
		Unit unit = UnitFacade.getUnit(uf.getUnitCode().toString());
		uf.setBean(unit);
		log.debug("unitCode: "+unit.getUnitName());
		log.debug("unitName: "+uf.getBean().getUnitName());
		//log.debug("unitName: "+unit.getSuperUnit().getUnitName());
		request.setAttribute("unit",uf);
		return mapping.findForward("DELETE");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (isCancelled(request)){
			log.info("was cancelled");
			return (mapping.findForward("CANCEL"));
		}		
		UnitForm uf = (UnitForm) form;
		try {
			log.debug("bean: "+uf.getBean());
			UnitFacade.deleteUnit(uf.getBean());
			request.getSession().setAttribute("unit",uf.getBean());
			return mapping.findForward("DELETE_SUCCESS");
		} catch (InvalidInputException e) {
			log.debug("iie: "+e);
			StrutsUtil.extractErrors(request, e);
			return mapping.findForward("DELETE_ERROR");
		}

	}
}
