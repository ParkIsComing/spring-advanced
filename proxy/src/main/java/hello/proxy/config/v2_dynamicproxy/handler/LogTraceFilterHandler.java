package hello.proxy.config.v2_dynamicproxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.springframework.util.PatternMatchUtils;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogTraceFilterHandler implements InvocationHandler {
	private final Object target;
	private final LogTrace logTrace;
	private final String[] patterns;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 메서드 이름 필터
		String methodName = method.getName();
		// save, request, reque*, *est
		if(!PatternMatchUtils.simpleMatch(patterns, methodName)) {
			return method.invoke(target, args); // 패턴과 일치하지 않으면 LogTrace 로직을 실행하지 않고 실제 로직을 바로 호출한다.
		}
		TraceStatus status = null;
		try {
			// 위치 로그 찍는 용도
			String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
			status = logTrace.begin(message);

			// 실제 로직 호출
			Object result = method.invoke(target, args);

			logTrace.end(status);
			return result;
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
