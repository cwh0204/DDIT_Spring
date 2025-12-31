package kr.or.ddit.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang3.function.Failable;

public class PopulateUtils {
	 public static void populate(final Object bean, final Map<String, ? extends Object> properties)
	 {
		 Converter timeConverter = new Converter() {
				
				@Override
				public <T> T convert(Class<T> type, Object value) {
					try {
						Method parseMethod = type.getMethod("parse", CharSequence.class);
						
						return (T) Optional.ofNullable(value)
									.filter(v->!v.toString().isBlank())
									.map(Failable.asFunction(v-> parseMethod.invoke(null, v.toString())))
									.orElse(null);
					}catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			};
			
			ConvertUtils.register(timeConverter, LocalDate.class);
			ConvertUtils.register(timeConverter, LocalDateTime.class);
			
			try {
				BeanUtils.populate(bean, properties);
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
	 } 
		        
}
















