package com.codachaya.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ChattingServlet")
public class ChattingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		
		String command = request.getParameter("command");
		System.out.println(command);
		
		//chat trainer과 chatbot은 이전 페이지에서 처리를 해줘야한다 
		//로그인 세션이 없거나 일반 회원일 경우에는 chatbot으로
		//로그인 했을 때 트레이너일 경우에는 chattrainer로 가도록 한다 
		if(command.equals("chatuser")) {
			
			
			
			
		}else if(command.equals("chattrainer")) {
			//session을 이용해 로그인, 
			//trainer번호와 이름을 함께 넘겨준다
			
			
			
		}else if(command.equals("chatbot")) {
			
			
			response.sendRedirect("pstm_normchatbot");
			
		}		
	}
	
	private void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
