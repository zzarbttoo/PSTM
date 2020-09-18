package com.codachaya.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.jsp.PageContext;
import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;

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
		DietinfoDao dao = new DietinfoDao();
		DailyinfoDao dailydao = new DailyinfoDao();

		String command = request.getParameter("command");
		System.out.println("command : " + command);
		if (command == null) {
			String path = request.getSession().getServletContext().getRealPath("imgfolder");
			int size = 1024 * 1024 * 5;
			MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
			command = multi.getParameter("command");

			if (command.equals("insertres")) {

				try {

					String uploadimg = multi.getParameter("uploadimg");
					String originimg = multi.getParameter("originimg");
					

					Enumeration files = multi.getFileNames();
					String str = (String) files.nextElement();

					uploadimg = multi.getFilesystemName(str);
					originimg = multi.getOriginalFileName(str);
					System.out.println(uploadimg);
					System.out.println(originimg);
					
					String timeeat = multi.getParameter("timeeat");
					
					
					
					
					// 이후 db 저장하기
					DietinfoDto dto = new DietinfoDto();
					dto.setUploadimg(uploadimg);
					dto.setOriginimg(originimg);
					dto.setTimeeat(timeeat);
					int res = dao.insert(dto);
					
					if (res > 0) {
						jsResponse("성공", "pstm_dailypage.jsp", response);
					} else {
						jsResponse("실패", "pstm_studentmypage.jsp", response);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			if (command.equals("selectres")) {
				// DB에서 DailyInfo 정보 가져와서 pstm_dailypage.jsp 로 보내주기
				List<DailyinfoDto> list = dailydao.selectList();
				request.setAttribute("list", list);
				dispatch("pstm_dailypage.jsp", request, response);
				
				List<DietinfoDto> lists = dao.selectList();
				request.setAttribute("lists", lists);
				dispatch("pstm_dailypage.jsp", request, response);

			} else if (command.equals("getimg")) {
				ServletOutputStream imgout = response.getOutputStream();
				String imgPath = request.getSession().getServletContext().getRealPath("imgfolder");
				String uploadimg = request.getParameter("uploadimg"); // db에 저장된 값 가져와서 넣어주기
				File f = new File(imgPath + File.separator + uploadimg);
				if (!f.exists()) {
					f.mkdir();
				}
				FileInputStream input = new FileInputStream(imgPath + "/" + uploadimg);
				int length;
				byte[] buffer = new byte[10];
				while ((length = input.read(buffer)) != -1) {
					imgout.write(buffer, 0, length);
				}
				System.out.println(new File(".").getAbsolutePath());
				System.out.println("upadloimg : " + uploadimg);

			} else if (command.equals("insertform")) {
				response.sendRedirect("pstm_dailyinsert.jsp");
				
				
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
