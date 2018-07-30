## Spring Cloud for AWS project presented in the cosee techtalk on 26.07.2018
This project shows the basic spring cloud functionality explained in the cosee techtalk. For more information on our next talks please visit: https://talks.cosee.biz/

The cloudformation template `springCloudSampleStack.template` defines necessary IAM roles and creates new VPC, Subnets and Security Groups.
It also creates a new SQS queue, RDS MySQL database and a EC2 Server with your running application.

The spring cloud application can access the AWS resources via the logical names defined in the cloudformation stack.

For a more comprehensive implementation of the Spring Cloud features refer to https://github.com/spring-cloud-samples/aws-refapp and https://cloud.spring.io/spring-cloud-aws/spring-cloud-aws.html

To create a new Stack yourself you need to do following steps:
1. Compile the application with `mvn clean package`
2. Upload the resulting jar to S3
3. Copy the path from S3. Go to the `springCloudSampleStack.template` file located in the `cloudformation` directory of this project. 
Replace the `BUCKET_NAME/PATH/TO/JAR` of the EC2 Server bash command with your path.
4. Start up the stack with the `springCloudSampleStack.template` file either via GUI or CLI.
For the CLI you have to upload the template file to S3. Replace the \<> parameters with your own values.
Then you can execute the following command:

aws cloudformation create-stack \
--stack-name \<STACK_NAME> \
--template-url \<PATH_TO_CLOUDFORMATION_TEMPLATE_IN_S3> \
--parameters ParameterKey=RdsPassword,ParameterValue=\<RDS_PW> \
--capabilities CAPABILITY_IAM



Repositories with applications from this Talk:
https://github.com/cosee/techtalk-spring-cloud-for-aws
https://github.com/cosee/techtalk-spring-boot-for-aws
https://github.com/cosee/techtalk-spring-cloud-for-aws-with-different-stack
