<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=UTF-8");%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="daily.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="command" value="insertres">
	<table border="1">
		<tr>
			<th>이미지</th>
			<td><input type="file" name="uploadimg"></td>
		</tr>
		<tr>
			<th>먹은 시간</th>
			<td></td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="작성">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>