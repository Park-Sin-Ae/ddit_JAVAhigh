package kr.or.ddit.basic.json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.or.ddit.vo.LprodVO;

@WebServlet("/lprodList2.do")
public class LprodList2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		// DB에서 자료 가져오기
		ILprodService service = LprodServiceImpl.getInstance();
		
		List<LprodVO> lprodList = service.getAllLprod();
		
		// 가져온 자료를 가지고 출력할 View페이지(jsp페이지)로 이동한다.
		request.setAttribute("lprodList", lprodList);
		request.getRequestDispatcher("/json/lproList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
