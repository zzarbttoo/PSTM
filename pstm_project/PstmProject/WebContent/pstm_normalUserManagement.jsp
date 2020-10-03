<%@page import="java.util.ArrayList"%>
<%@page import="com.codachaya.dto.DietinfoDto"%>
<%@page import="com.codachaya.dto.DailyinfoDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	List<DailyinfoDto> dailyList = (ArrayList<DailyinfoDto>)request.getAttribute("list");
	List<List<DietinfoDto>> dietList = (ArrayList<List<DietinfoDto>>)request.getAttribute("dietList");
%>



<%
	if(session.getAttribute("login") ==null){
		String result = "<script> alert('로그인을 먼저 해주세요!'); location.href='pstm_login.jsp'; </script> ";
		response.getWriter().append(result);
	}
%>


<%@ include file="./form/pstm_header.jsp"%>

<h1>강사의 회원 관리 페이지</h1>
	<br />
	<br />
	<h3>님의 운동 식단 기록</h3>



<form action="trainer.do" method="post">
	<input type="hidden" name="command" value="fbinsert">
<%
for(int i = 0; i < dailyList.size(); i++){
	for(int j = 0; j < dietList.get(i).size(); j++){
%>
		<table border="1">
			<tr>
				<th>칼로리</th>
				<td><%=dietList.get(i).get(j).getKcal() %></td>
			</tr>

			<tr>
				<th>음식정보</th>
				<td><%=dietList.get(i).get(j).getResult() %></td>
			</tr>

			<tr>
				<th>피드백</th>
				<td><textarea cols="50" rows="10" name="feedback"></textarea></td>
			</tr>

			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="피드백 전송" /></td>
			</tr>

		</table>
		<%
		}
	}
		%>
	</form>



<%@ include file="./form/pstm_footer.jsp"%>
</body>
</html>