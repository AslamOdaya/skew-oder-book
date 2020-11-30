package com.skew.test.message;

import java.util.List;

public class OrderBookData {


    private String timestamp;

    private String microtimestamp;

    private List<List<String>> bids;

    private List<List<String>> asks;


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMicrotimestamp() {
        return microtimestamp;
    }

    public void setMicrotimestamp(String microtimestamp) {
        this.microtimestamp = microtimestamp;
    }

    public List<List<String>> getBids() {
        return bids;
    }

    public void setBids(List<List<String>> bids) {
        this.bids = bids;
    }

    public List<List<String>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<String>> asks) {
        this.asks = asks;
    }
}
