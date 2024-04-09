package kr.or.ddit.resource;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;

import org.apache.commons.io.IOUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;

import lombok.extern.slf4j.Slf4j;

/**
 * Resource : 스프링에서 자원을 표현하는 추상 타입.
 * 	- 자원의 위치와 자원에 대한 접근 방법에 따른 구현체 지원
 *  1. FileSystemResource : 물리 경로
 *  2. ClassPathResource : 클래스패스 이후의 qualified name(논리경로)을 통해 접근
 *  3. UrlResource : 웹상에 존재하는 url(논리경로) 로 접근하는 자원
 *  
 *  ResourceLoader : 자원에 분류와 관계없이 일관된 방법으로 Resource를 로딩해주는 객체. -> readOnly만 가능
 *  	-> 읽는 용도의 파일이 많이 때문에 기본적으로 읽기만 가능하고 필요하다면 outputstream로 사용할 수 있다.
 *  
 * 
 */
@Slf4j
public class ResourceLoaderDesc {
	public static void main(String[] args) throws IOException {
		ResourceLoader loader = new ClassPathXmlApplicationContext();
		
		Resource fsRes = loader.getResource("file:D:/00.medias/another_day.txt");
		log.info("file system resource : {}", fsRes);
		Resource cpRes = loader.getResource("classpath:/kr/or/ddit/MemberData.properties");
		log.info("classpath system resource : {}", cpRes);
		Resource urlRes = loader.getResource("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
		log.info("url system resource : {}", urlRes);
		
		File destFolder = new File("D:/00.medias");
		copyResource(cpRes, destFolder);
		copyResource(urlRes, destFolder);
	}
	
	//파일 복사 util
	private static void copyResource(Resource src, File destFolder) {
		String fileName = src.getFilename();
		log.info("filename : {}", fileName);
		
		File destFile = new File(destFolder, fileName);
		
		WritableResource destRes = new FileSystemResource(destFile);
		try(
			InputStream is = src.getInputStream();
			OutputStream os = destRes.getOutputStream();	
		){
			//commons util 사용
			IOUtils.copy(is, os);
			
		}catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		
		

		
		
		
	}
}
