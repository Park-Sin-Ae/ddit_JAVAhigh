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

// 쿠키 정보를 삭제하는 서블릿

@WebServlet("/cookieDelete.do")
public class CookieDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");;
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
	/*
	 * - 저장된 쿠키 삭제하기
	 * 		==> 쿠키 정보를 삭제할 때는 삭제할 쿠키를 찾고 해당 쿠키의 유지 시간을 0으로 설정하여 다시 저장하면 된다.
	 * 	
	 */
		
		Cookie[] cookieArr = request.getCookies();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>쿠키 삭제</title></head>");
		out.println("<body>");
		
		out.println("<h3>저장된 Cookie 정보 삭제하기</h3><hr>");
		
		if(cookieArr==null || cookieArr.length==0) {
			out.println("<h4>저장된 쿠키가 하나도 없습니다.</h4>");
		} else {
			// 쿠키 배열에서 삭제할 쿠키를 찾는다.
			// 예) 'gender'쿠키 삭제하기
			for(Cookie cookie : cookieArr) {
				String cookieName = cookie.getName();		// '쿠키이름' 가져오기
				
				if("gender".equals(cookieName)) {			// 삭제할 쿠키 찾기
					cookie.setMaxAge(0);		// 찾은 쿠키의 유지시간을 0으로 설정
					response.addCookie(cookie); // 해당 쿠키를 다시 저장한다.
				}
				
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
