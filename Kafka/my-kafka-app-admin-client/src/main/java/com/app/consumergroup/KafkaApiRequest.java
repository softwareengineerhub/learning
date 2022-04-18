package com.app.consumergroup;

import org.apache.kafka.clients.ClientResponse;
import org.apache.kafka.clients.consumer.internals.ConsumerNetworkClient;
import org.apache.kafka.clients.consumer.internals.RequestFuture;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.requests.AbstractRequest;
import org.apache.kafka.common.requests.AbstractResponse;

public class KafkaApiRequest {

    private final ConsumerNetworkClient networkClient;
    private RequestFuture<ClientResponse> clientResponse;

    public KafkaApiRequest(final ConsumerNetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    public RequestFuture<ClientResponse> sendApiRequest(Node node, AbstractRequest.Builder<?> requestBuilder) {
        return networkClient.send(node, requestBuilder);
    }

    public AbstractResponse getLastApiResponse(final long waitTimeMsBetweenCheckingResponse) {
        while (!this.clientResponse.isDone()) {
            try {
                Thread.sleep(waitTimeMsBetweenCheckingResponse);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.clientResponse.value().responseBody();
    }

}
