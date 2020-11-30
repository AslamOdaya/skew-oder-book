package com.skew.test;

import com.skew.test.websocket.BitstampWebSocketConnector;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BitstampWebSocketConnector connector = new BitstampWebSocketConnector();
        connector.connect();

        new Scanner(System.in).nextLine();
    }

}
