<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
.wrapper {
	min-width: 1100px;
	padding: 30px;
}

.left-area {
	width: 640px;
	text-align: center;
	display: inline-block;
	border: 1px solid grey;
}

.right-area {
	width: 440px;
	display: inline-block;
	vertical-align: top;
	margin-left: 20px;
}

.titleofpt {
	font-size: 20px;
	font-weight: bold;
	color: black;
	margin: 0px 0px 30px;
}

.inner-right-area {
	background-color: white;
	border: 1px solid grey;
	padding: 30px 30px 40px;
}

.inner-left-area {
	padding: 30px 30px 22px;
}

.right_menu {
	margin: 5px 0 10px;
}

.right_menu>strong {
	font-size: 15px;
}

.optionpack {
	margin-top: 20px;
	border-top: 1px solid grey;
}

.payingbutton {
	margin-top: 30px;
}

.payingbutton {
	width: 100%;
}

.trainerimgsrc {
	border-radius: 50%;
}

.preintro{
	font-size : 13px;
	padding : 5px 0 15px;
	line-height : 18px;
}

.trainername{
	padding : 15px;
}

.trainermap{
	
	padding : 15px 0;
}
</style>
<body>
	<%@ include file="./form/pstm_header.jsp"%>
	<div class="wrapper">
		<div class="container">
			<div class="left-area">
				<div class="inner-left-area">
					<div class = "trainername">
						<h2>이동헌 강사</h2>
					</div>
					<div class="trainerimage">
						<img class="trainerimgsrc" src="./resources/img/select/img1.jpg"
							style="width: 150px; height: 150px; overflow: hidden;">
					</div>
					<div class="classinformation">
						<p class = "preintro">現) KH헬스장 전임강사</p>
						<p class = "preanswer">함께하겠습니다. 포기하지 맙시다</p>
					</div>
					
					<div class = "trainermap">
						<button>트레이너 위치 확인하기</button>
					</div>
				</div>
				</div>

		<div class=right-area>
			<div class="inner-right-area">
				<div class="pricetag">
					<p class="titleofpt">이동헌 강사님과 함께하는 pt서비스</p>
					<div class="pricebox">
						<span class="priceintroduce">월 53000원</span> <span
							class="introducemonth">(6개월기준)</span> <span
							class="priceintroduce"> / 월 30000원</span> <span
							class="introducemonth">(3개월기준)</span>
					</div>
				</div>
				<div class="optionpack">
					<div class="right_menu">
						<strong>개월 옵션 선택</strong>

					</div>
					<div class="right_menu menucore" style="width: 100%">
						<select>
							<option value="selectmonthoption" selected="selected">가격/개월
								수 선택</option>
							<option value="3month">3개월</option>
							<option value="6month">6개월</option>
						</select>
					</div>
				</div>
				<div class="optionpack">
					<div class="right_menu">
						<strong>결제 옵션 선택</strong>
					</div>
					<div class="right_menu menucore">
						<select>
							<option value="selectmethodoption" selected="selected">결제
								옵션 선택</option>
							<option value="kakaopay">카카오페이</option>
						</select>
					</div>
				</div>
				<div class="optionpack">
					<button class="payingbutton">결제하기</button>
				</div>
			</div>
		</div>
	</div>
	</div>
	


	<%@ include file="./form/pstm_footer.jsp"%>

</body>
</html>