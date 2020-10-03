package com.codachaya.controller;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codachaya.biz.userListBiz;
import com.codachaya.dao.PayingDao;
import com.codachaya.dao.UserDao;
import com.codachaya.dto.DailyinfoDto;
import com.codachaya.dto.DietinfoDto;
import com.codachaya.dto.LessonDto;
import com.codachaya.dto.ReceiptinfoDto;
import com.codachaya.dao.DailyinfoDao;
import com.codachaya.dao.DietinfoDao;

import com.codachaya.dto.UserDto;

@WebServlet("/trainer.do")
public class TrainerPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		DietinfoDao dietdao = new DietinfoDao();
		DailyinfoDao dailydao = new DailyinfoDao();

		String command = request.getParameter("command");
		System.out.printf("[%s]\n", command);

		UserDao dao = new UserDao();
		PayingDao pao = new PayingDao();
		userListBiz biz = new userListBiz();

		// 강사의 마이페이지 이동
		if (command.equals("list")) {

			int trainerid = Integer.parseInt(request.getParameter("trainerid"));
			System.out.println("trainerid:" + trainerid);
			List<UserDto> userlist = dao.selectPayingUserList(trainerid);
			System.out.println("userlist" + userlist);
			request.setAttribute("userlist", userlist);
			dispatch("pstm_trainerMyPage.jsp", request, response);

			// 유저의 마이페이지 이동
		} else if (command.equals("userlist")) {
			int userid = Integer.parseInt(request.getParameter("userid"));
			System.out.println("userid:" + userid);
			List<ReceiptinfoDto> receiptList = pao.selectReceipt(userid);
			System.out.println("receiptList:" + receiptList);

			List<ReceiptinfoDto> trainlist = biz.selectTrainid(userid);
			System.out.println("trainlist:" + trainlist);

			int trainid = 0;
			ArrayList<Integer> idList = new ArrayList<Integer>();

			for (ReceiptinfoDto rto : trainlist) {

				trainid = rto.getTrainerid();
				System.out.println("trainid:" + trainid);
				idList.add(trainid);
				System.out.println("idList:" + idList);

			}

			List<LessonDto> lessonList = biz.selectClassContent(idList);
			System.out.println("lessonList:" + lessonList);

			for (LessonDto less : lessonList) {
				less.getClasscontent();
				System.out.println("less.getClasscontent():" + less.getClasscontent());
			}

			request.setAttribute("receiptList", receiptList);
			request.setAttribute("lessonList", lessonList);
			dispatch("pstm_studentmypage.jsp", request, response);

		} else if (command.equals("studentlist")) {
			String userid = request.getParameter("userid");
			List<DailyinfoDto> list = dailydao.selectList();

			List<List<DietinfoDto>> lists = new ArrayList<List<DietinfoDto>>();

			for (int i = 0; i < list.size(); i++) {
				lists.add(dietdao.selectList(list.get(i).getDailyinfoid()));

				for (int j = 0; j < lists.get(i).size(); j++) {
					System.out.println(lists.get(i).get(j).getDietid());
				}
			}
			System.out.println("list : " + list);
			System.out.println("lists : " + lists);
			request.setAttribute("list", list);
			request.setAttribute("dietList", lists);
			dispatch("pstm_normalUserManagement.jsp", request, response);

		} else if (command.equals("fbinsert")) {

			String feedback = request.getParameter("feedback");

			DailyinfoDto dailydto = new DailyinfoDto();
			dailydto.setFeedback(feedback);

			int res = dailydao.feedbackinsert(dailydto);
			if (res > 0) {
				jsResponse("성공", "trainer.do?command=list", response);
			} else {
				jsResponse("실패", "trainer.do?command=studentlist", response);
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	private void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 리퀘스트 dispatcher 객체를 여러번 쓰기 힘들어서 메소드를 만듬
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);

	}

	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String result = "<script> alert(\"" + msg + "\"); location.href=\"" + url + "\"; </script> ";
		response.getWriter().append(result);
	}

}
