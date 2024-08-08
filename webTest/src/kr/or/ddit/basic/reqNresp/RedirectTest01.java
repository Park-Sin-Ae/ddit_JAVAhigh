package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/redirectTest01.do")
public class RedirectTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * - redirect 방식
		 * * 다른 페이지로 제어가 넘어가도록 한다.
		 * * 첫 번째 문서의 응답 시 브라우저에게 '이동할 URL'을 전송하여 브라우저가 
		 * 	  해당 URL로 이동시키는 방식이다.
		 * * 이 때 이동할 때는 GET방식으로 요청한다.
		 * 
		 * * 첫번째 문서와 이동할 문서 사에이는 데이터를 공유할 수 없다.
		 *   (이유 : 브라우저에게 새롭게 요청을 하기 때문에..)
		 *    
		 */
		
		/*
		// Request객체의 setAttribute()메서드로 데이터 셋팅해서 보내기
		//	==> 이것은 redirect 방식에서는 불가능하다.
		request.setAttribute("tel", "010-9999-8888");
		
		// Redirect방식으로 이동하기
		//	==> Response객체의 sendRedirect()메서드에 이동할 문서의 
		//		전체 URI주소를 지정해 주어야 한다.
		
		// 형식) Response객체.sendRedirect("이동할 문서의 전체URI 주소");
		response.sendRedirect(request.getContextPath() + "/redirectTest02.do");
		*/
		
		request.setCharacterEncoding("utf-8");
		// 데이터 보내기
		String tel = "010-9999-8888";
		String userName = request.getParameter("username");
		
		// 데이터는 GET 방식으로 전송하면 된다.
		// ==> 전송할 데이터들 중에서 한글이 포함된 데이터는 URLEncoder를 이용하여 인코딩해서 지정해 주면 된다.
		
		userName = URLEncoder.encode(userName, "utf-8");
		
		response.sendRedirect(request.getContextPath() + "/redirectTest02.do?username=" 
							+ userName + "&tel=" + tel );
		 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
