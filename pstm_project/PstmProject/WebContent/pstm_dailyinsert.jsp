<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>





<link href="css/pstm_daily.css" rel="stylesheet" type="text/css">
</head>
<body>


	<%@include file="./form/pstm_header.jsp"%>


	<form action="daily.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="command" value="insertres">
		 파일 : <input type="file" name="uploadimg"> 
		 먹은 시간 : <input type="text" name="timeeat">
		 
		 <input type="submit" value="upload">
	</form>


	<%@include file="./form/pstm_footer.jsp"%>
</body>
</html>