<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type=  "text/javascript">
	
	//일단 아직은 뭐 안넣고 나중에 넣는 거 처리해줄것임
	function chatPopupOpen(){
		
		//뭐 대충 나중에 회원 아이디도 보내줄 것임
		//(근데 아마 ajax로 넣어줘야할 듯(get방식으로 해야 보안 문제 없음 여튼 지긍믄 일단 간략하게))
		var popUrl = "http://localhost:9999/PstmProject/heyyowatssupp/hihi";
		var popOption = "width=500, height=700, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(option)
		window.open(popUrl, "", popOption);
	}

</script>

<body>

	<button onclick = 'chatPopupOpen()'>채팅창 오픈테스트</button>


</body>
</html>