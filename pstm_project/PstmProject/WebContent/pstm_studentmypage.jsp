<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=UTF-8");%>
 
 
<%
	if(session.getAttribute("login") == null && session.getAttribute("nLogin") == null){
		String result = "<script> alert('로그인을 먼저 해주세요!'); location.href='pstm_login.jsp'; </script> ";
		response.getWriter().append(result);
	}
%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	h1{
		height: 100px;
	}
	#topMenu{
		height: 100px;
		width: 850px;
		text-align: center;
		margin: auto;
	}
	#topMenu ul li{
		list-style : none;
		float: left;
		line-height: 30px;
		text-align: center;
		vertical-align: middle;
		
		
	}
	
	#topMenu .menulink{
		text-decoration: none;
		display: block;
		width: 150px;
		font-size: 12px;
		font-weight: bold;
		color: black;
	}
	table{
		margin: auto;
		text-align: center;
	}
</style>

</head>
<body>
		
	
	<%@include file="./form/pstm_header.jsp" %>
	<h1><% if(userdto != null){
		
		%>
			<%=userdto.getName() %>님의 마이페이지
		<%
			}
		%>
		</h1>
		
	
	
	<nav id="topMenu">
		<ul>
			<li><a class="menulink" href="#">인바디</a></li>
			<li><a class="menulink" href="#">강좌</a></li>
			<li><a class="menulink" href="#">정보변경</a></li>
			<li><a class="menulink" href="daily.do?command=selectres">식단/운동기록</a></li>
		</ul>
	</nav>
	
	
	<table border="1">
	<col width="100">
	<col width="100">
	<col width="100">
	<col width="100">
	<col width="100">
	<col width="100">
	<col width="100">
		<tr>
			<th>일</th>
			<th>월</th>
			<th>화</th>
			<th>수</th>
			<th>목</th>
			<th>금</th>
			<th>토</th>
		</tr>
		<tr>
			<td>1</td>
			<td>2</td>
			<td>3</td>
			<td>4</td>
			<td>5</td>
			<td>6</td>
			<td>7</td>
		</tr>
		<tr>
			<td>8</td>
			<td>9</td>
			<td>10</td>
			<td>11</td>
			<td>12</td>
			<td>13</td>
			<td>14</td>
		</tr>
		<tr>
			<td>15</td>
			<td>16</td>
			<td>17</td>
			<td>18</td>
			<td>19</td>
			<td>20</td>
			<td>21</td>
		</tr>
		<tr>
			<td>22</td>
			<td>23</td>
			<td>24</td>
			<td>25</td>
			<td>26</td>
			<td>27</td>
			<td>28</td>
		</tr>
	</table>
	
	<h1>구매정보</h1>
		<table border="1">
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="100">
		<col width="100">
			<tr>
				<th>결제일</th>
				<th>구매상품</th>
				<th>결재금액</th>
				<th>종강일</th>
				<th>상태</th>
				<th>강의</th>
			</tr>
			<tr>
				<td>2020-09-10</td>
				<td>준범강의</td>
				<td>290,000</td>
				<td>2020-09-11</td>
				<td>O</td>
				<td><input type="button" value="강의"></td>
			</tr>
		</table>

		<%@include file="./form/pstm_footer.jsp" %>
</body>
</html>