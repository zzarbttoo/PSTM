package com.codachaya.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codachaya.dao.UserDao;
import com.codachaya.dto.UserDto;

@WebServlet("/SingUpServlet")
public class SingUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");
		
		UserDao dao = new UserDao();
		
		if(command.equals("signupT")) {
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String password_key = request.getParameter("password_key");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String phone = request.getParameter("phone");
			String addr = request.getParameter("addr");
			String detailaddr = request.getParameter("detailaddr");
			String usertype = request.getParameter("usertype");
			String career = request.getParameter("career");
			String mycomment = request.getParameter("mycomment");
			String signout = request.getParameter("signout");
			
			UserDto dto = new UserDto(0, id, password, password_key, name, phone, addr, detailaddr, usertype, gender, 0, null, null, career, mycomment, null, signout);
			
			int res = dao.insertTrainer(dto);
			
			if(res > 0) {
				jsResponse("회원가입 성공", "pstm_login.jsp", response);
			}else {
				jsResponse("회원가입 실패", "pstm_trainerSignUp.jsp", response);
			}
		}else if(command.equals("signupN")) {
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String password_key = request.getParameter("password_key");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String addr = request.getParameter("addr");
			String detailaddr = request.getParameter("detailaddr");
			String usertype = request.getParameter("usertype");
			String gender = request.getParameter("gender");
			int height = Integer.parseInt(request.getParameter("height"));
			String signout = request.getParameter("signout");
			
			UserDto dto = new UserDto(0, id, password, password_key, name, phone, addr, detailaddr, usertype, gender, height, null, null, null, null, null, signout);
					
			int res = dao.insertNormalUser(dto);
			
			if (res > 0) {
				jsResponse("회원가입 성공!", "pstm_login.jsp", response);
			} else {
				jsResponse("회원가입 실패", "pstm_normalUserSignUp.jsp", response);
			}
		}else if(command.equals("idChk")) {
			
			String id = request.getParameter("id");
			UserDto dto = new UserDto();
			
			dto = dao.idCheck(id);
			boolean idNotUse = true;

			if (dto != null) {

				idNotUse = false;

			}

			response.sendRedirect("pstm_idchk.jsp?idNotUse=" + idNotUse);
			
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