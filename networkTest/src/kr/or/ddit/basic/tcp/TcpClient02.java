package kr.or.ddit.basic.tcp;

import java.net.Socket;

public class TcpClient02 {
	public static void main(String[] args) {
		// 소켓객체를 생성해서 서버에 접속을 시도하고
		// 서버에 접속이 완료되면 이 소켓 객체를 
		// 메시지를 받는 쓰레드와 메시지를 보내는 쓰레드에 
		// 주입한 후 쓰레드들을 실행하면 된다.
		
		try {
			Socket socket = new Socket("localhost" , 7777);
			System.out.println("서버에 연결되었습니다! ");
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
