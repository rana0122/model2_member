<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.MemberDAO" %>

<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="member" class="member.MemberDTO"/>
<jsp:setProperty name="member" property="*"/>

<%
	MemberDAO manager = MemberDAO.getInstance();
	int result = manager.deleteMember(member);
	
	if(result == 1){
%>    
		<script>
			alert("회원 탈퇴");	
			location.href="member.html";
		</script>
<%}else{ %>
		<script>
			alert("비번이 일치하지 않습니다.");
			history.go(-1);
		</script>
<%} %>

