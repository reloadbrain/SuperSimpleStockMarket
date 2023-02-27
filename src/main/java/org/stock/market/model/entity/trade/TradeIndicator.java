package org.stock.market.model.entity.trade;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum TradeIndicator {

    BUY,SELL;

    public static TradeIndicator strToIndicator(final String indicator){
        TradeIndicator result = null;
        try {
            result = TradeIndicator.valueOf(indicator.toUpperCase());
        } catch (IllegalArgumentException e) {
            log.error("[" + indicator + "] is not a valid trade indicator");
        }
        return result;
    }
}
