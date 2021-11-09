package com.jk.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext("com.jk.bean");
		System.out.println(ctx.getBean("user"));
		System.out.println(ctx.getBean("account"));

		Account account = (Account)ctx.getBean("account");
		account.setName("Jerry");
	}
}
