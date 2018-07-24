package biz.cosee.techtalks.springcloud.springcloudforaws;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.core.env.ResourceIdResolver;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringCloudForAwsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudForAwsApplication.class, args);
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync, ResourceIdResolver resourceIdResolver) {
        return new QueueMessagingTemplate(amazonSQSAsync, resourceIdResolver);
    }
}
