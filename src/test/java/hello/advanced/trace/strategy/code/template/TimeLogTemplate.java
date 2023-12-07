package hello.advanced.trace.strategy.code.template;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.strategy.code.strategy.ContextV2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {
	public void execute(Callback callback) {
		long startTime = System.currentTimeMillis();

		// 비즈니스 로직 실행하는 부분
		callback.call();

		// 비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime = {}", resultTime);

	}
}
