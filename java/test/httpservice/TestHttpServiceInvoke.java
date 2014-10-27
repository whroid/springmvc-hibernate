package httpservice;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anialy.webproj.httpservice.entity.Person;
import com.anialy.webproj.httpservice.service.PersonService;

public class TestHttpServiceInvoke {
	private static final Logger logger 
        = LoggerFactory.getLogger(TestHttpServiceInvoke.class);
	
	private ApplicationContext ctx;
	private PersonService personService;

	@Before
	public void init() throws IOException{
		ctx = new ClassPathXmlApplicationContext(new String[]{
				"classpath*:/applicationContext-client-http-service.xml",
				"classpath*:/applicationContext-constants.xml"
		});
		
		personService = (PersonService)  ctx.getBean("personService");
		System.out.println("");
	}
	
	
	@Test
	public void test() {
		Person person = personService.getPersonByName("anialy");
		logger.info("姓名：" + person.getName());
		logger.info("年龄：" + person.getAge());
	}
}
