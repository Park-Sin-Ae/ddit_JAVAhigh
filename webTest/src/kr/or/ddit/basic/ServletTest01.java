package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 서블릿이란?
 * 		==> 서블릿 컨테이너에 의해 관리되는 자바 기반 웹 컴포넌트로서,
 * 			동적인 웹 컨텐츠 생성을 가능하게 해 주는 클래스
 * 
 */

/*
 * 실행할 URL 주소 :  http://localhost:80/webTest/servletTest01.do
 * - http		==> 프로토콜
 * - localhost	==> 컴퓨터 이름(도메인명) 또는 서버의 IP주소
 * - 80			==> 포트번호 (80번은 생략 가능)
 * - /webTest	==> 컨텍스트 패스(보통 프로젝트명으로 지정된다.)
 * - /servletTest01.do ==> 서블릿 요청 URL패턴
 * 
 * 서블릿과 요청 URL패턴을 연결해 주는 작업이 필요하다.
 * ==> 방법) 배포 서술자(web.xml)를 이용하는 방법과 애노테이션(@WebServlet)을 이용하는 방법이 있다.
 * 
 * 이 예제는 배포 서술자를 이용하는 방법ㅡ
 * 
 */
// 서블릿 클래스는 HttpServlet을 상속해서 작성한다.
public class ServletTest01 extends HttpServlet {
	// 이 영억에서 service()메서드 또는 doGet()메서드와 doPost()메서드를
	// 재정의 해서 작성한다.
	
	// doGet()메서드나 doPost 메서드는 service()메서드를 통해서 자동으로 호출된다.
	// HttpServletRequest객체		==> 서비스 요청에 관련된 정보 및 메서드를 관리하는 객체
	// HttpServletResponse객체	==> 서비스 응답에 관련된 정보 및 메서드를 관리하는 객체
	
	
	// doGet()메서드 ==> GET 방식의 요청을 처리하는 메서드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*응답할때 기본으로 두줄이 들어간다.*/
		response.setCharacterEncoding("utf-8");		// 응답 문서의 인코딩 방식 결정
		response.setContentType("text/html; charset=utf-8");	// 응답 문서의 ContentType을 설정
		
		// 처리한 내용을 응답으로 보내기 위해서 PrintWriter객체를 생성한다.
		PrintWriter out = response.getWriter();
		
		// 처리한 내용을 HTML형식으로 출력한다.
		// 방법1) append()메서드 이용하기
		out.append("<html>")
		   .append("<head>")
		   .append("<meta charset='utf-8'>")
		   .append("<title>첫번째 Servlet 연습</title>")
		   .append("</head>")
		   .append("<body>")
		   .append("<h2 style='text-align: center;'>안녕하세요 첫번째 Servlet 프로그램입니다.<br><br>")
		   .append("실행 contextPath = " + request.getContextPath() + "</h2>")
		   .append("</body></html>");
		
	}
	
	// doPost()메서드 ==> POST 방식의 요청을 처리하는 메서드
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}
}
