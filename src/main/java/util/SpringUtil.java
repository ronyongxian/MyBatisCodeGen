package util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {

	private static ApplicationContext ctx 
	= new ClassPathXmlApplicationContext("spring/applicationContext.xml");

	public static <T> T getBean(Class<T> klass){
		return ctx.getBean(klass);
	}
	
}
