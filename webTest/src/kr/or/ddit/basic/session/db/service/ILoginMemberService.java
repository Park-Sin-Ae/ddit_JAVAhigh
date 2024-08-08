package kr.or.ddit.basic.session.db.service;

import kr.or.ddit.vo.MemberVO;

public interface ILoginMemberService {
	/**
	 * 
	 * 회원 ID와 Password가 저장된 MemberVO객체를 인수값으로 받아서
	 * 해당 회원을 검색하여 반환하는 메서드
	 * 
	 * @param memVo 검색할 회원 ID와 Password가 저장된 MemberVO객체
	 * @return 검색된 회원 정보가 저장된 MemeberVO객체 (검색된 자료가 없으면 null)
	 */
    public MemberVO getLoginMember(MemberVO memVo);
}
