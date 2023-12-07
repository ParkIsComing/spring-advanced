package hello.advanced.app.v3;

import org.springframework.stereotype.Repository;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

	private final LogTrace trace;

	public void save(String itemId) {
		TraceStatus status = null; // catch에서 status를 사용하기 위해 밖으로 빼서 선언해준다.
		try{ // 예외가 터져도 중단되지 않고 로그를 찍기 위해 try-catch문으로 처리해준다.

			status = trace.begin("OrderRepository.save()");
			// 저장 로직
			if(itemId.equals("ex")) {
				throw new IllegalStateException("예외 발생!");
			}
			sleep(1000); // 상품 저장에 소요되는 시간 가정

			trace.end(status);
		} catch (Exception e) {
			trace.exception(status, e);
			throw e; // 예외를 꼭 다시 던져줘야 한다.(안 하면 여기서 예외를 먹어 버려서 정상흐름이 됨)
		}


	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
