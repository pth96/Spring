<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}
function memberUpdateProcess(){
	  var pw = document.getElementById("mpassword").value;
	  var pwdb = "${memberupdate.mpassword}";
	  console.log(pw);
	  console.log(pwdb);
	  if(pw.length != 0){
		  if(pw == pwdb){
		     updateform.submit();
		     
		  }else{
			  alert("비밀번호가 틀립니다.");
		  }	 
		  
	  }else{
		  alert("비밀번호를 입력해주세요");
	  }
}


</script>
</head>
<body>
<h2>MemberUpdate.jsp</h2>
<form action="memberupdateprocess" method="post" name="updateform" enctype="multipart/form-data">
              <table border="1">
 <tr>
 <td>아이디</td>
 <td><input type="text" name="mid" id="mid" value="${memberupdate.mid}" readonly></td></tr>
 
 <tr>
 <td>프로필사진</td>
 <td><img src="Uploadfile1/${memberupdate.mfilename}" width="200" height="200"></td></tr>
 <tr>
 <td>사진 수정</td>
 <td> <input type="file" name="mfile" id="mfile">${memberupdate.mfilename}</td></tr>
 <tr>
 <td>이름</td>
 <td><input type="text" name="mname" id="mname" value="${memberupdate.mname}"><br></td></tr>
 <tr>
 <td>생년월일</td>
 <td><input type="date" id="mbirth"name="mbirth" value="${memberupdate.mbirth}" readonly></td></tr>
 <tr>
 <td>주소</td>
 <td><input type="text" name="mpostnum" id="sample4_postcode" value="${memberupdate.mpostnum}" placeholder="우편번호">
        <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
        <input type="text" name="maddressroad" id="sample4_roadAddress" value="${memberupdate.maddressroad}" placeholder="도로명주소">
        <input type="text" name="maddressjibun" id="sample4_jibunAddress" value="${memberupdate.maddressjibun}" placeholder="지번주소"><br>
        <span id="guide" style="color:#999;display:none"></span>
        <input type="text" name="maddress" id="sample4_detailAddress" value="${memberupdate.maddress}" placeholder="상세주소">
        <input type="text" name="maddress1" id="sample4_extraAddress" value="${memberupdate.maddress1} "placeholder="참고항목"><br></td></tr>
 <tr>
 <td>휴대폰번호</td>
 <td><input type="text" name="mphone" id="mphone" value="${memberupdate.mphone}" onkeyup="phoneCheck()"><br>
 <span id=phonech></span></td></tr>
 <tr>
 <td>이메일</td>
 <td><input type="text" name="memail" id="memail" value="${memberupdate.memail}"></td></tr>
 </table>
               
               비밀번호 확인 : <input type="password" name="mpassword" id="mpassword"><br>
              
   </form>
   <button onclick="memberUpdateProcess()">정보수정</button>
</body>
</html>