package com.jk.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Child extends Parent {
	@Value("12")
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Child{" +
				"name=" + getName() + ", " +
				"age=" + age +
				'}';
	}
}
