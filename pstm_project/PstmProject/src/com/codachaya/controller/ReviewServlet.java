package com.codachaya.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codachaya.biz.ReviewBiz;
import com.codachaya.dao.ReviewDao;
import com.codachaya.dto.ReviewDto;
import com.codachaya.dto.UserDto;
import com.codachaya.util.PagingUtil;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/review.do")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String command = request.getParameter("command");
		ReviewDao dao=new ReviewDao();
		System.out.println(command);
		
		ReviewBiz biz = new ReviewBiz();

		int offset = 0;
		int count = 6;
		int getreviewcount = 0;

		if (command.equals("review")) {
/*
			// db 저장
			int reviewId = Integer.parseInt(request.getParameter("reviewId"));
			String reviewtitle=request.getParameter("reviewtitle");
			String trainer=request.getParameter("trainer");
			String reviewcontent=request.getParameter("reviewcontent");

			int UserId = 39;
			ReviewDto dto=new ReviewDto();
			dto.setReviewtitle(reviewtitle);
			dto.setTrainer(trainer);
			dto.setReviewcontent(reviewcontent);
*/
			// DB 받아오기
			int currentPageNo = 1;
			
			if(request.getParameter("pages") != null) {
				currentPageNo = Integer.parseInt(request.getParameter("pages"));
				System.out.println("현재 페이지" + currentPageNo);
			}
			
			PagingUtil pagination = new PagingUtil(currentPageNo, count);
			//pagination.setRecordsPerPage(count);
			
			offset = (pagination.getCurrentPageNo() -1) * pagination.getRecordsPerPage();
			System.out.println("offSetnumdao" + offset);
			getreviewcount = biz.getselectReviewCount();
			
			pagination.setNumberOfRecords(getreviewcount);
			pagination.makePaging();
			
			List<ReviewDto> reviewList = biz.selectReveiwPaging(offset, count);
			System.out.println("reviewSize" + reviewList.size());
			
			request.setAttribute("reviewList", reviewList);
			request.setAttribute("pagination", pagination);
			
			dispatch("review.jsp", request, response);
			
		}else if(command.equals("selectres")) {
			String id = request.getParameter("ReviewId");
			ReviewDto reviewDto = biz.selectOne(Integer.parseInt(id));
//비즈에서 다오가서디비가서받아서받아오는거.biz..biz.?
			request.setAttribute("reviewDto", reviewDto);
			dispatch("reviewselect.jsp", request, response);
		}
		
		else if(command.equals("reviewsuch")) {
			String reviewtitle=request.getParameter("reviewtitle"); 
			//String reviewtitle=request.getParameter("reviewtitle");
			//String trainer=request.getParameter("trainer");
			System.out.println("res"+reviewtitle);
		//	System.out.println(trainer);
			//System.out.println(such);
			
			ReviewDto dto=new ReviewDto();
			dto.setReviewtitle(reviewtitle);
			List<ReviewDto> reviewDto= biz.reviewsuch(dto);
			//System.out.println("controller"+reviewDto.get(0).getTrainer());
			
			request.setAttribute("suchList", reviewDto);
			dispatch("review.jsp", request, response);
			
		}
		
		
		else if(command.equals("insertres")) {
			//review.do?command=insertres
			
			String reviewtitle=request.getParameter("reviewtitle");
			String trainer=request.getParameter("trainer");
			String reviewcontent=request.getParameter("reviewcontent");
			// uesrid 요중에 누가 썼는지 알 수 있을 때 바꿔놓기!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
			// 매우매우매우매우매우매우매우매우중요
			ReviewDto dto=new ReviewDto(1,1,"1번","테스트글","",null,0,"김선아");
			dto.setReviewtitle(reviewtitle);
			dto.setTrainer(trainer);
			dto.setReviewcontent(reviewcontent);
			
			int res=biz.insert(dto);
			
			if(res>0) {
				jsResponse("글 작성 성공", "review.do?command=review", response);
			}else {
				jsResponse("글 작성 실패","review.do?command=review_writer",response);
				
			}			
		} else if(command.equals("update")) {
			int reviewid=Integer.parseInt(request.getParameter("ReviewId"));
			
			ReviewDto dto=dao.selectOne(reviewid);
			request.setAttribute("dto", dto);
			dispatch("reviewupdate.jsp", request, response);
			
		}else if(command.equals("updateres")) {
			
			System.out.println(request.getParameter("reviewid"));
			
			int reviewid=Integer.parseInt(request.getParameter("reviewid"));
			String reviewtitle=request.getParameter("reviewtitle");
			String trainer=request.getParameter("trainer");
			String reviewcontent=request.getParameter("reviewcontent");
			
			ReviewDto dto=new ReviewDto();
			dto.setReviewid(reviewid);
			dto.setReviewtitle(reviewtitle);
			dto.setTrainer(trainer);
			dto.setReviewcontent(reviewcontent);
			
			//ReviewDto dto = biz.update(dto);

			//request.setAttribute("reviewDto", reviewid);
			//dispatch("reviewselect.jsp", request, response);
			int res=biz.update(dto);
			
			if(res>0) {
				jsResponse("글 수정 성공", "review.do?command=review", response);
			}else {
				jsResponse("글 수정 실패", "review.do?commain=reviewupdate.jsp&reviewid="+reviewid, response);
			}
			
		}else if(command.equals("delete")) {
			
			int reviewid=Integer.parseInt(request.getParameter("reviewid"));
			
			ReviewDto dto=new ReviewDto();
			dto.setReviewid(reviewid);
			
			int res=biz.delete(reviewid);
			
			if(res>0) {
				jsResponse("글 삭제 성공", "review.do?command=review", response);
			}else {
				jsResponse("글 삭제 실패", "review.do?commain=reviewdelete.jsp&reviewid="+reviewid, response);
		}
		}
		
	}

	private void dispatch(String path, HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		try {
			dispatch.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String result = "<script> alert(\"" + msg + "\"); location.href=\"" + url + "\"; </script> ";
		response.getWriter().append(result);
	}
	

}
