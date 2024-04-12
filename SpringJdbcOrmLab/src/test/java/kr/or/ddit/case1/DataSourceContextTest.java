package kr.or.ddit.case1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitConfig(locations = "classpath:/kr/or/ddit/case1/conf/*-context.xml")
class DataSourceContextTest {
	
	@Autowired
	Properties dbInfo;
	@Autowired
	DataSource datasource;
	@Resource(name = "jdbcTemplate")
	JdbcTemplate jdbctemplate;
	
	@Test
	void test() {
		log.info("dbInfo : {}",dbInfo);
	}
	@Test
	void test2() {
		log.info("datasource : {}",datasource);
	}
	
	@Test
	void text4() {
		log.info("jdbc template : {}", jdbctemplate);
	}
}
