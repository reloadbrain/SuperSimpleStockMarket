package org.stock.market.model.filter;

import org.stock.market.model.entity.trade.Trade;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.function.Predicate;

/**
 * Filter to match Trades which are recorded within specified time in Minutes
 */
@Slf4j
public class TradeAgeFilter implements Predicate<Trade> {
    private final long requiredTimeInMinutes;

    public TradeAgeFilter(final int requiredTimeInMinutes){
        this.requiredTimeInMinutes = requiredTimeInMinutes;
    }

    @Override
    public boolean test(final Trade trade) {
        final long timeAgo = System.currentTimeMillis() - (requiredTimeInMinutes * 60 * 1000);
        log.debug("Search Time "+LocalDateTime.ofInstant(Instant.ofEpochMilli(timeAgo), ZoneOffset.UTC));
        log.debug("Trade Time "+LocalDateTime.ofInstant(Instant.ofEpochMilli(trade.getTradeTimestamp()), ZoneOffset.UTC));
        return (timeAgo <= trade.getTradeTimestamp()) ? true : false;
    }
}
