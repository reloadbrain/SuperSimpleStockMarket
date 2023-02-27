package org.stock.market.model.entity.stock;

import jakarta.validation.constraints.NotEmpty;
import org.stock.market.model.entity.BaseEntity;
import org.stock.market.model.validator.ValidFixedDividend;
import org.stock.market.model.validator.ValidLastDividend;
import org.stock.market.model.validator.ValidParValue;
import org.stock.market.model.validator.ValidStockType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ValidFixedDividend
public class Stock extends BaseEntity {

    @NotEmpty(message = "Stock symbol cannot be empty")
    private String symbol;

    @ValidStockType
    private String type;

    @ValidLastDividend
    private Double lastDividend;

    //validated using ValidFixedDividend depending on type of stock
    private Double fixedDividend;

    @ValidParValue
    private Double parValue;

    @Override
    public String getId() {
        return symbol;
    }
}
