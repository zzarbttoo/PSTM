<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

body {
	width:1100px;
	margin: 0 auto;

}

.inputSize{

	width : 500px;
	height : 50px;
	font-size : 26px;
	border-right:0px; 
	border-top:0px; 
	border-left:0px; 
	border-bottom:1px solid brown;
	
	
}

.loginBtn {

	width :  510px;
	height :  50px;
	font-size : 26px;
	color : white;
	background-color : red;
	border : none;

}

.snsBtn {
	
	width : 250px;
	height : 50px;
	font-size : 26px;
	color : white;
	background-color : skyblue;
	border : none;

}

.inputSize:focus {

outline: 1px solid red;

}


</style>
</head>
<body>

<%@ include file="./form/pstm_header.jsp"%>

<h1>로그인</h1>

<hr />
<br />
<h3>PSTM에 오신 것을 환영합니다.</h3>

<form action="Login.do" method="post">
	<input type="hidden" name="command" value="login"/>
	<table>
		<tr>			
			<td><input type="text" name="id" placeholder="아이디" class="inputSize" /></td>			
		</tr>
		
		<tr>		
			<td><input type="password" name="password" placeholder="비밀번호" class="inputSize" /></td>		
		</tr>
		
		<tr>
			<td>&nbsp;</td>
		</tr>
		
		<tr>
			<td>	
				<input type="submit" value="로그인" class="loginBtn"/>		
			</td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>
		</tr>
		
		<tr>
			<td style="text-align:center">
				<a href="pstm_findidpw.jsp">아이디/비밀번호 찾기</a>	
				<span>&#124;</span>		
				<a href="pstm_signUp.jsp">회원가입</a>	
			</td>		
		</tr>	
	</table>
</form>
<br />
<h3>SNS 계정으로 로그인</h3>
<br />
	<table>
		<tr>
			<td>
						
			</td>	
		</tr>
	</table>




<%@ include file="./form/pstm_footer.jsp"%>

</body>
</html>