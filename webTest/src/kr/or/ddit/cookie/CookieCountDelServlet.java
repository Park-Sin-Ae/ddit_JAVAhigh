package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cookieCountDelServlet.do")
public class CookieCountDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
	/*
	 * - 저장된 쿠키 삭제하기
	 * 		==> 쿠키 정보를 삭제할 때는 삭제할 쿠키를 찾고 해당 쿠키의 유지 시간을 0으로 설정하여 다시 저장하면 된다.
	 * 	
	 */
		
		Cookie[] cookieArr = request.getCookies();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>쿠키 count 초기화</title></head>");
		out.println("<body>");
		
		
		if(cookieArr!=null) {
			// 쿠키 배열에서 삭제할 쿠키를 찾는다.
			// 예) 'count'쿠키 삭제하기
			for(Cookie cookie : cookieArr) {
				String cookieName = cookie.getName();		// '쿠키이름' 가져오기
				
				if("count".equals(cookieName)) {			// 삭제할 쿠키 찾기
					cookie.setMaxAge(0);		// 찾은 쿠키의 유지시간을 0으로 설정
					response.addCookie(cookie); // 해당 쿠키를 다시 저장한다.
				}
				
			}
		}
		out.println("<h2>count가 초기화 되었습니다.</h2>");
		out.println("<br><br>");
		out.println("<a href='" + request.getContextPath() + "/cookie/cookieTest02.jsp'> 시작 문서로 이동하기 </a>");
		out.println("</body></html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
