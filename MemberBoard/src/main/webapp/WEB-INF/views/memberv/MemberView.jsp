<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>상세조회</h2>
<table border="1">
 <tr>
 <td>아이디</td>
 <td>${memberview.mid}</td></tr>
 <tr>
 <td>이름</td>
 <td>${memberview.mname}</td></tr>
 <tr>
 <td>프로필사진</td>
 <td><img src="uploadfile/${memberview.mfilename}" width="200" height="200"></td></tr>
 <tr>
 <td>주소</td>
 <td>(${memberview.mpostnum})${memberview.maddressroad}${memberview.maddress}${memberview.maddress1}</td></tr>
 <tr>
 <td>휴대폰번호</td>
 <td>${memberview.mphone}</td></tr>
 <tr>
 <td>이메일</td>
 <td>${memberview.memail}</td>
 <tr>
 </table>

</body>
</html>