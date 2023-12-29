package hello.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import hello.proxy.config.v5_autoproxy.AutoProxyConfig;
import hello.proxy.config.v6_aop.AopConfig;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;

// @Import({AppV1Config.class, AppV2Config.class})
// @Import(ConcreteProxyConfig.class)
// @Import(DynamicProxyBasicConfig.class)
// @Import(DynamicProxyFilterConfig.class)
// @Import(ProxyFactoryConfigV1.class)
// @Import(ProxyFactoryConfigV2.class)
// @Import(BeanPostProcessorConfig.class)
// @Import(AutoProxyConfig.class)
@Import(AopConfig.class)
// config 패키지에서 등록될 빈들은 @Import를 통해 따로 지정해 줄 것이기 때문에 app 패키지만 스캔하도록 하기 위해 scanBasePackages 설정 사용
// 해당 설정을 생략하면 ProxyApplication이 있는 패키지와 그 하위 패키지를 스캔
@SpringBootApplication(scanBasePackages = "hello.proxy.app")
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}

}
