package kr.or.ddit.basic.session.db.service;

import kr.or.ddit.basic.session.db.dao.ILoginMemberDao;
import kr.or.ddit.basic.session.db.dao.LoginMemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class LoginMemberServiceImpl implements ILoginMemberService {
	private ILoginMemberDao dao;
	
	private static LoginMemberServiceImpl service;
	
	private LoginMemberServiceImpl() {
		dao = LoginMemberDaoImpl.getInstance();
	}
	
	public static LoginMemberServiceImpl getInstance() {
		if(service==null) service = new LoginMemberServiceImpl();
		
		return service;
	}
	
	
	@Override
	public MemberVO getLoginMember(MemberVO memVo) {
		// TODO Auto-generated method stub
		return dao.getLoginMember(memVo);
	}

}
