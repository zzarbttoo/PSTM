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
</style>
<body>

	<header id="header" style="margin-bottom: 70px;">
		<div class="header_inner">
			<div class="header_wrapper">
				<ul class="header_top_menu member-section">
					<li id="headerMemberLogin" class="btn login">로그인</li>
					<li id="headerMembersignup">회원가입</li>
					<li id="headerMemberPage">마이페이지</li>
				</ul>
			</div>
		</div>
		<div class="header_inner bottom" id="headerbottom">
			<div class="header_wrapper">
				<div id="PSTM_logo">
					<h2>PSTM</h2>
				</div>
				<ul class="header_top_menu" id="header_bottom">
					<li id="header_pstm_intro">교육과정</li>
					<li id="header_pstm_apply">신청하기</li>
					<li id="header_pstm_consult">상담센터</li>
					<li id="header_pstm_review">후기게시판</li>
				</ul>
			</div>
		</div>
	</header>


</body>
</html>