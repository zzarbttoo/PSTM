package com.codachaya.controller;

import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/UploadService")
@MultipartConfig(fileSizeThreshold=1024*1024*10, maxFileSize=1024*1024*50, maxRequestSize=1024*1024*100)  
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "imgfolder";
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String applicationPath = request.getServletContext().getRealPath("imgfolder");
		String uploadFilePath = applicationPath + UPLOAD_DIR;
		
		System.out.println(" LOG :: [서버 루트 경로] :: " + applicationPath);
		System.out.println(" LOG :: [파일 저장 경로] :: " + uploadFilePath);
		
		File fileSaveDir = new File(uploadFilePath);
		
		if(!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		String fileName = null;
		
		for(Part part : request.getParts()) {
			getPartConfig(part);
			fileName = getFileName(part);
			System.out.println(" LOG :: [업로드 파일 경로] :: " + uploadFilePath + File.separator + fileName);
			part.write(uploadFilePath + File.separator + fileName);
		}
		request.setAttribute("fileName", fileName);
		getServletContext().getRequestDispatcher("/response.jsp").forward(request, response);
		
		
		
		
	}
	private void getPartConfig(Part part) {
		System.out.println("------------------------------------------------------");
		System.out.println(" LOG :: [HTML태그의 폼태그 이름] :: " + part.getName());
		System.out.println(" LOG :: [ 파일 사이즈 ] :: " + part.getSize());
		for(String name : part.getHeaderNames()) {
			System.out.println(" LOG :: HeaderName ::" + name + ", HeaderValue :: " + part.getHeader(name) + "\n");
		}
		System.out.println("------------------------------------------------------");
	}
	
	private String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		System.out.println(" LOG :: content-disposition 헤어 :: = "+contentDisp);
		String[] tokens = contentDisp.split(";");
		for(String token : tokens) {
			System.out.println(" LOG :: 파일 이름 :: " + token);
			return token.substring(token.indexOf("=") + 2, token.length()-1);
		}
		return "";
	}

}
