package biz.cosee.techtalks.springcloud.springcloudforaws;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderSqsConsumer {

    private final static String QUEUE_NAME = "CandyOrder";

    @SqsListener(value = QUEUE_NAME, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void consumeOrderSqs(Order order) {
        System.out.println("Following order was consumed: " + order.toString());
    }

}
