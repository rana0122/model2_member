<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.MemberDAO" %>

<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="member" class="member.MemberDTO"/>
<jsp:setProperty name="member" property="*"/>

<%
		MemberDAO manager = MemberDAO.getInstance();
		int result = manager.memberAuth(member);

		if(result == 1){		// 회원 인증 성공	
			session.setAttribute("id", member.getId());
%>

		<%=member.getId() %>님 환영합니다. <br><br>
		
		<a href="updateMember.jsp">정보수정</a><br><br>
		
		<a href="deleteMember.jsp">회원 탈퇴</a><br><br>
		
		<a href="logout.jsp">로그아웃</a><br><br>


<%	}else{ %>	
	
			<script>
				alert("ID나 비밀번호가 틀렸습니다.");
				history.go(-1);
			</script>
			
<%	} %>
    