package io.github.shazxrin.sbemuadmin;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import org.junit.jupiter.api.Test;

public class SendMessageToServiceBusTest {
    @Test
    public void sendMessageToTopic() {
        String connectionString = "Endpoint=sb://localhost;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=SAS_KEY_VALUE;UseDevelopmentEmulator=true;";
        String topic = "my-topic";
        String message = "Hello, this is a test message!";
        ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
            .connectionString(connectionString)
            .sender()
            .topicName(topic)
            .buildClient();

        senderClient.sendMessage(new ServiceBusMessage(message));

        senderClient.close();
    }

    @Test
    public void sendMessageToQueue() {
        String connectionString = "Endpoint=sb://localhost;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=SAS_KEY_VALUE;UseDevelopmentEmulator=true;";
        String queue = "my-queue";
        String message = "Hello, this is a test message!";
        ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
            .connectionString(connectionString)
            .sender()
            .queueName(queue)
            .buildClient();

        senderClient.sendMessage(new ServiceBusMessage(message));

        senderClient.close();
    }
}
