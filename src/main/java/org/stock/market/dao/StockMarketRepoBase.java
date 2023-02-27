package org.stock.market.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.stock.market.model.entity.BaseEntity;
import org.stock.market.model.entity.stock.Stock;
import org.stock.market.model.entity.trade.Trade;
import org.stock.market.util.StockMarketInitHelper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Repository
public abstract class StockMarketRepoBase <T extends BaseEntity, S> implements StockMarketRepository<T, String> {

    private final ConcurrentHashMap<String, T> entities;
    public StockMarketRepoBase(){
        this.entities = new ConcurrentHashMap<>();
        save((Iterable<T>) StockMarketInitHelper.INSTANCE.initValues(this.getClass().getSimpleName()));
    }

    @Override
    public Iterable<T> findAll() {
        return this.entities.values();
    }

    @Override
    public Iterable<T> findAll(final Iterable<String> symbols) {
        return StreamSupport.stream(symbols.spliterator(),true)
                .map(sym->this.entities.get(sym))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<T> findById(final String symbol) {
        return Optional.ofNullable(this.entities.get(symbol));
    }

    @Override
    public T save(final T entity) {
        return this.entities.merge(entity.getId(),entity,(s1,s2)->s2);
    }

    @Override
    public Iterable<T> save(final Iterable<T> entities) {
        return StreamSupport.stream(entities.spliterator(),false)
                .map(this::save)
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(final T entity) {
        this.entities.remove(entity.getId());
    }


}
