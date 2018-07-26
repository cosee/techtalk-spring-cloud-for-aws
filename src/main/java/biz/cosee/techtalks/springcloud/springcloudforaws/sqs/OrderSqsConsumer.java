package biz.cosee.techtalks.springcloud.springcloudforaws.sqs;

import biz.cosee.techtalks.springcloud.springcloudforaws.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderSqsConsumer {

    private final static String QUEUE_NAME = "CandyOrderSQS";

    @SqsListener(value = QUEUE_NAME, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void consumeOrderSqs(Order order) {
        log.info("Following order was consumed: " + order.toString());
    }

}
