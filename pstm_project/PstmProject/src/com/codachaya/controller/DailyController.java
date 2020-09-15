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
		ServletOutputStream imgout = response.getOutputStream();
		String imgPath = "C:\\Users\\feelj\\OneDrive\\바탕 화면\\semi\\PSTM\\pstm_project\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\PstmProject\\imgfolder";
		String imgName = request.getParameter("uploadimg");
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
		if(command.equals("insertres")) {
			
			
			
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
