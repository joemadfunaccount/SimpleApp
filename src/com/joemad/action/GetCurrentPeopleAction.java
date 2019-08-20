package com.joemad.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.joemad.model.CurrentPeople;

public class GetCurrentPeopleAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String currentPeopleStr = CurrentPeople.getCurrentPeople();
		response.getWriter().write(currentPeopleStr);
		return null;
	}
}
