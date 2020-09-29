<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
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
<script>
		function kcalcount(){
			var fileValue = $(".excelFile").val().split("\\");
			var filename = fileValue[fileValue.length-1];
			$.ajax({
				  method: "POST",
				  url: "daily.do?command=vision&filename="+filename,
				  contentType : "application/text; charset=UTF-8 ",
				  dataType : "json",
				  success : function(data) {
					  
					  //alert("성공했지비"+data)
					 var obj = JSON.stringify(data);
					  
					 var foodlist = JSON.parse(obj);
					 //alert("아아"+foodlist);
					 console.log(foodlist);
					var foodnames = foodlist.toString().split(",");
				for(var i = 0; i < foodnames.length; i++){
							$("#foodselect").append("<option value="+foodnames[i]+">"+foodnames[i]+"</option>");
						} 
				  },
				//  complete : function(data){
				//	  alert("통신은 실패했지만 완료됬지비"+data)
				//  },
				  error:function(request,status,error){
					    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					   }
				})
				//.done(function( msg ) {
				    //alert( "Data Saved: " + msg );
				  //});
		}
		function foodnamesend(){
			var foodname = $("#foodselect option:selected").val();
			$.ajax({
				method : "POST",
				url : "http://koreanfood.rda.go.kr/kfi/openapi/service",
				data : {
					apiKey : "20200917103617HS0PNAS5YSH17TMJEO",
					serviceType : "AA002",
					//fdGrupp :"I",
					fdEngNm : ""+foodname+""
				}, 
			}).done(function(msg) {
				console.log(msg);
				var kcal = msg.service.list[0].irdnt[0].irdnttcket[0].contInfo;
				$("#kcalall").append("음식이름 :" +foodname+"<br> 칼로리 : "+kcal);
				
			})
		}
		function preview(){
			var fileValue = $(".excelFile").val().split("\\");
			var filename = fileValue[fileValue.length-1];
			
			$("#view").attr("src","imgfolder/"+filename);
			alert(filename);
		}
		
		
</script>
<script type="text/javascript">
var upload = document.querySelector("#upload");
var preview = document.querySelector("#preview");

upload.addEventListener("change",function (e) {
    var get_file = e.target.files;

    var image = document.createElement("img");

    /* FileReader 객체 생성 */
    var reader = new FileReader();

    /* reader 시작시 함수 구현 */
    reader.onload = (function (aImg) {
        console.log(1);

        return function (e) {
            console.log(3);
            /* base64 인코딩 된 스트링 데이터 */
            aImg.src = e.target.result
        }
    })(image)

    if(get_file){
        /* 
            get_file[0] 을 읽어서 read 행위가 종료되면 loadend 이벤트가 트리거 되고 
            onload 에 설정했던 return 으로 넘어간다.
            이와 함게 base64 인코딩 된 스트링 데이터가 result 속성에 담겨진다.
        */
        reader.readAsDataURL(get_file[0]);
        console.log(2);
    }

    preview.appendChild(image);
})
</script>




<link href="css/pstm_daily.css" rel="stylesheet" type="text/css">
</head>
<body>


	<%@include file="./form/pstm_header.jsp"%>


	<form method="post" enctype="multipart/form-data" id="createupload" action="daily.do">
		<input type="hidden" name="command" value="insertres">
		 파일 : <input type="file" name="uploadimg"  id="upload" class="excelFile">
		 <button onclick="upload(event)">이미지 업로드</button> 
		 <div id="preview">
		 
		 </div>
		 음식 : <input type="text" name="result"> 
		 먹은 시간 : <input type="text" name="timeeat">
		 
		칼로리 : <input type="text" name="kcal">

		 <input type="submit" value="upload">
		 		
	</form>
	<button onclick="preview()">미리보기</button>
	 <img id="view">
	<div>
		<button onclick="kcalcount();">계산하기</button>
	</div>
	
	<div>
		내가먹은 음식 선택 : 
		<select id="foodselect">
		</select>
		<button onclick="foodnamesend();">선택</button>
	</div>
	 <div><p id="kcalall"></div>

	<%@include file="./form/pstm_footer.jsp"%>
</body>
</html>