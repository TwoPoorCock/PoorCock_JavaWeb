package com.poolm.test;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.poolm.pojo.User;
import com.poolm.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class Test {

	private static Logger logger = Logger.getLogger(Test.class);
	
	@Resource
	private IUserService userService = null;
	
	@org.junit.Test
	public void test() {
		User user = userService.getUserById(1);
		logger.info(JSON.toJSONString(user));
	}

}
