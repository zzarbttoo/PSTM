package com.codachaya.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codachaya.dao.UserDao;
import com.codachaya.dto.UserDto;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");

		UserDao dao = new UserDao();

		if (command.equals("login")) {

			String id = request.getParameter("id");
			String password = request.getParameter("password");

			System.out.println("아이디: " + id);
			System.out.println("비번: " + password);

			UserDto dto = dao.login(id);

			if (dto.getId() != null) {

				HttpSession session = request.getSession();
				session.setAttribute("login", dto);

				session.setMaxInactiveInterval(10 * 60);

				response.sendRedirect("mainpage.jsp");

			} else {
				jsResponse(" 아이디 또는 비밀번호를 확인해주세요 ", "pstm_login.jsp", response);
			}
		} else if (command.equals("logout")) {

			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("pstm_login.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	public void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String res = "<script>alert('" + msg + "'); location.href='" + url + "';</script>";

		out.print(res);

	}

}
