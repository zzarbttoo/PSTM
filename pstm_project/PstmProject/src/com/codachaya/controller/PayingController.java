package com.codachaya.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codachaya.biz.PayingBiz;
import com.codachaya.dto.UserDto;


@WebServlet("/PayingController")
public class PayingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		
		String command = request.getParameter("command");
		
		PayingBiz biz = new PayingBiz();
		
		if(command.equals("subscription")) {
			
			List<UserDto> trainerList = biz.selectTrainerList();
			
		}
		
	
		
		
		
		
		
	}

}
