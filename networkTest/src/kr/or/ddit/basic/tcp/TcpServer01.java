package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer01 {
	public static void main(String[] args) throws IOException {
		// TCP소켓 통신을 하기 위해 ServerSocket객체를 생성한다.
		ServerSocket server = new ServerSocket(7777);
		
		System.out.println("접속을 기다립니다..");
		
		// accept() 메서드
		// ==> 클라이언트의 연결 요청이 올 때까지 계속 기다린다.
		// ==> 클라이언트의 연결 요청이 오면 새로운 Socket 객체를 생성해서
		//	       클라이언트의 Socket과 연결한다.
		
		Socket socket = server.accept();
		
		// 위의 accept()메서드 이후의 내용은 클라이언트와 연결된 상태에서
		// 실행되는 코드이다.
		System.out.println();
		System.out.println("클라이언트와 연결되었습니다.");
		System.out.println();
		
		System.out.println("상대방 컴퓨터의 정보..");
		System.out.println("IP주소 : " + socket.getInetAddress().getHostAddress());
		System.out.println("Port번호 : " + socket.getPort());
		
		System.out.println("현재 컴퓨터의 정보..");
		System.out.println("IP주소 : " + socket.getLocalAddress());
		System.out.println("Port번호 : " + socket.getLocalPort());
		System.out.println();
		
		// 클라이언트에게 메시지 보내기
		
		// Socket을 이용하여 출력용 스트림 객체를 구한다.
		OutputStream out = socket.getOutputStream();
		DataOutputStream dout = new DataOutputStream(out);		// 데이터를 자료형 데이터 그대로 출력하는 스트림
		
		// 메시지 보내기	==> 스트림을 이용한 출력 작업이 곧 송신 작업이 된다.
		dout.writeUTF("어서오새요! 환영합니다! :) ");
		System.out.println("메시지를 보냈습니다! >_<* ");
		System.out.println();
		
		// 소켓과 연결 스트림 닫기
		dout.close();
		socket.close();
		server.close();
	}
}
