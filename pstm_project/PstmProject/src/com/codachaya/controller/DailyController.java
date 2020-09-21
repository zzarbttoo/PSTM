package com.codachaya.controller;

import java.io.File;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.protobuf.ByteString;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


import net.sf.json.JSON;



@WebServlet("/daily.do")

public class DailyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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

	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doPost(request, response);
		Vision(request,response);
	}

	
		
		
		
		
	
	

	protected List<String> Vision(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ImageAnnotatorClient vision = ImageAnnotatorClient.create();

		String fileName =  request.getSession().getServletContext().getRealPath("img") + "\\beef.jpg";
		// String fileName = "C:\\Users\\feelj\\OneDrive\\바탕
		// 화면\\semi\\PSTM\\pstm_project\\PstmProject\\WebContent\\img\\515966_540.jpg";

		// Path : 파일을 찾는 개체, 파일 경로를 나타냄
		Path path = Paths.get(fileName);

		// 파일 경로 콘솔에 출력
		System.out.println(path.toAbsolutePath());

		// readAllBytes : 파일의 모든 byte를 읽는다 파일에 오류가 나면 파일이 닫힘
		byte[] data = Files.readAllBytes(path);
		// ByteString : 바이트 스트림으로 표시되는 이미지 콘텐츠
		// copyFrom : 주어진 바이트를 ByteString에 복사
		ByteString imgBytes = ByteString.copyFrom(data);

		// AnnotateImageRequest : 이미지, 사용자 요청 기능 및 컨텍스트 정보
		List<AnnotateImageRequest> requests = new ArrayList<AnnotateImageRequest>();
		// Image : Google Cloud Vision API 작업을 수행 할 클라이언트 이미지
		// setContent : 바이트로 표시되는 이미지
		Image img = Image.newBuilder().setContent(imgBytes).build();
		Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
		AnnotateImageRequest requestres = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
		requests.add(requestres);

		BatchAnnotateImagesResponse responseres = vision.batchAnnotateImages(requests);
		List<AnnotateImageResponse> responses = responseres.getResponsesList();
		List<String> result = new ArrayList<String>();
		for (AnnotateImageResponse res : responses) {
			if (res.hasError()) {
				System.out.printf("Error:%s\n", res.getError().getMessage());
				return null;	//나중에  null 꼭찾아와~~ 까먹지마~
			}
			for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {

				annotation.getAllFields().forEach((k, v) -> {
					System.out.println(k.toString());
					if (k.toString().equals("google.cloud.vision.v1.EntityAnnotation.description")) {
						result.add(v.toString());
					}
				});

			}
			System.out.println(result);
			JsonArray array = new Gson().toJsonTree(result).getAsJsonArray();
			//parse : 형식을 변화해주는것
			//Gson : Gson 을 사용하겠다.
			//toJsonTree : Json 객체로 변환해주는것
			//getAsJsonArray : 객체를 Array형식으로 만들어줌

			// JSON.stringify();
			// List를 JSON형식으로 만들어주는 애
			/*
			 * PrintWriter writer = resp.getWriter();
			 * 
			 * String jsonTxt = "{\"code\":\"200\", \"msg\":\"success\"}";
			 * writer.print(jsonTxt);
			 */
		}
		return result;
	}
	private void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		

	}
	


	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String result = "<script> alert(\"" + msg + "\"); location.href=\"" + url + "\"; </script> ";
		response.getWriter().append(result);
	}

}
