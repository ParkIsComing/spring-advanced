package hello.proxy.config.v2_dynamicproxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogTraceBasicHandler implements InvocationHandler {
	private final Object target;
	private final LogTrace logTrace;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		TraceStatus status = null;
		try {
			// 위치 로그 찍는 용도
			String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
			status = logTrace.begin(message);

			// 로직 호출
			Object result = method.invoke(target, args);

			logTrace.end(status);
			return result;
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
