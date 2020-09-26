<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page 
	import="com.codachaya.dto.UserDto" 
	import="java.util.List"
	import="java.util.ArrayList" 
	import="com.codachaya.biz.*"
	import = "com.codachaya.util.*"
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type = "text/javascript">

	var popUrl = "http://localhost:9999/PstmProject/trainerchating";
	var popOption = "width=500, height=700, resizable=no, scrollbars=no, status=no;"; //팝업창 옵션(option)
	var popUpPage = null;
	//trainerUserid를 나타나게 하는 지 모르겠다
	function gotochat(trainername, traineruserid){
	 
		popUpPage=window.open(popUrl, "",popOption);	
	
		$.ajax({
			
			url:popUrl,
			method : "GET",
			data : {
				
				'trainername' : trainername,
				'trainernum' : traineruserid,
				'istrainer' : 'false'
			}
		});
		
		
	}
	
	
</script>

<style type="text/css">
.wrapper {
	min-width: 1100px;
}

.trainerintroduce {
	margin: 0 auto;
	width: 800px;
}

.trainerintroduce>h1 {
	margin: 30px;
}

.innerintroduce {
	border: 2px solid grey;
	height: auto;
}

.trainerimg {
	margin: 50px;
	border-radius: 50%;
}

.subscription_navigation {
	padding-top: 100px;
	text-align: center;
}

.trainerbox{
	
	text-align : center;
}

.right-area{
	
	display : inline-block;
	width: 500px;
}

.lift-area{

	display : inline-block;
	width:300px;
	
}

.gotochatting{
	
	padding  : 10px;
	margin : 10px;
	width : 150px;
	height: 40px;
	
}

.gotoPaying{
	
	padding : 10px;
	margin : 10px;
	width  : 150px;
	height : 40px;
	
}

</style>

<body>


	<%@ include file="./form/pstm_header.jsp"%>

	<%
			PagingUtil pagination = (PagingUtil) request.getAttribute("pagination");
			List<UserDto> trainerList = (ArrayList<UserDto>) request.getAttribute("trainerList");
			
			if(pagination != null){
			System.out.println(pagination);
			System.out.println("받은 페이지 번호"+pagination.getCurrentPageNo());
			
			}
			
			//임시로 생성 <- 페이지간 연결 후 삭제하기
			if(pagination == null){
			PayingBiz biz = new PayingBiz();
			pagination = new PagingUtil(1, 3);
			pagination.setRecordsPerPage(3);
			pagination.setNumberOfRecords(biz.getTrainerCount());
			pagination.makePaging();
			trainerList = biz.selectTrainerPaging(0, 3);
			}
			
			System.out.println("jsp 현재 페이지" + pagination.getCurrentPageNo());
	%>


	<div class="wrapper">
		<div class="trainerintroduce">

			<h1>강사 소개</h1>

			<%
				for (int i = 0; i < trainerList.size(); i++) {

				System.out.println(i);
				System.out.println(trainerList.get(i));
				System.out.println(trainerList.get(i).getName());
				
				
			%>
			<div class="innerintroduce">
				<div class="body">
					<div class="trainerbox">
						<div class="left-area">
							<img class="trainerimg"
								src="<%="./resources/img/select/img" + i + ".jpg"%>"
								style="width: 150px; height: 150px;" />
						</div>
						<div class="right-area">
							<div class="trainername"><%=trainerList.get(i).getName()%> 강사</div>
							<div class = "trainercomment">강사 한마디  <%= trainerList.get(i).getMycomment()%> </div>
							<div class = "trainercareer">강사 경력 <%=trainerList.get(i).getCareer()%></div>
							<div><input class ="gotoPaying" type = "button" onclick="location.href='paying.do?command=payment&trainerUserId=<%=trainerList.get(i).getUserid()%>'" value = "강의 신청하기"></div>
							<div><input class ="gotochatting" type = "button" onclick = "gotochat('<%=trainerList.get(i).getName()%>',<%=trainerList.get(i).getUserid()%>)" value = "강사와 상담하기"></div>
						</div>
					</div>
				</div>
			</div>
			<%
				}
			%>
			<div class="subscription_navigation">
				<%
					for (int i = pagination.getStartPageNo(); i <= pagination.getEndPageNo(); i++) {
					System.out.println(i);
					System.out.println(pagination.getCurrentPageNo());
					if (i == pagination.getCurrentPageNo()) {
				%>

				<%=i%>
				<%
					} else { 
				%>

				<a href="paying.do?command=subscription&pages=<%=i%>"><%=i%></a>

				<%
					}
				}
				%>
			</div>
		</div>
	</div>


	<%@ include file="./form/pstm_footer.jsp"%>
</body>
</html>