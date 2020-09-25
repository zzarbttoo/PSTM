<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html; UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link href="css/pstm_slidecss.css"
	rel="stylesheet"
	type="text/css">
<body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

var slideIndex = 1;
showDivs(slideIndex);

function plusDivs(n) {
    showDivs(slideIndex += n);
}

function showDivs(n) {
    var i;
    var x = document.getElementsByClassName("mySlides");
    if (n > x.length) {slideIndex = 1}
    if (n < 1) {slideIndex = x.length} ;
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    x[slideIndex-1].style.display = "block";
}
	


</script>
	<%@ include file="./form/pstm_header.jsp"%>
	<div class="main">
	<div class="slide_one">
		<div class="slide_two">
			<div style="text-align: center;" class="slideimg"><img class="mySlides" src="imgfolder/img1.jpg" style="z-index:1; opacity: 1;"></div>
			<div style="text-align: center;" class="slideimg"><img class="mySlides" src="imgfolder/img2.jpg" style="z-index:1; opacity: 1;"></div>
			<div style="text-align: center;" class="slideimg"><img class="mySlides" src="imgfolder/img3.jpg" style="z-index:1; opacity: 1;"></div>
		</div><!-- slide_two -->
		<div>
			<a id="prebutton" onclick="plusDivs(-1)" style="z-index:10;"><img src="imgfolder/left.png" style="height: 20px;"></a>
			<a id="nextbutton" onclick="plusDivs(-1)" style="z-index:10;"><img src="imgfolder/right.png" style="height: 20px;"></a>
		</div>
	</div><!-- slide_one -->
	</div>
		<div><img src="imgfolder/cutedog.jpg" id="coatchimg"></div>
		<div><img src="imgfolder/rioncacao.png" id="hugi"></div>
		
		<%@ include file="./form/pstm_footer.jsp"%>

	
	
</body>
</html>
