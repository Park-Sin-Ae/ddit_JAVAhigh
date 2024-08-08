package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.MybatisUtil;

public class MemberDaoImpl implements IMemberDao {
	
	private static MemberDaoImpl dao;
	private MemberDaoImpl() {}
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		return dao;
	}

	@Override
	public List<MemberVO> selectMemberAll() {
		SqlSession session = null;
		List<MemberVO> memList = null;
		try {
			session = MybatisUtil.getSqlSession();
			memList = session.selectList("mymember.selectMember");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return memList;
	}

}
