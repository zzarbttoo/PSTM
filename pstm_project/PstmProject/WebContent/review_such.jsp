<%@page import="com.codachaya.dao.ReviewDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.codachaya.dto.ReviewDto" 
	import="java.util.List"
	import="java.util.ArrayList" 
	import="com.codachaya.biz.*"
	import="com.codachaya.util.*"%>
	<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
@import url("css/review.css");
</style>

</head>
<body>
<div class="content" id="app" style="">

		<%@ include file="./form/pstm_header.jsp"%>

		<% 		List<ReviewDto> reviewsuch=(List<ReviewDto>)request.getAttribute("suchList"); %>
	
		<div class="review_contents wrapper">
			<div class="member_header">
				<div class="title">수강후기</div>
				<div class="sub_title">
					<span class="content">수강후기</span> 
					<img class="right_arrow"alt="다음버튼" src="">
					<span class="color">전체보기</span>
				</div>
			</div>
			<div class="left-container">
				<div class="class_category_container">
					<div class="title">수강후기</div>
					<ul class="class_category_list">
						<li class="navi-item on"><a href="review.do?command=review">전체보기</a></li>
						<li class="navi-item"><a href="review_writer.jsp">후기쓰기</a></li>
					</ul>
				</div>
			</div>
			<div class="right-container">
				<div class="review_list_container">
					<div class="review_wrapper">
						<div class="title">전체후기</div>
						
						
						
						
						<form action="review.do" method="post">
							
								<input type="hidden" name="command" value="reviewsuch" />
								<select name="such" >
									<option value="reviewtitle">제목</option>
									<option value="trainer">트레이너</option>
								</select>
								<input type="text" name="reviewtitle">
								<input type="submit" value="검색" >

						</form>
						
						
						
						
						
						<div class="body-wrapper">
							<%
								for (int i = 0; i < reviewsuch.size(); i++) {


							%>
							<div class="review_item"
								onclick="location.href='review.do?command=selectres&ReviewId=<%=reviewsuch.get(i).getReviewid()%>'">
								<div class="review_img" style="background-color: green;">
									<img alt="후기사진"
										src="<%="./resources/img/select/img" + i + ".jpg"%>"
										style="width: 150px; height: 150px;">
								</div>
								<div class="review_text">
									<div class="review_class"><%=reviewsuch.get(i).getReviewtitle()%></div>
									<div class="review_summary"><%=reviewsuch.get(i).getTrainer()%></div>
								</div>
							</div>
							<%
								}
							%>
						</div>

						<div class="subscription_navigation">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="./form/pstm_footer.jsp"%>
</body>
</html>