package com.codachaya.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codachaya.dao.PayingDao;
import com.codachaya.dao.UserDao;
import com.codachaya.dto.LessonDto;
import com.codachaya.dto.UserDto;
import com.codachaya.util.PasswordUtil;

@WebServlet("/SingUpServlet")
public class SingUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");
		
		UserDao dao = new UserDao();
		PayingDao payingDao = new PayingDao();
		
		if(command.equals("signupT")) {
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String phone = request.getParameter("phone");
			String addr = request.getParameter("addr");
			String detailaddr = request.getParameter("detailaddr");
			String usertype = request.getParameter("usertype");
			String career = request.getParameter("career");
			String mycomment = request.getParameter("mycomment");
			String signout = request.getParameter("signout");
			
			String[] encryptedPassword = PasswordUtil.encrypt(password);
			
			UserDto dto = new UserDto(0, id, encryptedPassword[0], encryptedPassword[1], name, phone, addr, detailaddr, usertype, gender, 0, null,
			 career, mycomment, null, signout);
			
			int res = dao.insertTrainer(dto);
			
			if(res > 0) {
				
				dto = dao.login(id);
				if(dto.getUsertype().equals("T") && dto.getPassword().equals(password)) {
					
					LessonDto lessonDto = new LessonDto();

					lessonDto.setUserid(dto.getUserid());
					lessonDto.setClasscontent(dto.getName() + "의 강의");
					lessonDto.setPriceinfo("dummy");
					
					payingDao.insertLesson(lessonDto);
					
					jsResponse("회원가입 성공", "pstm_login.jsp", response);
				}
			}else {
				jsResponse("회원가입 실패", "pstm_trainerSignUp.jsp", response);
			}
		}else if(command.equals("signup")) {
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String addr = request.getParameter("addr");
			String detailaddr = request.getParameter("detailaddr");
			String usertype = "S";
			String gender = request.getParameter("gender");
			int height = Integer.parseInt(request.getParameter("height"));
			String signout = "N";

			String[] encryptedPassword = PasswordUtil.encrypt(password);
			
			UserDto dto = new UserDto(0, id, encryptedPassword[0], encryptedPassword[1], name, phone, addr, detailaddr, usertype, gender, height, 
			null, null, null, null, signout);
					
			int res = dao.insertNormalUser(dto);
			
			boolean register = false;
			
			if (res > 0) {
				dto = dao.login(id);
				
				if(dto.getUsertype().equals("S")) {
					if(PasswordUtil.checkPassword(password, dto.getPassword_key(), dto.getPassword())) {
						HttpSession session = request.getSession();
						session.setAttribute("login", dto);

						session.setMaxInactiveInterval(-1);
						
						register = true;
						
						jsResponse("회원가입 성공!", "pstm_mainpage.jsp", response);
					}
				}
				
			} 
			
			if(!register) {
				jsResponse("회원가입 실패", "pstm_normalUserSignUp.jsp", response);
			}
		}else if(command.equals("signupNaver")) {
			
			String id = request.getParameter("id");
			String password = "N";
			String password_key = "N";
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String addr = request.getParameter("addr");
			String detailaddr = request.getParameter("detailaddr");
			String usertype = "N";
			String gender = request.getParameter("gender");
			int height = Integer.parseInt(request.getParameter("height"));
			String imgurl = request.getParameter("imgurl");
			String signout = "N";
			
			System.out.println(id);
			System.out.println(imgurl);
			System.out.println(phone);
			System.out.println(height);
			System.out.println(name);
			System.out.println(gender);
			
			UserDto dto = new UserDto(0, id, password, password_key, name, phone, addr, detailaddr, usertype, gender, height, imgurl, null, null, null, signout);
					
			int res = dao.insertNormalUser(dto);
			
			boolean register = false;
			
			if (res > 0) {
				
				dto = dao.login(id);
				
				if(dto.getUsertype().equals("N")) {
					HttpSession session = request.getSession();
					session.setAttribute("login", dto);

					session.setMaxInactiveInterval(-1);

					register = true;
					
					jsResponse("회원가입 성공!", "pstm_mainpage.jsp", response);
				}
			}
			
			
			if(!register) {

				request.setAttribute("userid", id);
				request.setAttribute("name", name);
				request.setAttribute("imgurl", imgurl);
				request.setAttribute("gender", gender);
				request.setAttribute("error", "T");
				
				dispatch("pstm_naverUserSignUp.jsp", request, response);
			}
		}else if(command.equals("signupFB")) {
			
			String id = request.getParameter("id");
			String password = "F";
			String password_key = "F";
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String addr = request.getParameter("addr");
			String detailaddr = request.getParameter("detailaddr");
			String usertype = "F";
			String gender = request.getParameter("gender");
			int height = Integer.parseInt(request.getParameter("height"));
			String imgurl = request.getParameter("imgurl");
			String signout = "N";
			
			UserDto dto = new UserDto(0, id, password, password_key, name, phone, addr, detailaddr, usertype, gender, height, imgurl, null, null, null, signout);
					
			boolean register = false;
			
			int res = dao.insertNormalUser(dto);
			
			if (res > 0) {
				dto = dao.login(id);
				
				if(dto.getUsertype().equals("F")) {
					HttpSession session = request.getSession();
					session.setAttribute("login", dto);

					session.setMaxInactiveInterval(-1);
					
					register = true;

					jsResponse("회원가입 성공!", "pstm_mainpage.jsp", response);
					
				}
			} 
			
			if(!register) {
				request.setAttribute("userid", id);
				request.setAttribute("name", name);
				request.setAttribute("imgurl", imgurl);
				request.setAttribute("error", "T");

				dispatch("pstm_fbUserSignUp.jsp", request, response);
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

	private void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}
}
