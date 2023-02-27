package org.stock.market.model.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.stock.market.model.entity.stock.Stock;
import org.stock.market.model.entity.stock.StockType;
import org.springframework.stereotype.Component;


/**
 * Rules for validating FixedDividend for a Stock
 */
@Component
public class ValidFixedDividendValidator implements ConstraintValidator<ValidFixedDividend,Object> {

    @Override
    public boolean isValid(
            final Object value,
            final ConstraintValidatorContext context) {
        if(value instanceof Stock){
            final Stock stock = (Stock)value;
            if(stock.getType() == null)
                return true; //if type not determined then cannot validate fixedDividend

            final StockType stockType = StockType.strToType(stock.getType());
            if(stockType != StockType.PREFERRED)
                return true;

            if(stock.getFixedDividend() != null){
                final double fixedDividend = stock.getFixedDividend();
                return fixedDividend >= 0.0 && fixedDividend <= 100.0;
            }
        }
        return false;
    }
}
