package com.jk;

import com.jk.bean.Account;
import com.jk.bean.AppConfig;
import com.jk.bean.ChildService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * Test samples from https://zhuanlan.zhihu.com/p/189896257, 介绍 bean definition 的基础知识
 */
public class BeanDefinitionTests {
	@Test
	public void test_def_attribute_field() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Account.class);
		BeanDefinition accountDef = ctx.getBeanDefinition("account");
		for (String attrs : accountDef.attributeNames()) {
			System.out.println(attrs + " - " + accountDef.getAttribute(attrs));
		}
	}

	@Test
	public void test_parent_relationship() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		GenericBeanDefinition parentBeanDefinition = new GenericBeanDefinition();
		parentBeanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
		parentBeanDefinition.setAttribute("name", "codebear");
		parentBeanDefinition.setAbstract(true);
		parentBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(1);
		parentBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue("CodeBear");

		GenericBeanDefinition childBeanDefinition = new GenericBeanDefinition();
		childBeanDefinition.setParentName("parent");
		childBeanDefinition.setBeanClass(ChildService.class);

		context.registerBeanDefinition("parent", parentBeanDefinition);
		context.registerBeanDefinition("child", childBeanDefinition);

		context.refresh();

		BeanDefinition child = context.getBeanFactory().getMergedBeanDefinition("child");
		for (String s : child.attributeNames()) {
			System.out.println(s + ":" + child.getAttribute(s));
		}
		System.out.println("scope:" + child.getScope());

		System.out.println("-------------------");

		ChildService service = context.getBean(ChildService.class);
		System.out.println(service.getName());
		System.out.println(service.getId());
	}

	@Test
	public void test_all_supported_bean_definition_sub_type() {
		// GenericBeanDefinition - 通过 xml 定义的 bean
		ClassPathXmlApplicationContext pathCtx = new ClassPathXmlApplicationContext("app.xml");
		System.out.println(pathCtx.getBeanFactory().getBeanDefinition("account").getClass());
		//class org.springframework.beans.factory.support.GenericBeanDefinition

		// AnnotatedGenericBeanDefinition - 通过注解扫描到的 bean
		AnnotationConfigApplicationContext annCtx = new AnnotationConfigApplicationContext(Account.class);
		System.out.println(annCtx.getBeanDefinition("account").getClass());
		// class org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition

		// ScannedGenericBeanDefinition - 通过路径扫描的方式加载到的 bean
		AnnotationConfigApplicationContext annCtx2 = new AnnotationConfigApplicationContext("com.jk.bean");
		System.out.println(annCtx2.getBeanDefinition("account").getClass());
		// class org.springframework.context.annotation.ScannedGenericBeanDefinition

		// ConfigurationClassBeanDefinition - 以@Bean注解标记的会解析为该类型
		AnnotationConfigApplicationContext annCtx3 = new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println(annCtx3.getBeanDefinition("person").getClass());
		// class org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader$ConfigurationClassBeanDefinition
	}
}