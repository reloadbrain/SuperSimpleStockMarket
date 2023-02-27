package org.stock.market.model.entity.stock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum StockType {

    COMMON,
    PREFERRED;

    public static StockType strToType(final String stockType) {
        StockType result = null;
        try {
            result = StockType.valueOf(stockType.toUpperCase());
        } catch (IllegalArgumentException e) {
            log.error("[" + stockType + "] is not a valid type");
        }
        return result;
    }

}
