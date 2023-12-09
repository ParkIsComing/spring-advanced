package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 프록시 구현
 */
@Slf4j
public class CacheProxy implements Subject{
	private Subject target; // 실제 객체의 참조. 프록시가 최종적으로 실제 객체를 호출해야 하기 때문
	private String cacheValue;

	public CacheProxy(Subject target) { // 프록시가 RealSubject를 참조
		this.target = target;
	}

	@Override
	public String operation() {
		log.info("프록시 호출");
		if (cacheValue == null) { // 캐시값이 없으면 실제 객체 호출
			cacheValue = target.operation();
		}
		return cacheValue; // 캐시값이 있으면 그대로 반환
	}
}
