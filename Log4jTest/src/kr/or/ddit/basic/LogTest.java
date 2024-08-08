package kr.or.ddit.basic;

import org.apache.log4j.Logger;

public class LogTest {
	// Logger클래스의 인스턴스를 받아온다.
	private static Logger logger = Logger.getLogger(LogTest.class);
	
	public static void main(String[] args) {
		// 로그 메시지 출력하기
		// 형식) Logger객체.로그레벨이름("메시지");
		System.out.println("로그 출력 시작..!");
		
		logger.trace("이것은 Log4j의 [ TRACE ]레벨의 메시지 입니다.");
		logger.debug("이것은 Log4j의 [ DEBUG ]레벨의 메시지 입니다.");
		logger.info("이것은 Log4j의 [ INFO ]레벨의 메시지 입니다.");
		logger.warn("이것은 Log4j의 [ WARN ]레벨의 메시지 입니다.");
		logger.error("이것은 Log4j의 [ ERROR ]레벨의 메시지 입니다.");
		logger.fatal("이것은 Log4j의 [ FATAL ]레벨의 메시지 입니다.");
	}
}
