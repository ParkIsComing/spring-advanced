package hello.advanced.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

	private final OrderServiceV1 orderService;
	private final HelloTraceV1 trace;

	@GetMapping("/v1/request")
	public String request(String itemId) {
		TraceStatus status = null; // catch에서 status를 사용하기 위해 밖으로 빼서 선언해준다.
		try{ // 예외가 터져도 중단되지 않고 로그를 찍기 위해 try-catch문으로 처리해준다.
			status = trace.begin("OrderController.request()");
			orderService.orderItem(itemId);
			trace.end(status);
			return "ok";
		} catch (Exception e) {
			trace.exception(status, e);
			throw e; // 예외를 꼭 다시 던져줘야 한다.(안 하면 여기서 예외를 먹어 버려서 정상흐름이 됨)
		}


	}
}
