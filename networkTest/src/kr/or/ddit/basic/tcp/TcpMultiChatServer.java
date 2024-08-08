package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TcpMultiChatServer {
	
	// 접속한 클라이언트 정보를 저장할 변수 선언 (Map객체 이용)
	// ==> key 값 : 접속한 사람 이름
	//	   value 값 : 접속한 클라이언트와 연결된 Socket 객체
	
	private Map<String, Socket> clientMap;
	
	// 생성자
	public TcpMultiChatServer() {
		// 동기화 처리가 가능한 Map 객체 생성
		clientMap = Collections.synchronizedMap(new HashMap<String, Socket>());
	}
	
	public static void main(String[] args) {
		new TcpMultiChatServer().serverStart();

	}
	
	// 서버의 시작 메서드
	public void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다..");
			
			while(true) {
				socket = server.accept();		// 클라이언트이 접속을 기다린다.
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속했습니다.");
				System.out.println();
				//-----------------------------------------------------
				ServerReceiver serverThread = new ServerReceiver(socket);
				serverThread.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(server != null) try { server.close(); } catch(IOException e) {}
		}
	}	// 시작 메서드의 끝
	
	// clientMap 에 저장된 전체 사용자에게 메시지를 전송하는 메서드
	private void sendToAll(String msg) {
		// clientMap의 데이터 개수만큼 반복
		for(String name : clientMap.keySet()) {
			try {
				// key 값에 대응하는 Socket객체의 출력용 스트림 객체 생성
				DataOutputStream dout = 
						new DataOutputStream(clientMap.get(name).getOutputStream());
				
				dout.writeUTF(msg);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}	// sendToAll()메서드 끝
	
	//---------------------------------
	// 서벙에서 한 클라이언특 보내온 메시지를 전송하는 Thread 생성
	// (Inner Class 로 작성)
	class ServerReceiver extends Thread {
		private Socket socket;
		private DataInputStream din;
		private DataOutputStream dout;
		
		// 생성자
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				// 송신용 스트림 객체 생성
				dout = new DataOutputStream(this.socket.getOutputStream());
				
				// 수신용 스트림 객체 생성
				din = new DataInputStream(this.socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}	// 생성자 끝
		
	@Override
	public void run() {
		String name = "";
		try {
			// 클라이언트가 연결이 성공되면 첫번째로 '대화명(이름)'을 입력받아 서버로 보낸다.
			// 서버에서는 이 '대화명'을 받아서 중복되는지 여부를 검사하고,
			// 그 결과를 클라이언트에게 보내준다.
			
			// 클라이언트가 보내온 '대화명'이 중복되지 않을 때까지 반복처리
			while(true) {
				name = din.readUTF();	// 클라이언트가 보내온 '대화명' 받기
				
				if(clientMap.containsKey(name)) {	// '대화명'이 중복되면..
					dout.writeUTF("대화명중복");
				}else {		// 중복되지 않을 때..
					dout.writeUTF("OK");
					
					break; 	// 반복문 탈출
				}
			}	// while문 끝..
			
			// 접속한 사람의 대화명을 이용하여 다른 전체 클라이언트들에게
			// 새로운 사람의 대화방 참여 메시지를 보낸다.
			sendToAll("[" + name + "]님이 대화방에 입장했습니다.");
			
			// 대화명과 접속한 클라이언트의 Socket객체를 Map에 추가한다.
			clientMap.put(name, this.socket);
			System.out.println("현재 접속자 수 : " + clientMap.size() + "명");
			
			// 한 클라이언트가 보낸 메시지를 받아서 전체 클라이언트에게 보낸다.
			while(din != null) {
				sendToAll(din.readUTF());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// 이 finally 영역이 실행된다는 것은 클라이언트가 접속을 종료했다는 의미이다.
			
			sendToAll("[" + name + "]님이 접속을 종료했습니다");
			
			// 접속자 목록에서 해당 대화명을 삭제한다.
			clientMap.remove(name);
			
			System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속을 종료했습니다..");
			System.out.println();
			System.out.println("현재 접속자 수 : " + clientMap.size() + "명");
			System.out.println();
			//---------------------------------------------
			
			
		}
	}	
		
	}
}

