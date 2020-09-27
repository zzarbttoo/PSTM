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
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">

<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>


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


<body>

	<div class="content" id="app" style="">

	<%@ include file="./form/pstm_header.jsp"%>

		<div class="review_contents wrapper">
			<div class="member_header">
				<div class="title">수강후기</div>
				<div class="sub_title">
					<span class="content">수강후기</span> 
					<img class="right_arrow"alt="다음버튼" src=""> 
					<span class="color">후기쓰기</span>
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
				<form action="review.do" method="post">
				<input type="hidden" name="command" value="insertres">
					<table border="1">
						<tr>
							<td>제목<input type="text" name="reviewtitle" /></td>
							<td>강사선택 <select name="trainer" id="searchYear">
									<option value="김선아 강사님">김선아 강사님</option>
									<option value="배유진 강사님">배유진 강사님</option>
									<option value="박준범 강사님">박준범 강사님</option>
							</select>
							</td>
						</tr>
						
						</table>
						<textarea name="reviewcontent" id="summernote" class="summernote"
							style="margin: 0;"></textarea>
							
							
<div name="photo" class="styled__Box-sc-1etgh1k-1 etFHhl">
<h1>사진 등록</h1>
<div class="styled__Wrap-sc-1lw3m30-0 Xyvt">
<div class="styled__Tabs-sc-1lw3m30-1 dRgla">
<button class="styled__Tab-sc-1lw3m30-2 bMhVuC">일반사진</button>
<button class="styled__Tab-sc-1lw3m30-2 cKrEqj">360˚사진</button>
</div>
<div class="styled__DescWrap-sc-1tford7-0 hhdFgg">
<p>- 사진은 가로로 찍은 사진을 권장합니다. (가로 사이즈 최소 800px)</p>
<p>- 사진 용량은 사진 한 장당 10MB 까지 등록이 가능합니다.</p>
<p>- 사진은 최소 3장 이상 등록해야하며, 최대 15장 까지 권장합니다. 그 이상 등록할 경우 업로드 시간이 다소 지연될 수 있습니다.</p>
</div>
<div class="styled__PhotoWrap-sc-1tford7-1 heKOml">
<div class="styled__PhotoView-sc-1tford7-2 jAfNtH">
<div data-id="0" class="styled__Elem-crtn37-0 qfkUX Photo--elem">
<p class="styled__Badge-crtn37-2 cnHOPh">대표이미지</p>
<button class="styled__DelBtn-crtn37-3 jGgNRz">
::before
::after
</button>
<div class="styled__Photo-crtn37-1 kKDzEJ"></div>
<button class="styled__RotateBtn-crtn37-4 dEpLAB">
<svg width="17" height="17" viewBox="0 0 512 512">
<path d="m461 280c-1-4-4-8-7-10-4-2-9-2-13 0l-72 34c-5 2-8 7-8 12-1 5 2 10 7 12l25 14c-28 45-78 75-136 75-76 0-140-52-157-123-2-11-14-18-25-15-11 2-18 14-15 25 21 89 102 155 197 155 74 0 138-40 173-98l25 13c5 2 11 2 15-1 4-3 6-9 5-14z m-390-35l72-33c5-2 8-7 8-12 1-6-2-11-7-13l-26-13c29-48 80-79 139-79 76 0 140 52 157 123 3 11 14 18 25 15 11-2 18-14 15-25-21-89-101-155-197-155-75 0-140 41-175 102l-25-13c-5-3-11-2-15 1-4 3-6 8-5 13l14 79c1 4 4 8 7 10 4 2 9 2 13 0z" transform="scale(1 1)"></path>
</svg>
</button>
</div>
<div data-id="1" class="styled__Elem-crtn37-0 qfkUX Photo--elem">
<button class="styled__DelBtn-crtn37-3 jGgNRz">
::before
::after
</button>
<div class="styled__Photo-crtn37-1 cDaPdk"></div>
<button class="styled__RotateBtn-crtn37-4 dEpLAB">
<svg width="17" height="17" viewBox="0 0 512 512">
<path d="m461 280c-1-4-4-8-7-10-4-2-9-2-13 0l-72 34c-5 2-8 7-8 12-1 5 2 10 7 12l25 14c-28 45-78 75-136 75-76 0-140-52-157-123-2-11-14-18-25-15-11 2-18 14-15 25 21 89 102 155 197 155 74 0 138-40 173-98l25 13c5 2 11 2 15-1 4-3 6-9 5-14z m-390-35l72-33c5-2 8-7 8-12 1-6-2-11-7-13l-26-13c29-48 80-79 139-79 76 0 140 52 157 123 3 11 14 18 25 15 11-2 18-14 15-25-21-89-101-155-197-155-75 0-140 41-175 102l-25-13c-5-3-11-2-15 1-4 3-6 8-5 13l14 79c1 4 4 8 7 10 4 2 9 2 13 0z" transform="scale(1 1)"></path>
</svg>
</button>
</div>
<div data-id="2" class="styled__Elem-crtn37-0 qfkUX Photo--elem">
<button class="styled__DelBtn-crtn37-3 jGgNRz">
::before
::after
</button>
<div class="styled__Photo-crtn37-1 fTHLJO"></div>
<button class="styled__RotateBtn-crtn37-4 dEpLAB">
<svg width="17" height="17" viewBox="0 0 512 512">
<path d="m461 280c-1-4-4-8-7-10-4-2-9-2-13 0l-72 34c-5 2-8 7-8 12-1 5 2 10 7 12l25 14c-28 45-78 75-136 75-76 0-140-52-157-123-2-11-14-18-25-15-11 2-18 14-15 25 21 89 102 155 197 155 74 0 138-40 173-98l25 13c5 2 11 2 15-1 4-3 6-9 5-14z m-390-35l72-33c5-2 8-7 8-12 1-6-2-11-7-13l-26-13c29-48 80-79 139-79 76 0 140 52 157 123 3 11 14 18 25 15 11-2 18-14 15-25-21-89-101-155-197-155-75 0-140 41-175 102l-25-13c-5-3-11-2-15 1-4 3-6 8-5 13l14 79c1 4 4 8 7 10 4 2 9 2 13 0z" transform="scale(1 1)"></path>
</svg>
</button>
</div>
</div>
<div class="styled__UploadWrap-sc-1tford7-6 diOUAM" style="position: relative;">
<button id="uploadPhoto" class="styled__UploadBtn-sc-1tford7-7 biNQFM" style="position: relative; z-index: 1;">사진 추가하기</button>
<div id="html5_1eiba14p9c1115nkhln1hau1jd04_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 10px; left: 10px; width: 204px; height: 145px; overflow: hidden; z-index: 0;">
<input id="html5_1eiba14p9c1115nkhln1hau1jd04" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple accept=".jpg,.jpeg,.png" tabindex="-1">
</div>
</div>
::after
</div>
<p class="styled__Text-sc-1lw3m30-3 bhZAGT">
<svg width="18" height="18" viewBox="0 0 18 18">
<g fill="none" fill-rule="evenodd">
<circle cx="9" cy="9" r="9" fill="#E6E7EA"></circle>
<path fill="#9C9EA3" d="M8.997 13.5a.75.75 0 1 1 0-1.5.75.75 0 0 1 0 1.5zm.01-2.499c-.634 0-.75-5.449-.75-6.001 0-.552.336-1 .75-1s.75.448.75 1-.116 6.001-.75 6.001z"></path>
</g>
</svg>
<span>허위 매물을 등록할 경우 다방에서 임의로 계정 및 매물 전체 삭제 처리됩니다.</span>
<a href="https://blog.naver.com/dabangapp/220122235428" target="_blank" rel="noopener noreferrer">허위매물 제재 정책 확인하기</a>
::after
</p>
</div>



							
						
					<table style="float: right;">
						<tr>
							<td colspan="2" align="right">
							<input type="button" value="취소" onclick="location.href='review.do?command=review'" /> 
							<input type="submit" value="작성" onclick="location.href='review.do?command=insertres'" /></td>
						</tr>
					</table>
				</form>
			</div>


			<%@ include file="./form/pstm_footer.jsp"%>
		</div>
	</div>

</body>
</html>