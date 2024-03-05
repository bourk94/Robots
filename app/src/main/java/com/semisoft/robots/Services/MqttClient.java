package com.semisoft.robots.Services;

import com.hivemq.client.mqtt.mqtt5.Mqtt5Client;
import com.hivemq.client.mqtt.mqtt5.message.connect.connack.Mqtt5ConnAck;
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5PublishResult;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class MqttClient {

   private Mqtt5Client client;
   private CompletableFuture<Mqtt5ConnAck> connAckFuture;
   private String serverAddress;

    public MqttClient(String serverAddress) {
        this.client = Mqtt5Client.builder()
                .identifier(UUID.randomUUID().toString())
                .serverHost(serverAddress)
                .build();
        this.connAckFuture = client.toAsync().connect();
        this.serverAddress = serverAddress;
    }

    public void connect() {
        connAckFuture.whenComplete((connAck, throwable) -> {
            if (throwable != null) {
                System.out.println("Unable to connect: " + throwable.getMessage());
            } else {
                System.out.println("Connected");
            }
        });
    }

    public void disconnect() {
        client.toAsync().disconnect();
    }

    public void sendMessage(String topic, String message) {
        CompletableFuture<Mqtt5PublishResult> publishResultFuture =
                client.toAsync().publishWith()
                        .topic(topic)
                        .payload(message.getBytes())
                        .send();

        publishResultFuture.whenComplete((publishResult, throwablePublish) -> {
            if (throwablePublish != null) {
                System.out.println("Unable to publish message: " + throwablePublish.getMessage());
            } else {
                System.out.println("Message published");
            }
        });
    }

    public void subscribe(String topic) {
        client.toAsync().subscribeWith()
                .topicFilter(topic)
                .callback(publish -> {
                    System.out.println("Received message: " + new String(publish.getPayloadAsBytes()));
                })
                .send();
    }

    public void unsubscribe(String topic) {
        client.toAsync().unsubscribeWith()
                .topicFilter(topic)
                .send();
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public Mqtt5Client getClient() {
        return client;
    }

    public void setClient(Mqtt5Client client) {
        this.client = client;
    }

    public CompletableFuture<Mqtt5ConnAck> getConnAckFuture() {
        return connAckFuture;
    }

    public void setConnAckFuture(CompletableFuture<Mqtt5ConnAck> connAckFuture) {
        this.connAckFuture = connAckFuture;
    }
}
