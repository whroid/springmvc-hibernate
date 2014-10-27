package dao;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.anialy.webproj.dao.UserDao;
import com.anialy.webproj.model.User;
import com.anialy.webproj.platform.spring.ext.BeanLocator;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath:applicationContext.xml",
})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest {

	
/*	@Before
	public void init() {
		BeanLocator.setApplicationContext(context);
	}

	@Resource
	private ApplicationContext context;*/

	@Resource
	private UserDao userDao;
	
	
	@Test
	@Transactional
	public void test_user_save() {
		User user = new User();
		user.setName("anialy");
		user.setAge(21);
		userDao.saveUser(user);
	}


	@After
	public void result(){
		System.out.println(
				"-------------------------------------------------------\n"
						+ "" +
				"\n-------------------------------------------------------\n");
	}



}
