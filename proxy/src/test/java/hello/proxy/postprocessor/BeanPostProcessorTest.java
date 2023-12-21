package hello.proxy.postprocessor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

public class BeanPostProcessorTest {

	@Test
	void basicConfig() {
		// 스프링 컨테이너 생성 및 스프링 빈 등록
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

		// B 객체가 beanA라는 이름의 빈으로 등록된다.
		B b = applicationContext.getBean("beanA", B.class);
		b.helloB();

		// 객체 A는 빈으로 등록되지 않는다.
		Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(A.class));
	}

	@Slf4j
	@Configuration
	static class BeanPostProcessorConfig {
		@Bean(name = "beanA")
		public A a() {
			return new A();
		}

		// 빈 후처리기 (BeanPostProcessor 인터페이스 구현하고 스프링 빈으로 등록하여 사용)
		@Bean
		public AToBPostProcessor helloPostProcessor() {
			return new AToBPostProcessor();
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

	@Slf4j
	static class AToBPostProcessor implements BeanPostProcessor {
		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
			log.info("beanName={} bean={}", beanName, bean);
			// 빈 후처리 로직
			if (bean instanceof  A) {
				return new B();
			}
			return bean;
		}
	}
}
