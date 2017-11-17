<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.MemberDTO" %> 
<%@ page import="member.MemberDAO" %> 
    
<%
		String id = (String) session.getAttribute("id");
		MemberDAO manager = MemberDAO.getInstance();
		MemberDTO member = manager.getMember(id);
		
		String email[] = member.getEmail().split("@");
		String tel[] = member.getTel().split("-");
		String phone[] = member.getPhone().split("-");
		String gender = member.getGender();
		String hobby[] = member.getHobby().split("-");
		
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="member.js"></script>

</head>
<body>
<form  method=post action="update.jsp">
<input type=hidden name="id" value="<%=member.getId()%>">
<table border=1 align=center>
	<caption><h3> 회원 정보 수정</h3></caption>
	<tr><td>ID</td>
		   <td><%=member.getId() %></td>
	 </tr>
	 <tr><td>비밀번호</td>
	 		<td><input type="password" size=20 id="passwd" name="passwd"></td>
	 </tr>
	 <tr><td>성명</td>
	 		<td><input type=text size=20 id="name" name="name" value="<%=member.getName()%>"></td>
	 </tr>
	 <tr><td>주민번호</td>
	 		<td><input type=text size=6 maxlength=6 id="jumin1" name="jumin1" value="<%=member.getJumin1()%>">-
					<input type=text size=7 maxlength=7 id="jumin2" name="jumin2" value="<%=member.getJumin2()%>"></td>
	</tr>
	<tr><td>E-Mail주소</td>
			<td><input type=text size=15 id="mailid" name="mailid" value="<%=email[0]%>">@
					<input type=text size=20 id="domain" name="domain" value="<%=email[1]%>">
					<select id="sel" name="domain">
						<option value="">직접입력</option>		
						<option value="naver.com">naver</option>		
						<option value="daum.net">daum</option>		
						<option value="nate.com">nate</option>		
						<option value="gmail.com">gmail</option>							
					</select>
			</td>
	</tr>
	<tr><td>전화번호</td>
			<td><input type=text size=4 id="tel1"  name="tel1" maxlength=3 value="<%=tel[0]%>">-
					<input type=text size=4 id="tel2" name="tel2" maxlength=4 value="<%=tel[1]%>">-
					<input type=text size=4 id="tel3" name="tel3" maxlength=4 value="<%=tel[2]%>">
			</td>
	</tr>
	<tr><td>핸드폰 번호</td>
			<td><select id="phone1" name="phone1">
						<option value="">선택</option>
						<option value="010"  <%if(phone[0].equals("010")) %>  selected>010</option>
						<option value="011"  <%if(phone[0].equals("011")) %>  selected>011</option>
						<option value="016"  <%if(phone[0].equals("016")) %>  selected>016</option>
						<option value="018"  <%if(phone[0].equals("018")) %>  selected>018</option>
					</select>
					<input type=text size=4 id="phone2" name="phone2" maxlength=4 value="<%=phone[1]%>">-
					<input type=text size=4 id="phone3" name="phone3" maxlength=4 value="<%=phone[2]%>">
			</td>
	</tr>
	<tr><td>우편번호 </td>
			<td><input type=text size=5 id="postcode" name="postcode" value="<%=member.getPostcode()%>">
					<input type=button value="우편검색" id="find">
			</td>
	<tr><td>주소</td>
			<td><input type=text size=100 id="address" name="address" value="<%=member.getAddress()%>"></td>
	</tr>
	<tr><td>성별</td>
			<td>	
			
			<% if(gender.equals("남자")){ %>			
					<input type=radio id="male" name=gender value="남자" checked>남자
					<input type=radio id="female" name=gender value="여자">여자
			<% }else{ %>
					<input type=radio id="male" name=gender value="남자">남자
					<input type=radio id="female" name=gender value="여자" checked>여자
			<% } %>
			
			</td>
	</tr>
	<tr><td>취미</td>
			<td>
			
				<% for(int i=0; i<hobby.length; i++){ %>				
					<input type=checkbox id="h1" name="hobby" value="공부"  <%if(hobby[i].equals("공부")) %>   checked='checked'  >공부	
				<%}	%>
				
				<% for(int i=0; i<hobby.length; i++){ %>
					<input type=checkbox id="h2" name="hobby" value="게임"  <%if(hobby[i].equals("게임")) %>  checked='checked'  >게임
				<%}	%>
				
				<% for(int i=0; i<hobby.length; i++){ %>	
					<input type=checkbox id="h3" name="hobby" value="스포츠"  <%if(hobby[i].equals("스포츠")) %>  checked='checked' >스포츠
				<%}	%>
				
				<% for(int i=0; i<hobby.length; i++){ %>	
					<input type=checkbox id="h4" name="hobby" value="낚시"  <%if(hobby[i].equals("낚시")) %>  checked='checked'  >낚시
				<%}	%>
				
				<% for(int i=0; i<hobby.length; i++){ %>		
					<input type=checkbox id="h5" name="hobby" value="등산" <%if(hobby[i].equals("등산")) %> checked='checked' >등산
				<%}	%>
				
				
			</td>
	</tr>
	<tr><td>자기소개</td>
			<td><textarea id="intro" name="intro"  rows="10" cols="50"><%=member.getIntro() %></textarea>					
			</td>
	</tr>
	<tr><td colspan=2 align=center>
					<input type=submit value="수정">
					<input type=reset value="취소">
		   </td>
	</tr>
</table>
</form>

</body>
</html>