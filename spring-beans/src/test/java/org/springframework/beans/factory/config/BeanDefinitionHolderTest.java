package org.springframework.beans.factory.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

/**
 * 通过这个测试类，熟悉 Holder 的解析过程。源码中通过以下调用得到 holder: BeanDefinitionHolder bdHolder = delegate.parseBeanDefinitionElement(ele);
 */
class BeanDefinitionHolderTest {
	@Test
	public void withOpenInputStreamAndExplicitValidationMode() {
		SimpleBeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
		Resource resource = new InputStreamResource(getClass().getResourceAsStream("BeanDefinitionHolder-test.xml"));
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
		reader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_DTD);
		reader.loadBeanDefinitions(resource);
		System.out.println(registry.getBeanDefinitionCount());
	}
}