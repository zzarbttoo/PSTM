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
	width: 1100px;
}

.container{

	text-align : center;
}

.chatbot {
	float: left;
	padding: 0 100px;
	width: 400px;
	display: inline-block;
}


.chatbot_advice {
	padding: 100px 50px;
	width: 400px;
	display: inline-block;
	text-align: center;
}

.trainer_advice {
	padding: 100px 50px;
	width: 400px;
	display: inline-block;
	text-align: center;
}

.chatbot_advice>h1 {
	margin-bottom: 25px;
}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">

	var popUrl = "http://localhost:9999/PstmProject/trainerchating";
	var popOption = "width=500, height=700, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(option)
	var popUpPage = null;
	
	//trainerUserid를 나타나게 하는 지 모르겠다
	function gotochat(trainername, traineruserid) {

		
		
		alert(trainername + traineruserid);
		popUpPage = window.open(popUrl, "", popOption);

		$.ajax({

			url : popUrl,
			method : "GET",
			data : {

				'trainername' : trainername,
				'trainernum' : traineruserid,
				'istrainer' : 'true'
			}
		});
		
	

	}
</script>
<body>

	<%@ include file="./form/pstm_header.jsp"%>
	
	<div class="wrapper">
		<div class="container">
			<%
				if (userdto != null && userdto.getUsertype().equals("T")) {
				System.out.println("나는 트레이너다");
			%>

			<div class="trainerChat"></div>
			<div class="trainer_advice">
				<p>버튼을 클릭하면</p>
				<p>강사님의 채팅방으로 이동 됩니다"</p>
				<button onclick="gotochat('<%=userdto.getName()%>',<%=userdto.getUserid()%>)">채팅방 입장하기</button>
			</div>

			<%
				} else {
			%>
			<div class="chatbot">
				<iframe width="350" height="430"
					src="https://console.dialogflow.com/api-client/demo/embedded/2a5c55e5-3134-4bdc-8e8a-90d59296c43a">
				</iframe>
			</div>
			<div class="chatbot_advice">
				<h1>챗봇에게 물어보세요</h1>
				<p>챗봇에게 물어보고 싶은 질문을 물어보세요!</p>
				<br>
				<h3>강사님과 직접 상담하고 싶으세요?</h3>
				<br>
				<p>강사님 이름을 입력하면</p>
				<p>강사님의 채팅방으로 이동 됩니다"</p>
			</div>
			<%
				}
			%>
		</div>
	</div>

	<%@ include file="./form/pstm_footer.jsp"%>


</body>
</html>