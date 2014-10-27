package com.anialy.webproj.httpservice.service.impl;

import com.anialy.webproj.httpservice.entity.Person;
import com.anialy.webproj.httpservice.service.PersonService;

public class PersonServiceImpl implements PersonService{

	public Person getPersonByName(String name) {
		if("anialy".equals(name))
			return new Person("anialy", 100);
		return null;
	}

}
