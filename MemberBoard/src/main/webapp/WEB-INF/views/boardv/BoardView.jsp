<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
   $(document).ready(function(){
	   $("#commentWriteBtn").click(function(){
		  var cwriter = $("#cwriter").val();
		  var ccontents = $("#ccontents").val();
		  var cbnumber = "${boardView.bnumber}";
		  $.ajax({
			  type : "post",
			  url : "comment/commentwrite",
			  data : {
				  "cwriter" : cwriter,
				  "ccontents" : ccontents,
				  "cbnumber" : cbnumber},
				  dateType : "json",
				  success : function(result){
					  console.log("댓글성공");
					  console.log(result);
					  var output = "<table border='1'>";
					  output += "<tr><th>작성자</th>";
					  output += "<th>내용</th></tr>";
					  for(var i in result){
						  output += "<tr>";
						  output += "<td>"+result[i].cwriter+"</td>";
						  output += "<td>"+result[i].ccontents+"</td>";
						  output += "</tr>";
					  }
					  output += "</table>";
					  $("#commentArea").html(output);
					  $("#cwriter").val("");
					  $("#ccontents").val("");
				  },
				  error : function(){
					  console.log("댓글 등록 실패");
			  }
		  });
	   });
   });
   
   function boardupdate(){
 	  var id = "${sessionScope.loginId}";
 	  var iddb = "${boardView.bwriter}";
 	  if(id == iddb){
 		  location.href='boardupdate?bnumber=${boardView.bnumber}';
 	  }else{
 		  alert("해당글의 수정권한이 없습니다.")
 	  }
 	
 	  
   }
   function boarddelete(){
 	  var id = "${sessionScope.loginId}";
 	  var iddb = "${boardView.bwriter}";
 	  if(id == 'admin'){
 		  location.href='boarddelete?bnumber=${boardView.bnumber}';
 	  }else if(id == iddb){
 		  location.href='boarddelete?bnumber=${boardView.bnumber}';
 	  }else{
 		  alert("해당글의 삭제권한이 없습니다.")
 	  }
 	
 	  
   }
 </script>

</head>
<body>
	<h2>BoardView.jsp</h2>
	<table border="1">
		<tr>
			<td>글번호</td>
			<td>${boardView.bnumber}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${boardView.btitle}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${boardView.bwriter}</td>
		</tr>
		<tr>
			<td>생성날짜</td>
			<td>${boardView.bdate}</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${boardView.bhits}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${boardView.bcontents}</td>
		</tr>
		<tr>
		    <td>첨부파일 </td>
		    <td><img src="webapp/resources/uploadfile/${boardView.bfilename}" width="200" height="200"></td>
		    </tr>
	</table>
	<button onclick="boardupdate()">수정</button>
    <button onclick="boarddelete()">삭제</button>
	<button onclick="location.href='boardlist?page=${page}'">목록</button>
	
	
	<div id="commentWrite">
	작성자 : <input type="text" id="cwriter" value="${sessionScope.loginId}" readonly><br>
	내용 : <input type="text" id="ccontents"><br>
	<button id="commentWriteBtn">댓글입력</button>
	</div>
	
	<div id="commentArea">
	<table border='1'>
	<tr>
	<th>작성자</th>
	<th>내용</th></tr>
	<c:forEach var="comment" items="${commentList}">
	<tr>
	<td>${comment.cwriter}</td>
	<td>${comment.ccontents}</td></tr>
	</c:forEach>
	</table>
	
	
	</div>
</body>
</html>