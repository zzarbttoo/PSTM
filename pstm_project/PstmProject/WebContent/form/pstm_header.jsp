<%@page import="java.util.List"%>
<%@page import="com.codachaya.dto.ReceiptinfoDto"%>
<%@page import="com.codachaya.dto.UserDto"%>


<%
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
response.setDateHeader("Expires", 0L); // Do not cache in proxy server
%>




<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style type="text/css">
* {
	padding: 0;
	margin: 0;
}
header li {
	float: left;
}
.header_wrapper {
	width: 1100px;
	display: flex;
	text-align: center;
}
.header_inner {
	background-color: #fff;
	display: flex;
	lign-height: 40px;
	justify-content: center;
	border-bottom: 1px solid #efefef;
}
.header_top_menu {
	list-style: none;
	font-size: 13px;
	padding: 12px 0;
}
.header_top_menu.member-section {
	float: right;
	font-size: 12px;
	line-height: 15px;
	margin-left: auto;
}
.header_top_menu #headerMemberLogin, .header_top_menu #headerMembersignup
	{
	padding: 0 11px;
	border-right: 1px solid #c4c4c4;
}
.header_top_menu #headerMemberPage {
	padding-left: 11px;
}
#headerbottom {
	height: 70px;
	border-bottom: 1px solid #efefef;
}
.header_wrapper #PSTM_Logo {
	display: flex;
	align-items: center;
}
#PSTM_logo>h2 {
	margin-top : 18px;
	width: 115px;
	height: 25px;
	cursor: pointer;
}
#header_bottom {
	font-size: 14px;
	line-height: 14px;
	display: flex;
	align-items: center;
	margin-left: auto;
}
#header_bottom li {
	margin-left: 30px;
}
.header_top_menu li {
	cursor: pointer;
	position: relative;
}
.menubarcss{
text-decoration: none;
list-style: none;
}
.menubarcss:visited{
color:black;
}
</style>
<body>

	<header id="header" style="margin-bottom: 70px;">
		<div class="header_inner">
			<div class="header_wrapper">
				<ul class="header_top_menu member-section">
				
				
											
				<%
					
					UserDto userdto = (UserDto) session.getAttribute("login");
					
				%>
				
				<%
					if(userdto != null){
						
				%>		
						<li><img src="<%=userdto.getUploadimg() %> "  style="width:50px;height:30px;"/>&nbsp;</li>
						<li><%=userdto.getName() %></li>
						<li id="headerMemberLogin"><a class="menubarcss" href="Login.do?command=logout">로그아웃</a></li>
				<%
						if(userdto.getUsertype().equals("N") || userdto.getUsertype().equals("F") || userdto.getUsertype().equals("S")){
				%>
							<li id="headerMemberPage"><a class="menubarcss" href="yana.do?command=userlist&userid=<%=userdto.getUserid()%>">마이페이지</a></li>	
				<% 			
						}else if(userdto.getUsertype().equals("T")){
							
				%>
							<li id="headerMemberPage"><a class="menubarcss" href="yana.do?command=list&trainerid=<%=userdto.getUserid()%>">마이페이지</a></li>
				<% 			
						}
				
					}else{
				%>								
						<li id="headerMemberLogin" class="btn login"><a class="menubarcss" href="pstm_login.jsp">로그인</a></li>
						<li id="headerMembersignup"><a class="menubarcss" href="pstm_signUp.jsp">회원가입</a></li>
				<%
					}
				%>
	
								
				</ul>
			</div>
		</div>
		<div class="header_inner bottom" id="headerbottom">
			<div class="header_wrapper">
				<div id="PSTM_logo">
					<h2><a href="pstm_mainpage.jsp"  class="menubarcss">PSTM</a></h2>
				</div>
				<ul class="header_top_menu" id="header_bottom">
					<li id="header_pstm_intro"><a href="pstm_introduce.jsp"  class="menubarcss">교육과정</a></li>
					<li id="header_pstm_apply"><a href="pstm_subscription.jsp"  class="menubarcss">신청하기</a></li>
					<li id="header_pstm_consult"><a href="pstm_normchatbot.jsp"  class="menubarcss">상담센터</a></li>
					<li id="header_pstm_review"><a href="review.jsp"  class="menubarcss">후기게시판</a></li>
				</ul>
			</div>
		</div>
	</header>


</body>
</html>