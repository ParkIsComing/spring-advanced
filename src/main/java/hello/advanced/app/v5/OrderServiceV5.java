package hello.advanced.app.v5;

import org.springframework.stereotype.Service;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;

@Service
public class OrderServiceV5 {

	private final OrderRepositoryV5 orderRepository;
	private final TraceTemplate template;

	public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
		this.orderRepository = orderRepository;
		this.template = new TraceTemplate(trace);
	}

	public void orderItem (String itemId) {
		template.execute("OrderController.request()", new TraceCallback<>() {
			@Override
			public Object call() {
				orderRepository.save(itemId);
				return null;
			}
		});
	}

}
