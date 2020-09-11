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
			
			request.setAttribute("trainerList", trainerList);
			request.setAttribute("pg", pg);
			
			dispatch("pstm_subscription.jsp", request, response);
			
		}else if(command.equals("payment")){
			
			int trainerUserId = Integer.parseInt(request.getParameter("trainerUserId"));
			
			UserDto trainerDto = biz.selectTrainerOne(trainerUserId);
			
			System.out.println("trainerDto " + trainerDto);
			
		}
		
	}
	
	
	private void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}
}
