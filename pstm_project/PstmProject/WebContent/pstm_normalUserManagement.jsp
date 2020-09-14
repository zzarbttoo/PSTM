<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@ include file="./form/pstm_header.jsp"%>

<h1>강사의 회원 관리 페이지</h1>
	<br />
	<br />
	<h3>님의 운동 식단 기록</h3>



	<form action="">

		<table border="1">
			<tr>
				<th>칼로리</th>
				<td></td>
			</tr>

			<tr>
				<th>운동기록</th>
				<td></td>

			</tr>

			<tr>
				<th>음식정보</th>
				<td></td>
			</tr>


			<tr>
				<th>피드백</th>
				<td><textarea cols="50" rows="10"></textarea></td>
			</tr>

			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="피드백 전송" /></td>
			</tr>

		</table>
	</form>



<%@ include file="./form/pstm_footer.jsp"%>
</body>
</html>