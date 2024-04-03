package kr.or.ddit.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * encoding(부호화) : 전송이나 저장을 목적으로 매체를 이용해서 데이터를 이동시킬때
 * 			, 해당 매체가 인식할 수 있는 방식으로 데이터의 표현을 바꾸는 작업
 * 			decoding으로 원문 복원이 누구나 가능함
 * encrypting(암호화) : 허가받지 않은 접근을 막고, 권한이 없는 사용자의 리딩(스니핑)이나 변경(스푸핑)을 제어하기 위해 
 * 					 	키를 기준으로 데이터를 변환하는 작업  
 *
 *	단방향 암호화 : decrypting으로 평문 복원이 불가능한 방식. 주로 비밀번호 암호화에 사용됨
 *				SHA-128[256, 512] bit단위 길이가 길수록 보안의 강도가 강해진다
 *				hash함수 : 함수의 결과가 일정한 길이의 데이터로 출력되는 함수
 *	양방향 암호화 : 키를 통해 decryting이 가능한 방식, DRM, 전자서명
 *		대칭키(비밀키) : 동일키(비밀키)로 암호화 / 복호화를 진행하는 방식
 *					: AES-128[256]
 *	 	비대칭키 : 한쌍의 키로 암호화/복호화를 진행하는 방식
 *				공개키 / 개인키 2개의 키페어로 암복호화 진행
 *				RSA-1024[2048, 4096]
 */
public class EncrypDesc {
	public static void main(String[] args) throws Exception  {
		String plain = "암호화전일반평문, 변경을 한 데이터.";
		
		//RSA 지수연산을 하기때문에 시간이 오래걸린다 -> 장문의 데이터는 사용하지 않는다
		byte[] input = plain.getBytes();
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		//키 길이 설정
		keyPairGen.initialize(4096);
		KeyPair pair = keyPairGen.generateKeyPair();
		PrivateKey privateKey = pair.getPrivate();
		PublicKey publiceKey = pair.getPublic();
		
		//암호화
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] encrypted = cipher.doFinal(input);
		
		//부호화
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		System.out.println(encoded);
		
		//복호화
		cipher.init(Cipher.DECRYPT_MODE, publiceKey);
		byte[] decoded = Base64.getDecoder().decode(encoded);
		byte[] decrypted = cipher.doFinal(decoded);
		plain = new String(decrypted);
		System.out.println(plain);
	}
	
	private void encryptAES(String plain) throws Exception {
		//AES 블럭단위로 암호화하기때문에 맨 앞의 블럭도 체이닝하기 위해서 초기화 값을 넣어준다.
		String ivValue = "초기화 벡터값";
		MessageDigest md = MessageDigest.getInstance("md5");
		
		
		byte[] input = plain.getBytes();
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		//키 생성
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256);
		SecretKey secretKey =  keyGen.generateKey();
		
		//암호화
		
		byte[] iv = md.digest(ivValue.getBytes());//16바이트짜리 해시 코드 생성
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
		byte[] encrypted = cipher.doFinal(input);
		
		//부호화
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		
		System.out.println(encoded);
		
		//복호화
		byte[] decoded = Base64.getDecoder().decode(encoded);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
		byte[] decryted = cipher.doFinal(decoded);
		plain = new String(decryted);
		System.out.println(plain);
	}
	
	/**
	 * Sha-512 해시 함수로 단방향 암호화
	 * @param plain
	 * @return 암호문을 Base64방식으로 인코딩한 텍스트
	 * @throws Exception
	 */
	private String encryptSha512(String plain) throws Exception{
		byte[] input = plain.getBytes();
		
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] encrypted = md.digest(input);
		System.out.printf("암호문의 길이 : %d\n", encrypted.length * 8);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		
		System.out.println(encoded);
		
		return encoded;
	}

}
