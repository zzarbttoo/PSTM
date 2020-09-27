<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style type=  "text/css">
	
	.Chattrainner{
	
		width : 300px;
		height : 400px;
		border : 2px solid #000;
	
	}
	
</style>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type=  "text/javascript">
	
	//일단 아직은 뭐 안넣고 나중에 넣는 거 처리해줄것임
	function chatPopupOpen(){
		
		//결국 쿼리스트링으로 가능했다
		//채팅방 이름 trainer 
		var popUrl = "http://localhost:9999/PstmProject/trainerchating?istrainer=false&trainernum=4&trainername='user'&userid=null";
		var popOption = "width=500, height=700, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(option)
		var popUpPage = window.open(popUrl, "",popOption);
		
	}
function chatPopupOpen2(){
		
		//결국 쿼리스트링으로 가능했다
		var popUrl = "http://localhost:9999/PstmProject/trainerchating?trainernum=5&trainername='user'&userid=4&istrainer=false";
		var popOption = "width=500, height=700, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(option)
		var popUpPage = window.open(popUrl, "",popOption);
		
}

function chatPopupOpen3(){
	
	//결국 쿼리스트링으로 가능했다
	var popUrl = "http://localhost:9999/PstmProject/trainerchating?trainernum=5&trainername='user'&userid=3&istrainer=true";
	var popOption = "width=500, height=700, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(option)
	var popUpPage = window.open(popUrl, "popUpWin",popOption);
	
}


</script>

<body>

	<button onclick = 'chatPopupOpen()'>채팅창 오픈테스트</button>
	<button onclick = 'chatPopupOpen2()'>채팅창 오픈테스트</button>
	<button onclick = 'chatPopupOpen3()'>채팅창 오픈테스트</button>
	
	<div class = "Chattrainner" target = "popUpWin">
	</div>


</body>
</html>