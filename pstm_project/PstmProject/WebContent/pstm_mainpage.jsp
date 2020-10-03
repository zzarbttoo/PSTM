<%@page import="com.codachaya.dao.ReviewDao"%>
<%@page import="com.codachaya.dto.ReviewDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link href="css/maintest2.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function() {	
		var slideIndex = 0;
		var trainerIndex = 0;
		showSlides();
		showTrainer();

		//showTrainerSlides();

		function showSlides() {
			var i;
			var slides = document.getElementsByClassName("mySlides");
			var dots = document.getElementsByClassName("dot");
			for (i = 0; i < slides.length; i++) {
				slides[i].style.display = "none";
			}
			slideIndex++;
			if (slideIndex > slides.length) {
				slideIndex = 1
			}
			for (i = 0; i < dots.length; i++) {
				dots[i].className = dots[i].className.replace(" active", "");
			}
			slides[slideIndex - 1].style.display = "block";
			dots[slideIndex - 1].className += " active";
			setTimeout(showSlides, 2000); // Change image every 2 seconds

		}
		function showTrainer() {
			var j;
			var tslides = document.getElementsByClassName("trainer");
			var tdots = document.getElementsByClassName("dote");
			for (j = 0; j < tslides.length; j++) {
				tslides[j].style.display = "none";
			}
			trainerIndex++;
			if (trainerIndex > tslides.length) {
				trainerIndex = 1
			}
			for (j = 0; j < tdots.length; j++) {
				tdots[j].className = tdots[j].className.replace(" tactive", "");
			}
			tslides[trainerIndex - 1].style.display = "block";
			tdots[trainerIndex - 1].className += " tactive";
			setTimeout(showTrainer, 1000);

		}

	});
</script>

<body>
	<%@ include file="./form/pstm_header.jsp"%>


	<div class="slideshow-container">

		<div class="mySlides fade">
			<div class="numbertext">1 / 3</div>
			<img src="imgfolder/img1.jpg" style="width: 100%; height: 480px;">
			<div class="text">Caption One</div>
		</div>

		<div class="mySlides fade">
			<div class="numbertext">2 / 3</div>
			<img src="imgfolder/img2.jpg" style="width: 100%; height: 480px;">
			<div class="text">Caption Two</div>
		</div>

		<div class="mySlides fade">
			<div class="numbertext">3 / 3</div>
			<img src="imgfolder/img3.jpg" style="width: 100%; height: 480px;">
			<div class="text">Caption Three</div>
		</div>

	</div>
	<br>

	<div style="text-align: center">
		<span class="dot"></span> <span class="dot"></span> <span class="dot"></span>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="trainershow-container">
		<div class="trainer fade">
			<div class="trainertext">강사 멘트 이히히히힝 몸짱이 되자!~~에ㅔ에에에엥ㅈ베베베ㅔ</div>
			<img src="imgfolder/trainer1.jpg" style="width: 100%; height: 480px;">
		</div>
		<div class="trainer fade">
			<div class="trainertext">공부해요 여러분 운동하지마요~~~~~</div>
			<img src="imgfolder/trainer2.jpg" style="width: 100%; height: 480px;">
		</div>
		<div class="trainer fade">
			<div class="trainertext">제발 너무힘들어~~ 이거만든느것도 힘들어~~</div>
			<img src="imgfolder/trainer3.jpg" style="width: 100%; height: 480px;">
		</div>
	</div>
	<br>
	<div style="text-align: center">
		<span class="dote"></span> <span class="dote"></span> <span
			class="dote"></span>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div>
		<h1>후기 게시판</h1>
	</div>
 	
 	<div>
	<%
	ReviewDao dao = new ReviewDao();
	ReviewDto dto = dao.selectOne(18);
	 String firstimg = dto.getUploadimg();
	 String firsttitle = dto.getReviewtitle();
	 System.out.println(firstimg);
	 System.out.println(firsttitle);
	 ReviewDto dtto = dao.selectOne(2);
	 String twoimg = dtto.getUploadimg();
	 String twotitle = dtto.getReviewtitle();
	 ReviewDto ddto = dao.selectOne(1);
	 String threeimg = ddto.getUploadimg();
	 String threetitle = ddto.getReviewtitle();
	%>

		<img src =""/>
		<img src="<%= firstimg%>">
		<p><%= firsttitle %></p>
		<img src="<%= twoimg%>">
		<p><%= twotitle%></p>
		<img src="<%= threeimg%>">
		<p><%= threetitle %></p>
	</div>


	<%@ include file="./form/pstm_footer.jsp"%>

</body>
</html>