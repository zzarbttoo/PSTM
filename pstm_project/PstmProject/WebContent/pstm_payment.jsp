<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.codachaya.dto.UserDto"%>
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

.container {
	text-align: center;
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

.preintro {
	font-size: 13px;
	padding: 5px 0 15px;
	line-height: 18px;
}

.trainername {
	padding: 15px;
}

.trainermap {
	padding: 15px 0;
}
</style>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript">

	
	$(document).ready(function(){
			
		IMP.init("imp43617146");
		
	});
	

	function payment(){
		
		IMP.request_pay({
			
			pg : 'kakao',
			pay_method : 'card',
			merchant_uid : 'merchant_' + new Date().getTime(),
			name : "주문명 : 결제 테스트",
			amount : "14000",
			buyer_email : "qodbwls70@naver.com",
			buyer_name : "배유진",
			buyer_tel : "010-3333-3333",
			buyer_addr : "서울시 역삼역"
			
		}, function(rsp) {
			if (rsp.success) {
				var msg = "결제가 완료되었습니다";
				msg += "고유 아이디" + rsp.imp_uid;
				msg += "상점 거래 id" + rsp.merchant_uid;
				msg += "결제 금액" + rsp.paid_amount;
				msg += "카드 승인 번호" + rsp.apply_num;
				
			} else {
				var msg = "결제에 실패하였습니다";
				msg += "에러내용" + rsp.error_msg;
			}
			alert(msg);
		});
		
	}


	function popupOpen() {

		var popUrl = "pstm_trainmap.jsp"; //팝업창에 출력될 페이지 URL
		
		/*주소를 보내줘야한다*/
		
		var popOption = "width=500, height=700, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(option)
		window.open(popUrl, "", popOption);

	}
	
	
</script>

<body>
	<%@ include file="./form/pstm_header.jsp"%>
	<%
		UserDto trainerDto = (UserDto) request.getAttribute("trainerDto");
	System.out.println(trainerDto);
	%>
	<div class="wrapper">
		<div class="container">
			<div class="left-area">
				<div class="inner-left-area">
					<div class="trainername">
						<h2><%=trainerDto.getName()%>
							강사
						</h2>
					</div>
					<div class="trainerimage">
						<!-- 여기다가 이미지 루트 추가 -->
						<img class="trainerimgsrc" src="./resources/img/select/img1.jpg"
							style="width: 150px; height: 150px; overflow: hidden;">
					</div>
					<div class="classinformation">
						<p class="preintro"><%=trainerDto.getCareer()%></p>
						<p class="preanswer"><%=trainerDto.getMycomment()%></p>
					</div>
					<div class="trainermap">
						<button class="trainermapbutton" onclick = "popupOpen();">트레이너 위치 확인하기</button>
					</div>
				</div>
			</div>

			<div class=right-area>
				<div class="inner-right-area">
					<div class="pricetag">
						<p class="titleofpt"><%=trainerDto.getName()%> 강사님과 함께하는 pt서비스</p>
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
						<button class="payingbutton" onclick ="payment();" value = "payingdo">결제하기</button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<%@ include file="./form/pstm_footer.jsp"%>

</body>
</html>