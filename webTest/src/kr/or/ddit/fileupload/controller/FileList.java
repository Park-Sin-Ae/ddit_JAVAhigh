package kr.or.ddit.fileupload.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileinfoService;
import kr.or.ddit.vo.FileinfoVO;


@WebServlet("/fileupload/fileList.do")
public class FileList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IFileinfoService service = FileinfoServiceImpl.getInstance();
		
		// 파일 전체 목록 구하기
		List<FileinfoVO> fileList = service.getAllFileinfo();
		
		// 가져온 파일 전체 목록을  View 페이지로 보낸다.
		request.setAttribute("fileList", fileList);
		
		request.getRequestDispatcher("/fileupload/fileList.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
