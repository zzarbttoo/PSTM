<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
  <style>
    body { 
      margin: 0px;
      padding: 0px;
    }
    .big-box {
      width: 100%;
      background-color: gray;
      height: 100vh;
      border-top: 1px solid black;
    }
  </style>
</head>
<body>

<div class="big-box"><h1>Page 1</h1></div>
  <div class="big-box"><h1>Page 2</h1></div>
  
  <script>
    var page = 2;

    $(window).scroll(function() {
        if ($(window).scrollTop() == $(document).height() - $(window).height()) {
          console.log(++page);
          $("body").append('<div class="big-box"><h1>Page ' + page + '</h1></div>');

        }
    });
  </script>



</body>
</html>