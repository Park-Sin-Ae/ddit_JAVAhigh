package kr.or.ddit.cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
	
		// id, password, checkbox 정보 받아오기
		String userId = request.getParameter("userid");
		String userPass = request.getParameter("userpass");
		String chkId = request.getParameter("chkid");
		
		// 받아온 ID를 값으로 갖는 Cookie객체 생성
		Cookie loginCookie = new Cookie("USERID", userId);
		
		// 체크박스의 체크 여부를 확인해서 처리한다.
		// ==> 체크박스가 체크된 상태이면 Cookie를 저장하고,
		// 	      체크가 해제된 상태이면 Cookie를 삭제한다.
		if(chkId==null ) {		// checkbox가 해제된 상태이머,,
			loginCookie.setMaxAge(0);
		}
		
		response.addCookie(loginCookie);
		
		// 로그인 성공 여부 확인
		if("test".equals(userId) && "1234".equals(userPass)) {
			response.sendRedirect(request.getContextPath() + "/cookie/cookieMain.jsp");
			
		} else {
			response.sendRedirect(request.getContextPath() + "/cookie/cookieLogin.jsp");
		}
		
	}

}
