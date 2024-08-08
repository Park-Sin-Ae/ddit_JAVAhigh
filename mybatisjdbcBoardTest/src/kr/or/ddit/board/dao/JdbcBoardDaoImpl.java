package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardVo;
import kr.or.ddit.util.MybatisUtil;

public class JdbcBoardDaoImpl implements IBoardDao  {
	
	private static JdbcBoardDaoImpl dao;
	
	public static JdbcBoardDaoImpl getInstance() {
		if(dao==null) dao = new JdbcBoardDaoImpl();
		return dao;
	}
	
 	@Override
	public int insertBoard(BoardVo boardVo) {
 		SqlSession session = null;
 		int cnt = 0; 		// 반환값 변수
 		
 		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.insert("board.insertBoard", boardVo);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
 		
		return cnt;
	}

	@Override
	public int updateBoard(BoardVo boardVo) {
		SqlSession session = null;
 		int cnt = 0; 		// 반환값 변수
 		
 		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.update("board.updateBoard", boardVo);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
 		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		SqlSession session = null;
 		int cnt = 0; 		// 반환값 변수
 		
 		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.delete("board.deleteBoard", boardNo);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
 		
		return cnt;
	}

	@Override
	public List<BoardVo> getAllBoard() {
		SqlSession session = null;
		List<BoardVo> boardList = null;		//반환값 변수
		
		try {
			session = MybatisUtil.getSqlSession();
			
			boardList = session.selectList("board.getAllBoard");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return boardList;
	}

	@Override
	public BoardVo getBoard(int boardNo) {
		SqlSession session = null;
		BoardVo boardVo = null;	//반환값 변수
		
		try {
			session = MybatisUtil.getSqlSession();
			
			boardVo = session.selectOne("board.getBoard", boardNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return boardVo;
	}

	@Override
	public List<BoardVo> getSearchBoard(String title) {
		SqlSession session = null;
		List<BoardVo> boardList = null;		//반환값 변수
		
		try {
			session = MybatisUtil.getSqlSession();
			
			boardList = session.selectList("board.getSearchBoard", title);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		SqlSession session = null;
 		int cnt = 0; 		// 반환값 변수
 		
 		try {
			session = MybatisUtil.getSqlSession();
			
			cnt = session.update("board.setCountIncrement", boardNo);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
 		
		return cnt;
	}
	
}
