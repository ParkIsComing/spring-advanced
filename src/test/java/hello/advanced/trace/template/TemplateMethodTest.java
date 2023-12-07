package hello.advanced.trace.template;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemplateMethodTest {
	@Test
	void templateMethodV0() {
		logic1();
		logic2();
	}

	@Test
	@DisplayName("상속과 오버라이딩 적용")
	void templateMethodV1() {

		AbstractTemplate template1 = new SubClassLogic1(); //오버라이딩 (업캐스팅)
		template1.execute(); // 동적바인딩하여 자식 클래스 SubClassLogic1의 메서드 실행


		AbstractTemplate template2 = new SubClassLogic2();
		template2.execute();
	}

	@Test
	@DisplayName("익명 내부 클래스 적용")
	void templateMethodV2() {
		AbstractTemplate template1 = new AbstractTemplate() {
			@Override
			protected void call() {
				// 비즈니스 로직1 구현
				log.info("비즈니스 로직1 실행");
			}
		};
		log.info("클래스 이름1 = {}", template1.getClass()); // 클래스 이름1 = TemplateMethodTest$1
		template1.execute();

		AbstractTemplate template2 = new AbstractTemplate() {
			@Override
			protected void call() {
				// 비즈니스 로직2 구현
				log.info("비즈니스 로직2 실행");
			}
		};
		log.info("클래스 이름2 = {}", template2.getClass()); // 클래스 이름2 = TemplateMethodTest$2
		template2.execute();
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
