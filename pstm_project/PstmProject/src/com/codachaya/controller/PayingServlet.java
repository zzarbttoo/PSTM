package com.codachaya.controller;

import java.io.IOException;

import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codachaya.biz.PayingBiz;
import com.codachaya.dto.LessonDto;
import com.codachaya.dto.ReceiptinfoDto;
import com.codachaya.dto.UserDto;
import com.codachaya.util.PagingUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/PayingController")
public class PayingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");

		String command = request.getParameter("command");
		System.out.println(command);

		PayingBiz biz = new PayingBiz();

		//
		int normalUserNum = 0;
		int offset = 0;
		int count = 3;
		int gettrainercount = 0;

		if (command.equals("subscription")) {

			// 처음에 로그인을 했을 경우 normalUserNum에 로그인한 값이 들어가도록
			// 로그인 처리하면 userNum(상수)에 normalUserNum가 들어가도록
			int normalUser = 0;

			int currentPageNo = 1; // default는 1페이지로 오도록한다

			if (request.getParameter("pages") != null) {
				currentPageNo = Integer.parseInt(request.getParameter("pages"));
				System.out.println("현재 페이지" + currentPageNo);
			}

			// 한번에 3개씩 출력되도록 한다
			PagingUtil pagination = new PagingUtil(currentPageNo, 3);
			pagination.setRecordsPerPage(count);

			System.out.println("현재 페이지" + pagination.getCurrentPageNo());

			offset = (pagination.getCurrentPageNo() - 1) * pagination.getRecordsPerPage();
			gettrainercount = biz.getTrainerCount();

			pagination.setNumberOfRecords(gettrainercount);
			pagination.makePaging();

			List<UserDto> trainerList = biz.selectTrainerPaging(offset, count);

			System.out.println(trainerList);
			System.out.println(pagination.getCurrentPageNo());

			request.setAttribute("trainerList", trainerList);
			request.setAttribute("pagination", pagination);

			// jsp에서 post방식으로 다시 서블릿에 보내줘야하는데 그거 처리 필요
			request.setAttribute("normalUser", normalUser);

			dispatch("pstm_subscription.jsp", request, response);

		} else if (command.equals("payment")) {

			// 로그인 처리하면 userNum(상수)에 normalUserNum가 들어가도록
			// 이거 post방식으로 받아야한다

			int trainerUserId = Integer.parseInt(request.getParameter("trainerUserId"));
			// int normalUserId = Integer.parseInt(request.getParameter("normalUserId"));

			// 여기에는 임시로 추가를 하며, 로그인 처리 이후 이 코드는 삭제(userid = 39)
			//int normalUserId = 39;

			UserDto trainerDto = biz.selectTrainerOne(trainerUserId);
			//UserDto normalUserDto = biz.selectNormalUserOne(normalUserId);

			// Dto 안에 null 처리 해주기
			if (trainerDto.getCareer() == null) {
				trainerDto.setCareer("<-----등록되지 않았습니다---->");
			}
			if (trainerDto.getMycomment() == null) {
				trainerDto.setMycomment("<-----등록되지 않았습니다----->");
			}

			request.setAttribute("trainerDto", trainerDto);
			//request.setAttribute("normalUserDto", normalUserDto);
			dispatch("pstm_payment.jsp", request, response);

		} else if (command.equals("paymentres")) {

			String jsonData = request.getParameter("jsonData");

			JsonElement element = JsonParser.parseString(jsonData);
			JsonObject receiptJsonObject = element.getAsJsonObject();
			
			int normalUserId = receiptJsonObject.get("normalUserId").getAsInt();
			int trainerUserId = receiptJsonObject.get("trainerUserId").getAsInt();
			String imp_uid = receiptJsonObject.get("imp_uid").getAsString();
			int duration = receiptJsonObject.get("duration").getAsInt();
			String purchaseType = receiptJsonObject.get("purchaseType").getAsString();
			String isRight = receiptJsonObject.get("isRight").getAsString();
			int price = receiptJsonObject.get("price").getAsInt();
			Date tempDate = new Date();
			
		 
			
			System.out.println("parsingdata" + normalUserId + trainerUserId + imp_uid + duration + purchaseType + price);
			
			/*
			//testfile 제작 {"normalUserId":39,"trainerUserId":41,"imp_uid":"imp_184050782086","duration":3,"purchaseType":"카카오페이","price":30000}
			int normalUserId = 39;
			int trainerUserId = 41;
			String imp_uid = "imp_184050782086";
			int duration = 3;
			String purchaseType = "카카오페이";
			int price= 30000;
			*/
			
			ReceiptinfoDto newReceiptinfoDto = new ReceiptinfoDto(imp_uid, normalUserId, trainerUserId, tempDate , duration, purchaseType, isRight, price);
			
			int insertres = biz.insertNewReceiptinfo(newReceiptinfoDto);
			//lesson insert는 강사 회원 가입 시 돼있다고 생각하고 만들 것임
			LessonDto newUpdateLessonDto = new LessonDto(trainerUserId, "{\""+normalUserId + "\":\"" + biz.selectIdReceipt(imp_uid) +"\"}", 0);
			
			//"userid" : "receiptid" 이렇게 넣을 것이다
			int updateres = biz.updateLessonStudents(newUpdateLessonDto);
			
			
			
			
			
		}
	}

	private void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}
}
