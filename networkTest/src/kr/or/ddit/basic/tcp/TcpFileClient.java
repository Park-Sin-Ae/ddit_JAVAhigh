package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/*
 *  클라이언트는 서버와 접속이 완료되면 서버로 'd:/d_other' 폴더에 있는 '펭귄.jpg' 파일을 전송한다
 *  (파일에서 읽어서 Socket으로 출력하기)
*/
public class TcpFileClient {
	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}

private void clientStart() {
	// 전송할 파일정보를 갖는 File 객체 생성
	 File file = new File("d:/d_other/펭귄.jpg");
	  
	 String fileName = file.getName();
	 
	 if(!file.exists()) {
		 System.out.println(fileName + "파일이 없습니다.");;
		 System.out.println("파일 전송 중단");
		 return;
	 }
	 Socket socket = null;
	 DataOutputStream dout = null;
	 BufferedInputStream bin = null;
	 BufferedOutputStream bout = null;
	 
	 try {
		socket = new Socket("localhost", 7777);
		
		System.out.println("서버에 접속 완료!");
		System.out.println();
		System.out.println("파일 전송 시작!");
		
		dout = new DataOutputStream(socket.getOutputStream());
		
		// 서버에 접속하면 처음으로 파일명 전송하기
		dout.writeUTF(fileName);
		
		// 파일 전송을 위한 스트림 객체 생성
		bin = new BufferedInputStream(new FileInputStream(file));
		bout = new BufferedOutputStream(dout);
		
		// 파일을 읽어서 소켓으로 출력하기
		byte[] temp = new byte[1024];
		int length = 0;
		while((length = bin.read(temp)) != -1) {
			bout.write(temp, 0, length);
		}
		bout.flush();
		
		System.out.println("파일 전송 완료");
	} catch (IOException e) {
		System.out.println("파일 전송 실패!!!");
		e.printStackTrace();
		
	} finally {
		// 자원 반납
		if(dout != null) try { dout.close();} catch( IOException e) {}
		if(bin != null) try { bin.close();} catch (IOException e) {}
		if(bout != null) try { bout.close();} catch ( IOException e) {}
		if(socket != null) try { socket.close();} catch ( IOException e) {}
	}
	}
}
	
