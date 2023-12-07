package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {
	@Test
	void strategyV0() {
		logic1();
		logic2();
	}

	@Test
	@DisplayName("전략 패턴 사용")
	void strategyV1() {
		StrategyLogic1 strategyLogic1 = new StrategyLogic1();
		ContextV1 context1 = new ContextV1(strategyLogic1); // strategy 주입
		context1.execute(); // context 로직 실행

		StrategyLogic2 strategyLogic2 = new StrategyLogic2();
		ContextV1 context2 = new ContextV1(strategyLogic2);
		context2.execute();
	}

	@Test
	@DisplayName("익명 내부 클래스 적용")
	void strategyV2() {
		Strategy strategyLogic1 = new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		};
		ContextV1 context1 = new ContextV1(strategyLogic1); // strategy 주입
		context1.execute(); // context 로직 실행
	}

	@Test
	@DisplayName("+인라인 적용")
	void strategyV3() {
		ContextV1 context1 = new ContextV1(new Strategy() {
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
		}); // strategy 주입
		context1.execute(); // context 로직 실행
	}

	@Test
	@DisplayName("+람다 적용")
	void strategyV4() {
		ContextV1 context1 = new ContextV1(() -> log.info("비즈니스 로직1 실행")); // strategy 주입
		context1.execute(); // context 로직 실행

		ContextV1 context2 = new ContextV1(() -> log.info("비즈니스 로직1 실행")); // strategy 주입
		context2.execute(); // context 로직 실행
	}

	private void logic1() {
		long startTime = System.currentTimeMillis();

		// 비즈니스 로직 실행하는 부분
		log.info("비즈니스 로직1 실행");

		// 비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime = {}", resultTime);
	}

	private void logic2() {
		long startTime = System.currentTimeMillis();

		// 비즈니스 로직 실행하는 부분
		log.info("비즈니스 로직2 실행");

		// 비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime = {}", resultTime);
	}
}
