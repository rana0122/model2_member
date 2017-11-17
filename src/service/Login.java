package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Login");

		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./member/idcheck.jsp");		
		return forward;
	}

}
