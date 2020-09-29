<%@page import="com.codachaya.dto.NctinfoDto"%>
<%@page import="com.codachaya.dao.NctinfoDao"%>
<%@page import="com.codachaya.dto.DietinfoDto"%>
<%@page import="com.codachaya.dao.DietinfoDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.codachaya.dto.DailyinfoDto"%>
<%@page import="java.util.List"%>
<%@page import="com.codachaya.dao.DailyinfoDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<%
	if(session.getAttribute("login") == null){
		String result = "<script> alert('로그인을 먼저 해주세요!'); location.href='pstm_login.jsp'; </script> ";
		response.getWriter().append(result);
		
	}
	
	
%>
<style type="text/css">
	table{
		margin: auto;
	}
</style>
</head>
<body>
<%
	List<DailyinfoDto> dailyList = (ArrayList<DailyinfoDto>)request.getAttribute("list");
	List<List<DietinfoDto>> dietList = (ArrayList<List<DietinfoDto>>)request.getAttribute("dietList");
	
%>


	<%@include file="./form/pstm_header.jsp"%>

	<h1>마이페이지</h1>

	<nav id="topMenu">
		<ul>
			<li><a class="menulink" href="#">인바디</a></li>
			<li><a class="menulink" href="#">강좌</a></li>
			<li><a class="menulink" href="#">정보변경</a></li>
			<li><a class="menulink" href="daily.do?command=selectres">식단/운동기록</a></li>
		</ul>
	</nav>
	<aside class="profile">
		<div class="write">
			<a href="daily.do?command=insertform"><img src="imgfolder/pen_green.png" onmouseover='this.src="imgfolder/pen_black.png"' onmouseout='this.src="imgfolder/pen_green.png"'></a>
		</div>
	</aside>
	
	
	
	<%
	for(int i = 0; i < dailyList.size(); i++){
		for(int j = 0; j < dietList.get(i).size(); j++){
			System.out.println("이미지 : " + dietList.get(i).get(j).getUploadimg());
	%>
	<section class="main_text">
	<p>날짜 : <%=dailyList.get(i).getRegdate() %></p>


	<table border="1" style="width:500px; height: 500px;">
	
					
				<tr>
					<td colspan="4">
					<img src="daily.do?command=getimg&uploadimg=<%=dietList.get(i).get(j).getUploadimg()%>"></td>
				</tr>
				<tr>
					<td>
					<p>음식이름 : <%=dietList.get(i).get(j).getResult() %></p>
					<p>먹은시간 : <%=dietList.get(i).get(j).getTimeeat() %></p>
					<p>칼로리 : <%=dietList.get(i).get(j).getKcal() %></p>
					<p>피드백 : <%=dailyList.get(i).getFeedback() %></p>
					</td>
					
				</tr>
				

		
		<tr>
			<td colspan="4">
				<input type="button" value="수정" onclick="location.href='daily.do?command=upadteform'">
				<input type="button" value="삭제" onclick="location.href='daily.do?command=delete&dailyinfoid=<%=dietList.get(i).get(j).getDailyinfoid()%>'">
				<input type="button" value="목록" onclick="location.href='daily.do?command=selectres'">
			</td>
		</tr>
	</table>
	</section>
	<br>
	<%
		}
	}
	%>	
	

	<%@include file="./form/pstm_footer.jsp"%>

</body>
</html>