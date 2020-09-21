<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

.select {

	width: 500px;
	height: 250px;
	text-align:center;


}

input {

	width: 510px;
	height:50px;


}


</style>
</head>
<body>
<%@ include file="./form/pstm_header.jsp"%>
<h1>회원가입</h1>

	<table border="1">
		<tr>
			<td class="select">일반 회원<p>PT 서비스를 받고 싶은 분</p></td>
		</tr>

		<tr>
			<td><input type="button" value="가입하기" onclick="location.href='pstm_normalUserSignUp.jsp'"/></td>
		</tr>
	</table>
	<br />
	<hr />
	<br />

	<table border="1">
		<tr>
			<td class="select">
			트레이너
			<p>회원을 가르치고 싶은 분</p>
			(※자격증 보유 필수)
			</td>
		</tr>

		<tr>
			<td><input type="button" value="가입하기" onclick="location.href='pstm_trainerSignUp.jsp'"/></td>
		</tr>
	</table>
	<%@ include file="./form/pstm_footer.jsp"%>

</body>
</html>