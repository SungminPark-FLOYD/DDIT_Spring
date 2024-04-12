package kr.or.ddit.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomServletContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("서버 구동 후 컨텍스트 시작, 상위 컨테이너 구동 지점");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("컨텍스트 종료, 상위 컨테이너 종료");
	}

}
