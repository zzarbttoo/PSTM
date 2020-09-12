<%@ page language="java" contentType="text/html; charset=EUC-KR"
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
@import url("css/header.css");
</style>
<style type="text/css">
@import url("css/footer.css");
</style>
<style type="text/css">
@import url("css/review_writer.css");
</style>


<script src="/js/summernote/summernote-lite.js"></script>
<script src="/js/summernote/lang/summernote-ko-KR.js"></script>

<link rel="stylesheet" href="/css/summernote/summernote-lite.css">
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

<!-- include summernote css/is -->
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
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
						<li class="navi-item on"><a href="review.html">전체보기</a></li>
						<li class="navi-item"><a href="review_writer.html">후기쓰기</a></li>
					</ul>
				</div>
			</div>




			<div class="right-container">
				<form action="reviewcontroller.jsp" method="post">
					<table border="1">
						<tr>
							<th>제목</th>
							<td><input type="text" name="reviewtitle" /></td>
							<td>강사선택 <select name="searchYear" id="searchYear">
									<option value="kim">김선아 강사님</option>
									<option value="phe">배유진 강사님</option>
									<option value="pack">박준범 강사님</option>
							</select>


							</td>
						</tr>
					</table>
					<form method="post">
						<textarea name="reviewcontent" id="summernote" class="summernote"
							style="margin: 0;"></textarea>
					</form>
					<table style="float: right;">
						<tr>
							<td colspan="2" align="right"><input type="button"
								value="취소" onclick="location.href='reviewlist.jsp'" /> <input
								type="submit" value="작성" /></td>
						</tr>
					</table>
				</form>
			</div>







			<footer class="footer">
				<section>
					<div class="footer_inner">
						<table id="footer_top_table">
							<tbody>
								<tr>
									<td><img
										src="/site_media/new_images/footer-ic-call@2x.png"
										style="width: 20px; height: 19px;">
										<p id="service-center-title">
											<a href="/board/faq.html"> "고객센터 1:1 문의" <img
												src="/site_media/new_images/cs-arrow.png" alt="arrow"
												class="cs-arrow-icon">
											</a>
										</p></td>
									<td style="text-align: right;"><a href="" target="_blank"
										rel="noopener noreferrer"> <img
											src="/site_media/new_images/footer/m-menu-ic-kakaotalk@2x.png"
											alt="kakao-icon" class="footer-icon">
									</a> <a href="https://www.youtube.com" target="_blank"
										rel="noopener noreferrer"> <img
											src="/site_media/new_images/footer/m-menu-ic-youtube@2x.png"
											alt="youtube-icon" class="footer-icon"
											style="margin-left: 17px;">
									</a> <a href="https://www.instagram.com/dano.fit/" target="_blank"
										rel="noopener noreferrer"> <img
											src="/site_media/new_images/footer/m-menu-ic-instagram@2x.png"
											alt="instagram-icon" class="footer-icon"
											style="margin-left: 17px;">
									</a></td>
								</tr>
								<tr>
									<td id="center-information">"운영시간 : 평일 10:00 - 18:00" <br>
										"점심시간 : 평일 12:30 - 13:30 (주말, 공휴일 휴무)" <br>
									</td>
								</tr>
							</tbody>
						</table>
						<div id="seperate-line"></div>
						<table id="footer_bottom_table">
							<tbody>
								<tr>
									<td class="footer_bottom_td"
										style="width: 17%; text-align: left;"><img
										class="footer_logo"
										src="/site_media/new_images/footer-ic-logo@2x.png"></td>
									<td style="width: 70%; text-align: left;">
										<p class="copyright">
											<br> "사업장소재지 | 사업자번호 010-0000-0000 " <br> "대표이사
											pstm | 개인정보책임자 pstm |" <a
												href="https://auth.dano.me/terms_of_use_new" target="_blank"
												rel="noopener noreferrer"
												style="text-decoration: underline;">통합약관</a> "|" <a
												href="/terms-of-uses.html" target="_blank"
												rel="noopener noreferrer"
												style="text-decoration: underline;">이용약관</a> "|" <a
												href="/member/privacy-policy.html" target="_blank"
												rel="noopener noreferrer"
												style="text-decoration: underline;">개인정보 처리방침</a> <br>
											"Copyright © pstm, Inc. All Rights Reserved."
										</p>
									</td>
									<td style="width: 13%; text-align: right;"><a
										href="https://abr.ge/wqbttd" target="_blank"
										rel="noopener noreferrer"> <img class="footer-icon"
											src="/site_media/new_images/footer-ic-google-play@2x.png">
									</a> <a href="https://abr.ge/wqbttd" target="_blank"
										rel="noopener noreferrer"> <img class="footer-icon"
											src="/site_media/new_images/footer-ic-appstore@2x.png"
											style="margin-left: 15px;">
									</a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</section>
			</footer>
		</div>
	</div>

</body>
</html>