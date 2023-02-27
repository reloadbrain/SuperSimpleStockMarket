package org.stock.market.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.stock.market.dao.impl.StockRepository;
import org.stock.market.exception.ResourceNotFoundException;
import org.stock.market.model.StockValue;
import org.stock.market.model.entity.stock.Stock;
import org.stock.market.model.validator.ValidPrice;
import org.stock.market.formula.impl.DividendYield;
import org.stock.market.formula.factory.FormulaFactory;
import org.stock.market.formula.impl.PERatio;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/stockValue")
@RequiredArgsConstructor
public class StockValueController {

    public static final String DIVIDEND_YIELD = DividendYield.class.getSimpleName();
    private final FormulaFactory formulaFactory;

    private final StockRepository stockRepository;

    @GetMapping(path = "/{symbol}/dividendYield")
    public Double calculateDividendYield(
            @PathVariable @NotNull final String symbol,
            @RequestParam @ValidPrice final Double price) {

        final Optional<Stock> stock = stockRepository.findById(symbol);
        if (stock.isPresent()) {
            return formulaFactory.getFormula(DividendYield.class).apply(new StockValue(stock.get(), LocalDate.now(), price));
        } else {
            throw new ResourceNotFoundException(symbol + " NOT Found!");
        }
    }

    @GetMapping(path = "/{symbol}/peRatio")
    public Double calculatePERatio(
            @PathVariable @NotNull final String symbol,
            @RequestParam @ValidPrice final Double price) {

        final Optional<Stock> stock = stockRepository.findById(symbol);
        if (stock.isPresent()) {
            return formulaFactory.getFormula(PERatio.class).apply(new StockValue(stock.get(), LocalDate.now(), price));
        } else {
            throw new ResourceNotFoundException(symbol + " NOT Found!");
        }
    }
}
