<%@page import="com.codachaya.dao.ReviewDao" %>
<%@page import="com.codachaya.dto.ReviewDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String reviewtitle=request.getParameter("reveiwtitle");
	String reviewcontent=request.getParameter("reviewcontent");
	
	ReviewDto dto=new ReviewDto();
	dto.setReviewtitle(reviewtitle);
	dto.setReviewcontent(reviewcontent);
	
	ReviewDao dao=new ReviewDao();
	int res=dao.insert(dto);
	if(res>0){
%>
<script type="text/javascript">
	alert("글 작성 성공");
	location.href="review.jsp"
</script>
<%
	}else{
%>
<script type="text/javascript">
	alert("글 작성 실패");
	location.href="review_writer.jsp";
</script>
<%
	}
%>

</body>
</html>