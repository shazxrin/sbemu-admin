package io.github.shazxrin.sbemuadmin;

import com.azure.core.util.IterableStream;
import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceiverClient;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ConsumeMessageFromServiceBusTest {
    @ParameterizedTest
    @CsvSource({
        "my-topic, my-subscription-4"
    })
    public void receiveMessageFromTopicFromSubscription(String topic, String subscription) {
        String connectionString = "Endpoint=sb://localhost;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=SAS_KEY_VALUE;UseDevelopmentEmulator=true;";
        ServiceBusReceiverClient receiverClient = new ServiceBusClientBuilder()
            .connectionString(connectionString)
            .receiver()
            .topicName(topic)
            .subscriptionName(subscription)
            .buildClient();

        IterableStream<ServiceBusReceivedMessage> messages = receiverClient.receiveMessages(1, Duration.ofSeconds(3));
        int count = 0;
        for (ServiceBusReceivedMessage message : messages) {
            System.out.println("Received message: " + message.getBody());
            receiverClient.complete(message);
            count++;
        }
        System.out.println("Received " + count + " messages from topic: " + topic + ", subscription: " + subscription);

        receiverClient.close();
    }

    @Test
    public void receiveMessageFromQueue() {
        String connectionString = "Endpoint=sb://localhost;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=SAS_KEY_VALUE;UseDevelopmentEmulator=true;";
        String queue = "my-queue";
        ServiceBusReceiverClient receiverClient = new ServiceBusClientBuilder()
            .connectionString(connectionString)
            .receiver()
            .queueName(queue)
            .buildClient();

        IterableStream<ServiceBusReceivedMessage> messages = receiverClient.receiveMessages(1, Duration.ofSeconds(3));
        int count = 0;
        for (ServiceBusReceivedMessage message : messages) {
            System.out.println("Received message: " + message.getBody());
            receiverClient.complete(message);
            count++;
        }
        System.out.println("Received " + count + " messages from queue: " + queue);

        receiverClient.close();
    }
}
