package com.skew.test.websocket;


import com.skew.test.Channel;
import com.skew.test.Event;
import com.skew.test.NormalizedSymbol;
import com.skew.test.message.Data;
import com.skew.test.message.Message;
import com.skew.test.message.OrderBookMessage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.codehaus.jackson.map.ObjectMapper;

import javax.websocket.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@ClientEndpoint
public class BitstampWebSocketClientEndPoint {

    private int counter = 1;
    private static final String[] HEADERS = {"symbol", "bid quantity", "bid price", "ask price", "ask quantity"};
    private static final int MAX_SIZE = 100;
    private static final int MAX_OUTPUT_THRESHHOLD = 11;
    private CSVPrinter printer;

    @OnOpen
    public void onOpen(Session session) throws IOException {
        Message message = createMessage("order_book_" + Channel.BTC_USD.getCurrencyPair(), Event.SUBSCRIBE.getEventName());
        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(message);
        FileWriter writer = new FileWriter("orderbook.csv");
        printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(HEADERS).withDelimiter('|'));
        try {
            session.getBasicRemote().sendText(payload);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        if (message == null) {
            throw new RuntimeException("message is null");
        }

        OrderBookMessage orderBookMessage = mapper.readValue(message, OrderBookMessage.class);

        if (orderBookMessage == null) {

            throw new RuntimeException("OrderBookMessage is null");
        }

        String[] splitChannel = orderBookMessage.getChannel().split("_");
        Map<String, String> normalizedSymbolPairs = NormalizedSymbol.getNormalizedSymbolPairs();
        String normalizedSymbol = normalizedSymbolPairs.get(splitChannel[2]);

        if (normalizedSymbol == null) {
            throw new RuntimeException("normalized symbol is null");
        }

        if (counter <= 11 && orderBookMessage.getEvent().equals(Event.DATA.getEventName())) {

            List<List<String>> bids = orderBookMessage.getData().getBids();
            List<List<String>> asks = orderBookMessage.getData().getAsks();

            if (bids.isEmpty() || asks.isEmpty()) {
                throw new RuntimeException("Bids or Asks were empty");
            }

            System.out.println("Message " + message);
            for (int i = 0; i < MAX_SIZE; i++) {
                String bidPrice = bids.get(i).get(0);
                String bidQuantity = bids.get(i).get(1);
                String askPrice = asks.get(i).get(0);
                String askQuantity = asks.get(i).get(1);
                printer.printRecord(normalizedSymbol, bidPrice, bidQuantity, askPrice, askQuantity);
            }

            counter++;

            if (counter == MAX_OUTPUT_THRESHHOLD && orderBookMessage.getEvent().equals(Event.DATA.getEventName())) {
                printer.flush();
            }
        } else if (counter > 11) {
            System.exit(0);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println(String.format("Session %s close because of %s", session.getId(), closeReason));
    }

    private Message createMessage(String channel, String event) {
        Data data = new Data();
        data.setChannel(channel);
        Message message = new Message();
        message.setEvent(event);
        message.setData(data);

        return message;
    }
}
