package main.dynamodb;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.localstack.LocalStackContainer;

@ContextConfiguration(initializers = BaseDynamoDbTest.Initializer.class)
public abstract class BaseDynamoDbTest {
    private static LocalStackContainer localStackContainer =
            new LocalStackContainer().withServices(LocalStackContainer.Service.DYNAMODB);

    static {
        localStackContainer.start();
    }

    /**
     * Initialize the application context for the tests
     * i.e. Setup the amazon.dynamoUrl property to point to the LocalStack container
     */
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues values = TestPropertyValues.of("amazon.dynamoUrl",
                                                              localStackContainer.getEndpointConfiguration(LocalStackContainer.Service.DYNAMODB)
                                                              .getServiceEndpoint());
            values.applyTo(applicationContext);
        }
    }

}
