package kr.or.ddit.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

public class ValidateUtils {
	private static Validator validator;
	
	static {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	public static <T> boolean validate(T target, Map<String, List<String>> errors, Class...groups) {
		Set<ConstraintViolation<T>> violations = validator.validate(target, groups);
		for(ConstraintViolation<T> single : violations) {
			String propName = single.getPropertyPath().toString();
			String message = single.getMessage();
			List<String> messages = errors.get(propName);
			if(messages == null) {
				messages = new ArrayList<String>();
				errors.put(propName, messages);
			}
			messages.add(message);
		}
		boolean valid = violations.isEmpty();
		return valid;
	}
}
