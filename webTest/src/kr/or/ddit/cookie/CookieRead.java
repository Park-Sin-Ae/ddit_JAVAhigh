package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 쿠키 정보를 읽어오는 서블릿 

@WebServlet("/cookieRead.do")
public class CookieRead extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		/*
		 * - 저장된 쿠키 읽어오기
		 * 1. 전체 쿠키 정보를 Request객체를 통해서 가져온다.
		 * 		==> 가져온 쿠키 정보들은 배열로 저장된다.
		 *   형식) Cookie[] 쿠키배열변수 = Request객체.getCookies();
		 */
		
		Cookie[] cookieArr = request.getCookies();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>쿠키 읽기</title></head>");
		out.println("<body>");
		
		out.println("<h3>저장된 Cookie 정보 확인하기</h3><hr>");
		
		if(cookieArr==null || cookieArr.length==0) {
			out.println("<h4>저장된 쿠키가 하나도 없습니다.</h4>");
		} else {
	// 2. 쿠키 배열에서  쿠키 정보를 구해온다.
			for(Cookie cookie : cookieArr) {
				String cookieName = cookie.getName();		// '쿠키이름' 가져오기
//				String cookieValue = cookie.getValue();		// '쿠키값' 가져오기
				// '쿠키값'이 한글일 경우에는 디코딩 후 사용한다.
				String cookieValue = URLDecoder.decode(cookie.getValue(), "utf-8");	
				
				out.println("쿠키 이름 : " + cookieName + "<br>");
				out.println("쿠키 값 : " + cookieValue + "<hr>");
			}
		}
		
		out.println("<br><br>");
		out.println("<a href='" + request.getContextPath() + "/cookie/cookieTest01.jsp'> 시작 문서로 이동하기 </a>");
		out.println("</body></html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
