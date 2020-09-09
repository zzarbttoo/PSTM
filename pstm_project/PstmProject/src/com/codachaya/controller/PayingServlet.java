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

import com.codachaya.dto.UserDto;

@WebServlet("/PayingServlet")
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

		if (command.equals("subscription")) {
			
			List<UserDto> trainerlist = new ArrayList<UserDto>();
			
			

			String pg = null;
			pg = request.getParameter("pg");

			request.setAttribute("pg", pg);

			RequestDispatcher dispatch = request.getRequestDispatcher("pstm_subscription");
			dispatch.forward(request, response);

		}

	}

}
