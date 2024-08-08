package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVo;

public class BoardServiceImpl implements IBoardService {
	private IBoardDao dao;
	
	// 1
	private static BoardServiceImpl service;
	
	// 2
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	
	// 3
	public static BoardServiceImpl getInstance() {
		if(service==null) service = new BoardServiceImpl();
		return service;
	}
	// 이 3개를 추가하면 싱글톤이 된다.
	
	
	
	@Override
	public int insertBoard(BoardVo boardVo) {
		// TODO Auto-generated method stub
		return dao.insertBoard(boardVo);
	}

	@Override
	public int updateBoard(BoardVo boardVo) {
		// TODO Auto-generated method stub
		return dao.updateBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		// TODO Auto-generated method stub
		return dao.deleteBoard(boardNo);
	}

	@Override
	public List<BoardVo> getAllBoard() {
		// TODO Auto-generated method stub
		return dao.getAllBoard();
	}

	@Override
	public BoardVo getBoard(int boardNo) {
		// 조회수를 증가 시킨다.
		int cnt = dao.setCountIncrement(boardNo);
		if(cnt==0) {	// 조회수 증가 실패
			return null;
		}
		return dao.getBoard(boardNo);
	}

	@Override
	public List<BoardVo> getSearchBoard(String title) {
		// TODO Auto-generated method stub
		return dao.getSearchBoard(title);
	}

	@Override
	public int setCountIncrement(int boardNo) {
		// TODO Auto-generated method stub
		return dao.setCountIncrement(boardNo);
	}

}
