<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>메인페이지</h2>
${sessionScope.loginId} 님 환영합니다<br>
<c:if test="${sessionScope.loginId eq 'admin'}">
        <a href="memberlist">관리자모드(회원목록)</a><br>
        </c:if>
        
        <button onclick="location.href='memberlogout'">로그아웃</button>
        <button onclick="location.href='boardwriteform'">글쓰기</button><br>
        <button onclick="location.href='boardlist'">글목록</button>
        

</body>
</html>