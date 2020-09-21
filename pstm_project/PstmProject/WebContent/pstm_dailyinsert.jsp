<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	function upload(e){
		e.preventDefault();
		
		var frm = document.getElementById("createupload");
		frm.method = "POST";
		frm.enctype = "multipart/form-data";
		var fileData = new FormData(frm);
		
		jQuery.ajax({
			type:"POST",
			enctype:"multipart/form-data",
			cache:false,
			url:"daily.do",
			data:fileData,
			async:false,
			contentType:false,
			processData:false,
			dataType:"json",
			success:function(msg){
				jQuery("#createupload")[0].reset();
			},
			fail:function(){
				;
			}
		});
	}
</script>





<link href="css/pstm_daily.css" rel="stylesheet" type="text/css">
</head>
<body>


	<%@include file="./form/pstm_header.jsp"%>


	<form method="post" enctype="multipart/form-data" id="createupload" action="daily.do">
		<input type="hidden" name="command" value="insertres">
		 파일 : <input type="file" name="uploadimg" id="imput_img"> 
		 먹은 시간 : <input type="text" name="timeeat">
		 <button onclick="upload(event)">이미지 업로드</button>
		 
		 
		 
		 
		 <input type="submit" value="upload">
	</form>


	<%@include file="./form/pstm_footer.jsp"%>
</body>
</html>