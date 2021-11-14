package com.jk.beans.factory.xml;


import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * 发现 Spring 中那些类的测试真是宝藏，你时常可以从中发现之前看代码时忽略的一些细节
 *
 * 针对 XmlBeanDefinitionReader 从他的构造函数，我们可以体会到，他针对的是 definition bean register
 * e.g. XmlBeanDefinitionReader(BeanDefinitionRegistry registry)
 * 当我们使用这个 reader 的时候，他会读取配置文件中的信息，并将它塞到 register(注册表) 中
 */
public class MyXmlBeanDefinitionReaderTests {

	@Test
	public void init_with_register() {
		SimpleBeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
		reader.loadBeanDefinitions(new ClassPathResource("com/jk/beans/factory/xml/app.xml"));
		reader.loadBeanDefinitions(new ClassPathResource("app.xml", getClass()));
		System.out.println("Def names: " + Arrays.toString(registry.getBeanDefinitionNames()));
	}
}
