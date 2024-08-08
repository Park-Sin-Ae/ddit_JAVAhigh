package kr.or.ddit.basic.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.vo.LprodVO;

@WebServlet("/lprodList.do")
public class LprodList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		Gson gson = new Gson();
		
		// DB에서 자료 가져오기
		ILprodService service = LprodServiceImpl.getInstance();
		
		List<LprodVO> lprodList = service.getAllLprod();
		
		// JSON 구조의 문자열로 변환
		String jsonStr = gson.toJson(lprodList);
		
		PrintWriter out = response.getWriter();
		
		// JSON문자열을 응답으로 출력한다.
		out.write(jsonStr);
		
		response.flushBuffer();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
