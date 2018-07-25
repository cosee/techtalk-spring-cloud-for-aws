package biz.cosee.techtalks.springcloud.springcloudforaws;

import biz.cosee.techtalks.springcloud.springcloudforaws.database.candy.Candy;
import biz.cosee.techtalks.springcloud.springcloudforaws.database.candy.CandyRepository;
import biz.cosee.techtalks.springcloud.springcloudforaws.database.order.OrderItem;
import biz.cosee.techtalks.springcloud.springcloudforaws.database.order.OrderItemRepository;
import biz.cosee.techtalks.springcloud.springcloudforaws.sqs.OrderSqsProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final CandyRepository candyRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderSqsProducer orderSqsProducer;

    public OrderController(CandyRepository candyRepository,
                           OrderItemRepository orderItemRepository,
                           OrderSqsProducer orderSqsProducer) {
        this.candyRepository = candyRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderSqsProducer = orderSqsProducer;
    }

    @GetMapping
    public List<OrderItem> listOrders() {
        return orderItemRepository.findAll();
    }

    @PostMapping
    public void order() {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());

        List<Candy> allCandies = candyRepository.findAll();

        List<OrderItem> orderItems = allCandies.stream()
                .map(c ->
                        new OrderItem(order.getOrderId(), c.getId(), c.getName(), c.getPrice(), 1))
                .collect(Collectors.toList());

        orderItemRepository.saveAll(orderItems);

        order.setOrderItems(orderItems);
        orderSqsProducer.sendOutOrder(order);
    }
}
