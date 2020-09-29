<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style type = "text/css">
	
	footer a{
		text-decoration : none;
		color : inherit;
	
	}
	
	footer section p{
	
		text-align : left;
		max-width : 860px;
		float : left;
		color : #a9a9a9;
		font-size : 12px;
	
	}
	
	footer section{
	
		margin : auto;
		max-width : 1100px;
		color : #8f8f8f;
		line-height : 20px;
		
	}
	
	footer tbody{
		vertical-align : middle;
	}

	footer table{
	 	border-collapse: separate;
	 	border-spacing : 2px;
	}
	
	.footer_logo{
		
		padding-top : 18px;
		padding-left : 5%;
		width : 250px;
		height : 25px;
		font-size : 30px;
	}
	
	.footer_inner{
		
		width : 100%;
		height : 100%;
		overflow : hidden;
		padding : 35px 0 35px;
	
	}
	
	#seperate-line{
		width : 100%;
		height : 1px;
		background-color : #f2f2f2;
		margin : 38px 0;
		
	}
	
#footerlogo{
text-decoration: none;
list-style: none;
}


#footerlogo:visited{
color : #8f8f8f;
}
	
</style> 
	<footer>
		<section>
		<div class ="footer_inner">
			<div id = "seperate-line"></div>
			<table id = "footer_table">
				<tbody>
					<tr>
						<td class = "footer_bottom_td" style = "width:17%; text-align:left;">
							<h1 class = "footer_logo"><a href="pstm_mainpage.jsp" id="footerlogo">PSTM</a></h1>
						</td>	
						<td style ="width:70% text-align : left" >	
							<p class="copyright">
								<br>
								사업장소재지 (주)코더차야 서울시 역삼역 |사업자 번호:111-1111-111 |
								대표이사 kh정보교육원 
								<br>
								<a href = "#">통합약관 </a>|
								<a href ="#">이용약관 </a>|
								<a href ="#">개인정보 처리 방침</a>
								<br>
								Copyright © CoderChaya, Inc. All Rights Reserved.
							</p>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</section>
	</footer>

	

</body>
</html>