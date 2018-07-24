package biz.cosee.techtalks.springcloud.springcloudforaws;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderSqsConsumer {

    private final static String QUEUE_NAME = "CandyOrder";

    private final ObjectMapper mapper;

    public OrderSqsConsumer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    protected <T> T mapToMessage(String payload, Class<T> clazz) {
        try {
            return mapper.readValue(payload, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Could not parse payload from SQS.", e);
        }
    }

    @SqsListener(value = QUEUE_NAME, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void consumeOrderSqs(String payload) {
        System.out.println("Received order!");

        Order message = mapToMessage(payload, Order.class);

        System.out.println("Following order was consumed: " + message);
    }

}
