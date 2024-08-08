package kr.or.ddit.basic.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sessionLogin.do")
public class SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// id와 password 구하기
		String userId = request.getParameter("userid");
		String userpass = request.getParameter("userpass");
		
		HttpSession session = request.getSession();
		
		
		// 로그인 성공 여부 확인
		if("admin".equals(userId) && "1234".equals(userpass)) {
			// 로그인에 성공하면 세션에 로그인 정보 저장한다.
			session.setAttribute("loginID", userId);
			session.setAttribute("loginPass", userpass);
		}
		
		// sessionLogin.jsp로 이동하기
		response.sendRedirect(request.getContextPath() + "/session/sessionLogin.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
