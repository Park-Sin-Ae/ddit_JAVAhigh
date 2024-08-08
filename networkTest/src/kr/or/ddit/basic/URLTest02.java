package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest02 {
	public static void main(String[] args) throws IOException {
		// URLConnection ==> 애플리케이션과 URL간의 통신 연결을 위한 클래스
		
		// 특정 서버의 정보와 파일 내용을 가져와 출력하는 예제
		URL url = new URL("https://www.naver.com/index.html");
		
		// URLConnection 객체 구하기
		URLConnection urlCon = url.openConnection();
		
		// Header 정보 구하기
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		
		// headerMap의 key값과 value 값 출력하기
		for(String headerKey : headerMap.keySet()) {
			System.out.println(headerKey + " : " + headerMap.get(headerKey));
		}
		
		System.out.println("-------------------------------------");
		
		// URL에서 지정한 파일 내용 가져오기(index.html 문서 내용 가져오기)
		
		/*
		// 방법1 ==> URLConnection 객체를 이용하는 방법
		
		// index.html 파일문서 읽어오기 위한  스트림 객체 생성
		InputStream in = urlCon.getInputStream();
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(isr);
		
		// 파일 내용을 읽어와 화면에 출력하기
		while(true) {
			String str = br.readLine();		// 한 줄씩 읽어오기
			if(str == null) break;
			System.out.println(str);
		}
		
		br.close();			// 스트림 닫기
		 */
		
		// 방법2 ==> URL객체의 openStream()메서드 이용하기
		
		// 스트림 객체 ㅅ행성
		InputStream in2 = url.openStream();
		BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
		
		//파일 내용을 읽어와 화면에 출력하기
		while(true) {
			String str = br2.readLine();	// 한 줄씩 읽어오기
			if(str == null) break;
			System.out.println(str);
		}
		br2.close();		// 스트림 닫기
	}
}
