<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script src="member.js"></script>

</head>
<body>
<form  method=post action="/model2_member/MemberInsert.do">
<table border=1 align=center>
	<caption>회원관리</caption>
	<tr><td>ID</td>
		   <td><input type=text size=20 id="id" name="id">
	 			  <input type=button value="ID중복검사"  id="idcheck"></td>
	 </tr>
	 <tr><td>비밀번호</td>
	 		<td><input type="password" size=20 id="passwd" name="passwd"></td>
	 </tr>
	 <tr><td>성명</td>
	 		<td><input type=text size=20 id="name" name="name"></td>
	 </tr>
	 <tr><td>주민번호</td>
	 		<td><input type=text size=6 maxlength=6 id="jumin1" name="jumin1">-
					<input type=text size=7 maxlength=7 id="jumin2" name="jumin2"></td>
	</tr>
	<tr><td>E-Mail주소</td>
			<td><input type=text size=15 id="mailid" name="mailid">@
					<input type=text size=20 id="domain" name="domain">
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
			<td><input type=text size=4 id="tel1"  name="tel1" maxlength=3>-
					<input type=text size=4 id="tel2" name="tel2" maxlength=4>-
					<input type=text size=4 id="tel3" name="tel3" maxlength=4>
			</td>
	</tr>
	<tr><td>핸드폰 번호</td>
			<td><select id="phone1" name="phone1">
						<option value="">선택</option>
						<option>010</option>
						<option>011</option>
						<option>016</option>
						<option>018</option>
					</select>
					<input type=text size=4 id="phone2" name="phone2" maxlength=4>-
					<input type=text size=4 id="phone3" name="phone3" maxlength=4>
			</td>
	</tr>
	<tr><td>우편번호 </td>
			<td><input type=text size=5 id="postcode" name="postcode">
					<input type=button value="우편검색" id="find">
			</td>
	<tr><td>주소</td>
			<td><input type=text size=100 id="address" name="address"></td>
	</tr>
	<tr><td>성별</td>
			<td><input type=radio id="male" name=gender value="남자">남자
					<input type=radio id="female" name=gender value="여자">여자
			</td>
	</tr>
	<tr><td>취미</td>
			<td><input type=checkbox id="h1" name="hobby" value="공부">공부	
					<input type=checkbox id="h2" name="hobby" value="게임">게임
					<input type=checkbox id="h3" name="hobby" value="스포츠">스포츠
					<input type=checkbox id="h4" name="hobby" value="낚시">낚시
					<input type=checkbox id="h5" name="hobby" value="등산">등산
			</td>
	</tr>
	<tr><td>자기소개</td>
			<td><textarea id="intro" name="intro"  rows="10" cols="50"></textarea>					
			</td>
	</tr>
	<tr><td colspan=2 align=center>
					<input type=submit value="회원가입">
					<input type=reset value="취소">
		   </td>
	</tr>
</table>
</form>

</body>
</html>