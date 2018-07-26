package biz.cosee.techtalks.springcloud.springcloudforaws;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.core.env.ResourceIdResolver;
import org.springframework.cloud.aws.jdbc.config.annotation.EnableRdsInstance;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@SpringBootApplication
@EnableRdsInstance(
        dbInstanceIdentifier = "RdsInstance",
        password = "${cloud.aws.rds.RdsInstance.password}"
)
public class SpringCloudForAwsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudForAwsApplication.class, args);
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync, ResourceIdResolver resourceIdResolver) {
        ObjectMapper mapper = new ObjectMapper();

        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setSerializedPayloadClass(String.class);
        converter.setObjectMapper(mapper);

        return new QueueMessagingTemplate(amazonSQSAsync, resourceIdResolver, converter);
    }
}
