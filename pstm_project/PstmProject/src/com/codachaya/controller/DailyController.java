package com.codachaya.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.codachaya.dao.DailyinfoDao;
import com.codachaya.dao.DietinfoDao;
import com.codachaya.dto.DailyinfoDto;
import com.codachaya.dto.DietinfoDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/daily.do")
public class DailyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String command = request.getParameter("command");
		DietinfoDao dao = new DietinfoDao();
		
		System.out.println(new File(".").getAbsolutePath());

		if(command.equals("insertres")) {
			String path = request.getSession().getServletContext().getRealPath("imgfolder");
			int size = 1024 * 1024 * 5;

			try {
				String file = "";
				String realfile = "";
				
				MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
				Enumeration files = multi.getFileNames();
				String str = (String) files.nextElement();

				file = multi.getFilesystemName(str);
				realfile = multi.getOriginalFileName(str);
				// 이후 db 저장하기

			} catch (Exception e) {

			}
		} else if(command.equals("selectres")) {
			// DB에서 DailyInfo 정보 가져와서 pstm_dailypage.jsp	로 보내주기

		} else if(command.equals("getimg")) {
			ServletOutputStream imgout = response.getOutputStream();
			String imgPath = request.getSession().getServletContext().getRealPath("imgfolder");
			String imgName = request.getParameter("uploadimg"); // db에 저장된 값 가져와서 넣어주기
			System.out.println(imgPath + "/" + imgName);
			File f = new File(imgPath + File.separator + imgName);
			if(!f.exists()) {
				
			}
			FileInputStream input = new FileInputStream(imgPath + "/" + imgName);
			int length;
			byte[] buffer = new byte[10];
			while ((length = input.read(buffer))!= -1) {
				imgout.write(buffer, 0, length);
			}
			System.out.println(new File(".").getAbsolutePath());
		}
		
	}

	private void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String result = "<script> alert(\"" + msg + "\"); location.href=\"" + url + "\"; </script> ";
		response.getWriter().append(result);
	}

}
