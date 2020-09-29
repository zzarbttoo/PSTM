<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.codachaya.dto.UserDto"
	import="net.sf.json.JSONObject" import="net.sf.json.JSONSerializer"%>
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
	
	function correct(jsonNormalUserDto, jsonTrainerDto){
		
		if(jsonNormalUserDto == null){
			
			alert("로그인해주세요!");
			
		}else{
		
		console.log(jsonNormalUserDto);
		console.log(jsonTrainerDto);
		
		var obj = new Object();
		var isright = null;
		var jsonData;
		var selectedamount = $(".monthoptionselect option:selected").text();
		var selectedpg = $(".payoptionselect option:selected").text(); 
		var userpay;
		var duration;
		
		alert(selectedamount);
	
		
		if(selectedamount != "가격/개월 수 선택" && selectedpg != "결제 옵션 선택"){
					
			if(selectedamount == "3개월"){
				
				userpay = 30000;
				duration = 3;
				
			}else{
				userpay = 53000;
				duration = 6;
			}
			
			IMP.request_pay({
				
				pg : 'kakao',
				pay_method : 'card',
				merchant_uid : 'merchant_' + new Date().getTime(),
				name : "주문명 :" + jsonTrainerDto["name"]+ "과 함께하는 강좌",
				amount : userpay,
				buyer_name : jsonNormalUserDto["name"],
				buyer_tel : jsonNormalUserDto["phone"],
				buyer_addr : jsonNormalUserDto["addr"]
				
			
			}, function(rsp) {
				
				if (rsp.success) {
					
					alert('imp_uid' + rsp.imp_uid);	
					$.ajax({
						url : "http://localhost:9999/aaa",
						method : "POST", 
						dataType : "text",
						data : {
							'imp_uid' : rsp.imp_uid,
							'amount' : userpay
						}
						
					}).done(function(data){
						
						if(data == 'W'){
							alert('오류입니다. 관리자에게 문의해주세요');
						}else{
							alert('결제가 완료되었습니다');
						}
						isright = data;
						alert("isRight" + isright);
						console.log(isright);
						
						obj.normalUserId  = jsonNormalUserDto["userid"];
						obj.trainerUserId = jsonTrainerDto["userid"];
						obj.imp_uid = rsp.imp_uid;
						obj.duration = duration;
						obj.purchaseType =selectedpg;
						obj.price = userpay;
						obj.isRight = isright;
						
						jsonData = JSON.stringify(obj);
						alert("objisRight" + obj.isRight);
						
						alert(jsonData);
						
						$.ajax({
							url : "paying.do",
							method : "POST",
							data :{
								"command" : "paymentres",
								"jsonData" : jsonData
							}
							
						}).done(function(data){
							
							alert(msg);
							//리다이렉트 조건 넣기
							
						});
						
					});
					
					
			
				} else {
					
					var msg = "결제에 실패하였습니다";
					msg += "에러내용" + rsp.error_msg;
				}
			});
			
		}else{
			
			alert("요구 조건들을 다시 선택해주세요");
		}
		}
	}
	
	function mapPopupOpen(jsonTrainerDto) {
		
		var addr = jsonTrainerDto["addr"];
		var detailaddr = jsonTrainerDto["detailaddr"];
		var trainername = jsonTrainerDto["name"];
		
		var popUrl = "pstm_trainermap.jsp?addr=" + addr + "&detailaddr=" + detailaddr + "&name=" + trainername; //팝업창에 출력될 페이지 URL
		var popOption = "width=500, height=700, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(option)
		window.open(popUrl, "", popOption);
		
	}
	
	
</script>

<body>
	<%@ include file="./form/pstm_header.jsp"%>
	<%
		UserDto trainerDto = (UserDto) request.getAttribute("trainerDto");
	//UserDto normalUserDto = (UserDto) request.getAttribute("normalUserDto");
	//JsonObject로 하면 javascript로 넘길 때 오류가 발생하므로 일단 하나하나 옮기는 것으로 진행후 성공 시 차후 수정
	JSONObject jsonNormalUserDto = JSONObject.fromObject(JSONSerializer.toJSON(userdto));
	JSONObject jsonTrainerDto = JSONObject.fromObject(JSONSerializer.toJSON(trainerDto));
	System.out.println(jsonTrainerDto);
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
						<button class="trainermapbutton"
							onclick='mapPopupOpen(<%=jsonTrainerDto%>)'>트레이너위치 확인하기</button>

					</div>
				</div>
			</div>

			<div class=right-area>
				<div class="inner-right-area">
					<div class="pricetag">
						<p class="titleofpt"><%=trainerDto.getName()%>
							강사님과 함께하는 pt서비스
						</p>
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
							<select class="monthoptionselect">
								<option value="selectmonthoption" selected="selected">가격/개월 수 선택</option>
									
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
							<select class="payoptionselect">
								<option value="selectmethodoption" selected="selected">결제 옵션 선택</option>
								<option value="kakaopay">카카오페이</option>
							</select>
						</div>
					</div>
					<div class="optionpack">
						<button class="payingbutton"
							onclick='correct(<%=jsonNormalUserDto%>, <%=jsonTrainerDto%>)'>결제하기</button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<%@ include file="./form/pstm_footer.jsp"%>

</body>
</html>