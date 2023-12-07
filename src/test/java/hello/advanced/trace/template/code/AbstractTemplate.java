package hello.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {
	public void execute() {
		long startTime = System.currentTimeMillis();

		/*
			비즈니스 로직 실행하는 부분 (변하는 부분)
		*/
		call(); // 상속

		// 비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime = {}", resultTime);
	}

	// 실제 실행 영역은 상속받는 클래스에 맡김.
	// 여기서는 SubClassLogic~ 클래스에서 call을 재정의해야 한다.
	protected abstract void call();
}
