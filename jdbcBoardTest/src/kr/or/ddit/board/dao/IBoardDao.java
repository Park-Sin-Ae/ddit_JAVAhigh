package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.vo.BoardVo;

/*
 * 입력, 수정, 삭제, 검색
 */
public interface IBoardDao {
	
	/**
	 * BoardVo객체에 저장된 자료를 DB에 insert하는 메서드
	 * 
	 * @param boardVo DB에 insert할 자료가 저장된 BoardVo객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int insertBoard(BoardVo boardVo);
	
	/**
	 * 매개변수로 받은 BoardVo 객체에 저장된 자료를 이용하여 update하는 메서드
	 * 
	 * @param boardVo DB에 update할 자료가 저장된 BoardVo객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateBoard(BoardVo boardVo);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글을 삭제하는 메서드
	 *  
	 * @param boardNo 삭제한 게시글 번호 
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * DB의 jdbc_board 테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * 
	 * @return BoardVO객체가 저장된 List객체
	 */
	public List<BoardVo> getAllBoard();
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글 내용을 가져와 반환하는 메서드
	 * 
	 * @param boardNo 검색할 게시글 번호
	 * @return 게시글 번호에 맞는 자료가 있으면 해당 게시글 정보를 갖는 BoardVo객체,
	 * 			자료가 없으면 null 반환
	 * 
	 */
	public BoardVo getBoard(int boardNo);
	
	/**
	 * 검색 단어를 매개변수로 받아서 게시글 제목을 기준으로 검색하여 결과를 반환하는 메서드
	 * 
	 * @param title 검색할 단어가
	 * @return 검색된 결과가 저장된 List객체
	 */
	public List<BoardVo> getSearchBoard(String title);
	/**
	 * 
	 * @param boardNo 조회수를 증가시킬 게시글 번호
	 * @return 작업성공 : 1, 작업 실패
	 */
	public int setCountIncrement(int boardNo);
}
