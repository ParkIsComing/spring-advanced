package hello.advanced.app.v2;

import org.springframework.stereotype.Service;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

	private final OrderRepositoryV2 orderRepository;
	private final HelloTraceV2 trace;

	public void orderItem (TraceId traceId, String itemId) {
		TraceStatus status = null; // catch에서 status를 사용하기 위해 밖으로 빼서 선언해준다.
		try{ // 예외가 터져도 중단되지 않고 로그를 찍기 위해 try-catch문으로 처리해준다.
			status = trace.beginSync(traceId, "OrderService.orderItem()");
			orderRepository.save(status.getTraceId(), itemId);
			trace.end(status);
		} catch (Exception e) {
			trace.exception(status, e);
			throw e; // 예외를 꼭 다시 던져줘야 한다.(안 하면 여기서 예외를 먹어 버려서 정상흐름이 됨)
		}
	}
}
