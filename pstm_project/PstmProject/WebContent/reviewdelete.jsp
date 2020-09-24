<%@page import="com.codachaya.dao.ReviewDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text.html;charset=UTF-8"); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	
	int reviewid=Integer.parseInt(request.getParameter("reviewid"));
	ReviewDao dao=new ReviewDao();
	int res=dao.delete(reviewid);
	
	if(res>0){
	%>
	<script type="text/javascript">
		alert("글 삭제 성공");
		location.href = "review.jsp";
	</script>
	<%
	} else {
	%>
	<script type="text/javascript">
		alert("글 삭제 실패");
		location.href="review.jsp";
	</script>
	<%
		}
	%>

</body>
</html>