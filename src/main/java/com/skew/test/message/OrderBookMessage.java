package com.skew.test.message;

import java.util.List;

public class OrderBookMessage {

    private  OrderBookData data;

    private String channel;

    private String event;

    public OrderBookData getData() {
        return data;
    }

    public void setData(OrderBookData data) {
        this.data = data;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
