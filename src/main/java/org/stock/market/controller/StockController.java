package org.stock.market.controller;

import jakarta.validation.Valid;
import org.stock.market.dao.StockMarketRepository;
import org.stock.market.exception.ResourceNotFoundException;
import org.stock.market.model.entity.stock.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Crud operations for Stocks
 */
@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
@Validated
public class StockController {

    private final StockMarketRepository<Stock,String> repository;

    @GetMapping(path = "/{symbol}")
    public Stock getStock(@PathVariable final String symbol){
        final Optional<Stock> stock = repository.findById(symbol.toUpperCase());
         if (stock.isPresent() )
             return stock.get() ;
        else
             throw new ResourceNotFoundException(symbol+" NOT Found!");
    }

    @GetMapping(path = "/list")
    public Iterable<Stock> list(){
        return repository.findAll();
    }

    @PutMapping
    public Stock addUpdateStock(
            @Valid
            @RequestBody final Stock stock){
        return repository.save(stock);
    }

}
