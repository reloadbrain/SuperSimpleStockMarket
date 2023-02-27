package org.stock.market.formula.impl;

import org.stock.market.formula.factory.Formula;
import org.stock.market.model.StockValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.function.Function;

/**
 * Service to calculate PERatio of stock at a given price
 *
 */
@Service
@Slf4j
public class PERatio implements Formula, Function<StockValue,Double> {

    private final DividendYield dividendYield;

    @Inject
    public PERatio(final DividendYield dividendYield){
        this.dividendYield = dividendYield;
    }

    @Override
    public Double apply(final StockValue stockValue) {
        log.debug(String.format("Calculating PERatio " +
                " for [%s] at price [%s]", stockValue.getStock().getSymbol(), stockValue.getPrice()));
        return stockValue.getPrice() / dividendYield.apply(stockValue);
    }
}