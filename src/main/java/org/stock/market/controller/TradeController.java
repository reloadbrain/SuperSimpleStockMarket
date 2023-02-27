package org.stock.market.controller;

import com.google.common.collect.Iterables;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.stock.market.dao.StockMarketRepository;
import org.stock.market.model.StockValue;
import org.stock.market.model.entity.stock.Stock;
import org.stock.market.model.entity.trade.Trade;
import org.stock.market.model.entity.trade.TradeIndicator;
import org.stock.market.model.filter.TradeAgeFilter;
import org.stock.market.formula.factory.FormulaFactory;
import org.stock.market.formula.impl.GeometricMeanPrice;
import org.stock.market.formula.impl.VolumeWeightedPrice;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.TimeZone;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Interface to recording Trades, listing
 */
@RestController
@RequestMapping("/trade")
@RequiredArgsConstructor
@Validated
public class TradeController {

    private final StockMarketRepository<Trade,String> repository;
    private final StockMarketRepository<Stock,String> stockRepository;
    private final FormulaFactory formulaFactory;

    @GetMapping(path = "/list")
    public Iterable<Trade> list(@RequestParam(required = false) final Integer timeInMinutes) {
        if (timeInMinutes != null && timeInMinutes > 0){
            final TradeAgeFilter tradeAgeFilter = new TradeAgeFilter(timeInMinutes);
            final Iterable<Trade> trades = repository.findAll();
            return StreamSupport.stream(trades.spliterator(),false)
                    .filter(t->tradeAgeFilter.test(t))
                    .collect(Collectors.toList());
        }
        return repository.findAll();
    }

    @GetMapping(path = "/VolumeWeightedStockPrice/{timeInMinutes}")
    public Double volumeWeightedStockPrice(@PathVariable final Integer timeInMinutes) {
        return formulaFactory.getFormula(VolumeWeightedPrice.class).apply(repository.findAll(),new TradeAgeFilter(timeInMinutes));
    }

    @GetMapping(path = "/GBCEShareIndex")
    public Double calculateGBCEShareIndex(){
        final Iterable<Trade> allTrades = repository.findAll();
        if(Iterables.isEmpty(allTrades))
            return null;
        return formulaFactory.getFormula(GeometricMeanPrice.class).apply(StreamSupport.stream(allTrades.spliterator(),false)
                .map(t->{
                    final Optional<Stock> stock = stockRepository.findById(t.getSymbol());
                    if(stock.isPresent()){
                        return new StockValue(stock.get(),
                                LocalDateTime.ofInstant(Instant.ofEpochSecond(t.getTradeTimestamp()),
                                        TimeZone.getDefault().toZoneId()).toLocalDate(),
                                t.getTradePrice());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

    @PostMapping(path = "/BUY/{symbol}")
    public Trade buy(
            @PathVariable final String symbol,
            @RequestParam final Double price,
            @RequestParam final Integer quantity){
        return repository.save(
                new Trade(
                        UUID.randomUUID().toString(),
                        System.currentTimeMillis(),
                        price,
                        quantity,
                        symbol,
                        TradeIndicator.BUY.name()));
    }

    @PostMapping(path = "/SELL/{symbol}")
    public Trade sell(
            @PathVariable final String symbol,
            @RequestParam final Double price,
            @RequestParam final Integer quantity){
        return repository.save(
                new Trade(
                        UUID.randomUUID().toString(),
                        System.currentTimeMillis(),
                        price,
                        quantity,
                        symbol,
                        TradeIndicator.SELL.name()));
    }
}
