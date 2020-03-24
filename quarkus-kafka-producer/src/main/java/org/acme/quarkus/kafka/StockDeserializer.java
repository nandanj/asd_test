package org.acme.quarkus.kafka;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class StockDeserializer extends JsonbDeserializer<Stock> {

    public StockDeserializer() {
        super(Stock.class);
    }
}
