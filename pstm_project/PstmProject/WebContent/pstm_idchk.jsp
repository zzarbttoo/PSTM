<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	onload = function() {
		var id = opener.document.getElementsByName("id")[0].value;
		document.getElementsByName("inid")[0].value = id;
	}

	function idConfirm(bool) {
		if (bool == "true") {
			opener.document.getElementsByName("id")[0].title="y";
			opener.document.getElementsByName("password")[0].focus();
		} else {
			opener.document.getElementsByName("id")[0].focus();
		}
		self.close();
	}
</script>
</head>
<%
	String idNotUse = request.getParameter("idNotUse");
%>
<body>

<table border="1">
		<tr>
			<td><input type="text" name="inid" readonly="readonly" /></td>
		</tr>

		<tr>
			<td><%=idNotUse.equals("true") ? "아이디 생성 가능" : "중복된 아이디가 존재"%></td>
		</tr>

		<tr>
			<td><input type="button" value="확인" onclick="idConfirm('<%=idNotUse%>');" /></td>
			<!-- 싱글쿼테이션을 안해주면 변수취급 싱글쿼테이션하면 문자 취급 -->
		</tr>


	</table>

</body>
</html>