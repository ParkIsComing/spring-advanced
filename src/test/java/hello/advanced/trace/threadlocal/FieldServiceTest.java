package hello.advanced.trace.threadlocal;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldServiceTest {
	private FieldService fieldService = new FieldService();

	@Test
	void field() {
		log.info("main start");
		Runnable userA = () -> {
			fieldService.logic("userA");
		};

		Runnable userB = () -> {
			fieldService.logic("userB");
		};

		Thread threadA = new Thread(userA);
		threadA.setName("threadA");
		Thread threadB = new Thread(userB);
		threadB.setName("threadB");

		threadA.start();;
		sleep(2000); // 동시성 문제 발생 x
		threadB.start();

		sleep(3000); // 메인 쓰레드 종료 대기
		log.info("main exit");


	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
