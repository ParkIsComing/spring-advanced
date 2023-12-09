package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements Subject{

	@Override
	public String operation() { // 데이터 조회 상황을 가정
		log.info("실제 객체 호출");
		sleep(1000);
		return "data";
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
