package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cookieCountServlet.do")
public class CookieCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 쿠키이름 ==> "count"로 정함
		// 'count'라는 쿠키가 있는지 검사한다.
		Cookie[] cookieArr = request.getCookies();
		
		int cnt = 0;		// 읽어온 count쿠키의 쿠키값이 저장될 변수
		if(cookieArr!=null) {
			for(Cookie cookie : cookieArr) {
				String cookieName = cookie.getName();
				if("count".equals(cookieName)) {	//'count'쿠키 찾기
					String cookieValue = cookie.getValue();	//현재의 count값 구하기
					cnt = Integer.parseInt(cookieValue);
					break;
				}
			}
		}
		
		cnt++;		// count값 증가
		
		// 중가된 count값을 값는 Cookie객체 생성 
		Cookie cntCookie = new Cookie("count", String.valueOf(cnt));
		
		response.addCookie(cntCookie);		// 쿠키 저장
				
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>쿠키 Count</title></head>");
		out.println("<body>");
		
		out.println("<h2>어서오세요! 당신은 " + cnt + "번째 방문입니다.</h2><br><br>");
		
		out.println("<a href='" + request.getContextPath() + "/cookieCountServlet.do'>카운트 증가하기 </a>");
		out.println("<a href='" + request.getContextPath() + "/cookie/cookieTest02.jsp'> 시작 문서로 이동하기 </a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
