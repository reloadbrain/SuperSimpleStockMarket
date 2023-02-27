package org.stock.market.util;

import com.google.common.collect.Lists;
import org.stock.market.model.entity.BaseEntity;
import org.stock.market.model.entity.stock.Stock;
import org.stock.market.model.entity.trade.Trade;

import static org.stock.market.model.entity.trade.TradeIndicator.BUY;
import static org.stock.market.model.entity.trade.TradeIndicator.SELL;

public enum StockMarketInitHelper {
    INSTANCE;


    public static final String TRADE_REPOSITORY = "TradeRepository";
    public static final String STOCK_REPOSITORY = "StockRepository";

    public Iterable<? extends BaseEntity> initValues(String type) {
        switch (type) {
            case STOCK_REPOSITORY :
            {
                return Lists.newArrayList(
                        Stock.builder().symbol("TEA").type("common").lastDividend(0.0).parValue(100.0).build(),
                        Stock.builder().symbol("POP").type("common").lastDividend(8.0).parValue(100.0).build(),
                        Stock.builder().symbol("ALE").type("common").lastDividend(23.0).parValue(60.0).build(),
                        Stock.builder().symbol("GIN").type("preferred").lastDividend(8.0).fixedDividend(2.0).parValue(100.0).build(),
                        Stock.builder().symbol("JOE").type("common").lastDividend(13.0).parValue(250.0).build());

            }
            case TRADE_REPOSITORY :
            {
                return Lists.newArrayList(
                        new Trade(
                                "trade-test1",
                                System.currentTimeMillis() - (10 * 60 * 1000),
                                52.0,
                                670,
                                "JOE",
                                SELL.name()),
                        new Trade(
                                "trade-test2",
                                System.currentTimeMillis() - (10 * 60 * 1000),
                                88.0,
                                15,
                                "TEA",
                                SELL.name()),
                        new Trade(
                                "trade-test3",
                                System.currentTimeMillis() - (1 * 60 * 1000),
                                15.0,
                                300,
                                "POP",
                                SELL.name()),
                        new Trade(
                                "trade-test4",
                                System.currentTimeMillis() - (30 * 60 * 1000),
                                29.0,
                                70,
                                "GIN",
                                BUY.name()),
                        new Trade(
                                "trade-test5",
                                System.currentTimeMillis() - (10 * 60 * 1000),
                                90.0,
                                90,
                                "ALE",
                                BUY.name()));
            }

        }

        return null;
    }


}
