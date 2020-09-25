<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
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
	<script>
		// This is called with the results from from FB.getLoginStatus().
		function statusChangeCallback(response) {

			if (response.status === 'connected') {
				//	사용자가 Facebook에 로그인하고 웹페이지에 로그인했습니다.
				window.location.href = 'Login.do?command=FBlogin&access_token='
						+ response.authResponse.accessToken;
			} else {
				// The person is not logged into your app or we are unable to tell.
				document.getElementById('status').innerHTML = 'Please log '
						+ 'into this app.';
			}
		}
		// This function is called when someone finishes with the Login
		// Button. See the onlogin handler attached to it in the sample
		// code below.
		function checkLoginState() {
			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
			});
		}
		window.fbAsyncInit = function() {
			FB.init({
				appId : '1182399198800286',
				cookie : true, // enable cookies to allow the server to access 
				// the session
				xfbml : true, // parse social plugins on this page
				version : 'v8.0' // use graph api version 
			});
			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
			});
		};
		// Load the SDK asynchronously
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "https://connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
		// Here we run a very simple test of the Graph API after login is
		// successful. See statusChangeCallback() for when this call is made.
	</script>

</head>
<body>

<%@ include file="./form/pstm_header.jsp"%>


<%
    String clientId = "MtBEFWjjQiBrT7PZ7GhD";//애플리케이션 클라이언트 아이디값";
    String redirectURI = URLEncoder.encode("http://localhost:8787/PstmProject/Login.do?command=naverLogin", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    apiURL += "&client_id=" + clientId;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&state=" + state;
    session.setAttribute("state", state);
 %>
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
			<td>
						&nbsp;
			</td>
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
				<a href="<%=apiURL%>"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
				<fb:login-button scope="public_profile" onlogin="checkLoginState();"></fb:login-button>
				<div id="status"></div>
			</td>	
		</tr>
	</table>




<%@ include file="./form/pstm_footer.jsp"%>

</body>
</html>