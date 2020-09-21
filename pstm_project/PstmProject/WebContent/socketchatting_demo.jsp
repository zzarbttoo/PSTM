<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type=  "text/javascript">
	
	//일단 아직은 뭐 안넣고 나중에 넣는 거 처리해줄것임
	function chatPopupOpen(){
		
		//결국 쿼리스트링으로 가능했다
		var popUrl = "http://localhost:9999/PstmProject/trainerchating?trainernum=4&trainername='user'&userid=3";
		var popOption = "width=500, height=700, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(option)
		var popUpPage = window.open(popUrl, "",popOption);
		
		
		/*
		var popUrl = "http://localhost:9999/PstmProject/trainerchating";
		var popOption = "width=500, height=700, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(option)
		var popUpPage = window.open("about:blank", "",popOption);
		
		$.ajax({
			
			url : "http://localhost:9999/PstmProject/heyyowatssupp/hihi",
			method : "POST",
			data : {
				
				"trainernum" : "1",
				"trainername" : "djdh"
					
			},
			success:eventSuccess,
			error:function(){
				console.log("fail");
				alert("에러!");
			}

			
		});
		
		function eventSuccess(data){
			popUpPage.location.href = popUrl;
		}
		*/
	}

</script>

<body>

	<button onclick = 'chatPopupOpen()'>채팅창 오픈테스트</button>


</body>
</html>