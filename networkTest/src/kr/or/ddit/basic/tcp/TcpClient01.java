package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient01 {
	public static void main(String[] args) throws UnknownHostException, IOException {
		// 접속할 서버의 IP주소와 Port번호를 지정하여 Socket갹채를 생성한다.
		// 1) 원래의 IP 주소 : 192.168.35.105
		// 2) 지정된 IP 주소 : 127.0.0.1
		// 3) 원래의 컴퓨터 이름 : DESKTOP-COTIAK9
		// 4) 지정된 컴퓨터 이름 : localhost
		
		// 접속할 서버의 IP주소와 Port번호를 지정하여 Socket 객체를 생성한다.
		// 위에 네가지 중 하나를 이름으로 정하면 된다.
		// Socket 객체는 생성이 완료되면 해당 서버로 연결 요청 신호를 보낸다.
//		Socket socket = new Socket("DESKTOP-COTIAK9", 7777);
		Socket socket = new Socket("localhost", 7777);
		
		// 이 부분부터는 서버와 연결이 완료된 이후에 실행되는 부분이다.
		System.out.println("서버에 연결되었습니다.");
		System.out.println();
		
		// 서버에서 보내온 메시지를 받아서 화면에 출력하기
		
		// Socket 객체를 이용하여 입력용 스트림 객체를 구한다.
		InputStream in = socket.getInputStream();
		DataInputStream din  = new DataInputStream(in);
		
		// 메시지 받가 => 스트림을 이용한 입력 작업은 곧 수신 작업이 된다.
		System.out.println("서버에서 보낸 메세지 : " + din.readUTF());
		System.out.println();
		
		System.out.println("수신완료");
		
		// 사용했던 자원 반납
		din.close();
		socket.close();
		
	}
}
