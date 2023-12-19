package hello.proxy.cglib;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CglibTest {
	@Test
	void cglib() {
		ConcreteService target = new ConcreteService();
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(ConcreteService.class); //구체 클래스 기반 프록시 생성
		enhancer.setCallback(new TimeMethodInterceptor(target));
		ConcreteService proxy = (ConcreteService)enhancer.create();
		log.info("targetClass={}", target.getClass());
		log.info("proxyClass={}", proxy.getClass());

		proxy.call();
	}
}
