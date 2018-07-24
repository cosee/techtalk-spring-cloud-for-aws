package biz.cosee.techtalks.springcloud.springcloudforaws;

import com.google.common.collect.ImmutableList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderSqsProducer orderSqsProducer;

    public OrderController(OrderSqsProducer orderSqsProducer) {
        this.orderSqsProducer = orderSqsProducer;
    }

    @PostMapping
    public void order() {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());

        order.setOrderItems(
                ImmutableList.of(
                        new OrderItem("1", "Chocolate Bar", 2),
                        new OrderItem("3", "Gummy Bears", 1),
                        new OrderItem("12", "T-Bone Steak", 5)
                )
        );

        orderSqsProducer.sendOutOrder(order);
    }
}
