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

</head>
<body>
<%
	DailyinfoDao dao = new DailyinfoDao();
	List<DailyinfoDto> list = dao.selectList();
	
	DietinfoDao daos = new DietinfoDao();
	List<DietinfoDto> lists = daos.selectList();
	
	NctinfoDao nctdao = new NctinfoDao();
	List<NctinfoDto> nctlist = nctdao.selectList();
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
			<a href="daily.do?command=insertform"><img src="imgfolder/pen_green.png" onmouseover='this.src="imgfolder/pen_black.png"' onmouseout='this.src="imgfolder/pen_green.png"'></a>
		</div>
	</aside>
	<section class="main_text">
	
	
	<table border="1" style="width:500px; height: 500px;">
		<%
		for(int j = 0; j < list.size(); j++) {
			for(int i = 0; i < lists.size(); i++) {
				for(int z = 0; z < nctlist.size(); z++){
				if(list.get(j).getDailyinfoid() == lists.get(i).getDailyinfoid()) {
					
		%>
				<tr>
					<td><img src="daily.do?command=getimg&uploadimg=<%=lists.get(i).getUploadimg()%>" style="width:500px; height: 500px;"></td>
				</tr>
				<tr>
					<td>
						<p>음식 : <%=lists.get(i).getResult() %></p>
						<p>먹은 시간 : <%=lists.get(i).getTimeeat() %></p>
						<p>칼로리 : <%=nctlist.get(z).getKcal() %></p>
						<p>피드백 : </p>
					</td>
				</tr>
		
		<tr>
			<td>
				<input type="button" value="수정" onclick="location.href='daily.do?command=upadteform'">
				<input type="button" value="삭제" onclick="location.href='daily.do?command=delete&dietid=<%=lists.get(i).getDietid()%>'">
				<input type="button" value="목록" onclick="">
			</td>
		</tr>
				<%
					}
				}
			}
		}
	%>
		
		
	</table>
	
	</section>
	

	<%@include file="./form/pstm_footer.jsp"%>



</body>
</html>