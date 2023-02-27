package org.stock.market.formula;

import org.stock.market.formula.impl.GeometricMeanPrice;
import org.stock.market.model.entity.stock.Stock;
import org.stock.market.model.StockValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.stock.market.util.StockMarketInitHelper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeometricMeanPriceTest {

    private GeometricMeanPrice gmp;

    @BeforeEach
    void setUp() {
        gmp = new GeometricMeanPrice();
    }

    @Test
    public void verify_GM_price1(){
        final Iterable<Stock> stocks = (Iterable<Stock>) StockMarketInitHelper.INSTANCE.initValues(StockMarketInitHelper.STOCK_REPOSITORY);
        final List<StockValue> stockValues = new ArrayList<>();
        stocks.forEach(stock->{
            stockValues.add(new StockValue(
                    stock,
                    LocalDate.now(),
                    25.0
            ));
        });
        final BigDecimal bd = BigDecimal.valueOf(gmp.apply(stockValues)).setScale(3,RoundingMode.HALF_UP);
        assertEquals(25.000,bd.doubleValue());
    }

    @Test
    public void verify_GM_price2(){
        final Iterable<Stock> stocks =  (Iterable<Stock>) StockMarketInitHelper.INSTANCE.initValues(StockMarketInitHelper.STOCK_REPOSITORY);;
        final List<StockValue> stockValues = new ArrayList<>();
        int i = 1;
        for (Stock stock : stocks) {
            stockValues.add(new StockValue(
                    stock,
                    LocalDate.now(),
                    (27.0 * i)
            ));
            i = i + 8;
        }
        final BigDecimal bd = BigDecimal.valueOf(gmp.apply(stockValues)).setScale(3,RoundingMode.HALF_UP);
        assertEquals(282.874,bd.doubleValue());
    }

}