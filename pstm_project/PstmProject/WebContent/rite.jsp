<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
@import url("css/rite.css");
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
							placeholder : '최대 2048자 까지 쓸 수 있습니다. 글쓰기만 가능합니다.<사진 불가>'
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

	<%@ include file="./form/pstm_header.jsp"%>
	<form action="review.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="command" value="insertres">
	<div id="root">
		<div class="styled__Content-sc-12ma6qu-1 ggiCSo">
			<div class="styled__Wrap-sc-1p6u4xc-0 jtRefx">
				<ul class="styled__Tabs-sc-1p6u4xc-2 jSSNqy">
				</ul>
			</div>
			<ul class="styled__DescWrap-sc-1etgh1k-0 eKCsrg">
				<li>
					<p>글만 등록할 수 있습니다.</p>"예시 게시물을 보고싶을 경우" 
						<span>“리뷰 예시”</span> "를 이용해주세요.
						&nbsp;" <a href="https://hub.dabangapp.com" target="_blank"
							rel="noopener noreferrer">예시 바로가기</a>
				</li>
				<li>"기입내용을 전부 입력해주셔야 리뷰 등록이 가능합니다."</li>
			</ul>
			<div name="roomType" class="styled__Box-sc-1etgh1k-1 etFHhl">
				<h1>신청강좌</h1>
				<table>
					<colgroup>
						<col width="150px">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<th>강사선택</th>
							<td class="styled__Td-loqpne-0 fNOtpc" name="trainer">
							<label class="styled__RadioBtn-j1kyvo-0 styled__Radio-loqpne-1 dqavRH"
									>
									<input type="radio" name="trainer" value="김선아 강사님" checked>
									<p>김선아 강사님</p>
							</label> 
							<label class="styled__RadioBtn-j1kyvo-0 styled__Radio-loqpne-1 dqavRH">
									<input type="radio" name="trainer" value="김지훈 강사님">
									<p>김지훈 강사님</p>
							</label> 
							<label class="styled__RadioBtn-j1kyvo-0 styled__Radio-loqpne-1 dqavRH">
									<input type="radio" name="trainer" value="박준범 강사님">
									<p>박준범 강사님</p>
							</label> 
							<label class="styled__RadioBtn-j1kyvo-0 styled__Radio-loqpne-1 dqavRH">
									<input type="radio" name="trainer" value="배유진 강사님">
									<p>배유진 강사님</p>
							</label> 
							<label class="styled__RadioBtn-j1kyvo-0 styled__Radio-loqpne-1 dqavRH">
									<input type="radio" name="trainer" value="조용승 강사님">
									<p>조용승 강사님</p>
							</label></td>
						</tr>
						<tr>
							<th>수강 내용</th>
							<td class="styled__Td-loqpne-0 fNOtpc">
							<label class="styled__RadioBtn-j1kyvo-0 styled__Radio-loqpne-1 dqavRH">
									<input type="radio"  name="reviewtitle" value="체중감량"  checked>
									<p>체중감량</p>
							</label> 
							<label class="styled__RadioBtn-j1kyvo-0 styled__Radio-loqpne-1 dqavRH">
									<input type="radio"  name="reviewtitle" value="근육 증강">
									<p>근육 증강</p>
							</label> 
							<label class="styled__RadioBtn-j1kyvo-0 styled__Radio-loqpne-1 dqavRH">
									<input type="radio"  name="reviewtitle" value="식단 조절">
									<p>식단 조절</p>
							</label> 
							<label class="styled__RadioBtn-j1kyvo-0 styled__Radio-loqpne-1 dqavRH">
									<input type="radio" name="reviewtitle" value="유연성 증가">
									<p>유연성 증가</p>
							</label>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div name="location" class="styled__Box-sc-1etgh1k-1 etFHhl">
				<h1>글쓰기</h1>
				<table>
					<colgroup>
						<col width="150px;">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<th>수강 후기</th>
							<td class="styled__Td-sc-1wmqs5y-0 dZxpBV">
								<textarea name="reviewcontent" id="summernote" class="summernote1" style="margin: 0;"></textarea>
							</td>
						</tr>
						<tr>
							<th>만족도</th>
							<td class="styled__Td-sc-1wmqs5y-0 dZxpBV">
							<label class="styled__RadioBtn-j1kyvo-0 styled__Radio-loqpne-1 dqavRH">
									<input type="radio" name="reviewstar" value="★ "  checked>
									<p>★</p>
							</label> 							
							<label class="styled__RadioBtn-j1kyvo-0 styled__Radio-loqpne-1 dqavRH">
									<input type="radio" name="reviewstar" value="★★ ">
									<p>★★</p>
							</label> 
							<label class="styled__RadioBtn-j1kyvo-0 styled__Radio-loqpne-1 dqavRH">
									<input type="radio" name="reviewstar" value="★★★ ">
									<p>★★★</p>
							</label> 
							<label class="styled__RadioBtn-j1kyvo-0 styled__Radio-loqpne-1 dqavRH">
									<input type="radio" name="reviewstar" value="★★★★ ">
									<p>★★★★</p>
							</label> 
							<label class="styled__RadioBtn-j1kyvo-0 styled__Radio-loqpne-1 dqavRH">
									<input type="radio" name="reviewstar" value="★★★★★">
									<p>★★★★★</p>
							</label> 
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div name="photo" class="styled__Box-sc-1etgh1k-1 etFHhl">
				<h1>사진 등록</h1>
				<div class="styled__Wrap-sc-1lw3m30-0 Xyvt">
					<div class="styled__DescWrap-sc-1tford7-0 hhdFgg">
						<p>- 첫번째 사진은 리뷰 게시판의 메인에 장식됩니다.</p>
						<p>- 사진은 최소 1장 이상 등록해야하며, 최대 5장 까지 권장합니다.</p>
					</div>
					<div class="styled__PhotoWrap-sc-1tford7-1 heKOml">
						<div class="styled__PhotoView-sc-1tford7-2 jAfNtH">
							<div data-id="0" class="styled__Elem-crtn37-0 qfkUX Photo--elem">
								<p class="styled__Badge-crtn37-2 cnHOPh">대표이미지</p>
							
								<div class="styled__Photo-crtn37-1 kKDzEJ"></div>
								
							</div>
							
						</div>
						<div class="styled__UploadWrap-sc-1tford7-6 diOUAM"
							style="position: relative;">
							
							<button id="uploadPhoto"
								class="styled__UploadBtn-sc-1tford7-7 biNQFM"
								style="position: relative; z-index: 1;">사진 추가하기</button>
							
							<div id="html5_1eiba14p9c1115nkhln1hau1jd04_container"
								class="moxie-shim moxie-shim-html5"
								style="position: absolute; top: 10px; left: 10px; width: 204px; height: 145px; overflow: hidden; z-index: 0;">
								<input id="html5_1eiba14p9c111d5nkhln1hau1jd04" type="file"
									style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;"
									multiple accept=".jpg,.jpeg,.png" tabindex="-1">
							</div>
							
						</div>
					</div>

				</div>
			</div>
			<div class="styled__AgreeWrap-sc-1etgh1k-2 gfvOAV">
				<label class="Checkbox__Label-ifp1yz-0 kAqGVi clearfix" size="22">
					<span class="styled__AgreeText-sc-1etgh1k-3 eUWDCR">리뷰를 작성해주셔서 감사합니다 빠진 내용이 없는지 다시한번 확인해주세요</span>
				</label>
				<button class="styled__CancelBtn-sc-1etgh1k-4 kcMULl" type="button" onclick="location.href='review.do?command=review'">취소</button>
				<button class="styled__AddBtn-sc-1etgh1k-5 gsLmxa" type="submit">리뷰등록</button>
			</div>
		</div>
		
	</div>

</form>
<%@ include file="./form/pstm_footer.jsp"%>




</body>
</html>