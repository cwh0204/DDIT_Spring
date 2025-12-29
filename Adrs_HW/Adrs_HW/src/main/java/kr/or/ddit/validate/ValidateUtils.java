package kr.or.ddit.validate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import kr.or.ddit.dto.MemberDTO;

public class ValidateUtils {
	
	private static Validator validator;
	
	static {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();		
	}
	
	public static <T> Map<String, String> validate(T target, Class<?>...groups) {
		Set<ConstraintViolation<T>> constraintViolation =
				validator.validate(target, groups);
		Map<String,String> errors = new HashMap<>();
		for(ConstraintViolation<T> sigleViolation : constraintViolation) {
			String property = sigleViolation.getPropertyPath().toString();
			String message = sigleViolation.getMessage();
			errors.put(property, message);
		}
		return errors;
	}
}
