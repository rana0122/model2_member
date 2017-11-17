<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.MemberDAO" %>

<%	request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="member" class="member.MemberDTO"/>
<jsp:setProperty name="member" property="*" />

<%
		String email = request.getParameter("mailid")+"@"+
							  request.getParameter("domain");

		member.setEmail(email);
		
		String tel = request.getParameter("tel1") + "-" +
						  request.getParameter("tel2") + "-" +
						  request.getParameter("tel3");
		member.setTel(tel);
		
		String phone = request.getParameter("phone1") + "-" +
				  			   request.getParameter("phone2") + "-" +
				  			   request.getParameter("phone3");
		member.setPhone(phone);	
		
		String hobby="";
		String[] h = request.getParameterValues("hobby");
		for(String s : h ){
			hobby += s+"-";
		}
		member.setHobby(hobby);
		
		
		MemberDAO manager = MemberDAO.getInstance();
		int result = manager.updateMember(member);		
		
		if(result == 1){				
%>
			<script>
				alert("정보수정 성공");
				location.href="loginForm.jsp";
			</script>

<%   }else{ %>
			<script>
				alert("비밀번호가 일치하지 않습니다.");
				history.go(-1);
			</script>
<%   }%>

