package com.anialy.webproj.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anialy.webproj.common.ResponseEntity;
import com.anialy.webproj.controller.base.BaseController;
import com.anialy.webproj.httpservice.entity.Person;

@Controller
@RequestMapping("/Test")
public class TestController extends BaseController{
	private static Logger logger 
         = LoggerFactory.getLogger(TestController.class);

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/test/{name}", method=RequestMethod.GET
			, produces="application/json")
	public @ResponseBody ResponseEntity<Person> test(@PathVariable String name) {
		Person person = new Person(name, 23);
		logger.info("param is： {} !", name);
		responseEntity.setPayload(person);
		return responseEntity;
	}
}
