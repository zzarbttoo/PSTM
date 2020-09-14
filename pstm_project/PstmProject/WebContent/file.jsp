<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getSession().getServletContext().getRealPath("img");
int size = 1024 * 1024 * 5;
String file = "";
String realfile = "";

try {
	MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
	Enumeration files = multi.getFileNames();
	String str = (String) files.nextElement();

	file = multi.getFilesystemName(str);
	realfile = multi.getOriginalFileName(str);

} catch (Exception e) {

}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<img src="img/<%=file%>">




</body>
</html>