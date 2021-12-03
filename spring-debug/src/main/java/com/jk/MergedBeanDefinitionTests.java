package com.jk;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.Test;

public class MergedBeanDefinitionTests {
	/**
	 * 从 https://blog.csdn.net/taiyangdao/article/details/51063481 得到的灵感，def 相关的东西应该是 factory 的职责
	 */
	@Test
	public void test_merged_in_xml() {
		DefaultListableBeanFactory registry = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
		reader.loadBeanDefinitions(new ClassPathResource("merged.xml"));
		for (String beanDefinitionName : registry.getBeanDefinitionNames()) {
			System.out.println("----> " + beanDefinitionName);
		}
		BeanDefinition bd1 = registry.getMergedBeanDefinition("child");
		System.out.println("bd1 props: " + bd1.getPropertyValues());

		BeanDefinition bd2 = registry.getBeanDefinition("child");
		System.out.println("bd2 props: " + bd2.getPropertyValues());
	}

	@Test
	public void test_merged_with_annotation() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.jk.bean");
		System.out.println("ann ctx: " + ctx.getBeanDefinition("child").getPropertyValues());
	}
}
