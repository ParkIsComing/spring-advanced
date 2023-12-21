package hello.proxy.postprocessor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

public class BasicTest {

	@Test
	void basicConfig() {
		// 스프링 컨테이너 생성 및 스프링 빈 등록
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

		A a = applicationContext.getBean("beanA", A.class);
		a.helloA();

		Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(B.class));
	}

	@Slf4j
	@Configuration
	static class BasicConfig {
		@Bean(name = "beanA")
		public A a() {
			return new A();
		}
	}

	@Slf4j
	static class A {
		public void helloA() {
			log.info("hello A");
		}
	}

	@Slf4j
	static class B {
		public void helloB() {
			log.info("hello B");
		}
	}
}
