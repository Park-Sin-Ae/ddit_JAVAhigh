package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
 * 이 예제는 애노테이션(@WebServlet)을 이용해서 Servlet을 등록 처리하는 예제
 * ==> 애노테이션(@WebServlet)은 서블릿 버전 3.0이상에서 사용할 수 있다.
 * 
 * -----------------------------------------------------
 * - @WebServlet 애노테이션의 속성들
 * 1.name : 서블릿의 이름을 설정한다.(기본값 : 빈 문자열(""))
 * 2.urlPatterns : 서블릿의 URL패턴의 목록을 설정한다. (기본값 : 빈배열({})
 * 	예) urlPatterns = "/url1" 또는 urlPatterns={"/url1"} ==> 패턴이 1개일 경우
 *  예) urlPatterns = {"/url1", "/url2",...} ==> 패턴이 2개 이상일 경우
 * 3.value : urlPatterns와 동일한 방법으로 설정한다.
 * 4.description : 주석(설명글)을 설정한다. 
 * 
 * 
 */

@WebServlet( urlPatterns = {"/servletTest02.ddit"},
			description = "애노테이션을 이용한 서블릿 설정하기")
public class ServletTest02 extends HttpServlet {

	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		// 처리한 내용을 HTML형식으로 출력한다.
		// 방법2) print(), println(), printf()메서드 이용하기
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>두번째 서블릿</title></head>");
		out.println("<body>");
		out.println("<h2 style='color: crimson; text-align:center;'>"
					+ "안녕하세요. 두 번째 서블릿 예제입니다. <br><br>"
					+ "@WebServlet 애노테이션을 이용한 예제입니다.</h2>");
		out.println("</body></html>");
	}
	
	 
	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
