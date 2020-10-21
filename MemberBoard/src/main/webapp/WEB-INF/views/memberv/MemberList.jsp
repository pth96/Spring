<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script>
 function memberdelete(deleteid){
	  console.log(deleteid);
	  location.href="memberdelete?mid="+deleteid;
 }
 </script>
</head>
<body>
<h2>회원목록</h2>
<table border="1">
	<tr>
		<th>아이디</th> <th>비밀번호</th> <th>이름</th>
	    <th>생년월일</th> <th>주소</th> <th>전화번호</th>
	    <th>이메일</th> <th>조회</th> <th>삭제</th></tr>
		<c:forEach var="member" items="${memberlist}">
			<tr>
			<td>${member.mid}</td>
			<td>${member.mpassword}</td>
			<td>${member.mname}</td>
			<td>${member.mbirth}</td>
			<td>(${member.mpostnum})${member.maddressroad} ${member.maddress}${member.maddress1}</td>
			<td>${member.mphone}</td>
			<td>${member.memail}</td>
			<td><a href="memberview?mid=${member.mid}">조회</a></td>
			<td><button onclick="memberdelete('${member.mid}')">삭제</button></td>
			</tr><br>
		</c:forEach>
	</table>

</body>
</html>