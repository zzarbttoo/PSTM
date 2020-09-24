<%@page import="com.codachaya.dto.LessonDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


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

<%
	List<UserDto> List = (List<UserDto>) request.getAttribute("userlist");
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
				
				<%
					if (List.size() == 0) {
				%>
				<tr>
					<td colspan="3">------작성된 글이 존재하지 않습니다.------</td>
				</tr>
								
				<%				
					}else{
						
					for( UserDto user : List){
				%>
					<tr>
						<td><%=user.getUploadimg() %></td>
						<td><%=user.getName() %></td>
						<td align="center">
							<input type="button" value="회원관리" onclick="location.href='pstm_normalUserManagement.jsp'"/>
							<input type="button" value="webRTC" onclick=""/>
						</td>
					</tr>				
				<%
						}
					}
				%>
				
			
		
		
				
		</table>


<%@ include file="./form/pstm_footer.jsp"%>
</body>
</html>