package kr.or.ddit.basic.json;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.LprodVO;

public class LprodDaoImpl implements ILprodDao {
	private static LprodDaoImpl dao;
	
	private LprodDaoImpl() {}
	
	public static LprodDaoImpl getInstance() {
		if(dao==null) dao = new LprodDaoImpl();
		return dao;
	}
	
	public List<LprodVO> getAllLprod() {
		SqlSession session = null;
		List<LprodVO> lprodList = null;		// 반환값 변수
		
		try {
			session = MybatisUtil.getSqlSession();
			
			lprodList = session.selectList("lprod.getAllLprod");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return lprodList;
	}
}
