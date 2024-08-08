package kr.or.ddit.fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileinfoService;
import kr.or.ddit.vo.FileinfoVO;


/*
 * - Servlet 3.0 이상에서 파일 업로드를 처리하려면 서블릿에 @MultipartConfig
 *   에노테이션을 설정해야 한다.
 * 
 * - @MultipartConfig 애노테이션에 설정할 변수들.
 *   1) location : 업로드한 파일이 임시로 저장될 경로 지정(기본값 : "")
 *   2) fileSizeThreshold : 이 곳에 지정한 값 보다 큰 파일이 전송되면 임시 경로에 파일을 저장한다.
 *   						(단위 : byte, 기본값 : 0 (무조건 임시경로에 저장한다.))
 *   3) maxFileSize : 1개 파일의 최대 크기 (단위 : byte, 기본값 : -1L(무제한))
 *   4) maxRequestSize : 서버로 전송되는 전체 Request 데이터의 최대 크기 
 *   					 (단위 : byte, 기본값 : -1L(무제한))
 *   					(모든 파일 크기 + formData)
 *   
 * 
 */
@WebServlet("/fileupload/fileupload.do")
@MultipartConfig(
			fileSizeThreshold = 1024*1024*10,
			maxFileSize = 1024*1024*30,
			maxRequestSize = 1024*1024*50
		)
public class Fileupload extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 업로드 폼으로 이동
		request.getRequestDispatcher("/fileupload/fileuploadForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 업로드 처리 
		request.setCharacterEncoding("utf-8");
		
		// 업로드 된 파일들이 저장될 폴더 설정
		String uploadPath = "d:/d_other/uploadFiles";
		
		// 저장될 폴더가 없으면 새로 만들어 준다.
		File f = new File(uploadPath);
		if(!f.exists()) {
			f.mkdirs();
		}
		
		// 파일이 아닌 데이터들은 getParameter()메서드나 getParameterValues()메서드를 
		// 이용해서 구한다.
		
		String userName = request.getParameter("username");
		System.out.println("일반 파라미터(username) : " + userName);
		
		//----------------------------------------------------------------
		// 파일 데이터 처리하기
		
		/*
		 * - Servlet 3.0이상에서 새롭게 추가된 Upload용 메서드
		 * 1) Request객체.getParts()
		 * 		==> 전체 Part객체를 Collection객체에 담아서 반환한다.
		 * 2) Request객체.getPart("part이름");
		 * 		==> 지정된 'part'이름을 가진 개별 Part객체를 반환한다.
		 * 		==> 'part이름'은 <form>태그 안의 입력 요소의 name 속성값으로 구별한다. 
		 */
		
		List<FileinfoVO> fileList = new ArrayList<FileinfoVO>();
		
		// 전체 Part객체의 개수만큼 반복 처리
		for(Part part : request.getParts()) {
			// Upload한 파일명 구하기
			String fileName = extractFileName(part);
			
			// 찾은 파일명의 반문자열("") 이면 이것ㅇㄹ 파일이 아니라 일반 테이터란 의미이다.
			if(!"".equals(fileName)) {	// 파일인 검사
				// 파일 정보를 저장할 VO객체 생성
				FileinfoVO fileVo = new FileinfoVO();
				
				fileVo.setFile_writer(userName);		// 작성자를 VO에 저장
				fileVo.setOrigin_file_name(fileName);	// 원래의 파일명을 VO에 저장
				
				// 실제 저장되는 파일 이릉이 중복되는 것을 방지하기 위해서
				// UUID클래스를 이용하여 저장할 파일명을 만든다.
				String saveFileName = UUID.randomUUID().toString() + "_" + fileName;

				// 이 저장 파일명을 VO에 저장
				fileVo.setSave_file_name(saveFileName);
				
				// upload된 파일의 크기는 Part객체의 getSize()메서드로 구할 수 있다.
				fileVo.setFile_size(part.getSize());
				
				try {
					// Part객체의 write() 메서드로 파일을 저장한다.
					part.write(uploadPath + File.separator + saveFileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				fileList.add(fileVo);		// Upload된 파일 정보 List에 추가하기 
			} // if문 끌
			
		} // for문 끝
		  // Upload된 파일 정보에 DB에 추가하기
		   IFileinfoService service = FileinfoServiceImpl.getInstance();
		   
		   //List에 저장된 데이터를 DB에 저장한다.
		   for(FileinfoVO fvo : fileList) {
		      service.insertFileinfo(fvo);
		   }
		   
		   // 작업이 완료된 후 파일 목록을 보여준다.
		   response.sendRedirect(request.getContextPath()+"/fileupload/fileList.do");
	}	// doPost()메서드 끝
	
	//----------------------------------------------------------------
	/*
		- Part의 구조
		1) 파일이 아닌 일반 데이터일 경우
		-----------------sdfsdfsdfsdfsdf9sd80fsd		==> Part를 구분하는 구분선 
		content-disposition: form-data; name="username"	==> 파라미터 명
														==> 빈줄
		hong
	
	=================================================================	
	
		2) 파일일 경우
		-----------------sdfsdfsdfsdfsdf9sd80fsd		==> Part를 구분하는 구분선
		content-disposition: form=data, name="upFile1"; filename="test1.txt";	==> 파일 정보
		content-type: text/plain						==> 파일종류
														==> 빈줄		
		abc1234안녕										==> 파일내용
	*/
	//----------------------------------------------------------------
	
	// Part구조 안에서 파일명을 찾는 메서드
	private String extractFileName(Part part) {
		String fileName = "";
		
		// 헤더의 키값이 'content-disposition'인 헤더의 Value값 구하기
		String headerValue = part.getHeader("content-disposition");
		String[] itemArr = headerValue.split(";");
		
		for(String item : itemArr) {
			if(item.trim().startsWith("filename")) {	// 'filename' 찾기
				fileName = item.substring(item.indexOf("=") + 2, item.length()-1);
				
			}
		}
		return fileName;
	}
	
	

}
