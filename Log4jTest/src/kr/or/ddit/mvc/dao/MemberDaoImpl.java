package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao{
	private static final Logger logger = Logger.getLogger(MemberDaoImpl.class);
	// 1번
	private static MemberDaoImpl dao;
	
	// 2번
	private MemberDaoImpl() {	}
	
	// 3번
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		
		return dao;
	}

   @Override
   public int insertMember(MemberVO memVo) {
      Connection conn = null;
      PreparedStatement pstmt= null;
      int cnt = 0;		// 반환값이 저장 될 변수
      try {
         conn = DBUtil3.getConnection();
         logger.info("Connection객체 생성 완료!");
         
         String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr)\r\n" + 
                    " values(?, ?, ?, ?, ?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, memVo.getMem_id());
         pstmt.setString(2, memVo.getMem_pass());
         pstmt.setString(3, memVo.getMem_name());
         pstmt.setString(4, memVo.getMem_tel());
         pstmt.setString(5, memVo.getMem_addr());
         
         logger.debug("PreparedStatement객체 생성 완료!");
         logger.debug("실행 SQL문장 : " + sql);
         logger.debug("사용 데이터 : [" + memVo.getMem_id() + ", " +
        		 	memVo.getMem_pass() + ", " + memVo.getMem_name() + ", " +
        		 	memVo.getMem_tel() + ", " + memVo.getMem_addr() + "]"        		 	
        		 );
         
         cnt = pstmt.executeUpdate();
         logger.debug("작업 성공~!_!");
         
      } catch (SQLException e) {
    	 logger.error("작업 실패!!", e);
         e.printStackTrace();
      } finally {
         if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
         if(conn!=null) try {conn.close();} catch(SQLException e) {}
         logger.debug("자원 반납 성공!!");
      }
      return cnt;
   }

   @Override
   public int deleteMember(String memId) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      int cnt = 0;		// 반환값이 저장 될 변수ㄴ
      
      try {
         conn = DBUtil3.getConnection();
         logger.info("Connection객체 생성 완료!");
         
         String sql = "delete from mymember where mem_id = ?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, memId);
         
         logger.debug("PreparedStatement객체 생성 완료!");
         logger.debug("실행 SQL 문장 : " + sql);
         logger.debug("사용 데이터 : [" + memId + "]");
         
         cnt = pstmt.executeUpdate();
         logger.debug("작업 성공!_!");
         
      } catch (SQLException e) {
    	 logger.error("작업 실패!!", e);
         e.printStackTrace();
      } finally {
         if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
         if(conn != null) try {conn.close();} catch(SQLException e) {}
         logger.debug("자원 반납 성공!!");
      }
      return cnt;
   }

   @Override
   public int updateMember(MemberVO memVo) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      int cnt = 0;		// 반환값이 저장 될 변수
      
      try {
         conn = DBUtil3.getConnection();
         logger.info("Connection객체 생성 완료!");
         
         String sql= "update mymember \r\n" + 
                  "   set MEM_PASS =?, MEM_NAME=?, MEM_TEL=?, MEM_ADDR=? \r\n" + 
                  "  where MEM_ID=?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, memVo.getMem_pass());
         pstmt.setString(2, memVo.getMem_name());
         pstmt.setString(3, memVo.getMem_tel());
         pstmt.setString(4, memVo.getMem_addr());
         pstmt.setString(5, memVo.getMem_id());
         
         logger.debug("PreparedStatement객체 생성 완료!");
         logger.debug("실행 SQL 문장 : " + sql);
         logger.debug("사용 데이터 : [" + memVo.getMem_pass() + ", " + memVo.getMem_name() + ", " +
        		 	memVo.getMem_tel() + ", " + memVo.getMem_addr() + ", " + memVo.getMem_id() + "]"
        		 );
         
         cnt = pstmt.executeUpdate();
         logger.debug("작업 성공 !_!");
         
      } catch (SQLException e) {
    	 logger.error("작업 실패!!", e);
         e.printStackTrace();
      } finally {
         if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
         if(conn != null) try {conn.close();} catch(SQLException e) {}
         logger.debug("자원 반납 성공!!");
      }
      return cnt;
   }

   @Override
   public List<MemberVO> getAllMember() {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<MemberVO> memList = null;      //반환값이 저장될 변수
      try {
         conn = DBUtil3.getConnection();
         String sql = "select * from mymember";
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         logger.info("Connection객체 생성 완료!");
         
         memList = new ArrayList<MemberVO>();
         
         while(rs.next()) {
            // 1개의 레코드가 저장될 VO객체 생성
            MemberVO memVo = new MemberVO();
            
            // VO객체에 select한 데이터를 저장한다.
            memVo.setMem_id(rs.getString("mem_id"));
            memVo.setMem_pass(rs.getString("mem_pass"));
            memVo.setMem_name(rs.getString("mem_name"));
            memVo.setMem_tel(rs.getString("mem_tel"));
            memVo.setMem_addr(rs.getString("mem_addr"));
            
            logger.debug("PreparedStatement객체 생성 완료!!");
            logger.debug("실행 SQL 문장 : " + sql);
            logger.debug("사용 데이터 : [" + rs.getString("mem_id") + ", " +
            			rs.getString("mem_pass") + ", " + rs.getString("mem_name") + ", " +
            			rs.getString("mem_tel") + ", " + rs.getString("mem_addr") + "]"
            		); 
            
            //List에 VO객체 추가
            memList.add(memVo);
            logger.debug("작업 성공!!");
         }
         
      } catch (SQLException e) {
    	 logger.error("작업 실패!!", e);
         e.printStackTrace();
      } finally {
         if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
         if(rs != null) try {rs.close();} catch(SQLException e) {}
         if(conn != null) try {conn.close();} catch(SQLException e) {}
         logger.debug("자원 반납 성공!!");
      }
      return memList;
   }

   @Override
   public int getMemberIdCount(String memId) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      int count = 0;          //반환 값이 저장될 변수
      
      try {
         conn = DBUtil3.getConnection();
         logger.info("Connection객체 생성 완료!");
         
         String sql =  "select count(*) cnt from mymember where mem_id =?";
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setNString(1, memId);
         
         logger.debug("PreparedStatement객체 생성 완료!!");
         logger.debug("실행 SQL 문장 : " + sql);
         logger.debug("사용 데이터 : [" + memId + "]");
         
         rs = pstmt.executeQuery();
         logger.debug("작업 성공!");
         
         if(rs.next()) {
            count = rs.getInt("cnt");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if(rs != null) try {rs.close();} catch(SQLException e) {}
         if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
         if(conn != null) try {conn.close();} catch(SQLException e) {}
         logger.debug("자원 반납 성공!");
      }
      
      return count;
   }

   @Override
   public int updateMember2(Map<String, String> paramMap) {
      // key값의 정보 ==> 회원ID(memID),컬럼명(field), 수정할 데이터값(data)
      Connection conn = null;
      PreparedStatement pstmt = null;
      int cnt = 0;		// 반환값이 저장 될 변수
      
      try {
         conn = DBUtil3.getConnection();
         logger.info("Connection객체 생성 완료!");
         String sql = "update mymember set "+paramMap.get("field")
                  + "=? where mem_id =?";
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, paramMap.get("data"));
         pstmt.setString(2, paramMap.get("memID"));
         
         logger.debug("PreparedStatement객체 생성 완료!");
         logger.debug("실행 SQL 문장 : " + sql);
         logger.debug("사용 데이터 :: [" + paramMap.get("data") + ", " + paramMap.get("memID") + "]");
         
         cnt = pstmt.executeUpdate();
         logger.debug("작업 성공!");
         
      } catch (SQLException e) {
    	 logger.error("작업 실패!!", e);
         e.printStackTrace();
      } finally {
         if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
         if(conn != null) try {conn.close();} catch(SQLException e) {}
         logger.debug("자원 반납 성공!!");
      }
      return cnt;
   }

}