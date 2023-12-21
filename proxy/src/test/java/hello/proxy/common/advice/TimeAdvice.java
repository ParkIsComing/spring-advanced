package hello.proxy.common.advice;

import org.aopalliance.intercept.MethodInterceptor; // 패키지 주의
import org.aopalliance.intercept.MethodInvocation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		log.info("TimeProxy 실행");
		long startTime = System.currentTimeMillis();

		// Object result = method.invoke(target, args);
		Object result = invocation.proceed(); // invovation에 포함된 target 클래스를 호출하고 그 결과를 받는다.

		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("TimeProxy 종료 resultTime ={}", resultTime);
		return result;
	}
}
