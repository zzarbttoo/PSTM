package com.codachaya.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codachaya.dao.UserDao;
import com.codachaya.dto.UserDto;

@WebServlet("/TrainerPageServelt")
public class TrainerPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String command = request.getParameter("command");
		System.out.printf("[%s]\n", command);

		UserDao dao = new UserDao();

		if (command.equals("list")) {

			int trainerid = Integer.parseInt(request.getParameter("trainerid"));
			System.out.println("trainerid:" +trainerid);
			List<UserDto> userlist = dao.selectPayingUserList(trainerid);

			request.setAttribute("userlist", userlist);
			dispatch("pstm_trainerMyPage.jsp", request, response);

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

}
