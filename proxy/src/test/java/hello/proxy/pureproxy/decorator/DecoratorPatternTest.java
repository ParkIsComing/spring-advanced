package hello.proxy.pureproxy.decorator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.proxy.pureproxy.decorator.code.Component;
import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.proxy.pureproxy.decorator.code.RealComponent;
import hello.proxy.pureproxy.decorator.code.TimeDecorator;
import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecoratorPatternTest {
	
	@Test
	void noDecorator() {
		Component realComponent = new RealComponent();
		DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
		client.execute();
	}

	@Test
	@DisplayName("1개의 데코레이터 적용")
	void decorator1() {
		Component realComponent = new RealComponent();
		Component messageDecorator = new MessageDecorator(realComponent);
		DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
		client.execute();
	}

	@Test
	@DisplayName("2개의 데코레이터를 연쇄적으로 적용")
	void decorator2() {
		Component realComponent = new RealComponent();
		Component messageDecorator = new MessageDecorator(realComponent); // 두 개의 데코레이터
		Component timeDecorator = new TimeDecorator(messageDecorator);
		DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
		client.execute();
	}

}
