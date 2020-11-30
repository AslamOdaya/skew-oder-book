package com.skew.test;

public enum  Event {

    SUBSCRIBE("bts:subscribe"),
    UNSUBSCRIBE("bts:unsubscribe"),
    DATA("data");

    private String eventName;

    Event(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}

