<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	String addr = request.getParameter("addr");
String detailaddr = request.getParameter("detailaddr");
String trainerName = request.getParameter("name");
String wholeaddr = "'" + addr+ " " + detailaddr + "'";
System.out.println(wholeaddr);

%>

<body>

	<div id="map" style="width: 500px; height: 700px;"></div>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=18de9bf1d4dd5e0fc3db2f13a73abc7d&libraries=services,clusterer,drawing"></script>
	<script type="text/javascript">


	var Container = document.getElementById('map');	//지도를 담을 영역의 DOM 레퍼런스
	
	var options = {	//지도를 생성할 때 필요한 기본 옵션
			
			center: new kakao.maps.LatLng(37.345479, 126.736520),	//지도의 중심좌표
			level: 3	// 지도의 레벨(확대, 축소 정도)
	};
	
	// 지도를 생성합니다    
	var map = new kakao.maps.Map(Container, options); 

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();	
	
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(<%=addr%>, function(result, status) {

		  // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {

	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });

	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">강사의 위치 : <%=wholeaddr%></div>'
	        });
	        infowindow.open(map, marker);

	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	    } 
	});

</script>
</body>
</html>