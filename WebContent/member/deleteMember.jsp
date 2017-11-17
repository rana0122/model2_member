<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String) session.getAttribute("id");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(document).ready(function(){
		$("form").submit(function(){			
			if($("#passwd").val()==""){
				alert("비밀번호를 입력하세요");
				$("#passwd").focus();
				return false;
			}	
		});			
	});
</script>
<body>

<center><h1>회원 탈퇴</h1></center>

<form method=post action="delete.jsp">
	<input type=hidden name="id" value="<%=id%>">
	<table border=1 align=center width=300>
		<tr><td>비밀번호</td>
				<td><input type=password id="passwd" name="passwd"></td>
		</tr>
		<tr><td colspan=2 align=center>
					<input type=submit value="회원탈퇴">
					<input type=reset value="취소">
			   </td>
		</tr>		
	</table>
</form>

</body>
</html>