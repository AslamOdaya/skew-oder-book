package com.skew.test.websocket;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BitstampWebSocketConnector implements WebSocketConnector{

    @Override
    public  void connect()  {

        String uri = "wss://ws.bitstamp.net";
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            container.connectToServer(BitstampWebSocketClientEndPoint.class, URI.create(uri));
        } catch (DeploymentException | IOException e) {
            throw new RuntimeException("An error occurred :" + e.getMessage());
        }

    }
}
