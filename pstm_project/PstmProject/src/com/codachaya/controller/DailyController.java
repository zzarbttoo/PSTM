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
			String uploadimg = request.getParameter("uploadimg");
			String path = request.getSession().getServletContext().getRealPath("imgfolder");
			int size = 1024 * 1024 * 5;
			String realfile = "";

			try {
				MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
				Enumeration files = multi.getFileNames();
				String str = (String) files.nextElement();

				uploadimg = multi.getFilesystemName(str);
				realfile = multi.getOriginalFileName(str);

			} catch (Exception e) {
				
			}
			DietinfoDto dto = new DietinfoDto();
			dto.setUploadimg(uploadimg);
			int res = dao.insert(dto);
			if(res > 0) {
				jsResponse("성공", "pstm_dailypage.jsp", response);
			}
			
			
			
			
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
