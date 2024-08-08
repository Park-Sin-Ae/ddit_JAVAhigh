package kr.or.ddit.basic.reqNresp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/forwardTest01.do")
public class ForwardTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*
	  	- forward방식
	   	  * 특정 서블릿이나 JSP문서에 대한 요청을 다른 서블릿이나 JSP로 넘겨준다.
	   	  	(이 때 HttpServletRequest 객체와 HttpServletResponse객체를 같이 공유하기
	   	  	때문에 파라미터를 넘길 수 있다.)
	   	  * 처음 요청했던 URL주소는 처음 값 상태에서 바뀌지 않는다.
	   	  * 서버 내부에서만 사용할 수 있다.
	   	 
	   	 - 이동하는 다른 문서에 데이터를 넘기려면 Request객체의 setAttribute()메서드로 
	   	      데이터를 저장하고, 받는 쪽에서는 getAttribute()메서드로 읽어가면 된다.
	   	   
	   	  * 보낼때 형식) Request객체.setAtrribute("key값", 데이터)
              ==> 'key값'은 문자열로 지정하고 '데이터'는 java의 모든 자료형을 지정할 수 있다.
         
          * 받을 때 형식) Request객체.getAtrribute("key값");
             ==> 'key'값은 'setAtrribute()'메서드에 지정한 'key'값을 사용한다.
       */
		
		request.setAttribute("tel", "010-1234-5678");	// 공유할 데이터 저장
		
		// forward방식으로 이동하기
		//	==> Request 객체의 getRequestDispatcher()메서드에 이동할
		//		서블릿이나 JSP를 지정해 주는데
		//		이동할 문서의 전체 URI경로 중에서 Context Path 이후의 경로를 지정해 주면 된다.
		// 현재 예제에서 이동할 문서의 URL => "/webTest/forwardTest02.do"
		// '/forwardTest02.do'만 지정하면 된다.
		RequestDispatcher rd = request.getRequestDispatcher("/forwardTest02.do");
		rd.forward(request, response);	   	 	
 	 	 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
