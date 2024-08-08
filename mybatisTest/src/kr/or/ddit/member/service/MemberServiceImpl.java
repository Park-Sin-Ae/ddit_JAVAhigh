package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;


public class MemberServiceImpl implements IMemberService {
   private IMemberDao dao;      // DAO객체가 저장될 변수 선언
   
   
   // 1번 
   private static MemberServiceImpl service;
   
   // 생성자 -- 2번
//   public MemberServiceImpl() {
   	private MemberServiceImpl() {
//      dao = new MemberDaoImpl();     		  // DAO객체 생성
      dao = MemberDaoImpl.getInstance();      // DAO객체 생성
   }
   	// 3번
    public static MemberServiceImpl getInstance() {
        if(service == null) service = new MemberServiceImpl();
        
        return service;
     }
   
   @Override
   public int insertMember(MemberVO memVo) {
      
      return dao.insertMember(memVo);
   }

   @Override
   public int deleteMember(String memId) {
      
      return dao.deleteMember(memId);
   }

   @Override
   public int updateMember(MemberVO memVo) {
      
      return dao.updateMember(memVo);
   }

   @Override
   public List<MemberVO> getAllMember() {
   
      return dao.getAllMember();
   }

   @Override
   public int getMemberIdCount(String memId) {
   
      return dao.getMemberIdCount(memId);
   }
   
   @Override
   public int updateMember2(Map<String, String> paramMap) {
   
      return dao.updateMember2(paramMap);
   }

}
