package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
*  서버는 클라이언트가 파일을 전송해 오면 그 파일을 받아서 서버의 'd:/d_other/연습용'폴더에 저장한다.
*  (Socket으로 읽어서 파일로 출력하기)
*/
public class TcpFileServer {
	public static void main(String[] args) {
		new TcpFileServer().serverStart();
	}

	private void serverStart() {
		File file = new File("d:/d_other/연습용");		// 저장할 폴더 설정
		if(!file.exists()) {			// 저장할 폴더가 없으면 새로 생성한다.
			file.mkdir();
		}
		ServerSocket server = null;
		Socket socket = null;
		DataInputStream din = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다..");
			System.out.println();
			
			socket = server.accept();		// 클라이언트의 요청을 기다린다.
			
			System.out.println("파일 수신 시작...");
			
			// 클라이언트가 서버와 접속해서 처음으로 보내오는 데이터를 받는다.
			// (파일명 받기)
			din = new DataInputStream(socket.getInputStream());
			String fileName = din.readUTF();
			
			// 파일 저장 폴더와 파일명을 지정하여 File 객체 생성
			File saveFile = new File(file, fileName);
			
			// 파일 입출력용 스트림 객체 생성
			bin = new BufferedInputStream(din);
			bout = new BufferedOutputStream(new FileOutputStream(saveFile));
			
			byte[] temp = new byte[1024];
			int length = 0;
			while((length = bin.read(temp)) != -1) {
				bout.write(temp, 0, length);
			}
			bout.flush();
			
			System.out.println("파일 수신 완료!");
		
	
		} catch (IOException e) {
			System.out.println("파일 수신 실패!");
			e.printStackTrace();
		} finally {
			
			if(din != null) try {din.close();} catch(IOException e) {}
			if(bin != null) try {bin.close(); }catch(IOException e) {}
			if(bout != null) try {bout.close(); }catch(IOException e) {}
			if(socket != null) try {socket.close(); }catch(IOException e) {}
			if(server != null ) try {server.close(); }catch(IOException e) {}
			
		}
	}
}
