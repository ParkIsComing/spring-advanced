package hello.proxy.pureproxy.proxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;

public class ProxyPatternTest {
	@Test
	@DisplayName("프록시 사용 ‹x")
	void noProxyTest() {
		RealSubject realSubject = new RealSubject();
		ProxyPatternClient client = new ProxyPatternClient(realSubject);
		client.execute();
		client.execute();
		client.execute();

	}

	@Test
	@DisplayName("프록시 사용 o")
	void cacheProxyTest() {
		RealSubject realSubject = new RealSubject();
		CacheProxy proxy = new CacheProxy(realSubject); // 프록시는 RealSubject를 참조
		ProxyPatternClient client = new ProxyPatternClient(proxy); // 클라이언트는 프록시를
		client.execute();
		client.execute();
		client.execute();
	}
}
