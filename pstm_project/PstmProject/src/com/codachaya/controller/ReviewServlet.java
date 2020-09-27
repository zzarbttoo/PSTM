package com.codachaya.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
		ReviewDao dao = new ReviewDao();
		System.out.println(command);

		ReviewBiz biz = new ReviewBiz();

		int normalUserNum = 0;
		int offset = 0;
		int count = 3;
		int getreviewcount = 0;

		if (command == null) {
			String path = request.getSession().getServletContext().getRealPath("imgfolder");
			int size = 1024 * 1024 * 5;
			MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
			command = multi.getParameter("command");

			if (command.equals("insertres")) {
				try {
					String reviewtitle = multi.getParameter("reviewtitle");
					String trainer = multi.getParameter("trainer");
					String reviewcontent = multi.getParameter("reviewcontent");
					String uploadimg = multi.getParameter("uploadimg");

					Enumeration files = multi.getFileNames();
					String str = (String) files.nextElement();

					uploadimg = multi.getFilesystemName(str);

					ReviewDto dto = new ReviewDto();
					dto.setReviewtitle(reviewtitle);
					dto.setTrainer(trainer);
					dto.setReviewcontent(reviewcontent);
					dto.setUploadimg(uploadimg);

					int reviewres = dao.insert(dto);
					if (reviewres > 0) {
						jsResponse("성공", "review.do?command=review", response);
					} else {
						jsResponse("실패", "review.do?command=review", response);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} else {

			if (command.equals("subscription")) {
				int normalUser = 0;

				int currentPageNo = 1;

				if (request.getParameter("pages") != null) {
					currentPageNo = Integer.parseInt(request.getParameter("pages"));
					System.out.println("ReviewServlet 현재 페이지" + currentPageNo);

				}
				PagingUtil pagination = new PagingUtil(currentPageNo, 3);
				pagination.setRecordsPerPage(count);

				System.out.println("ReviewServlet현재페이지" + pagination.getCurrentPageNo());

				offset = (pagination.getCurrentPageNo() - 1) * pagination.getRecordsPerPage();
				getreviewcount = biz.getselectReviewCount();

				pagination.setNumberOfRecords(getreviewcount);
				pagination.makePaging();

				List<ReviewDto> reviewList = biz.selectReviewPaging(offset, count);

				System.out.println(reviewList);
				System.out.println(pagination.getCurrentPageNo());

				request.setAttribute("reviewList", reviewList);
				request.setAttribute("pagination", pagination);

				request.setAttribute("normalUser", normalUser);
				dispatch("pstm_review.jsp", request, response);

			} else if (command.equals("review")) {
				/*
				 * // db 저장 int reviewId = Integer.parseInt(request.getParameter("reviewId"));
				 * String reviewtitle=request.getParameter("reviewtitle"); String
				 * trainer=request.getParameter("trainer"); String
				 * reviewcontent=request.getParameter("reviewcontent");
				 * 
				 * int UserId = 39; ReviewDto dto=new ReviewDto();
				 * dto.setReviewtitle(reviewtitle); dto.setTrainer(trainer);
				 * dto.setReviewcontent(reviewcontent);
				 */
				/// DB 받아오기

				List<ReviewDto> reviewDto = dao.selectReviewList();
				System.out.println("controller" + reviewDto.get(0).getTrainer());
				request.setAttribute("reviewList", reviewDto);
				dispatch("review.jsp", request, response);

			} else if (command.equals("selectres")) {
				String id = request.getParameter("ReviewId");
				ReviewDto reviewDto = biz.selectOne(Integer.parseInt(id));
				request.setAttribute("reviewDto", reviewDto);
				dispatch("reviewselect.jsp", request, response);
			}

			else if (command.equals("reviewsuch")) {
				String reviewtitle=request.getParameter("reviewtitle"); 
				
				System.out.println("reviewtitle"+ reviewtitle);
				
				ReviewDto dto=new ReviewDto();
				dto.setReviewtitle(reviewtitle);
				List<ReviewDto> reviewDto=biz.reviewsuch(dto);
				
				request.setAttribute("suchList",reviewDto);
				dispatch("review_such.jsp", request, response);
				
				
			} else if (command.equals("reviewinsert")) {
				response.sendRedirect("review_writer.jsp");
				
			}else if (command.equals("update")) {
				int reviewid = Integer.parseInt(request.getParameter("ReviewId"));

				ReviewDto dto = dao.selectOne(reviewid);
				request.setAttribute("dto", dto);
				dispatch("reviewupdate.jsp", request, response);

			} else if (command.equals("updateres")) {

				System.out.println(request.getParameter("reviewid"));

				int reviewid = Integer.parseInt(request.getParameter("reviewid"));
				String reviewtitle = request.getParameter("reviewtitle");
				String trainer = request.getParameter("trainer");
				String reviewcontent = request.getParameter("reviewcontent");

				ReviewDto dto = new ReviewDto();
				dto.setReviewid(reviewid);
				dto.setReviewtitle(reviewtitle);
				dto.setTrainer(trainer);
				dto.setReviewcontent(reviewcontent);

				// ReviewDto dto = biz.update(dto);

				// request.setAttribute("reviewDto", reviewid);
				// dispatch("reviewselect.jsp", request, response);
				int res = biz.update(dto);

				if (res > 0) {
					jsResponse("글 수정 성공", "review.do?command=review", response);
				} else {
					jsResponse("글 수정 실패", "review.do?commain=reviewupdate.jsp&reviewid=" + reviewid, response);
				}

			} else if (command.equals("delete")) {

				int reviewid = Integer.parseInt(request.getParameter("reviewid"));

				ReviewDto dto = new ReviewDto();
				dto.setReviewid(reviewid);

				int res = biz.delete(reviewid);

				if (res > 0) {
					jsResponse("글 삭제 성공", "review.do?command=review", response);
				} else {
					jsResponse("글 삭제 실패", "review.do?commain=reviewdelete.jsp&reviewid=" + reviewid, response);
				}
			}else if (command.equals("getimg")) {
				// Response에 결과값을 String으로 보낼 때 사용
				// 이미지 주소값
				ServletOutputStream imgout = response.getOutputStream();
				String imgPath = request.getSession().getServletContext().getRealPath("imgfolder");
				String uploadimg = request.getParameter("uploadimg"); // db에 저장된 값 가져와서 넣어주기
				File f = new File(imgPath + File.separator + uploadimg);
				if (!f.exists()) {
					f.mkdir();
				}
				FileInputStream input = new FileInputStream(imgPath + "/" + uploadimg);
				int length;
				byte[] buffer = new byte[10];
				while ((length = input.read(buffer)) != -1) {
					// String 보내주기
					imgout.write(buffer, 0, length);
				}
				System.out.println(new File(".").getAbsolutePath());
				System.out.println("upadloimg : " + uploadimg);
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