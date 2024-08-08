package kr.or.ddit.basic.session.db.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.MemberVO;

public class LoginMemberDaoImpl implements ILoginMemberDao {
	private static LoginMemberDaoImpl dao;
	
	private LoginMemberDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static LoginMemberDaoImpl getInstance() {
		if(dao==null) dao = new LoginMemberDaoImpl();
		
		return dao;
	}
	
	@Override
	public MemberVO getLoginMember(MemberVO memVo) {
		MemberVO loginMemberVo = null;			// 반환값이 저장 될 변수
		SqlSession session = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			
			loginMemberVo = session.selectOne("member.getLoginMember" ,  memVo);
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if(session!=null) session.close();
		} 
		
		return loginMemberVo;
	}

}
