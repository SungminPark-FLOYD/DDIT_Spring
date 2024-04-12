package kr.or.ddit.case9.conf;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.case9.obj.Bibitan;
import kr.or.ddit.case9.obj.Gun;
import kr.or.ddit.case9.obj.Hunter;
import kr.or.ddit.case9.obj.HuntinDog;
import kr.or.ddit.case9.obj.RifleGun;

//scope설정과 lazy설정 어노테이션으로 가능하다
@Configuration
@Lazy
@ComponentScan("kr.or.ddit.case9.obj")
public class Case9Context {
	
////	bean등록하면서 id값주기 value는 바로 쓸수있다.
//	@Bean("dog")
//	public HuntinDog dog() {
//		return new HuntinDog();
//	}
//	
////	이름지정안하면 메소드이름이 id값이 된다
//	@Bean
//	@Scope("prototype")
//	public Gun rifleGun() {
//		return new RifleGun();
//	}
//	
//	
//	@Bean
//	@Scope("prototype")
//	
//	public Gun bibitan() {
//		return new Bibitan();
//	}
//	
////	@Autowired를 파라미터로쓰면 파라미터 이름을 통해서 주입받는다
//	@Bean
//	public Hunter hunter1(@Autowired Gun rifleGun, HuntinDog dog) {
//		Hunter hunter = new Hunter(rifleGun);
//		hunter.setDog(dog);
//		return hunter;
//	}
//	@Bean
//	public Hunter hunter2(@Autowired Gun rifleGun, HuntinDog dog) {
//		Hunter hunter = new Hunter(rifleGun);
//		hunter.setDog(dog);
//		return hunter;
//	}
}
