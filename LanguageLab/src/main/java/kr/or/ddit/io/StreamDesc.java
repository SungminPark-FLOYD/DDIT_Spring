package kr.or.ddit.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 스트림(stream)
 * 	:연속성을 지닌 순차적인 일련의 데이터 집합이면서 데이터 집합이 이동하는 단방향 통로
 * 
 * 스트림의 분류
 * 1. 전송 데이터 크기
 * 	1) 1byte : byte stream, ~~In[Out]putStream
 * 		FileInputStream/FileOutputStream
 * 		ByteArrayInputStream/ByteArrayOutputStream
 * 		SocketInputStream(읽기)/SocketOutputStream(쓰기)
 * 	2) 2byte (1 char) : character stream, ~~Reader[Writer]
 * 		StringReader(읽기)/StringWriter(쓰기)
 * 2. 스트림 생성 방법과 필터링 여부에 따른 분류
 * 	1) 1차 스트림 : 매체에 직접 연결
 * 			ex) FileInputStream
 *  2) 2차 스트림(연결형 스트림) : 1차 스트림에 연결형으로 생성되는 스트림
 *  		ex) BufferdInputStream, BufferdWriter
 * 
 * 스트림 사용 단계
 * 1. media(매체)를 객체화 시킨다
 * 		ex) new File(), new byte[], Socket.open
 * 2. media 종류에 따른 입출력 스트림 개방
 * 		ex) FileInputStream(media)/FileReader(media) : 항상 기본형 생성자가 없기 때문에 미디어를 넘겨주어야 한다
 * --(optional) 2차 스트림으로 전송 효율을 높일 수 있음.
 * 3. 전송크기에 따라 전송데이터의 끝(EOF : -1, null) 반복적인 i(read..)/o(writer..) 작업 수행
 * 4. media release 단계(close)
 */
public class StreamDesc {
	public static void main(String[] args) throws IOException {
		//readEngString_byteStream();
		//readEngString_charStream();
//		readKorString_charStream();
		readKorString_trywithResource();
	}

	private static void readKorString_trywithResource() throws IOException{
		File txtFile = new File("D:/00.medias/ETA_ANSI.txt");
//		try(Closable 객체 선언문) {} -> 자동으로 finally추가
		try(
			FileInputStream fis = new FileInputStream(txtFile);
			//캐릭터 셋 설정같이 해주기
			InputStreamReader isr = new InputStreamReader(fis, "ms949");
			BufferedReader reader = new BufferedReader(isr);
			
		){
		
		String readStr = null;
		while((readStr = reader.readLine())!= null) {
			System.out.println(readStr);
			}
		}
		
	}

	private static void readKorString_charStream()  throws IOException {
		File txtFile = new File("D:/00.medias/ETA_ANSI.txt");
		FileInputStream fis = null;
		//캐릭터 셋 설정같이 해주기      
		InputStreamReader isr = null;
		BufferedReader reader = null;
		try {
			fis = new FileInputStream(txtFile);
			//캐릭터 셋 설정같이 해주기
			isr = new InputStreamReader(fis, "ms949");
			reader = new BufferedReader(isr);
			String readStr = null;
			while((readStr = reader.readLine())!= null) {
				System.out.println(readStr);
			}
		}finally {
			if(fis != null)
				fis.close();
			if(isr != null)
				isr.close();
			if(reader != null)
				reader.close();
		}
		
//		int readByte = -1;
//		while((readByte = fr.read())!= -1) {
//			System.out.print((char)readByte);
//		}
		
		
	}

	private static void readEngString_charStream() throws IOException {
		File txtFile = new File("D:/00.medias/another_day.txt");
		FileReader fr = new FileReader(txtFile);
		BufferedReader reader = new BufferedReader(fr);
		String readStr = null;
		while((readStr = reader.readLine())!= null) {
			System.out.println(readStr);
		}
//		int readByte = -1;
//		while((readByte = reader.read())!= -1) {
//			System.out.print((char)readByte);
//		}
		fr.close();
		reader.close();

		
	}

	private static void readEngString_byteStream() throws IOException{
		File txtFile = new File("D:/00.medias/another_day.txt");
		FileInputStream fis = new FileInputStream(txtFile);
		BufferedInputStream bis = new BufferedInputStream(fis);
		int readByte = -1;
		while((readByte = bis.read())!= -1) {
			System.out.print((char)readByte);
		}
		fis.close();
		bis.close();

		
		
	}
}
