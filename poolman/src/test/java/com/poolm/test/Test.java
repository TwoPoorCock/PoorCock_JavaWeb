package com.poolm.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.poolm.service.admin.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class Test {

	private static Logger logger = Logger.getLogger(Test.class);

	@Resource
	private UserService userService = null;

	@org.junit.Test
	public void test() {
		System.out.println("abccdwe".matches(".*\\d.*"));
	}
}
