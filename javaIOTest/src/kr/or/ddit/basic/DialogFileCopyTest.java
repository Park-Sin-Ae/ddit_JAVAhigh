package kr.or.ddit.basic;

import java.awt.Panel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogFileCopyTest {
	public static void main(String[] args) {
		DialogFileCopyTest test = new DialogFileCopyTest();
//		File file = new File("d:/d_other/펭귄.jpg");
		File file = test.DialogOpen("OPEN");
		
		if(file == null ) {
			System.out.println("원본 파일 선택에 실패했습니다.");
			System.out.println("복사 작업을 중지합니다.");
			 return;
		}
		
		// 파일 유무 확인
		if(!file.exists()) {
			System.out.println(file.getAbsolutePath() + "파일이 없습니다.");
			System.out.println("복사 작업을 중지합니다.");
			return;
		}
		
		FileInputStream fin = null;
		FileOutputStream fout = null;
		
		try {
			// 원본 파일을 읽어올 스트림 객체 생성
			fin = new FileInputStream(file);
			
			// 대상 파일에 출력할 스트림 객체 생성
			File targetFile = test.DialogOpen("SAVE");
			if(targetFile == null) {
				System.out.println("대상 파일 선택에 실패했습니다.");
				System.out.println("복사 작업을 중지합니다.");
				return;
			}
			
//			fout = new FileOutputStream("d:/d_other/연습용/펭귄_복사본.jpg");
			fout = new FileOutputStream(targetFile);
			
			System.out.println("복사 작업 시작!");
			
			int data; 			// 읽어온 자료가 저장될 변수
			
			while((data = fin.read()) != -1) {
				fout.write(data);
			}
			
			System.out.println("복사 작업 완료!!");
		
		} catch (IOException e) {
			// TODO: handle exception
		} finally {
			// 사용했던 스트림 닫기
			if(fin != null) try {fin.close();} catch(IOException e) {}
			if(fout != null) try {fout.close();} catch(IOException e) {}
		}
	}
	
	private File DialogOpen(String type) {
		// SWING의 파일 열기, 저장 창 연습
		JFileChooser chooser = new JFileChooser();
		
		// 선택할 파일의 확장자 설정
		FileNameExtensionFilter txt = new FileNameExtensionFilter("Text파일(*.txt)", "txt");
		
		FileNameExtensionFilter doc = new FileNameExtensionFilter("MS Word File", new String[] {"docx", "doc"});
		FileNameExtensionFilter img = new FileNameExtensionFilter("Images File", "png", "jpg", "gif");
		FileNameExtensionFilter pdf = new FileNameExtensionFilter("PDF File", "pdf");
		
		
		// 선택할 파일 목록 중 '모든 파일' 목록의 표시 여부 설정
		// (true : 표시(기본ㅁ값), false: 표시하지 않는다)
		chooser.setAcceptAllFileFilterUsed(false);
		
		chooser.addChoosableFileFilter(txt);
		chooser.addChoosableFileFilter(doc);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(pdf);
		
		
		// 선택할 파일 목록 중 처음부터 선택되게 하고  싶은 파일 목록 설정하기.
		chooser.setFileFilter(img);
		
		// '찾는 위치' (처음부터 작업할 폴더) 설정하기
		chooser.setCurrentDirectory(new File("d:/d_other"));
		
		// Dialog 창 띄우기
		int result;
		if("SAVE".equals(type.toUpperCase())) {
			result = chooser.showSaveDialog(new Panel());		// 저장 창
		} else if ("OPEN".equals(type.toUpperCase())) {
			result =  chooser.showOpenDialog(new Panel());		// 열기 창
		}else {
			return null;
		}
		
		// Dialog 창에서 선택한 파일 정보 구하기
		
		// '열기' 버튼 또는 '저장'버튼을 눌렀는지 여부 검사
		File selectFile = null;
		if(result == JFileChooser.APPROVE_OPTION) {
			selectFile = chooser.getSelectedFile();		// 선택한 파일 정보 반환
		}
		
		return selectFile;
	}
}
