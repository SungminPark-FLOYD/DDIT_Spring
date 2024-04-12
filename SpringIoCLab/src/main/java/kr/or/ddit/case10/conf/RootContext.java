package kr.or.ddit.case10.conf;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan(basePackages = "kr.or.ddit.case5", 
	excludeFilters = {@Filter(classes = Controller.class)} )
public class RootContext {
	@Bean
	public Properties propBak(@Value("classpath:/kr/or/ddit/MemberData.properties") Resource cpRes) throws IOException {
		Properties props = new Properties();
		props.load(cpRes.getInputStream());
		return props;
	}
	
	@Bean
	public PropertiesFactoryBean props(@Value("classpath:/kr/or/ddit/MemberData.properties") Resource cpRes) {
		PropertiesFactoryBean props = new PropertiesFactoryBean();
		props.setLocation(cpRes);
		return props;
	}

}
