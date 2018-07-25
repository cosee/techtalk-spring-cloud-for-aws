package biz.cosee.techtalks.springcloud.springcloudforaws;

import biz.cosee.techtalks.springcloud.springcloudforaws.database.order.OrderItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Order {

    private String orderId;
    private List<OrderItem> orderItems;
}
