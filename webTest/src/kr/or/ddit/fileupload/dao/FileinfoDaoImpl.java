package kr.or.ddit.fileupload.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.FileinfoVO;

public class FileinfoDaoImpl implements IFileinfoDao {
	private static FileinfoDaoImpl dao;

	public FileinfoDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	
	public static FileinfoDaoImpl getInstance() {
		if(dao==null) dao = new FileinfoDaoImpl();
		
		return dao;
	}
	
	@Override
	public int insertFileinfo(FileinfoVO fileVo) {
		SqlSession session = null;
		int cnt = 0;		// 반환값 변수
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.insert("fileinfo.insertFileinfo", fileVo);
			
			if(cnt>0) {
				session.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
		
	}

	@Override
	public List<FileinfoVO> getAllFileinfo() {
		SqlSession session = null;
		List<FileinfoVO> fileList = null;		// 반환값 변수
		try {
			session = MybatisUtil.getSqlSession();
			fileList = session.selectList("fileinfo.getAllFileinfo");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return fileList;
	}

	@Override
	public FileinfoVO getFileinfo(int file_no) {
		SqlSession session = null;
		FileinfoVO fileVo = null;		// 반환값 변수
		try {
			session = MybatisUtil.getSqlSession();
			
			fileVo = session.selectOne("fileinfo.getFileinfo", file_no);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return fileVo;
	}

}
