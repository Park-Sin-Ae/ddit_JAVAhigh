package kr.or.ddit.basic;

import java.net.MalformedURLException;
import java.net.URL;

public class URLTest01 {
	public static void main(String[] args) throws MalformedURLException {
		// URL 클래스 ==> 인터넷에 존재하는 서버들의 자원에
		//				접근할 수 있는 주소를 다루는 클래스
		
		// URL주소의 구조
		// ==> 프로토콜 ://호스트명:포트번호/경로명/파일명?쿼리스트링#참조
		
		// http://ddit.or.kr:80/index.html?test=123
		
		URL url = new URL("http", "ddit.or.kr", 80, "index.html?test=123");
		
		System.out.println("Protocol :" + url.getProtocol());
		System.out.println("Host : " + url.getHost());
		System.out.println("Port : " + url.getPort());
		System.out.println("File : " + url.getFile());
		System.out.println("Path : " + url.getPath());
		System.out.println("Query : " + url.getQuery());
		System.out.println();
		System.out.println(url.toExternalForm());
	}
}
