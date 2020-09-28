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

<%
	String reviewtitle=request.getParameter("reviewtitle");
	String trainer=request.getParameter("trainer");
	String reviewcontent=request.getParameter("reviewcontent");
	String uploadimg=request.getParameter("uploadimg");
	
	ReviewDto dto=new ReviewDto();
	dto.setReviewtitle(reviewtitle);
	dto.setTrainer(trainer);
	dto.setReviewcontent(reviewcontent);
	dto.setUploadimg(uploadimg);
	
	ReviewDao dao=new ReviewDao();
	int res=dao.insert(dto);
%>

</head>
<body>
<%
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