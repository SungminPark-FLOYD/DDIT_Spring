package kr.or.ddit.enumpkg;

public enum BrowserInfo {
	//enum은 생성자가 기본적으로 private이다
	//enum안에서 상수들의 순서가 유지가 된다.
	//상수는 기본적으로 대문자로 작성한다
	EDG("엣지"),
	WHALE("웨일"),
	CHROME("크롬"),
	SAFARI("사파리"),
	OTHERS("기타");
	
	// 상수가 만들어지는 시점에 정해져야함
	private String browserName;

	private BrowserInfo(String browserName) {
		this.browserName = browserName;
	} 
	
	public String getBrowserName() {
		return browserName;
	}
	
	public static BrowserInfo findBrowser(String userAgent) {
		BrowserInfo finded = OTHERS;
		for(BrowserInfo single : values()) {
			if(userAgent.toUpperCase().contains(single.name())) {
				finded = single;
				break;
			}
		}		
		return finded;
	}
	
	public static String findBrowserName(String userAgent) {
		BrowserInfo info = findBrowser(userAgent);
		return info.getBrowserName();
	}
		
}
