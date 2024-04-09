package kr.or.ddit.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.AbstractConverter;

import kr.or.ddit.vo.ProdVO;

public class PopulateUtils {
	
	//메소드 시그니처가 T라는 것을 알려 주어야한다
	public static <T> void populate(T bean, Map<String, ? extends Object> paramMap) {
		//localDateTime 변환하기 위한 converter 내부 정의
		AbstractConverter localDateConverter = new AbstractConverter() {
			
			@Override
			protected Class<?> getDefaultType() {
				return Temporal.class;
			}
			
			@Override
			protected <T> T convertToType(Class<T> type, Object value) throws Throwable {
				//변환할 값이 없을때
				if(value == null || value.toString().isEmpty())
					return null;
				else {
					//req에 있는 데이터 변환
					String paramValue = value.toString();
//					return (T) LocalDate.parse(paramValue);
//					return (T) LocalDateTime.parse(paramValue);
					
					//static 메소드는 객체가 없어도 접근이 가능하기때문에 null을 넣고 클래스에 접근한다
					return (T) type.getDeclaredMethod("parse", CharSequence.class).invoke(null, paramValue);
				}
			}
		};
		
		ConvertUtils.register(localDateConverter, LocalDate.class);
		ConvertUtils.register(localDateConverter, LocalDateTime.class);
		
		//사용되는 프로퍼티들을 bean에 넣어주는 작업 -> key와 vo의 프로퍼티 이름이 같으면 bean으로 넣어준다
		try {
			BeanUtils.populate(bean, paramMap);
		} catch (Exception e) {
			throw new RuntimeException(e);
//					String insdate = req.getParameter("prodInsdate");
//					LocalDate prodInsdate = LocalDate.parse(insdate);
//					prod.setProdInsdate(prodInsdate);
		}
	}
}
