package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * - 서블릿의 실행 과정 (Servlet의 LifeCycle)
 * 1. 사용자(클라이언트)가 URL을 클릭하면  Http 요청(Request)을
 *    Servlet Container로 전송한다. ==> 요청 작업
 * 2. 컨테이너는 web.xml에 정의된 url패턴을 확인하여 어느 서블릿을 통해 처리해야 할 지를 검색한다.
 * 	    이 때 검색된 서블릿 클래스가 로딩이 안되어 있으면 로딩한다.
 *    (처음 로딩 시 init()메서드가 자동으로 호출된다.)
 *    (이 작업은 서블릿 버전 3.0이상에서는 @WebServlet 애노테이션으로 설정할 수 있다.)
 * 3. Servlet Conatiner는 개별 요청을 처리할 Thread를 생성하여 실행할 서블릿의 
 *    service()메서드를 호출한다.
 *    (이 때 HttpServletRequest 객체외 HttpServletResponse 객체를 생성하여
 *    파라미터로 넘겨 준다.)
 * 4. service()메서드는 전송방식(GET,POST등)에 맞는 메서드를 자동으로 호출한다.
 * 	  (doGet(), doPost(), doPut(), doDelete() 등)
 * 5. 요청 작업 및 응답 처리가 모두 완료되면 HttpServletRequest 객체와 HttpServletResponse객체는 자동으로 소멸된다.
 * 6. 컨테이너로부터 서블릿이 제거되는 경우에는 destroy()메서드가 호출된다.
 */

@WebServlet("/servletTest03.do")
public class ServletTest03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
@Override
	public void init() throws ServletException {
		System.out.println(this.getServletName() + "에서 init()메서드 호출---");
	}

@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service()메서드 시작---");
		
		// 전송 방식(GET, POST)을 구분하여 전송방식에 맞는 메서드 호출하기
		
		// 방법1) 부모 클래스인 HttpServelet의 service()메서드로 위임하기
		super.service(req, resp);
		
		// 방법2) 직접 검사해서 처리하기
		String method = req.getMethod();	// 전송 방식 구하기
		System.out.println("method >> " + method);
		if("GET".equals(method)) {
			doGet(req, resp);
		} else {
			doPost(req, resp);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()메서드 시작---");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'></head>");
		out.println("<body>");
		out.println("<h2 style='color:crimson;'>doGet()메서드 처리 결과");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()메서드 시작---");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'></head>");
		out.println("<body>");
		out.println("<h2 style='color:royalblue;'>doPost()메서드 처리 결과");
		out.println("</body></html>");
	}
	
	private void detroy() {
		System.out.println(this.getServletName() + "에서 destroy()메서드 호출");
	}

}
