package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		try {
			// 소켓 생성
			DatagramSocket socket = new DatagramSocket();
			
			// 상대방의 주소 정보 생성하기
//			InetAddress address = InetAddress.getByName("localhost");
			InetAddress address = InetAddress.getByName("127.0.0.1");
			
			while(true) {
				// 전송할 메시지 입력
				System.out.println("보낼 메시지 입력 >>");
				String msg = scan.nextLine();
				
				// 전송용 패킷 객체 생성
				DatagramPacket outPacket = new DatagramPacket(msg.getBytes("utf-8"),
											msg.getBytes("utf-8").length, address, 8888);
				
				// 전송하기
				socket.send(outPacket);
				
				// 통신 종료 메시지 검사
				if("/end".equals(msg)) break;
				
				System.out.println();
				//--------------------------------------------------
				
				
				// 상대방이 보낸 메시지 받기
				byte[] inMsg = new byte[1024];
				
				// 수신용 패킷객체 생성
				DatagramPacket inPacket = new DatagramPacket(inMsg, inMsg.length);
				
				// 데이터 수신하기
				socket.receive(inPacket);
				
				// 수신 받은 데이터 문자열로 변환하기
				String receiveMsg = new String(inMsg, 0, inPacket.getLength(), "utf-8");
				
				System.out.println("서버의 응답 데이터 : " + receiveMsg);
				System.out.println();
				
			}
			
			System.out.println("통신 끝...!");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
