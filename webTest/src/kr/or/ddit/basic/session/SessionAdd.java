package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Session 정보를 저장하는 서블릿
@WebServlet("/sessionAdd.do")
public class SessionAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * - Session 저장하는 방법
		 * 
		 * 1. Session객체를 생성하거나 현재 세션 가져오기
		 * 	   형식1)Request 객체.getSession(); 또는 Request객체.getSession(true);
		 * 		==> 현재 세션이 존재하면 현재 세션을 반환하고, 존재하지 않으면 새로운 세션을 생성해서 반환한다.
		 *   형식2)Request 객체 .getSession(false);
		 *   	==> 현재 세션이 존재하면 현재 세션을 반환하고, 존재하지 않으면 새로운 세션을 생성하지 않고 null을 반환한다.
		 */
		
		HttpSession session = request.getSession();
		
		/*
		 * 2. Session 값 저장하기	==> Session객체의 setAttribute()메서드 이용
		 *   형식) Session객체.setAttribute("key값", 저장할 데이터);
		 *   	==> 'key값'은 문자열, '저장할 데이터'는 Java의 모든 자료형
		 */
		
		session.setAttribute("testSession", "연습용 세션입니다.");
		session.setAttribute("userName", "홍길동");
		session.setAttribute("age", 30);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Session저장</title></head>");
		out.println("<body>");
		
		out.println("<h3>Session 데이터 저장</h3><br><br>");
		
		out.println("<a href='" +  request.getContextPath() + "/session/sessionTest01.jsp" + "'> 시작문서로 이동</a>");
		
		out.println("</body></html>");
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
