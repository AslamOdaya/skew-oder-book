package com.skew.test;

import java.util.HashMap;
import java.util.Map;

public class NormalizedSymbol {


    private static Map<String,String> normalizedSymbolPairs = new HashMap<>();



    public static Map<String,String> getNormalizedSymbolPairs(){
        normalizedSymbolPairs.put("btcusd","BTC/USD");

        return  normalizedSymbolPairs;
    }

}
