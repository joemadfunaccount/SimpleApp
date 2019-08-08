package com.joemad.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.joemad.model.CurrentPeople;
import com.joemad.model.entity.User;

public class UserActiveAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			try {
				HttpSession session = request.getSession();
				User loggedInUser = (User) session.getAttribute("loggedInUser");
				CurrentPeople.addUser(loggedInUser);
			} catch(Exception e){
				e.printStackTrace();
			}
			return null;
	}
}
