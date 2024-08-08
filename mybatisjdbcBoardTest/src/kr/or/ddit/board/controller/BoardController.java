package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVo;

public class BoardController {
	private IBoardService service;
	private Scanner scan;
	
	// 생성자
	public BoardController() {
		scan = new Scanner(System.in);
		service = BoardServiceImpl.getInstance();
	}

	public static void main(String[] args) {
		new BoardController().boardStart();

	}
	
	// 시작 메서드
	public void boardStart() {
		String searchword = null;
		int choice = 0;
		
		while(true) {
			if(choice != 3) {
				searchword = null;
			}
			
			choice = displayMenu(searchword);
			switch(choice) {
			case 1 :	// 새 글 작성
				insertBoard();
				break;
			case 2 :	// 게시글 보기
				viewBoard();
				break;
			case 3 :	// 검색
				searchword = searchBoard();
				break;
			case 0 :	// 작업 끝
				System.out.println("게시판 프로그램을 종료합니다.");
				return; 
			default : 
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요");
			}
		}
	}
	
	// 검색할 제목을 입력 받아 반환하는 메서드
	private String searchBoard() {
		System.out.println();
		scan.nextLine();	// 버퍼 비우기
		
		System.out.println("검색 작업");
		System.out.println("-----------------------------------");
		System.out.println("- 검색할 제목 입력 : ");
		return scan.nextLine();
	}

	// 게시글 내용을 보여주는 메서드
	private void viewBoard() {
		System.out.println();
		System.out.println("보기를 원하는 게시물 번호 입력 >> ");
		int no = scan.nextInt();
		
		// 게시글 번호에 맞는 게시글 정보 가져오기
		BoardVo boardVo = service.getBoard(no);
		
		if(boardVo==null) {
			System.out.println(no + "번의 게시글이 존재하지 않습니다.");
			
			return;
		}
		
		System.out.println();
		System.out.println(no + "번 글 내용");
		System.out.println("-----------------------------------");
		System.out.println("- 제  목 : " + boardVo.getBoard_title());
		System.out.println("- 작성자 : " + boardVo.getBoard_writer());
		System.out.println("- 내  용 : " + boardVo.getBoard_content());
		System.out.println("- 작성일 : " + boardVo.getBoard_date());
		System.out.println("- 조회수 : " + boardVo.getBoard_cnt());
		System.out.println("-----------------------------------");
		System.out.println("메뉴 : 1. 수정     2. 삭제    3. 리스트로 가기");
		System.out.println("작업선택 >> ");
		System.out.println();
		
		int choice = scan.nextInt();
		
		switch(choice) {
			case 1 : 		// 수정
				updateBoard(no);
				break;
			case 2 : 		// 삭제
				deleteBoard(no);
				break;
			case 3 : 		// 리스트로 가기
				return;
		}
		
	}

	// 게시글을 삭제하는 메서드
	private void deleteBoard(int boardNo) {
		int cnt = service.deleteBoard(boardNo);
		
		if(cnt>0) {
			System.out.println(boardNo + "번 글이 삭제되었습니다.");
		} else {
			System.out.println( boardNo + "번 글 삭제 실패!!");
		}
		
	}

	// 게시글을 수정하는 메서드
	private void updateBoard(int boardNo) {
		System.out.println();
		scan.nextLine();	// 버퍼 비우기
		
		System.out.println("수정 작업하기");
		System.out.println("-----------------------------------");
		System.out.println("- 제  목 : ");
		String newTitle = scan.nextLine();
	
		System.out.println("- 내  용 : ");
		String newContent = scan.nextLine();
		
		// 입력 받은 정보와 게시글 번호를 Vo객체에 저장한다.
		BoardVo boardVo = new BoardVo();
		boardVo.setBoard_title(newTitle);
		boardVo.setBoard_content(newContent);
		boardVo.setBoard_no(boardNo);
		
		int cnt = service.updateBoard(boardVo);
		if(cnt > 0) {
			System.out.println(boardNo + "번 글이 수정되었습니다.");
		}else {
			System.out.println(boardNo + "번 글 수정 실패!");
		}
		
	}

	//새 글을 작성하는 메서드
	private void insertBoard() {
		System.out.println();
		scan.nextLine();	// 버퍼 비우기
		
		System.out.println("새 글 작성하기");
		System.out.println("-----------------------------------");
		System.out.println("- 제  목 : ");
		String title = scan.nextLine();
		
		System.out.println("- 작성자 : ");
		String writer = scan.nextLine();
		
		System.out.println("- 내  용 : ");
		String content = scan.nextLine();
		
		// 입력 받은 자료를 VO에 저장한다.
		BoardVo boardVo = new BoardVo();
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);
		
		int cnt = service.insertBoard(boardVo);
		
		if(cnt > 0) {
			System.out.println("새 글이 추가되었습니다.");
		} else {
			System.out.println("새 글 추가 실패!");
		}
		
	}

	// 게시판 목록을 보여주고 메뉴는 나타내며 사용자가 입력한 메뉴 번호를 
	// 반환하는 메서드
	private int displayMenu(String searchword) {
		 System.out.println();
		 
		 // 검색할 단어가 null 이면 전체 목록을 가져오고
		 // 검색할 단어가 null이 아니면 해당 검색할 단어를 이용하여 검색한 목록을 가져와서 출력한다.
		 
		 List<BoardVo> boardList = null;
		 
		 // 게시글 목록 가져오기
		 if(searchword==null) {
			 boardList = service.getAllBoard();
		 } else {
			 boardList = service.getSearchBoard(searchword);
			 
		 }
		 
		 // 게시글 목록 가져오기
		 
		 System.out.println("-------------------------------------------------------------");
		 System.out.println("No\t제 목\t작성자\t조회수");
		 System.out.println("-------------------------------------------------------------");
		 
		 if(boardList==null || boardList.size()==0) {
			 System.out.println("게시글 목록이 하나도 없습니다.");
		 } else {
			 for(BoardVo boardVo : boardList) {
				 System.out.println(boardVo.getBoard_no() + "\t" 
						 + boardVo.getBoard_title() + "\t"
						 + boardVo.getBoard_writer() + "\t"
						 + boardVo.getBoard_cnt());
				 			
			 }
		 }
		 System.out.println("-------------------------------------------------------------");
		 System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
		 System.out.println("작업선택 >> ");
		 System.out.println();
		 
		 
		return scan.nextInt();
	}

}
