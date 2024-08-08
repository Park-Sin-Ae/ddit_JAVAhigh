package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String oprnd1 = request.getParameter("oprnd1");
		String operation = request.getParameter("operation");
		
		// request객체의 getParameterValues()메서드 이용하기
		// 형식) request객체.getParameterValues("파라미터명");
		//		==> '파라미터명'이 같은 것이 여러개 일 경우에 사용한다.
		//		==> 가져오는 데이터의 자료형은 'String[]'이다.
		String oprnd2 = request.getParameter("oprnd2");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Request객체 연습02</title></head>");
		out.println("<body>");
		
		out.println("<h2>계산결과</h2>");
//		out.println("<table border='1'>");
//		out.println("<tr><td>이름</td>");
//		out.println("<td>" + userName + "</td></tr>");
//		out.println("<tr><td>직업</td>");
//		out.println("<td>" + job + "</td></tr>");
//		out.println("<tr><td>취미</td>");
//		out.println("<td>");
		
		
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
