<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<style type="text/css">
h1 {
	height: 100px;
}

#topMenu {
	height: 100px;
	width: 850px;
	text-align: center;
}

#topMenu ul li {
	list-style: none;
	float: left;
	line-height: 30px;
	text-align: center;
	vertical-align: middle;
}

#topMenu .menulink {
	text-decoration: none;
	display: block;
	width: 150px;
	font-size: 12px;
	font-weight: bold;
	color: black;
}

.btn_fix {
	position : fixed;
	background-color: black;
	color: white;
	border: 0;
	outline: 0;
	right: 50px;
	width: 50px;
	height: 50px;
	border-bottom-left-radius: 50%;
	border-bottom-right-radius: 50%;
	border-top-right-radius: 50%;
	border-top-left-radius: 50%;
}

.btn_fix:hover {
	background: tomato;
	color: white;
}


</style>

</head>
<body>

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



	<script>
    var page = 2;

    $(window).scroll(function() {
        if ($(window).scrollTop() == $(document).height() - $(window).height()) {
          console.log(++page);
          $("body").append('<div class="big-box"><h1>Page ' + page + '</h1></div>');
		  $("body").append('<%@include file="./form/pstm_footer.jsp" %>');
        }
    });
  </script>














	<div>
		<input type="button" class="btn_fix" value="글쓰기">
	</div>


	

</body>
</html>