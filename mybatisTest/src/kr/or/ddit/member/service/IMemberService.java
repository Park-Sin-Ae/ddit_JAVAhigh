package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemberVO;


/**
 * Service객체는 DAO에 작성된 메서드를 원하는 작업에 맞게 호출하여 실행하고
 * 그 결과를 받아오고, 받아온 결과를 Controller에게 보내주는 역할을 한다.
 * 
 * 그래서 보통 DAO의 메서드와 구조를 같게 작성한다.
 * 
 * @author PC-20
 *
 */

public interface IMemberService {
   /**
    * MemberVO객체에 담겨진 자료를 DB에 insert 하는 메서드
    * @param memVo DB에 insert할 자료가 저장된 MemberVO 객체
    * @return insert 작업성공 :1, insert 작업실패 : 0
    */
   public int insertMember(MemberVO memVo);
   
   /**
    * 회원ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
    * 
    * @param memId   삭제할 회원 ID
    * @return delete 작업 성공 :1 delete 작업 실패:0
    */
   public int deleteMember(String memId);
   
   /**
    * 매개변수로 받은 MemberVO객체를 이용하여 DB에 update하는 메서드
    *    
    * @param memVo update할 회원 정보가 저장된 MemberVo객체
    * @return 작업성공 : 1,  작업 실패 : 0
    */
   public int updateMember(MemberVO memVo);
   
   /**
    * DB의 전체 회원 정보를 가져와서 List에 담아서 반환하는 메서드
    * @return 전체 MemberVO객체가 저장된 List
    */
   public List<MemberVO> getAllMember();
   
   /**
    * 회원ID를 매개변수로 받아서 해당 회원ID의 개수르르 반환하는 메서드
    * @param memId 검색할 회원ID
    * @return   검색된 회원ID의 개수
    */
   public int getMemberIdCount(String memId);
   
   
   /**
    * Map의 정보를 이용하여 회원 정보 중 원하는 컬럼을 수정하는 메서드
    *       key값의 정보 ==> 회원ID(memID),컬럼명(field), 수정할 데이터값(data)
    * @param paramMap 수정할 회원ID, 수정할 컬럼명, 수정할 데이터가 저장된 Map객체
    * @return 작업 성공 :1 , 작업 실패 : 0
    */
   public int updateMember2(Map<String, String> paramMap);
   // 키값을 사용할  때는 Map을 사용하는게 좋다.
}
