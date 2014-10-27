package com.anialy.webproj.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.anialy.webproj.model.User;

@Repository
public class UserDao{
	@Resource
	protected SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
 
	public void saveUser(User user){  
		this.getSession().save(user);  
	}  
}
