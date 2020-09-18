package com.codachaya.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doPost(request, response);
		Vision(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Vision(request,response);
	/*	PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		DietinfoDao dao = new DietinfoDao();
		MultipartRequest multi = null;
		int size = 10*1024*1024;
		String Path = request.getSession().getServletContext().getRealPath("img");
		try {
			multi = new MultipartRequest(request, Path, size, "UTF-8", new DefaultFileRenamePolicy());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		String uploadimg = multi.getFilesystemName("uploadimg");
		DietinfoDto dto = new DietinfoDto(0, 0, uploadimg, null, null, 0);
		int res = dao.insert(dto);
		if(res > 0) {
			jsResponse("성공", "pstm_dailypage.jsp", response);
		}*/
		
		
		
		
	}
	

	protected void Vision(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				return;
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
			
			request.setAttribute("result",result);
			
			dispatch("pstm_kal.jsp",request,response);
			
			//JSON.stringify();
			// List를 JSON형식으로 만들어주는 애
			/*
			 * PrintWriter writer = resp.getWriter();

    String jsonTxt = "{\"code\":\"200\", \"msg\":\"success\"}";
    writer.print(jsonTxt);
			 */
		}
	}
	
	
	private void dispatch(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String result = "<script> alert(\"" + msg + "\"); location.href=\""+url+"\"; </script> ";
		response.getWriter().append(result);
	}

}
