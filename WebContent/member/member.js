$(document).ready(function(){
	
	 // ID중복검사
	 $("#idcheck").click(function(){
		 if($("#id").val()==""){
			 alert("ID를 입력하세요");
			 $("#id").focus();
		 }else{
			 var ref="/model2_member/IdCheck.do?id="+$("#id").val();
			 window.open(ref,"idcheck","width=200,height=100");
		 } 	
	 });
	 
	 
	//주민번호  포커스 이동
	 $("#jumin1").keyup(function(){
		 if($("#jumin1").val().length==6)			 
		 	$("#jumin2").focus();
	 });
	 
	
	//이메일 선택
	$("#sel").change(function(){
		if($("#sel").val()==""){		// 직접입력
			$("#domain").removeAttr("disabled");
			$("#domain").val("").focus();
		}else{								// 도메인 선택
			$("#domain").val($("#sel").val());
			$("#domain").attr("disabled","disabled");
		}		
	});
	
	
	// 우편번호, 주소 Daum API
	function openDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {				
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				// 우편번호와 주소 정보를 해당 필드에 넣고, 커서를 상세주소 필드로 이동한다.
				document.getElementById('postcode').value = data.zonecode;
				document.getElementById('address').value = data.address;				
			}
		}).open();
	}
	
	
	// 우편번호, 주소 검색
	$("#find").click(function(){
		openDaumPostcode();
	});
	
	
	
	// 유효성 검사
	$("form").submit(function(){	
		if($("#id").val()==""){
			alert("ID를 입력하세요");
			$("#id").focus();
			return false;
		}
		if($("#passwd").val()==""){
			alert("비밀번호를 입력하세요");
			$("#passwd").focus();
			return false;
		}
		if($("#name").val()==""){
			alert("이름을 입력하세요");
			$("#name").focus();
			return false;
		}
		if($("#jumin1").val()==""){
			alert("주민번호 앞자리를 입력하세요");
			$("#jumin1").focus();
			return false;
		}
		if($("#jumin1").val().length!=6){
			alert("주민번호 앞자리를 6자리입력하세요");
			$("#jumin1").val("").focus();
			return false;
		}
		if(isNaN($("#jumin1").val())){
			alert("숫자만 입력 가능 합니다.");
			$("#jumin1").val("").focus();
			return false;
		}
		if($("#jumin2").val()==""){
			alert("주민번호 뒷자리를 입력하세요");
			$("#jumin2").focus();
			return false;
		}
		if($("#jumin2").val().length!=7){
			alert("주민번호 뒷자리를 6자리입력하세요");
			$("#jumin2").val("").focus();
			return false;
		}
		if(isNaN($("#jumin2").val())){
			alert("숫자만 입력 가능 합니다.");
			$("#jumin2").val("").focus();
			return false;
		}
		if($("#mailid").val()==""){
			alert("E-Mail주소를 입력하세요");
			$("#mailid").focus();
			return false;
		}
		if($("#domain").val()==""){
			alert("도메인명을 입력하세요");
			$("#domain").focus();
			return false;
		}
		if($("#tel1").val()==""){
			alert("전화번호 앞자리를 입력하세요");
			$("#tel1").focus();
			return false;
		}
		if(isNaN($("#tel1").val())){
			alert("숫자만 입력 가능합니다.");
			$("#tel1").val("").focus();
			return false;
		}
		if($("#tel2").val()==""){
			alert("전화번호 중간자리를 입력하세요");
			$("#tel2").focus();
			return false;
		}
		if(isNaN($("#tel2").val())){
			alert("숫자만 입력 가능합니다.");
			$("#tel2").val("").focus();
			return false;
		}
		if($("#tel3").val()==""){
			alert("전화번호 끝자리를 입력하세요");
			$("#tel3").focus();
			return false;
		}
		if(isNaN($("#tel3").val())){
			alert("숫자만 입력 가능합니다.");
			$("#tel3").val("").focus();
			return false;
		}
		if($("#phone1").val()==""){
			alert("핸드폰 앞자리를 선택하세요");
			return false;			
		}
		if($("#phone2").val()==""){
			alert("핸드폰 중간자리를 입력하세요");
			$("#phone2").focus();
			return false;
		}
		if(isNaN($("#phone2").val())){
			alert("숫자만 입력 가능합니다.");
			$("#phone2").val("").focus();
			return false;
		}
		if($("#phone3").val()==""){
			alert("핸드폰 끝자리를 입력하세요");
			$("#phone3").focus();
			return false;
		}
		if(isNaN($("#phone3").val())){
			alert("숫자만 입력 가능합니다.");
			$("#phone3").val("").focus();
			return false;
		}
		if($("#postcode").val()==""){
			alert("우편번호를 입력하세요");
			$("#postcode").focus();
			return false;
		}
		if($("#address").val()==""){
			alert("주소를 입력하세요");
			$("#address").focus();
			return false;
		}
		if($("#male").is(":checked")==false &&
			$("#female").is(":checked")==false){
			alert("남자.여자중에서 1개 선택 하세요");
			return false;
		}
		
		var cnt=0;
		if($("#h1").is(":checked")) cnt++;
		if($("#h2").is(":checked")) cnt++;
		if($("#h3").is(":checked")) cnt++;
		if($("#h4").is(":checked")) cnt++;
		if($("#h5").is(":checked")) cnt++;
		if(cnt<2){
			alert("취미를 2개이상 선택하세요");
			return false;
		}
		
		if($("#intro").val()==""){
			alert("자기소개를 입력하세요");
			$("#intro").focus();
			return false;
		}
		if($("#intro").val().length<2 ||
			$("#intro").val().length >100){
			alert("자기소개는 2~100자까지 입력가능 합니다.");
			return false;
		}		
		
	}); // submit() end
	
}); 