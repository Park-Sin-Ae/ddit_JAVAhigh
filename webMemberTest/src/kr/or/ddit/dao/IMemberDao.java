package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

public interface IMemberDao {
	
	public List<MemberVO> selectMemberAll();
}

