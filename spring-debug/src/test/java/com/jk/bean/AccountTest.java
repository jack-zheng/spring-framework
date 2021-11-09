package com.jk.bean;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class AccountTest {
	@Test
	public void test() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Account.class);
		System.out.println(ctx.getBean("account"));
	}
}