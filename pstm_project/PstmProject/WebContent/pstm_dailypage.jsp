<%@page import="com.codachaya.dto.DailyinfoDto"%>
<%@page import="java.util.List"%>
<%@page import="com.codachaya.dao.DailyinfoDao"%>
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
<style type="text/css">
h1 {
	height: 100px;
}

#topMenu {
	height: 100px;
	width: 850px;
	text-align: center;
	margin: auto;
}

#topMenu ul li {
	list-style: none;
	float: left;
	line-height: 30px;
	text-align: center;
	vertical-align: middle;
}

#topMenu .menulink {
	text-decoration: none;
	display: block;
	width: 150px;
	font-size: 12px;
	font-weight: bold;
	color: black;
}

.btn_fix {
	position: fixed;
	background-color: black;
	color: white;
	border: 0;
	outline: 0;
	right: 50px;
	width: 50px;
	height: 50px;
	border-bottom-left-radius: 50%;
	border-bottom-right-radius: 50%;
	border-top-right-radius: 50%;
	border-top-left-radius: 50%;
}

.btn_fix:hover {
	background: tomato;
	color: white;
}

body {
	margin: 0px;
	padding: 0px;
}

.big-box {
	width: 80%;
	background-color: white;
	height: 100vh;
	border-top: 1px solid black;
}

.date {
	width: 500px;
	height: 100px;
	border: 1px solid gray;
	margin: auto;
}

.img {
	width: 500px;
	height: 500px;
	border: 1px solid gray;
	margin: auto;
}

.table {
	width: 500px;
	height: 500px;
	border: 1px solid gray;
	margin: auto;
}


a{
	text-decoration: none;
}
 .profile{
 	padding-top: 50px;
 	text-align: center;
 	position: fixed;
 	float: right;
 	right: 100px;
 	display: table-cell; 
 }
 .profile img{
 	width: 100px;
 	height: 100px;
 }
 .write{
 	position: absolute;
 	bottom: -170px;
	right: 300px;
 }
 .write img{
 	width: 50px;
 	height: 50px;
 }
 .profile_td{
 	height: 20px;
 }
 .info{
 	padding-left: 20px;
 	text-align: left;
 }
 .main_text{
 	padding-top: 50px;
 	position: relative;
 	display: table-cell;
 	left: 250px;
 }
.main_text table{
	width: 700px;
	height: 400px;
	border-collapse: collapse;
	margin-bottom: 20px;
}
 

</style>


</head>
<body>
<%
	String uploadimg = request.getParameter("uploadimg");
%>
	<%@include file="./form/pstm_header.jsp"%>

	<h1>마이페이지</h1>

	<nav id="topMenu">
		<ul>
			<li><a class="menulink" href="#">인바디</a></li>
			<li><a class="menulink" href="#">강좌</a></li>
			<li><a class="menulink" href="#">정보변경</a></li>
			<li><a class="menulink" href="pstm_dailypage.jsp">식단/운동기록</a></li>
		</ul>
	</nav>
	<aside class="profile">
		<div class="write">
			<a href="pstm_dailyinsert.jsp"><img src="img/pen_green.png" onmouseover='this.src="imgfolder/pen_black.png"' onmouseout='this.src="imgfolder/pen_green.png"'></a>
		</div>
	</aside>
	
	<section class="main_text">
	<table border="1" style="width:800px; height:100px; border-collapse:collapse">
		<tr>
			<td><img src="daily.do?uploadimg=<%=uploadimg%>"></td>
		</tr>
	</table>
	</section>
	
	
	


	<%@include file="./form/pstm_footer.jsp"%>



</body>
</html>