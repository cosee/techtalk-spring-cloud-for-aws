package biz.cosee.techtalks.springcloud.springcloudforaws;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderSqsProducer {

    private final QueueMessagingTemplate queueMessagingTemplate;

    private final static String QUEUE_NAME = "CandyOrder";

    public OrderSqsProducer(AmazonSQSAsync amazonSQSAsync) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsync);
    }

    public void sendOutOrder(Order order) {
        queueMessagingTemplate.convertAndSend(
                QUEUE_NAME,
                MessageBuilder.withPayload(order).build());
    }
}
