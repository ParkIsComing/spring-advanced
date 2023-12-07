package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 전략을 파라미터로 전달 받는 방식
 * -> Context 는 Strategy 인터페이스에만 의존
 * -> 따라서 Strategy 의 구현체를 변경하거나 새로 만들어도 Context 코드에는 영향을 주지 않는다.
 */
@Slf4j
public class ContextV2 {
	public void execute(Strategy strategy) {
		long startTime = System.currentTimeMillis();

		// 비즈니스 로직 실행하는 부분
		strategy.call();

		// 비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime = {}", resultTime);

	}
}
