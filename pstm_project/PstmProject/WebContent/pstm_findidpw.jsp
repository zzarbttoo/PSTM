<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.inputSize {
	width: 400px;
	height: 50px;
	font-size: 22px;
	border: none;
}

#findBtn {
	width: 400px;
	height: 50px;
	font-size: 22px;
	border: none;
	color: white;
	background-color: red;
}
</style>
</head>
<body>
<%@ include file="./form/pstm_header.jsp"%>

<h1>아이디/비밀번호 찾기</h1>
<br />
<form action="" method="post">

	<table>
			<tr>
				<td><input type="text" name="name" placeholder=" 이름" class="inputSize" /></td>							
			</tr>
			
			<tr>
				<td><input type="text" name="phone" placeholder=" 전화번호" class="inputSize"/></td>
			</tr>
			
			<tr>
				<td></td>
			</tr>
			
			<tr>	
				<td><input type="submit" value="아이디/비밀번호 찾긔" id="findBtn"/></td>
			
			</tr>
	</table>
</form>


<%@ include file="./form/pstm_footer.jsp"%>
</body>
</html>