<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript">

function popupOpen(){

	var popUrl = "map.html";	//팝업창에 출력될 페이지 URL

	var popOption = "width=500, height=700, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)

		window.open(popUrl,"",popOption);

	}


</script>



<a href="javascript:popupOpen();" > 지도 뿅! </a>



</body>
</html>