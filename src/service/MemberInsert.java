package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberInsert implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemeberInsert");
		
		request.setCharacterEncoding("utf-8");
		
		String email = request.getParameter("mailid")+"@"+
				  request.getParameter("domain");

		String tel = request.getParameter("tel1") + "-" +
					  request.getParameter("tel2") + "-" +
					  request.getParameter("tel3");
		String phone = request.getParameter("phone1") + "-" +
			  			   request.getParameter("phone2") + "-" +
			  			   request.getParameter("phone3");
		
		
		String hobby="";
		String[] h = request.getParameterValues("hobby");
		for(String s : h ){
		hobby += s+"-";
		}

				
		MemberDTO member = new MemberDTO();
		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));
		member.setName(request.getParameter("name"));
		member.setJumin1(request.getParameter("jumin1"));
		member.setJumin2(request.getParameter("jumin2"));
		member.setEmail(email);
		member.setTel(tel);
		member.setPhone(phone);
		member.setPostcode(request.getParameter("postcode"));
		member.setAddress(request.getParameter("address"));
		member.setGender(request.getParameter("gender"));
		member.setHobby(hobby);
		member.setIntro(request.getParameter("intro"));
		
		MemberDAO dao= MemberDAO.getInstance();
		int result =dao.insertMember(member);
		System.out.println("insert result="+result);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./Login.do");		
		
		return forward;
	}

}
