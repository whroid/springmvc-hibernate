package com.anialy.webproj.platform.spring.ext;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.context.ApplicationContext;

/**
 * Spring封装服务定位器
 * <p>
 * 可以通过两种方法初始化:
 * <li>提供一个org.springframework.context.ApplicationContext实例;</li>
 * <li>提供spring配置文件的相对路径,相对路径的跟为当前项目的classpath路径</li>
 *
 */
public class BeanLocator {

	/**
	 * <p>
	 * 获得Spring中指定名称的bean
	 *
	 * @param name bean name
	 * @param clazz type of bean
	 * @return spring object
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		//		if (!init.get()) {
		//			init();
		//		}

		return (T) applicationContext.getBean(name);
	}

	public static <T> T getBean(Class<T> clazz) {
		//		if (!init.get()) {
		//			init();
		//		}

		String[] names = applicationContext.getBeanNamesForType(clazz);
		if (names == null || names.length == 0) {
			return null;
		}
		return applicationContext.getBean(names[0], clazz);
	}

	public static <T> List<T> getBeans(Class<T> clazz) {
		//		if (!init.get()) {
		//			init();
		//		}

		String[] names = applicationContext.getBeanNamesForType(clazz);
		if (names == null || names.length == 0) {
			return null;
		}
		List<T> beans = new ArrayList<T>();
		for (String name : names) {
			beans.add(applicationContext.getBean(name, clazz));
		}
		return beans;
	}

	public static <T> T getBean(Class<T> clazz, BeanSelector<T> selector) {
		//		if (!init.get()) {
		//			init();
		//		}

		String[] names = applicationContext.getBeanNamesForType(clazz);
		if (names == null || names.length == 0) {
			return null;
		}

		for (String beanName : names) {
			T bean = applicationContext.getBean(beanName, clazz);
			if (selector.select(bean, applicationContext)) {
				return bean;
			}
		}

		return null;
	}

	private static ApplicationContext applicationContext = null;

	public synchronized static void setApplicationContext(ApplicationContext applicationContext) {
		BeanLocator.applicationContext = applicationContext;
		init.set(applicationContext != null);
	}

	//	public static ApplicationContext getApplicationContext() {
	//		return applicationContext;
	//	}

	private static AtomicBoolean init = new AtomicBoolean(false);

	//	public synchronized static void init() {
	//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:*.context.xml");
	//		context.start();
	//		applicationContext = context;
	//		init.set(true);
	//	}

	/**
	 * context可能存在多个满足条件的bean,由用户实现选择逻辑
	 *
	 * @since 2010-8-16
	 * @author pippo
	 * @param <T>
	 */
	public static interface BeanSelector<T> {

		boolean select(T bean, ApplicationContext context);

	}

}
