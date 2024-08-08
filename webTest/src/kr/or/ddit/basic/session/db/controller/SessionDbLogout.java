package kr.or.ddit.basic.session.db.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionDbLogout.do")
public class SessionDbLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 객체 생성
		HttpSession session = request.getSession();
		
		// 전체 session 정보 삭제하기
		session.invalidate();
		
		// Login 페이지로 이동
		response.sendRedirect(request.getContextPath() + "/session/dbLogin/sessionDbLogin.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
