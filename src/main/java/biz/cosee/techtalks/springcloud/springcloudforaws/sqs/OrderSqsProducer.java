package biz.cosee.techtalks.springcloud.springcloudforaws.sqs;

import biz.cosee.techtalks.springcloud.springcloudforaws.Order;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.cloud.aws.core.env.ResourceIdResolver;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderSqsProducer {

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final ResourceIdResolver resourceIdResolver;

    private final static String QUEUE_NAME = "CandyOrder";

    public OrderSqsProducer(AmazonSQSAsync amazonSQSAsync, ResourceIdResolver resourceIdResolver) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsync);
        this.resourceIdResolver = resourceIdResolver;
    }

    public void sendOutOrder(Order order) {


        try {
            queueMessagingTemplate.convertAndSend(
                    QUEUE_NAME, order);

        } catch (Exception e) {
            System.out.println("Sending Message produced error: ");
            e.printStackTrace();
            System.out.println("RESOURCE_ID_RESOLVER: " + resourceIdResolver.resolveToPhysicalResourceId(QUEUE_NAME));

            queueMessagingTemplate.convertAndSend(
                    resourceIdResolver.resolveToPhysicalResourceId(QUEUE_NAME),
                    order);
        }
    }
}
