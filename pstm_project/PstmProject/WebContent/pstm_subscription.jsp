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

.subscription_navigation{

	padding-top : 100px;
	text-align : center;
}
</style>
<body>


	<%@ include file="./form/pstm_header.jsp"%>

	<%
		//더미 데이터
	String[][] list = { { "img1", "name", "age", "where" }, { "img2", "배유진", "헬창", "어디삼" }, { "img3", "그러니", "어디가", "집에가" },
			{ "img4", "왜그러니", "siasa", "hoh,o" }, { "img5", "왜그러니", "siasa", "hoh,o" },
			{ "img6", "왜그러니", "siasa", "hoh,o" }, { "img7", "왜그러니", "siasa", "hoh,o" },
			{ "img8", "왜그러니", "siasa", "hoh,o" } };
	String strPg = request.getParameter("pg"); //현제 페이지 

	int rowSize = 3; //한 페이지에 몇개의 글 출력	
	int pg = 1; //이동할 페이지 
	int from; //시작 페이지
	int to; //마지막 페이지 
	int total; //총 게시물 수 
	int allPage; //전체 페이지 수
	int block; // 한번에 출력될 페이징 수
	int fromPage; //출력되는 페이지 중 첫번째
	int toPage; //출력되는 페이지 중 마지막

	//1pg 가 아닐 경우
	if (strPg != null) {

		pg = Integer.parseInt(strPg);

	}

	//시작, 끝 페이지를 정해준다(한페이지)
	from = (pg * rowSize) - (rowSize - 1);
	to = (pg * rowSize);

	if (to > list.length) {

		to = list.length;

	}

	total = list.length; //dao 사용할 경우 int totla = dao.getTotal();
	allPage = (int) Math.ceil(total / (double) rowSize);

	/*
	//전체 페이지가 아니라 일부 페이지씩 출력하려면 block 지정
	block = 2;
	fromPage = ((pg-1)/block*block) + 1;
	toPage = ((pg-1)/ block * block) + block;

	*/
	%>


	<div class="wrapper">

		<div class="trainerintroduce">

			<h1>강사 소개</h1>

			<%
				for (int i = from - 1; i < to; i++) {
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
							<div class="trainername">"강사 이름" 강사</div>
						</div>
					</div>
				</div>
			</div>

			<%
				}
			%>
		</div>
		<div class="subscription_navigation">
			<%
				for (int i = 1; i <= allPage; i++) {

				if (i == pg) {
			%>

				<%=i %>


			<%
				} else {
			%>
	
				<a href ="paying.do?command=subscription&pg=<%=i %>"><%=i %></a>

			<%
				}
			}
			%>
		</div>
	</div>


	<%@ include file="./form/pstm_footer.jsp"%>
</body>
</html>