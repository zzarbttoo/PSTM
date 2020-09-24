F<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<body>

	<script type="text/javascript">
	<% 
	List<String> list = (ArrayList<String>) request.getAttribute("result");
	System.out.println(request.getAttribute("result"));
	System.out.println(list);
	if (list != null) {
	for (int i = 0; i < list.size(); i++) {
	%>
		$.ajax({
			method : "POST",
			url : "http://koreanfood.rda.go.kr/kfi/openapi/service",
			data : {
				apiKey : "20200917103617HS0PNAS5YSH17TMJEO",
				serviceType : "AA002",
				//fdGrupp :"I",
				fdEngNm : "<%= list.get(i)  %>"
			}, 

		}).done(function(msg) {
			console.log(msg);
			var calorie = msg.service.list[0].irdnt[0].irdnttcket[0].contInfo;
			alert(calorie);
		})
	<%
		}
	}
%>
		
	</script>

</body>
</html>