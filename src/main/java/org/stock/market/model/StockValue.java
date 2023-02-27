package org.stock.market.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.stock.market.model.entity.stock.Stock;
import org.stock.market.model.validator.ValidPrice;
import lombok.Value;

import java.time.LocalDate;

@Value
public class StockValue {

    @NotNull
    @Valid
    private final Stock stock;

    @NotNull
    private final LocalDate date;

    @ValidPrice
    private final Double price;
}
