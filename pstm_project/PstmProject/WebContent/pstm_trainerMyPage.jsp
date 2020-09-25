<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	input {
	
		width:90px;height:50px;
	
	}

</style>
</head>

<%
	if(session.getAttribute("login") == null){
		String result = "<script> alert('로그인을 먼저 해주세요!'); location.href='pstm_login.jsp'; </script> ";
		response.getWriter().append(result);
		
	}
	
	
%>


<body>
<%@ include file="./form/pstm_header.jsp"%>

<h1><%=dto.getName() %> 강사 마이페이지</h1>
<br />
<br />
<h3>회원 정보 리스트</h3>

		<table border="1">
			
			<col width="150px;" />
			<col width="300px;" />
			<col width="100px;" />
		
				<tr>
					<th>회원사진</th>
					<th>회원정보</th>
					<th>화상채팅</th>				
				</tr>
				
				<c:choose>
				<c:when test="${empty userlist }">
					<tr>
						<td colspan="3" align="center">---------신청한 회원이 존재하지 않습니다.-------</td>
					</tr>
				</c:when>				
				<c:when test="${userlist }">
					<c:forEach items="${userlist }" var="dto">
						<tr>
							<td>${dto.uploadimg }</td>
							<td>${dto.name }
								 
							<input type="button" value="회원관리" onclick="location.href='pstm_normalUserManagement.jsp'" />
							</td>
							<td align="center">
					<input type="button" value="webRTC" onclick="" />					
							</td>
						</tr>
					</c:forEach>				
				</c:when>
				<c:otherwise>
					<c:forEach items="${list }" var="duo">
							<td>${duo.profile_image }</td>
							<td>${duo.name } </td>
							<td align="center">
					<input type="button" value="회원관리" onclick="location.href='pstm_normalUserManagement.jsp'" style="width:90px;height:50px;"/>
					<input type="button" value="webRTC" onclick="" style="width:90px;height:50px; "/>					
							</td>					
					</c:forEach>				
				</c:otherwise>
			
			
			</c:choose>
				
				
			
		
		
				
		</table>


<%@ include file="./form/pstm_footer.jsp"%>
</body>
</html>