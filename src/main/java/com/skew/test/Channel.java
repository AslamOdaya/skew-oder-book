package com.skew.test;

public enum  Channel {

    BTC_USD("btcusd");

    private String currencyPair;

    Channel(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }
}
