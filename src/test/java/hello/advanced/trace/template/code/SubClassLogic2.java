package hello.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubClassLogic2 extends AbstractTemplate {
	@Override
	protected void call() {
		// 비즈니스 로직 구현
		log.info("비즈니스 로직2 실행");
	}
}
