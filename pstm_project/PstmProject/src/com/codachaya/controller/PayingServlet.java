package com.codachaya.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codachaya.biz.PayingBiz;
import com.codachaya.dto.UserDto;


@WebServlet("/PayingController")
public class PayingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		
		String command = request.getParameter("command");
		System.out.println(command);
		
		PayingBiz biz = new PayingBiz();
		
		if(command.equals("subscription")) {
			
			List<UserDto> trainerList = biz.selectTrainerList();
			System.out.println(trainerList);
			String pg = request.getParameter("pg");
			
			//등록되지 않은 트레이너의 경우에는 list에 출력되지 않도록(비즈 or sql 바꾸기)
			request.setAttribute("trainerList", trainerList);
			request.setAttribute("pg", pg);
			
			dispatch("pstm_subscription.jsp", request, response);
			
		}else if(command.equals("payment")){
			
			int trainerUserId = Integer.parseInt(request.getParameter("trainerUserId"));
			UserDto trainerDto = biz.selectTrainerOne(trainerUserId);
			
			//Dto 안에 null 처리 해주기
			if(trainerDto.getCareer() == null) {
				trainerDto.setCareer("<-----등록되지 않았습니다---->");
			}
			if(trainerDto.getMycomment() == null) {
				trainerDto.setMycomment("<-----등록되지 않았습니다----->");
			}
			
			request.setAttribute("trainerDto", trainerDto);
			dispatch("pstm_payment.jsp", request, response);
			
		}
		
	}
	
	
	private void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}
}
