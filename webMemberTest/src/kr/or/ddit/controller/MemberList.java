package kr.or.ddit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.service.IMemberService;
import kr.or.ddit.service.MemberServiceImpl;

@WebServlet("/memberList.do")
public class MemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		IMemberService service = MemberServiceImpl.getInstance();
		memList = service.selectMemberAll();
		
		request.setAttribute("memList", memList);
		
		request.getRequestDispatcher("/member/memberList.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}


}
