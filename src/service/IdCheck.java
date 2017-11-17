package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;

public class IdCheck implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("IdCheck");
		
		String id = request.getParameter("id");
		MemberDAO dao = MemberDAO.getInstance();
		
		int result = dao.idCheck(id);
		request.setAttribute("result", result);
		System.out.println("idCheck result : "+result);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./member/idcheck.jsp");		
		return forward;
	}

}
