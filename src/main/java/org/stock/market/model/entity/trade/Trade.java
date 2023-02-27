package org.stock.market.model.entity.trade;

import org.stock.market.model.entity.BaseEntity;
import org.stock.market.model.validator.ValidPrice;
import org.stock.market.model.validator.ValidTradeIndicator;
import lombok.Value;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Value
public class Trade extends BaseEntity {

    @NotNull
    private String tradeId;

    @NotNull
    private Long tradeTimestamp;

    @ValidPrice
    private Double tradePrice;

    @Min(value = 1)
    private Integer quantity;

    @NotNull
    private String symbol;

    @ValidTradeIndicator
    private String tradeIndicator;

    @Override
    public String getId() {
        return tradeId;
    }
}
