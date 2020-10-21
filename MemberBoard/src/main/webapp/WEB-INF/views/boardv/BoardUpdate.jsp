<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function boardUpdate(){
			boardupdateform.submit();
		
	}
</script>
</head>
<body>
<h2>글수정</h2>
	<form action="boardupdateprocess" method="post" enctype="multipart/form-data">
<table border="1">
 <tr>
 <td>글번호</td>
 <td><input type="text" name="bnumber" id="bnumber" value="${boardUpdate.bnumber}" readonly><br></td></tr>
 
 <tr>
 <td>작성자</td>
 <td><input type="text" name="bwriter" id="bwriter" value="${boardUpdate.bwriter}" readonly></td></tr>
 <tr>
 <td>글제목</td>
 <td><input type="text" name="btitle" id="btitle" value="${boardUpdate.btitle}"></td></tr>
 <tr>
 <td>글내용</td>
 <td><textarea cols="10" rows="10" name="bcontents" id="bcontents">${boardUpdate.btitle}</textarea></td></tr>
 <tr>
 <td>파일이미지</td>
 <td><img src="webapp/resources/uploadfile/${boardUpdate.bfilename}" width="200" height="200"></td></tr>
 <tr>
 <td>파일수정</td>
 <td><input type="file" name="bfile" id="bfile"></td></tr>
 </table>
 <button>수정</button>
 </form>

</body>
</html>