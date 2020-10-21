<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>로그인</h2>
<form action="memberlogin" method="post">
아이디 : <input type="text" name="mid" id="mid"><br>
비밀번호 : <input type="password" name="mpassword" id="mpassword"><br>
<button>로그인</button> 
</form>
<h3>카카오로 로그인</h3>
<a href="kakaologin">
   <img src="${pageContext.request.contextPath}/resources/img/카카오.png/">
</a>
<h3>네이버로 로그인</h3>
<a href="naverlogin">
   <img src="${pageContext.request.contextPath}/resources/img/네이버.png/">
</a>

</body>
</html>