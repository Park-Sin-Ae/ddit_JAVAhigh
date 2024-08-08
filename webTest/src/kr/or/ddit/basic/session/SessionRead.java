package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


// Session 정보 읽기용 서블릿
@WebServlet("/sessionRead.do")
public class SessionRead extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Session 읽기</title></head>");
		out.println("<body>");
		
		out.println("<h2>저장된 Session 데이터 확인하기</h2>");
		
		// 저장 된 Session정보 읽어오기
		
		// 1. Session객체를 생성하거나 현재 세션 가져오기
		HttpSession session = request.getSession();
		
		// 2. Session값 읽기 ==> Session 객체의 getAttribute()메서드 이용
		// 형식) Session객체.getAttribute("key값");
		//		==> 'key값'과 일치하는 값을 찾아서 반환하고 일치하는 'key값'이 없으면 null을 반환한다.
		
		out.println("<h3>세션 데이터 1개 확인하기</h3>");
		
		String sessionValue =  (String)session.getAttribute("testSession");
		if(sessionValue==null) {
			System.out.println("testSession의 세션값이 없습니다.");
		} else {
			out.println("testSession의 세션값 ==> " + sessionValue + "<br>");
		}
		
		out.println("<hr>");
		
		out.println("<h3>전체 세션 데이터 확인하기</h3>");
		out.println("<ol>");
		
		Enumeration<String> sessionNames =  session.getAttributeNames();
		
		int cnt = 0;
		while(sessionNames.hasMoreElements()) {
			cnt++;
			String sessionKey = sessionNames.nextElement();
			out.println("<li>" + sessionKey + " ==> " + session.getAttribute(sessionKey) + "</li>");
		}
		
		if(cnt==0) {
			out.println("<li>세션 데이터가 하나도 없습니다..</li>");
		}
		
		out.println("</ol>");
		out.println("<hr>");
		
		// 세션관련 정보들
		out.println("세션 ID : " + session.getId() + "<br><br>");
		
		// 생성시간 ==> 1970년 1월 1일부터 경과한 시간(밀리세컨드 단위)
		out.println("세션 생성 시간 : " + session.getCreationTime() + "<br><br>");
		out.println("세션 최근 접근 시간 : " +  session.getLastAccessedTime() + "<br><br>");
		
		// 유효시간 ==> (초
		out.println("세션 유효 시간 : " + session.getMaxInactiveInterval() + "<br><br>");
		
		
		out.println("<a href='" +  request.getContextPath() + "/session/sessionTest01.jsp" +
					"'> 시작문서로 이동</a>");
		
		out.println("</body></html>");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
