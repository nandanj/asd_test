package org.acme.quarkus.kafka;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.enterprise.context.ApplicationScoped;

import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class StockQuoteProducer {

    private List<Stock> stocks = Collections.unmodifiableList(
            Arrays.asList(
                    new Stock("GOOGL", "1200"),
                    new Stock("AAPL", "270"),
                    new Stock ("FB", "170"),
                    new Stock("CSCO", "37"),
                    new Stock("INTC", "50")
            ));

    private Random random = new Random();

    @Outgoing("stockquote")
    public Flowable<KafkaMessage<String, Stock>> generate() {

        return Flowable.interval(500, TimeUnit.MILLISECONDS).map(tick -> {
            Stock stock = stocks.get(random.nextInt(stocks.size()));
            BigDecimal value = stock.getValue().add(BigDecimal.valueOf(random.nextGaussian())).setScale(2, RoundingMode.HALF_UP);
            stock.setValue(value);
            return KafkaMessage.of(stock.getSymbol(), stock);
        }).onBackpressureDrop();
    }

}
