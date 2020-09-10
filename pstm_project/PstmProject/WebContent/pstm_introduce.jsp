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
	text-align : center;
}

.title {
	display: inline-block;
}

.content {
	
}

.subcontent{
	padding: 30px;
	margin-top : 20px;
}

.subcontent_text{
	margin-top : 20px;
}

.subcontent_text > p{
	
	line-height: 20px;
	padding-top : 8px;

}

</style>
<body>
	<%@ include file="./form/pstm_header.jsp"%>


	<div class="wrapper">
		<div class="inner-wrapper">
			<div class="title">
				<h1>PSTM</h1>
				<h3>온라인으로 이용하는 PT서비스를 소개합니다.</h3>
			</div>

			<div class="content">
				<div class="subcontent">
					<div class ="subcontent_img">
					<img src="./resources/img/select/health.jpg"
						style="width: 1100px; height: 800px;"></div>
					<div class ="subcontent_text">
					<p>집에 가고 싶을 때는 어떻게 해야할까요 </p>
					<p>하지만 전 화면 설계를 열심히 할거에요 으하하핳</p>
					<p>둠칫둠칫 우하하하얍얍얍얍얍욥욥욥욥욥욥욜ㄹ롤로롤ㄹㄹㄹ로로롤ㄹ</p>
					<p>얍얍욥욥 동해물과 백두산이 마르고 닳도록하나님이보우하사우리나라만세ㅔ</p>
					<p>무궁화 삼천리 화려강산 근데 사실 애국가는 친일파가 지었다고 하는데 정말 말세야</p>
					<p>그런건 모르겠고 난 집에 가고 싶단 말이야</p></div>
					
				</div>
				<div class ="subcontent">
					<div class ="subcontent_text">
						<h2>그럴 때 필요한 것이 PSTM 입니다</h2>
						<br>
						<p>호잇호잇 얍얍 욥욥 함께합시다 넵넵뇹뇹 낙낙낙낙</p>
						<p>함께 하겠습니다.</p>
					</div>
					<div class ="subcontent_button">
						<br><br>
						<button style ="width : 280px; height : 30px;"> 강사 소개로 가기</button>
					</div>
				</div>

	
			</div>
		</div>
	</div>

	<%@ include file="./form/pstm_footer.jsp"%>
</body>
</html>