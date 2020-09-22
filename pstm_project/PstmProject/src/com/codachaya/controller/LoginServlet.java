package com.codachaya.controller;

import java.io.IOException;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.codachaya.dao.SnsDao;
import com.codachaya.dao.UserDao;
import com.codachaya.dto.NaverDto;
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
		SnsDao sns = new SnsDao();
		PrintWriter out = response.getWriter();

		if (command.equals("login")) {

			String id = request.getParameter("id");
			String password = request.getParameter("password");

			UserDto dto = dao.login(id, password);

			if (dto.getId() != null && dto.getPassword() != null) {

				HttpSession session = request.getSession();
				session.setAttribute("login", dto);

				session.setMaxInactiveInterval(10 * 60);

				response.sendRedirect("mainpage.jsp");

			} else {

			}
		} else if (command.equals("logout")) {

			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("pstm_login.jsp");
		} else if (command.equals("naverLogin")) {
			String clientId = "MtBEFWjjQiBrT7PZ7GhD";// 애플리케이션 클라이언트 아이디값";
			String clientSecret = "Y2ouJi2JiL";// 애플리케이션 클라이언트 시크릿값";
			String code = request.getParameter("code");
			String state = request.getParameter("state");
			String redirectURI = URLEncoder.encode("http://localhost:8787/PstmProject/Login.do?command=naverLogin",
					"UTF-8");
			String apiURL;
			apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
			apiURL += "client_id=" + clientId;
			apiURL += "&client_secret=" + clientSecret;
			apiURL += "&redirect_uri=" + redirectURI;
			apiURL += "&code=" + code;
			apiURL += "&state=" + state;
			String access_token = "";
			String refresh_token = "";
			System.out.println("apiURL=" + apiURL);
			try {
				URL url = new URL(apiURL);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				int responseCode = con.getResponseCode();
				BufferedReader br;
				System.out.println("responseCode=" + responseCode);
				if (responseCode == 200) { // 정상 호출
					br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else { // 에러 발생
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}
				String inputLine;
				StringBuffer res = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					res.append(inputLine);
				}
				br.close();

				if (responseCode == 200) {
					out.println(res.toString()); // 웹에 출력함
					JSONParser parsing = new JSONParser();
					Object obj = parsing.parse(res.toString());
					JSONObject jsonObj = (JSONObject) obj;

					access_token = (String) jsonObj.get("access_token");
					refresh_token = (String) jsonObj.get("refresh_token");

					String token = access_token; // 네이버 로그인 접근 토큰;
					String header = "Bearer " + token; // Bearer 다음에 공백 추가

					// 네이버 회원 프로필 조회 API
					String apiurl = "https://openapi.naver.com/v1/nid/me";

					Map<String, String> requestHeaders = new HashMap<>();

					/*
					 * 접근 토큰(access token)을 전달하는 헤더 다음과 같은 형식으로 헤더 값에 접근 토큰(access token)을 포함합니다. 토큰
					 * 타입은 "Bearer"로 값이 고정돼 있습니다. Authorization: {토큰 타입] {접근 토큰]
					 */

					requestHeaders.put("Authorization", header);
					String responseBody = get(apiurl, requestHeaders);
					System.out.println(responseBody);

					Object answer = parsing.parse(responseBody);

					JSONObject JsonObj = (JSONObject) answer;
					JSONObject resObj = (JSONObject) JsonObj.get("response");

					String id = (String) resObj.get("id");
					String profile_image = (String) resObj.get("profile_image");
					String gender = (String) resObj.get("gender");
					String name = (String) resObj.get("name");
					String usertype = "";
					String signout = "";

					if (sns.naverLogin(id) == null) {
						NaverDto dto1 = new NaverDto(0, id, name, usertype, gender, 0, profile_image, null, signout);

						int result = sns.insert(dto1);

						if (result > 0) {
							System.out.println("db 저장 성공");
						} else {
							System.out.println("db 저장 실패");
						}

					}

					NaverDto dto = new NaverDto(0, id, name, usertype, gender, 0, profile_image, null, signout);

					dto = sns.naverLogin(id);

					System.out.println("id:" + dto.getId());
					System.out.println("usertype:" + dto.getUsertype());
					System.out.println("signout:" + dto.getSignout());

					if (dto.getId() != null) {

						HttpSession session = request.getSession();
						session.setAttribute("nLogin", dto);

						session.setMaxInactiveInterval(10 * 60);

						response.sendRedirect("mainpage.jsp");
					}

				}

			} catch (Exception e) {
				System.out.println("ERROR");
				System.out.println(e);
			}
		} else if (command.equals("FBlogin")) {

			String access_token = request.getParameter("access_token");
			try {

				String url = "https://graph.facebook.com/v8.0/me?fields=id,name,picture,email&access_token="
						+ access_token;
				URL obj = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				// optional default is GET
				con.setRequestMethod("GET");
				// add request header
				con.setRequestProperty("User-Agent", "Mozilla/5.0");
				int responseCode = con.getResponseCode();
				System.out.println("\nSending 'GET' request to URL : " + url);
				System.out.println("Response Code : " + responseCode);
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer respon = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					respon.append(inputLine);
				}
				in.close();
				System.out.println(respon);

				if (responseCode == 200) {

					JSONParser parsing = new JSONParser();
					Object ob = parsing.parse(respon.toString());
					JSONObject jsonObj = (JSONObject) ob;
					
					Object obje=jsonObj.get("picture");
					JSONObject jsonObj2 = (JSONObject) obje;
					Object objec = jsonObj2.get("data");
					JSONObject jsonObj3 = (JSONObject) objec;

					String id = (String) jsonObj.get("id");
					String name = (String) jsonObj.get("name");
					String profile_image = (String) jsonObj3.get("url");
					String gender = "";
					String usertype = "";
					String signout = "";
					System.out.println("id:" + id);
					System.out.println("name:" + name);
					System.out.println("profile_image :" + profile_image);

					if (sns.FBLogin(id) == null) {
						NaverDto dto = new NaverDto(0, id, name, usertype, gender, 0, profile_image, null, signout);
						int res = sns.FBinsert(dto);

						if (res > 0) {
							System.out.println("db 저장 성공");
						} else {
							System.out.println("db 저장 실패");
						}

					}

					NaverDto dto = new NaverDto(0, id, name, usertype, gender, 0, profile_image, null, signout);

					dto = sns.FBLogin(id);

					if (dto.getId() != null) {

						HttpSession session = request.getSession();
						session.setAttribute("nLogin", dto);

						session.setMaxInactiveInterval(-1);

						response.sendRedirect("mainpage.jsp");
					}

				}
			} catch (Exception e) {
				System.out.println("error");
				e.printStackTrace();
			}

		}

	}

	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
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
