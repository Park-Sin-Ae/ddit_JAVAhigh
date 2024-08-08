package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultiChatClient {

	public static void main(String[] args) {
		new TcpMultiChatClient().clientStart();

	}
	
	// 시작 메서드
	public void clientStart() {
		Socket socket = null;
		
		try {
			socket = new Socket("192.168.35.32", 7777);
			System.out.println("서버에 연결되었습니다.");
			//--------------------------------------
			
			// 메시지를 전송용 쓰레드 객체 생성
			ClientSender sender = new ClientSender(socket);
			
			// 메시지 수신용 쓰레드 객체 생성
			ClientReceiver receiver = new ClientReceiver(socket);
			
			// 쓰레드 실행
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}	// 시작 메서드 끝..
	
	// 메시지 전송용 쓰레드
	class ClientSender extends Thread {
		private Socket socket;
		private DataInputStream din = null;
		private DataOutputStream dout = null;
		
		private String name;
		private Scanner scan;
		
		// 생성자
		public ClientSender(Socket socket) {
			this.socket = socket;
			scan = new Scanner(System.in);
			
			try {
				// 수신용
				din = new DataInputStream(this.socket.getInputStream());
				
				// 송신용
				dout = new DataOutputStream(this.socket.getOutputStream());
				
				if(dout != null) {
					// 클라이언트가 연결이 성공되면 첫번째로 '대화명(이름)'을 입력받아 서버로 보낸다.
					// 서버에서는 이 '대화명'을 받아서 중복되는지 여부를 검사하고,
					// 그 결과를 클라이언트에게 보내준다.
					
					while(true) {
						// 대화명을 입력받아 서버로 전송하기
						System.out.println("대화명 입력 >> ");
						String name = scan.nextLine();
						dout.writeUTF(name);
						
						// 전송한 대화명의 중복여부 받기
						String feedBack = din.readUTF();
						
						if("대화명중복".equals(feedBack)) {		// 대화명이 중복될 때
							System.out.println(name + "은(는) 중복되는 대화명입니다.");
							System.out.println("다른 대화명을 입력하세요..");
						}else {		// 대화명이 중복되지 않을 때..
							this.name = name; 
							System.out.println(name + " 대화명으로 대화방에 입장했습니다.");
							break;	// 반복문 탈출!
						}
					}	// while문 끝
					
				}	// if문 끝
				                         
			} catch (Exception e) {
				// TODO: handle exception
			}
		}	// 생성자 끝
		
		@Override
		public void run() {
			try {
				while(dout != null) {
					// 키보드로 입력한 메시지를 서버로 전송한다.
					dout.writeUTF("[" + name + "]" + scan .nextLine());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}	// 전송용 쓰레드 끝
	
	// -----------------------------------------------
	// 메시지 수신용 쓰레드
	class ClientReceiver extends Thread{
		private Socket socket;
		private DataInputStream din;
		
		// 생성자
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				din = new DataInputStream(this.socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}	// 생성자 끝
		
		@Override
		public void run() {
			try {
				while(din != null) {
					// 서버가 보내온 메시지를 받아서 화면에 출력하기
					System.out.println(din.readUTF());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
