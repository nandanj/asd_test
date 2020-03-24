package org.acme.quarkus.kafka;

import java.math.BigDecimal;

public class Stock {

    private String symbol;

    private BigDecimal value;

    public Stock() {}

    public Stock(String symbol, String value) {
        this.symbol = symbol;
        this.value = new BigDecimal(value);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

}
