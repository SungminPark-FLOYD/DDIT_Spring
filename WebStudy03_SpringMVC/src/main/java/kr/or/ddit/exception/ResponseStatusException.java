package kr.or.ddit.exception;

public class ResponseStatusException extends RuntimeException {
	private int status;
	
	//생성자 생성(alt+shift+s) 후 파라미터에 int status 삽입
	public ResponseStatusException(int status, String message) {
		super(message);
		this.status= status;
	}

	public ResponseStatusException(int status) {
		this(status, String.format("%d 상태코드 결정을 위한 예외", status));//생성자에서 또다른 생성자를 호출할수 있음
	}
	
	
	public int getStatus() {
		return status;
	}
	
}
