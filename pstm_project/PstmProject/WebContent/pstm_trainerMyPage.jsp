<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
	if(session.getAttribute("login") ==null){
		String result = "<script> alert('로그인을 먼저 해주세요!'); location.href='pstm_login.jsp'; </script> ";
		response.getWriter().append(result);
	}
%>


<body>
<%@ include file="./form/pstm_header.jsp"%>

<h1>강사 마이페이지</h1>
<br />
<br />
<h3>회원 정보 마이페이지</h3>

		<table border="1">
			
			<col width="150px;" />
			<col width="150px;" />
			<col width="300px; " />
			<col width="100px;" />
		
				<tr>
					<th>회원사진</th>
					<th>회원정보</th>
					<th>그래프</th>
					<th>화상회의</th>				
				</tr>
			
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td align="center">
					<input type="button" value="회원관리" onclick="location.href='pstm_normalUserManagement.jsp'" style="width:90px;height:50px;"/>
					<input type="button" value="webRTC" onclick="" style="width:90px;height:50px; "/>					
					</td>
				</tr>
				
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td align="center">
					<input type="button" value="회원관리" onclick="location.href='pstm_normalUserManagement.jsp'" style="width:90px;height:50px;"/>
					<input type="button" value="webRTC" onclick="" style="width:90px;height:50px;"/>
					</td>
				</tr>
				
				
		</table>


<%@ include file="./form/pstm_footer.jsp"%>
</body>
</html>