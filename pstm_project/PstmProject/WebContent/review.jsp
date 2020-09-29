<%@page import="com.codachaya.dao.ReviewDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="com.codachaya.dto.ReviewDto" import="java.util.List"
   import="java.util.ArrayList" import="com.codachaya.biz.*"
   import="com.codachaya.util.*"%>
<%
   request.setCharacterEncoding("UTF-8");
%>
<%
   response.setCharacterEncoding("UTF-8");
%>
<%
   //response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
@import url("css/review.css");
</style>
</head>

<body>

      <%@ include file="./form/pstm_header.jsp"%>
      <%
         //ReviewDao dao=new ReviewDao();
      //List<ReviewDto> reviewList=dao.selectReviewList();
      //System.out.println(reviewList.size());
      PagingUtil pagination = (PagingUtil) request.getAttribute("pagination");
      List<ReviewDto> reviewList = (ArrayList<ReviewDto>) request.getAttribute("reviewList");
      //임시로 생성 <- 페이지간 연결 후 삭제하기
      if (pagination == null) {
         ReviewBiz biz = new ReviewBiz();
         pagination = new PagingUtil(1, 6);
         pagination.setRecordsPerPage(6);
         pagination.setNumberOfRecords(biz.getselectReviewCount());
         System.out.println("selectReviewCount" + biz.getselectReviewCount());
         pagination.makePaging();
         reviewList = biz.selectReviewPaging(0, 6);
      }
      System.out.println("ListSizejsp" + reviewList.size());
      %>

      <div class="review_contents wrapper">
         <div class="member_header">
            <div class="title">수강후기</div>
            <div class="sub_title">
               <span class="content">수강후기</span> 
               <img class="right_arrow" alt="다음버튼" src="img/right.png"> 
               <span class="color">전체보기</span>
            </div>
         </div>
         <div class="left-container">
            <div class="class_category_container">
               <div class="title">수강후기</div>
               <ul class="class_category_list">
                  <li class="navi-item on"><a href="review.do?command=review">전체보기</a></li>
                  <li class="navi-item"><a
                     href="review.do?command=reviewinsert">후기쓰기</a></li>
               </ul>
            </div>
         </div>
         <div class="right-container">
            <div class="review_list_container">
               <div class="review_wrapper">
                  <div class="title">전체후기</div>
                  <form action="review.do" method="post">

                     <input type="hidden" name="command" value="reviewsuch" /> <select
                        name="such">
                        <option value="reviewtitle">제목</option>
                        <option value="trainer">트레이너</option>
                     </select> <input type="text" name="reviewtitle"> <input
                        type="submit" value="검색">

                  </form>
                  <div class="body-wrapper">
                     <%
                        for (int i = 0; i < reviewList.size(); i++) {
                     %>
                     <div class="review_item"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                        onclick="location.href='review.do?command=selectres&ReviewId=<%=reviewList.get(i).getReviewid()%>'">

                        <div class="review_img">
                           <img

                              src="http://localhost:8787/PstmProject/imgfolder/<%=reviewList.get(i).getUploadimg()%>"
                              style="height: 220px; width:220px; background-position: 50%;  background-size: cover;">

                           <!--                이미지주소&이미지 이름             -->
                        </div>
                        <div class="review_text">
                        	
                           <div class="review_class"><%=reviewList.get(i).getReviewtitle()%></div>
                           <div class="review_summary"><%=reviewList.get(i).getTrainer()%></div>
                        </div>
                     </div>                            
                     <%                                     
                        }
                     %>
                  </div>

                  <div class="subscription_navigation"></div>
                  <div class="reviewPage_navigation">
                     <%
                        for (int i = pagination.getStartPageNo(); i <= pagination.getEndPageNo(); i++) {
                        System.out.println(i);
                        System.out.println(pagination.getCurrentPageNo());
                        if (i == pagination.getCurrentPageNo()) {
                     %>

                     <%=i%>
                     <%
                        } else {
                     %>

                     <a href="review.do?command=review&pages=<%=i%>"><%=i%></a>

                     <%
                        }
                     }
                     %>
                  </div>
               </div>
            </div>
         </div>
      </div>
      
      </div>
   <%@ include file="./form/pstm_footer.jsp"%>

</body>
</html>