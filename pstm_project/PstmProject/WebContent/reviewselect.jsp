<%@page import="com.codachaya.dto.ReviewDto"%>
<%@page import="com.codachaya.dao.ReviewDao"%>
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

<style type="text/css">
@import url("css/review_writer.css");
</style>

<link rel="stylesheet" href="css/summernote/summernote-lite.css">
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">

<!-- include libraries(jQuery, bootstrap) -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>


<script src="css/summernote/summernote-lite.js"></script>
<script src="css/summernote/lang/summernote-ko-KR.js"></script>
<script>
$(document).ready(
		function() {
			$('#summernote').summernote(
					{
						lang : 'ko-KR',
						height : 600,
						minHeight : null,
						maxHeight : null,
						focus : true,
						fontNames : [ '맑은고딕', 'Arial', 'Arial Black',
								'Comic Sans MS', 'Courier New', ],
						placeholder : '최대 2048자 까지 쓸 수 있습니다.'
					});

		})

function updateSummernoteImageFile(file, editor) {
	data = new FormData();
	data.append("file", file);
	$.ajax({
		data : data,
		type : "POST",
		url : "/uploadSummernoteImageFile",
		processData : false,
		contentType : false,
		success : function(data) {
			$(editor).summernote('insertImage', data.url);
		}
	});
}
</script>
</head>
<%
	int reviewid = Integer.parseInt(request.getParameter("ReviewId"));
ReviewDao dao = new ReviewDao();
ReviewDto dto = dao.selectOne(reviewid);
%>
<body>
	<%@ include file="./form/pstm_header.jsp"%>
	<div class="content" id="app" style="">
		<div class="review_contents wrapper">
			<div class="member_header">
				<div class="title">수강후기</div>
				<div class="sub_title">
					<span class="content">수강후기</span> <img class="right_arrow"
						alt="다음버튼" src=""> <span class="color">후기쓰기</span>
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
				<input type="hidden" name="reviewid" value="<%=dto.getReviewid()%>">
				<table border="1">
					<tr>
						<th>제목</th>
						<td><%=dto.getReviewtitle()%></td>
						<th>강사</th>
						<td><%=dto.getTrainer()%></td>
					</tr>
				</table>

				<table border="1">

						<img src="http://localhost:8787/PstmProject/imgfolder/<%=dto.getUploadimg()%>" style="width: 150px; height: 150px;">
					<textarea name="reviewcontent" id="summernote" class="summernote" style="margin: 0;"readonly="readonly"><%=dto.getReviewcontent() %></textarea>

				</table>
				<table style="float: right;">
					<tr>
						<td colspan="2" align="right"><input type="button" value="취소"
							onclick="location.href='review.do?command=review'" /> <input
							type="button" value="수정"
							onclick="location.href='review.do?command=update&ReviewId=<%=dto.getReviewid()%>'" />
							<input type="button" value="삭제"
							onclick="location.href='review.do?command=delete&reviewid=<%=dto.getReviewid()%>'" />
						</td>
					</tr>
				</table>
			</div>






			<%@ include file="./form/pstm_footer.jsp"%>
		</div>
	</div>





</body>
</html>