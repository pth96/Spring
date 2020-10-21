<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<button onclick="location.href='memberloginform'">로그인</button>
<button onclick="location.href='memberjoinform'">회원가입</button>

<h3>카카오로 회원가입</h3>
<a href="kakaojoin">
   <img src="${pageContext.request.contextPath}/resources/img/카카오.png/">
</a>
<h3>네이버로 회원가입</h3>
<a href="naverjoin">
   <img src="${pageContext.request.contextPath}/resources/img/네이버.png/">
</a>

</body>
</html>
