package com.anialy.webproj.model;

import javax.persistence.Entity;

import com.anialy.webproj.framework.model.base.IDEntity;

@Entity
public class User extends IDEntity{
	private static final long serialVersionUID = 8628001128901841011L;
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
