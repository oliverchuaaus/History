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
import com.tougher.util.ProgramAttributes;
import com.tougher.util.StrutsUtil;

/** 
 * @struts.action name="SearchUnitForm" path="/SearchUnit" scope="request" input="/SearchUnitInput.do"
 * @struts.action-forward name="SUCCESS" path="/pages/employee/unit/SearchUnit.jsp"
 * @struts.action-forward name="ERROR" path="/SearchUnitInput.do"
 */
public class SearchUnitAction extends Action {
	private static final Logger log = Logger.getLogger(SearchUnitAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchUnitForm sf = (SearchUnitForm) form;
		try {
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
					&& null == sf.getSuperUnitCode()
					&& null == itemOffsetString;
			log.debug("newPage: " + newPage);
			request.setAttribute("newPage", new Boolean(newPage));
			if (!newPage) {
				List unitListResults = UnitFacade.getUnitListSubset(sf
						.getBean());
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
				String maxPageItems = ProgramAttributes
						.getProperty("maxPageItems");
				request.setAttribute("maxPageItems", maxPageItems);
			}

			return mapping.findForward("SUCCESS");
		} catch (InvalidInputException e) {
			StrutsUtil.extractErrors(request, e);
			return mapping.findForward("ERROR");
		}

	}
}
